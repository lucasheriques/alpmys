using System;
namespace RestfulApi.Models
{
    public class Cria
    {
        public long Id { get; set; }

        public long OrganizadorId { get; set; }

        public Organizador Organizador { get; set; }

        public long UsuarioId { get; set; }

        public Usuario Usuario { get; set; }
    }
}