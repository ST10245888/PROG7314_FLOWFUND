using Microsoft.EntityFrameworkCore;

namespace FlowFund.Api.Models
{
    public enum GroupStatus
    {
        Forming,
        Active,
        Completed,
        Cancelled
    }

    public enum RotationStrategy : byte
    {
        Random,
        JoinDate,
        Manual
    }

    public class StokvelGroup
    {
        public int Id { get; set; }
        public string StokvelGroupName { get; set; } = string.Empty;

        [Precision(18, 2)]
        public decimal Contribution { get; set; }

        public GroupStatus Status { get; set; }
        public RotationStrategy RotationStrategy { get; set; }

        public DateTime CycleStartDate { get; set; }
    }
}