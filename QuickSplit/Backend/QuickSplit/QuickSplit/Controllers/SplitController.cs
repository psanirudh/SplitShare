using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using QuickSplit.DBConnection;
using QuickSplit.Models;
using System.Diagnostics;
using Group = QuickSplit.Models.Group;

namespace QuickSplit.Controllers
{
    [ApiController]
    [Route("split")]
    public class SplitController : ControllerBase
    {
        [HttpGet]
        [Route("ping")]
        public string ping()
        {
            return "hi";
        }
        [HttpGet]
        [Route("ping")]
        public string ping2()
        {
            return "hello";
        }

        [HttpGet]
        [Route("Group/Add")]
        public int GroupAdd(string groupName)
        {

            Models.Group group = new Models.Group() { Name = groupName };
            using (var dbcon = new MyDBContext())
            {
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
        [Route("AddMember")]
        public void AddMembers(int groupId,int MemberId){

            using (var dbcon = new MyDBContext())
            {
                var grp = dbcon.groups.Where(x => x.Id == groupId).First();
                var memberObjs = dbcon.User.Where(x => x.id == MemberId).ToList();
                Debug.WriteLine("ani querying " + dbcon.User.Where(x => x.id == MemberId).ToQueryString());
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
                    SharesAsJson = SharesAsJson
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
