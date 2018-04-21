using System;
namespace RestfulApi.Models
{
    public class Endereco
    {
        public long Id { get; set; }

        public string Logradouro { get; set; }

        public int Numero { get; set; }

        public string Complemento { get; set; }

        public string Bairro { get; set; }

        public string Cidade { get; set; }

        public string Uf { get; set; }

        public string Cep { get; set; }

        public long UsuarioId { get; set; }

        public Usuario Usuario { get; set; }

    }
}


