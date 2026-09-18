namespace FlowFund.Api.Models
{
    public enum PaymentStatus : byte
    {
        Paid,
        Overdue
    }
    public class PayoutSchedule
    {
        public int Id { get; set; }
        public int StokvelGroupId { get; set; }

        public int UserId { get; set; }

        public PaymentStatus Status { get; set; }
        public DateTime PayoutDate { get; set; }
        
    }
}
