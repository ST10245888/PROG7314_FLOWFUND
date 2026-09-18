namespace FlowFund.Api.Models
{
    public enum Platform: byte
    {
        Android,
        IOS
    }
    public class Device
    {
        public int Id { get; set;  }
        public int UserId { get; set; }

        public string Token { get; set; } = string.Empty;

        public Platform Platform { get; set; }

        
    }
}
