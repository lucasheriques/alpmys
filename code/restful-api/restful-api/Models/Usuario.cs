using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace RestfulApi.Models
{
    public class Usuario
    {
        public int Id { get; set; }

        [Required]
        [RegularExpression(@"^[A-Z]+[a-zA-Z""'\s-]*$")]
        public string Nome { get; set; }

        [EmailAddress]
        [Required]
        [StringLength(254)]
        public string Email { get; set; }

        [Required]
        public string Senha { get; set; }

        public string Celular { get; set; }

        public ICollection<Evento> Eventos { get; set; }
    }
}
