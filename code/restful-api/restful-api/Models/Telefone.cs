using System;

namespace RestfulApi.Models
{
    public class Telefone
    {
        public long Id { get; set; }

        public string NumeroTelefone { get; set; }

        public string Ddd { get; set; }

        public long ContatoId { get; set; }

        public Contato Contato { get; set; }
    }
}

