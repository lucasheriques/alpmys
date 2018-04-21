using System;
namespace RestfulApi.Models
{
    public class Promocao
    {
        public long Id { get; set; }

        public int QuantidadeIngressos { get; set; }

        public int PorcentagemDeDesconto { get; set; }

        public String CodigoPromocional { get; set; }

        public long EventoId { get; set; }

        public Evento Evento { get; set; }
    }
}

