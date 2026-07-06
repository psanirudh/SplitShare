namespace QuickSplit.Models
{
    public class User
    {
        public int id { get; set; }
        public string name { get; set; }
        public ICollection<Group> Groups { get; set; }

    }
}
