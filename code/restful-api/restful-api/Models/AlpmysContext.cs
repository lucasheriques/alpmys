using Microsoft.EntityFrameworkCore;
using RestfulApi.Models;

namespace RestfulApi.Models
{
    // this is the main class that coordinates Entity Framework funcionality for our models
    // this is done by deriving from Microsoft.EntityFrameworkCore.DbContext
    public class AlpmysContext : DbContext
    {
        public AlpmysContext(DbContextOptions<AlpmysContext> options) : base(options) { }
        public DbSet<RestfulApi.Models.Endereco> Endereco { get; set; }
        public DbSet<RestfulApi.Models.Usuario> Usuario { get; set; }
        public DbSet<RestfulApi.Models.Local> Local { get; set; }
        public DbSet<RestfulApi.Models.Evento> Evento { get; set; }
        public DbSet<RestfulApi.Models.Ingresso> Ingresso { get; set; }
        public DbSet<RestfulApi.Models.Compra> Compra { get; set; }

    }
}
