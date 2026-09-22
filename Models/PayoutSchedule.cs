using Microsoft.EntityFrameworkCore;

namespace FlowFund.Api.Models
{
    public enum PayoutStatus : byte
    {
        Scheduled,
        Completed,
        Skipped
    }

    public class PayoutSchedule
    {
        public int Id { get; set; }
        public int StokvelGroupId { get; set; }
        public int UserId { get; set; }

        public int RotationPosition { get; set; }
        public DateTime PayoutDate { get; set; }

        [Precision(18, 2)]
        public decimal Amount { get; set; }

        public PayoutStatus Status { get; set; }

        public DateTime CreatedAt { get; set; }
    }
}