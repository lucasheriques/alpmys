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
    [Route("api/Enderecos")]
    public class EnderecosController : Controller
    {
        private readonly AlpmysContext _context;

        public EnderecosController(AlpmysContext context)
        {
            _context = context;
        }

        // GET: api/Enderecos
        [HttpGet]
        public IEnumerable<Endereco> GetEndereco()
        {
            return _context.Endereco;
        }

        // GET: api/Enderecos/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetEndereco([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var endereco = await _context.Endereco.SingleOrDefaultAsync(m => m.LocalId == id);

            if (endereco == null)
            {
                return NotFound();
            }

            return Ok(endereco);
        }

        // PUT: api/Enderecos/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutEndereco([FromRoute] int id, [FromBody] Endereco endereco)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != endereco.LocalId)
            {
                return BadRequest();
            }

            _context.Entry(endereco).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!EnderecoExists(id))
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

        // POST: api/Enderecos
        [HttpPost]
        public async Task<IActionResult> PostEndereco([FromBody] Endereco endereco)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            _context.Endereco.Add(endereco);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetEndereco", new { id = endereco.LocalId }, endereco);
        }

        // DELETE: api/Enderecos/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteEndereco([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var endereco = await _context.Endereco.SingleOrDefaultAsync(m => m.LocalId == id);
            if (endereco == null)
            {
                return NotFound();
            }

            _context.Endereco.Remove(endereco);
            await _context.SaveChangesAsync();

            return Ok(endereco);
        }

        private bool EnderecoExists(int id)
        {
            return _context.Endereco.Any(e => e.LocalId == id);
        }
    }
}