using System;
namespace RestfulApi.Models
{

    public class Conquistas
    {
        public long Id { get; set; }

        public int Avaliacao { get; set; }

        public string Descricao { get; set; }

        public long OrganizadorId { get; set; }

        Organizador Organizador { get; set; }

    }
}
