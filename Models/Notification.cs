namespace FlowFund.Api.Models
{
    public enum NotificationType: byte
    {
        EarlyReminder,
        UrgentReminder,
        Overdue,
        Confirmation
    }
    public class Notification
    {
        public int Id { get; set; }
        public int UserId { get; set;  }

        public string Message { get; set; } = string.Empty;
        public NotificationType NotificationType { get; set; }

        public bool IsRead { get; set; }
    }
}
