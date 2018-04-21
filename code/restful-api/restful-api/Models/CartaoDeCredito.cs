using System;
namespace RestfulApi.Models
{
    public class CartaoDeCredito
    {
        public long Id { get; set; }
        public string Bandeira { get; set; }

        public string NumeroDoCartao { get; set; }

        public string NomeImpressoCartao { get; set; }

        public DateTime Validade { get; set; }

        public int CodigoDeSeguranca { get; set; }

    }
}
