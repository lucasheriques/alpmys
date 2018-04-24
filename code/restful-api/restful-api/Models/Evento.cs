<<<<<<< HEAD
﻿using System;
=======
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace RestfulApi.Models
{
    public class Evento
    {
        public long Id { get; set; }
        public String Nome { get; set; }
        public String Descricao { get; set; }
        public DateTime Data { get; set; }
        public DateTime HorarioInicio { get; set; }
        public DateTime HorarioTermino { get; set; }
        public String LinkPagina { get; set; }
        public long LocalId { get; set; }
        public Local Local { get; set; }
        public long OrganizadorId { get; set; }
        public Organizador Organizador { get; set; }
        public long TipoDeEventoId { get; set; }
        public TipoDeEvento TipoDeEvento { get; set; }
   }
}
