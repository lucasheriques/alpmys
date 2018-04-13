using System;
namespace RestfulApi.Models
{
    public class Contato
    {
        public long Id { get; set; }

        public string tipoContato { get; set; }

        public string Responsavel { get; set; }

        public long OrganizadorId { get; set; }

        public Organizador Organizador { get; set; }
    }
}