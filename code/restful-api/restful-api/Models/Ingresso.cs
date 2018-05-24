using System;
using System.ComponentModel.DataAnnotations;

namespace RestfulApi.Models
{
    public class Ingresso
    {
        public int Id { get; set; }

        [Required]
        public bool Disponivel { get; set; }

        public double Valor { get; set; }

        public int UsuarioId { get; set; }
        public Usuario Usuario { get; set; }

        public int EventoID { get; set; }
        public Evento Evento { get; set; }

        public Compra Compra { get; set; }

    }
}
