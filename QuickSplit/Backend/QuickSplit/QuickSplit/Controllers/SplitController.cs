using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using QuickSplit.DBConnection;
using QuickSplit.Models;
using System.Diagnostics;
using System.Text.Json;
using Group = QuickSplit.Models.Group;

namespace QuickSplit.Controllers
{

    public class test
    {
        public string add(int a,int b) {
            return (a + ":" + b);
        }

        public string  add(int a, int b,int c=34)
        {
            return (a + ":" + b+":"+c);
        }
    }

    [ApiController]
    [Route("split")]
    public class SplitController : ControllerBase
    {
        public static int currentUserId=7;

        [HttpGet]
        [Route("ping")]
        public string ping()
        {
            return new test().add(2, 3);
            return "hi";
        }
       

        [HttpGet]
        [Route("Group/Add")]
        public int GroupAdd(string groupName)
        {

            Models.Group group = new Models.Group() { Name = groupName };
            using (var dbcon = new MyDBContext())
            {
                var groupAdmin = dbcon.User.Where(x => x.id == SplitController.currentUserId).First();
                group.members = new List<User>();
                group.members.Add(groupAdmin);
                dbcon.groups.Add(group);
                dbcon.SaveChanges();
            }
            return group.Id;

        }

        [HttpGet]
        [Route("User/Add")]
        public int UserAdd(string UserName)
        {

            User usr = new User() { name = UserName };
            using (var dbcon = new MyDBContext())
            {
                dbcon.User.Add(usr);
                dbcon.SaveChanges();
            }
            return usr.id;
        }

        [HttpGet]
        [Route("Users/Get")]
        public object UsersGet()
        {
            List<User> allUsers;
            using (var dbcon = new MyDBContext())
            {
               allUsers =   dbcon.User.AsNoTracking().ToList();
            }
            return allUsers;
        }

        [HttpGet]
        [Route("Groups/Get")]
        public object GroupsGet()
        {
            List<Group> groups;
            using (var dbcon = new MyDBContext())
            {
                groups = dbcon.groups.AsNoTracking().ToList();
            }
            return groups;
        }



        [HttpGet]
        [Route("Group/Get")]
        public object GroupGet(int groupId)
        {
            object groupWithDetails =null;
            using (var dbcon = new MyDBContext())
            {
                groupWithDetails = dbcon.groups.Where(z => z.Id == groupId).Include(x => x.members).Include(x => x.transactions).Select(g => new
                {
                    g.Id,
                    g.Name,

                    Members = g.members.Select(m => new
                    {
                        m.id,
                        m.name
                    }
                    ).ToList()
                }).First();

            }
            return groupWithDetails;
        }

        [HttpGet]
        [Route("AddMember")]
        public void AddMembers(int groupId,int memberId){

            using (var dbcon = new MyDBContext())
            {
                var grp = dbcon.groups.Where(x => x.Id == groupId).First();
                var memberObjs = dbcon.User.Where(x => x.id == memberId).ToList();
                Debug.WriteLine("ani querying " + dbcon.User.Where(x => x.id == memberId).ToQueryString());
                if (grp.members is null)
                {
                    grp.members = new List<User>();
                }
                grp.members = grp.members.Union(memberObjs).ToList();
                dbcon.groups.Update(grp);
                dbcon.SaveChanges();
            }

        }

        [HttpGet]
        [Route("AddMembers")]
        public void AddMembers(int groupId, List<int> MemberIds)
        {

            using (var dbcon = new MyDBContext())
            {
                var grp = dbcon.groups.Where(x => x.Id == groupId).First();
                var memberObjs = dbcon.User.Where(x => MemberIds.Contains(x.id)).ToList();

                grp.members = grp.members.Union(memberObjs).ToList();
                dbcon.groups.Update(grp);
                dbcon.SaveChanges();
            }

        }

        [HttpGet]
        [Route("AddTransaction")]
        public void AddTransaction(int groupId,string descrpion,int total,string SharesAsJson)
        {

            using (var dbcon = new MyDBContext())
            {
                var transaction = new Transaction()
                {
                    Descrption = descrpion,
                    groupId = groupId,
                    Total = total,
                };
                transaction.Validate();
                dbcon.Transaction.Add(transaction);
                dbcon.SaveChanges();
            }

        }
    }
}

/*
 https://localhost:7166/split/group/add?groupName=Brogrammers
 https://localhost:7166/split/User/add?username=ani
https://localhost:7166/split/User/add?username=asker
https://localhost:7166/split/User/add?username=dsp */
