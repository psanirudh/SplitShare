namespace QuickSplit.Models
{
    public class Group
    {
        public int Id { get; set; }
        public string Name { get; set; }   
        public ICollection<User> members { get; set; }
        public ICollection<Transaction> transactions { get; set; }

    }
}
