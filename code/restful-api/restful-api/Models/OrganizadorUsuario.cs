using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace RestfulApi.Models
{
    public class OrganizadorUsuario
    {
        public long OrganizadorId { get; set; }

        public Organizador Organizador { get; set; }

        public long UsuarioId { get; set; }

        public Usuario Usuario { get; set; }
    }
}
