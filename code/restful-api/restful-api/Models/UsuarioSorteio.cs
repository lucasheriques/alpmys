using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace RestfulApi.Models
{
    public class UsuarioSorteio
    {
        public long Id { get; set; }

        public long UsuarioId { get; set; }

        public Usuario Usuario { get; set; }

        public long SorteioId { get; set; }

        public Sorteio Sorteio { get; set; }

    }
}
