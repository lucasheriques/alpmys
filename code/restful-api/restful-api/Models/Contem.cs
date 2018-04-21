using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace RestfulApi.Models
{
    public class Contem
    {
        public long id { get; set; }

        public long IngressoId { get; set; }

        public Ingresso Ingresso { get; set; }

        public long PromocaoId { get; set; }

        public Promocao Promocao { get; set; }
    }
}
