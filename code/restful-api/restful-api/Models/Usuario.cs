using System;

namespace RestfulApi.Models
{
    public class Usuario : IRedesSociais
    {
        public long Id { get; set; }

        public string Nome { get; set; }

        public string Sobrenome { get; set; }

        public string Email { get; set; }

        public string Senha { get; set; }

        public Boolean Organizador { get; set; }

        public string Cpf { get; set; }

        public DateTime DataNascimento { get; set; }

        public CartaoDeCredito[] CartaoDeCredito { get; set; }

        public long PontosId { get; set; }

        public Pontos Pontos { get; set; }

        public WishList WishList { get; set; }

        public long WishListId { get; set; }
    }
}