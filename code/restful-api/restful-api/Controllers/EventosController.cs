using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using RestfulApi.Models;

namespace RestfulApi.Controllers
{
    [Produces("application/json")]
    [Route("api/Eventos")]
    public class EventosController : Controller
    {
        private readonly AlpmysContext _context;

        public EventosController(AlpmysContext context)
        {
            _context = context;
        }

        // GET: api/Eventos
        [HttpGet]
        public async Task<IActionResult> GetEvento()
        {
            var evento = from e in _context.Evento
                         join l in _context.Local on e.LocalId equals l.Id
                         join u in _context.Usuario on e.UsuarioId equals u.Id
                         select new
                         {
                             organizador = e.Organizador,
                             id = e.Id,
                             nome = e.Nome,
                             descricao = e.Descricao,
                             data = e.Data,
                             duracao = e.Duracao,
                             tipoIngressos = from i in _context.Ingresso
                                             where i.Disponivel == true && e.Id == i.EventoId
                                             group i by i.TipoIngreso into t
                                             select new
                                             {
                                                 EventoId = t.First().EventoId,
                                                 Valor = t.First().Valor,
                                                 TipoIngresso = t.Key,
                                                 Quantidade = t.Count()
                                             },
                             linkImagem = e.LinkImagem,
                             linkPagina = e.LinkPagina,
                             usuarioId = e.UsuarioId,
                             localId = e.LocalId,
                             ingressos = e.Ingressos,
                             local = e.Local
                         };
            var eventos = evento.ToList();
            if (eventos == null)
            {
                return NotFound();
            }
            return Ok(eventos);
        }

        // GET: api/Eventos/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetEvento([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var evento = await _context.Evento.SingleOrDefaultAsync(m => m.Id == id);

            if (evento == null)
            {
                return NotFound();
            }

            return Ok(evento);
        }

        // PUT: api/Eventos/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutEvento([FromRoute] int id, [FromBody] Evento evento)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != evento.Id)
            {
                return BadRequest();
            }

            _context.Entry(evento).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!EventoExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: api/Eventos
        [HttpPost]
        public async Task<IActionResult> PostEvento([FromBody] Evento evento)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            _context.Evento.Add(evento);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetEvento", new { id = evento.Id }, evento);
        }

        // DELETE: api/Eventos/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteEvento([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var evento = await _context.Evento.SingleOrDefaultAsync(m => m.Id == id);
            if (evento == null)
            {
                return NotFound();
            }

            _context.Evento.Remove(evento);
            await _context.SaveChangesAsync();

            return Ok(evento);
        }

        private bool EventoExists(int id)
        {
            return _context.Evento.Any(e => e.Id == id);
        }
    }
}