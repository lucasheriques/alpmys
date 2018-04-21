using Microsoft.EntityFrameworkCore;

namespace RestfulApi.Models
{
    // this is the main class that coordinates Entity Framework funcionality for our models
    // this is done by deriving from Microsoft.EntityFrameworkCore.DbContext
    public class AlpmysContext : DbContext
    {
        public AlpmysContext(DbContextOptions<AlpmysContext> options) : base(options) { }
        public DbSet<Usuario> Usuarios { get; set; }
    }
}
