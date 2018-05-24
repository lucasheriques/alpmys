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

        public ICollection<Evento> Eventos { get; set; }

        public Endereco Endereco { get; set; }
    }
}
