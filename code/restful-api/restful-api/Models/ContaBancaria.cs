using System;
namespace RestfulApi.Models
{

    public class ContaBancaria
    {
        public long Id { get; set; }

        public string Banco { get; set; }

        public string Agencia { get; set; }

        public string Conta { get; set; }

        public string Tipo { get; set; }

        public string Favorecido { get; set; }

        public int Cnpj { get; set; }

        public string Cpf { get; set; }

        public long OrganizadorId { get; set; }

        public Organizador Organizador { get; set; }

    }
}
