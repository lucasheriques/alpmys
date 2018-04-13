using System;
namespace RestfulApi.Models
{
    public class Pontos
    {
        public long Id { get; set; }

        public float Valor { get; set; }

        public float PontosAExpirar { get; set; }

        public DateTime DataVencimento { get; set; }

    }

}

