using System;
namespace RestfulApi.Models
{
    public class Local
    {
        public long Id { get; set; }

        public string Nome { get; set; }

        public int CapacidadeDePessoas { get; set; }

        public string Descricao { get; set; }

        public long EnderecoId { get; set; }

        public Endereco Endereco { get; set; }

    }
}

