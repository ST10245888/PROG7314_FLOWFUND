using FlowFund.Api.Models;
using Microsoft.EntityFrameworkCore;

namespace FlowFund.Api.Data
{
    public class FlowFundDbContext : DbContext
    {
        public FlowFundDbContext(DbContextOptions<FlowFundDbContext> options) :base(options) { }
    public DbSet<User> Users{ get; set;  }
        public DbSet<Contribution> Contributions{ get; set; }
        public DbSet<Device> Devices { get; set; }
        public DbSet<Meeting> Meetings { get; set; }
        public DbSet<Membership> Memberships { get; set; }
        public DbSet<Notification> Notifications { get; set; }
        public DbSet<PayoutSchedule> PayoutSchedules { get; set; }
        public DbSet<StokvelGroup> StokvelGroups { get; set; }
        public DbSet<Translation> Translations { get; set; }

    }
}
