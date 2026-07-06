using Microsoft.EntityFrameworkCore.Infrastructure;
using QuickSplit.Models;

namespace QuickSplit.DBConnection
{
    public class DataSeeder
    {
        public static void seed()
        {
            using(var dbcontextt = new MyDBContext())
            {
                User fetchedUser = dbcontextt.User.Where(x => x.id == 1).FirstOrDefault();
                dbcontextt.groups.Add(new Models.Group() { Name = "trip", members = new List<User>() { new User() { name = "anii" } } });
                dbcontextt.SaveChanges();
            }
        }
    }
}
