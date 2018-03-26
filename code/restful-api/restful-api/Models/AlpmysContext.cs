using Microsoft.EntityFrameworkCore;

namespace restfulapi.Models
{
    // this is the main class that coordinates Entity Framework funcionality for our models
    // this is done by deriving from Microsoft.EntityFrameworkCore.DbContext
    public class AlpmysContext : DbContext
    {
        public AlpmysContext(DbContextOptions<AlpmysContext> options) : base(options) { }

        public DbSet<Event> Events { get; set; }
        public DbSet<User> Users { get; set; }
    }
}
