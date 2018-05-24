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

        [DisplayFormat(DataFormatString = "{0:dd-MM-yyyy}")]
        [Required]
        public DateTime Data { get; set; }

        [DisplayFormat(DataFormatString = "{0:HH:mm}")]
        [Required]
        public DateTime HoraInicio { get; set; }

        [DisplayFormat(DataFormatString = "{0:HH:mm}")]
        [Required]
        public DateTime HoraFim { get; set; }

        [Required]
        [Url]
        public string Imagem { get; set; }

        [Url]
        public string LinkPagina { get; set; }


        public int UsuarioId { get; set; }
        public Usuario Organizador;

        public int LocalId { get; set; }
        public Local Local { get; set; }

        public ICollection<Ingresso> Ingressos { get; set; }
    }
}
