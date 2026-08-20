using Microsoft.EntityFrameworkCore;
using QuickSplit.Models;

namespace QuickSplit.DBConnection
{
    public class MyDBContext : DbContext
    {
        public MyDBContext():base() { }
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseSqlServer(@"Server=localhost;Database=master;Trusted_connection=true;TrustServerCertificate=True;");
        }
        public DbSet<Group> groups { get; set; }
        public DbSet<User> User { get; set; }
        public DbSet<Transaction> Transaction { get; set; }


    }
}
