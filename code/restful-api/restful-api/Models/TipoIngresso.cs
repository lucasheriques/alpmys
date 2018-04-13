using System;
namespace RestfulApi.Models
{
    public class TipoIngresso
    {
        public long Id { get; set; }

        public float Valor { get; set; }

        public string Tipo { get; set; }

        public float Taxa { get; set; }

        public int Quantidade { get; set; }

        public int NumeroLote { get; set; }

        public DateTime DataInicio { get; set; }

        public int IngressosRestante { get; set; }

    }
}
