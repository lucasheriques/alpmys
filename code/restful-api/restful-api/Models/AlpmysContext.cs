using Microsoft.EntityFrameworkCore;
using RestfulApi.Models;

namespace RestfulApi.Models
{
    // this is the main class that coordinates Entity Framework funcionality for our models
    // this is done by deriving from Microsoft.EntityFrameworkCore.DbContext
    public class AlpmysContext : DbContext
    {
        public AlpmysContext(DbContextOptions<AlpmysContext> options) : base(options) { }

        public DbSet<Event> Events { get; set; }
        public DbSet<User> Users { get; set; }
        public DbSet<CartaoDeCredito> CartaoDeCredito { get; set; }
        public DbSet<Compra> Compra { get; set; }
        public DbSet<Conquistas> Conquistas { get; set; }
        public DbSet<ContaBancaria> ContaBancaria { get; set; }
        public DbSet<Contato> Contato { get; set; }
        public DbSet<Endereco> Endereco { get; set; }
        public DbSet<Evento> Evento { get; set; }

        public DbSet<Ingresso> Ingresso { get; set; }
        //        public DbSet<IPaypal> IPaypal { get; set; }
        //      public DbSet<IRedesSociais> IRedesSociais { get; set; }
        public DbSet<Local> Local { get; set; }
        public DbSet<Organizador> Organizador { get; set; }
        public DbSet<UsuarioSorteio> Participa { get; set; }
        public DbSet<Pontos> Pontos { get; set; }
        public DbSet<UsuarioCartaoDeCredito> Possui { get; set; }
        public DbSet<Promocao> Promocao { get; set; }
        public DbSet<Sorteio> Sorteio { get; set; }
        public DbSet<Telefone> Telefone { get; set; }
        public DbSet<TipoDeEvento> TipoDeEvento { get; set; }
        public DbSet<TipoIngresso> TipoIngresso { get; set; }
        public DbSet<Usuario> Usuario { get; set; }
        public DbSet<WishList> WishList { get; set; }


    }
}
