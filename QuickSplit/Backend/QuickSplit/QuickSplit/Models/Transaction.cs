using System.ComponentModel.DataAnnotations.Schema;

namespace QuickSplit.Models
{
    public class Transaction
    {
        public int Id { get; set; }
        public int groupId { get; set; }

        public string Descrption { get; set; }
        public int Total { get; set; }
        public string SharesAsJson{ get; set; }

        public ICollection<User> User { get; set; }

        public bool Validate()
        {
            return true;
        }

        [NotMapped]
        public Dictionary<int,int> Shares{ 
         get {
             return  System.Text.Json.JsonSerializer.Deserialize<Dictionary<int, int>>(SharesAsJson);
         } 
        }
    }
}
