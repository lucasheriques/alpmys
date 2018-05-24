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
    [Route("api/Locais")]
    public class LocaisController : Controller
    {
        private readonly AlpmysContext _context;

        public LocaisController(AlpmysContext context)
        {
            _context = context;
        }

        // GET: api/Locais
        [HttpGet]
        public IEnumerable<Local> GetLocal()
        {
            return _context.Local;
        }

        // GET: api/Locais/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetLocal([FromRoute] long id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var local = await _context.Local.SingleOrDefaultAsync(m => m.Id == id);

            if (local == null)
            {
                return NotFound();
            }

            return Ok(local);
        }

        // PUT: api/Locais/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutLocal([FromRoute] long id, [FromBody] Local local)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != local.Id)
            {
                return BadRequest();
            }

            _context.Entry(local).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!LocalExists(id))
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

        // POST: api/Locais
        [HttpPost]
        public async Task<IActionResult> PostLocal([FromBody] Local local)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            _context.Local.Add(local);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetLocal", new { id = local.Id }, local);
        }

        // DELETE: api/Locais/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteLocal([FromRoute] long id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var local = await _context.Local.SingleOrDefaultAsync(m => m.Id == id);
            if (local == null)
            {
                return NotFound();
            }

            _context.Local.Remove(local);
            await _context.SaveChangesAsync();

            return Ok(local);
        }

        private bool LocalExists(long id)
        {
            return _context.Local.Any(e => e.Id == id);
        }
    }
}