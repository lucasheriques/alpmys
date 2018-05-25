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
    [Route("api/Ingressos")]
    public class IngressosController : Controller
    {
        private readonly AlpmysContext _context;

        public IngressosController(AlpmysContext context)
        {
            _context = context;
        }

        // GET: api/Ingressos
        [HttpGet]
        public IEnumerable<Ingresso> GetIngresso()
        {
            return _context.Ingresso;
        }

        // GET: api/Ingressos/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetIngresso([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var ingresso = await _context.Ingresso.SingleOrDefaultAsync(m => m.Id == id);

            if (ingresso == null)
            {
                return NotFound();
            }

            return Ok(ingresso);
        }

        // PUT: api/Ingressos/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutIngresso([FromRoute] int id, [FromBody] Ingresso ingresso)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != ingresso.Id)
            {
                return BadRequest();
            }

            _context.Entry(ingresso).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!IngressoExists(id))
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

        // POST: api/Ingressos
        [HttpPost]
        public async Task<IActionResult> PostIngresso([FromBody] Ingresso ingresso)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            _context.Ingresso.Add(ingresso);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetIngresso", new { id = ingresso.Id }, ingresso);
        }

        // DELETE: api/Ingressos/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteIngresso([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var ingresso = await _context.Ingresso.SingleOrDefaultAsync(m => m.Id == id);
            if (ingresso == null)
            {
                return NotFound();
            }

            _context.Ingresso.Remove(ingresso);
            await _context.SaveChangesAsync();

            return Ok(ingresso);
        }

        private bool IngressoExists(int id)
        {
            return _context.Ingresso.Any(e => e.Id == id);
        }
    }
}