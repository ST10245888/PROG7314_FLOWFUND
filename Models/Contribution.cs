using Microsoft.EntityFrameworkCore;

namespace FlowFund.Api.Models
{
    public enum PaymentStatus : byte
    {
        Pending,
        Paid,
        Overdue
    }

    public class Contribution
    {
        public int Id { get; set; }
        public int MembershipId { get; set; }

        [Precision(18, 2)]
        public decimal Amount { get; set; }

        public DateTime DueDate { get; set; }
        public DateTime? PaidDate { get; set; }

        public PaymentStatus Status { get; set; }

        public string? PaymentReference { get; set; }
        public int? ConfirmedByUserId { get; set; }

        public Guid ClientGeneratedId { get; set; }

        public DateTime CreatedAt { get; set; }
    }
}