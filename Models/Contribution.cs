using Microsoft.EntityFrameworkCore;

namespace FlowFund.Api.Models
{
    public class Contribution
    {
        public int Id { get; set; }
        public int MembershipId { get; set; }

        [Precision(18, 2)]
        public decimal Amount { get; set;  }

        public DateTime DueDate { get; set; }

       public PaymentStatus Status { get; set; }

        public Guid ClientGeneratedId { get; set; }
    }
}
