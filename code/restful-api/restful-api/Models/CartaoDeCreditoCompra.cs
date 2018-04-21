using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace RestfulApi.Models
{
    public class CartaoDeCreditoCompra
    {
        public long CompraID { get; set; }

        public Compra Compra { get; set; }

        public long CartaoDeCreditoID { get; set; }

        public CartaoDeCredito CartaoDeCredito { get; set; }
    }
}
