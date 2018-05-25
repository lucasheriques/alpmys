using System;
using System.ComponentModel.DataAnnotations;

namespace RestfulApi.Models
{
    public class Compra
    {
        public int Id { get; set; }

        public double Valor { get; set; }

        [Required]
        public DateTime DataCompra { get; set; }

        public int IngressoId { get; set; }
        public Ingresso Ingresso { get; set; }
        
        public int UsuarioId { get; set; }
        public Usuario Usuario { get; set; }
    }
}
