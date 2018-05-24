using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace RestfulApi.Models
{
    public class Evento
    {
        public int Id { get; set; }

        [Required]
        public string Nome { get; set; }

        [Required]
        public string Descricao { get; set; }

        [Required]
        public DateTime Data { get; set; }

        public string Duracao { get; set; }

        [Required]
        [Url]
        public string LinkImagem { get; set; }

        [Url]
        public string LinkPagina { get; set; }


        public int UsuarioId { get; set; }
        public Usuario Organizador;

        public int LocalId { get; set; }
        public Local Local { get; set; }

        public ICollection<Ingresso> Ingressos { get; set; }
    }
}
