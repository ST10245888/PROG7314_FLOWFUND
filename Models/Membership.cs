namespace FlowFund.Api.Models
{
    public enum Role : byte
    {
        Member,
        Treasurer,
        Admin
    }

    public enum MembershipStatus : byte
    {
        Invited, 
        Active, 
        Removed
    }

    public class Membership
    {
        public int Id { get; set; }
        public int GroupId {get; set;}
        public int UserId { get; set;}
        public Role Role { get; set; }

        public MembershipStatus Status { get; set; }
    }
}
