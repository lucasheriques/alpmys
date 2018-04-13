using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace RestfulApi.Models
{
    public class Realiza
    {
        public long Id { get; set; }
        CartaoDeCredito CartaoDeCredito { get; set;  }
        Compra Compra { get; set; }

    }
}
