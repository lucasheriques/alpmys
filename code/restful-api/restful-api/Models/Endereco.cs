using System;
using System.ComponentModel.DataAnnotations;

namespace RestfulApi.Models
{
    public class Endereco
    {
        public int Id { get; set; }
        
        [Required]
        public string Cep { get; set; }

        [Required]
        public string Rua { get; set; }

        public int Numero { get; set; }

        public string Complemento { get; set; }

        [Required]
        public string Cidade { get; set; }

        [Required]
        public string Uf { get; set; }

        public int LocalId { get; set; }
        public Local Local { get; set; }
    }
}
