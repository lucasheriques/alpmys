using System;

namespace RestfulApi.Models
{
    public class Sorteio
    {
        public long Id { get; set; }

        public DateTime DataFim { get; set; }

        public string Nome { get; set; }

        public string descricao { get; set; }

        public DateTime DataInicio { get; set; }

        public DateTime HorarioInicio { get; set; }

        public DateTime HorarioFim { get; set; }

        public string Premios { get; set; }

        public string Observacao { get; set; }

        public long OrganizadorId { get; set; }

        public Organizador Organizador { get; set; }

        public Usuario[] Usuario { get; set; }

    }

}

public class Sorteio
{
	public DateTime DataFim{ get; set; }

	public string Nome{ get; set; }

	public string descricao{ get; set; }

	public DateTime DataInicio{ get; set; }

	public DateTime HorarioInicio{ get; set; }

	public DateTime HorarioFim{ get; set; }

	public string Premios{ get; set; }

	public string Observacao{ get; set; }
}

