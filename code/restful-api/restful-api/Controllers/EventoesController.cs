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
    [Route("api/Eventoes")]
    public class EventoesController : Controller
    {
        private readonly AlpmysContext _context;

        public EventoesController(AlpmysContext context)
        {
            _context = context;
        }

        // GET: api/Eventoes
        [HttpGet]
        public IEnumerable<Evento> GetEvento()
        {
            return _context.Evento;
        }

        // GET: api/Eventoes/5
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

        // PUT: api/Eventoes/5
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

        // POST: api/Eventoes
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

        // DELETE: api/Eventoes/5
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