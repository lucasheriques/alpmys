
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

/*        // GET: api/Eventos
        [HttpGet]
        public IEnumerable<Evento> GetEvento()
        {
            return _context.Evento;
        }
        */
        // GET: api/Eventos
        [HttpGet]
        public async Task<IActionResult> GetEvento()
        {
            var eventoEndereco = (from evento in _context.Evento
                                  join local in _context.Local
                                  on evento.LocalId equals local.Id
                                  join endereco in _context.Endereco
                                  on local.EnderecoId equals endereco.Id
                                  select new
                                  {
                                      Nome = evento.Nome,
                                      Data = evento.Data,
                                      Descricao = evento.Descricao,
                                      Cidade = endereco.Cidade,
                                      HorarioInicio = evento.HorarioInicio,
                                      HorarioTermino = evento.HorarioTermino,
                                      LinkPagina = evento.LinkPagina
                                  });


            var eventoEnderecoList = eventoEndereco.ToList();

            if (eventoEnderecoList == null)
            {
                return NotFound();
            }

            return Ok(eventoEnderecoList);
        }

        [HttpPost("nome")]
        public async Task<IActionResult> GetEventosByName([FromBody] Evento eventos)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }
            long id = 1;
            var evento2 = await _context.Evento.SingleOrDefaultAsync(m => m.Id == id);

            var eventoEndereco = (from evento in _context.Evento
                                  join local in _context.Local
                                  on evento.LocalId equals local.Id
                                  join endereco in _context.Endereco
                                  on local.EnderecoId equals endereco.Id
                                  where evento.Nome == eventos.Nome
                                  select new
                                  {
                                      evento.Nome,
                                      evento.Data,
                                      evento.Descricao,
                                      NomeLocal = local.Nome,
                                      EnderecoCompleto = (endereco.Logradouro + ", " + endereco.Numero + " " + endereco.Bairro + " " + endereco.Complemento),
                                      endereco.Cidade,
                                      endereco.Uf,
                                      evento.HorarioInicio,
                                      evento.HorarioTermino,
                                      evento.LinkPagina
                                  });


            var eventoEnderecoList = eventoEndereco.ToList();

            return Ok(eventoEnderecoList);
        }

        // GET: api/Eventos/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetEvento([FromRoute] long id)
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
        public async Task<IActionResult> PutEvento([FromRoute] long id, [FromBody] Evento evento)
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
        public async Task<IActionResult> DeleteEvento([FromRoute] long id)
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

        private bool EventoExists(long id)
        {
            return _context.Evento.Any(e => e.Id == id);

        }
    }
}