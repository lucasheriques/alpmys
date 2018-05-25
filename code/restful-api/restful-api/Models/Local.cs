using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace RestfulApi.Models
{
    public class Local
    {
        public int Id { get; set; }

        [Required]
        public string Nome { get; set; }

        public string Descricao { get; set; }

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

        public ICollection<Evento> Eventos { get; set; }
    }
}
