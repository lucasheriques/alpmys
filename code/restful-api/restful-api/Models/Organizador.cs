using RestfulApi.Models;
using System;
namespace RestfulApi.Models
{
    public class Organizador : Usuario
    {
        public new long Id { get; set; }

        public string Cnpj { get; set; }

        public string UrlSite { get; set; }

        public string Responsavel { get; set; }

        public DateTime OrganizadorDesde { get; set; }

    }
}
