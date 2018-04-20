using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace RestfulApi.Models
{
    public class UsuarioCartaoDeCredito
    {
        public long Id { get; set; }
        
        public CartaoDeCredito CartaoDeCredito { get; set; }

        public Usuario Usuario { get; set; }
    }
}
