using System;
namespace RestfulApi.Models
{
    public class Ingresso
    {
        public long Id { get; set; }

        public string NumeroDoIngresso { get; set; }

        public string Nome { get; set; }

        public string Sobrenome { get; set; }

        public string Celular { get; set; }

        public string Email { get; set; }

        public long TipoIngressoId { get; set; }

        public TipoIngresso TipoIngresso { get; set; }

        public long OrganizadorId { get; set; }

        public Organizador Organizador { get; set; }

        public long EventoID { get; set; }

        public Evento Evento { get; set; }

        public long PromocaoId { get; set; }

        public Promocao Promocao { get; set; }
    }
}

