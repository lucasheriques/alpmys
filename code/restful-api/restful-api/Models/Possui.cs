using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace RestfulApi.Models
{
    public class Possui
    {
        public long Id { get; set; }
        
        CartaoDeCredito CartaoDeCredito { get; set; }

        Usuario Usuario { get; set; }
    }
}
