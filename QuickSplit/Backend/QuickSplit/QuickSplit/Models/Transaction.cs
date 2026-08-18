using System.ComponentModel.DataAnnotations.Schema;

namespace QuickSplit.Models
{
    public class Transaction
    {
        public int Id { get; set; }
        public int groupId { get; set; }

        public string Descrption { get; set; }
        public int Total { get; set; }

        public ICollection<Share> Shares { get; set; }

        public bool Validate()
        {
            int Totall = 0;
            foreach(var share in Shares)
            {
                Totall += share.Amount;
            }
            return Totall ==0;
        }

    }
}
