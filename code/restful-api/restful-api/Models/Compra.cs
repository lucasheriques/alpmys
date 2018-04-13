using System;

namespace RestfulApi.Models
{

    public class Compra : IPaypal
    {
        public long Id { get; set; }

        public float ValorCompra { get; set; }

        public int QuantidadeIngressos { get; set; }

        public float ValorUnitario { get; set; }

        public DateTime DataCompra { get; set; }

        public DateTime DataVencimento { get; set; }

        public string FormaPagamento { get; set; }

        public string Status { get; set; }

        public long IngressoId { get; set; }

        public Ingresso Ingresso { get; set; }
        public long UsuarioId { get; set; }

        public Usuario Usuario { get; set; }

    }
}
