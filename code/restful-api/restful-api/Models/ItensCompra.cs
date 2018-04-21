using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace RestfulApi.Models
{
    public class ItensCompra
    {

        public long IngressoId { get; set; }

        public Ingresso Ingresso { get; set; }

        public long CompraId { get; set; }

        public Compra Compra { get; set; }

        public float ValorUnitario { get; set; }
    }
}
