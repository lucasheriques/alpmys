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
    [Route("api/Compras")]
    public class ComprasController : Controller
    {
        private readonly AlpmysContext _context;

        public ComprasController(AlpmysContext context)
        {
            _context = context;
        }

        // GET: api/Compras
        [HttpGet]
        public IEnumerable<Compra> GetCompra()
        {
            return _context.Compra;
        }

        // GET: api/Compras/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetCompra([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }
      
            var compra = from c in _context.Compra join i in _context.Ingresso on c.IngressoId equals i.Id join u in _context.Usuario on c.UsuarioId equals u.Id
                                select new
                                {
                                    id = c.Id,
                                    valor=c.Valor,
                                    dataCompra=c.DataCompra,
                                    ingressoId=c.IngressoId,
                                    ingresso= c.Ingresso,
                                    usuarioId=c.UsuarioId,
                                    usuario=c.Usuario
                                    

                                };
            var compras = compra.ToList()[0];
            if (compras == null)
            {
                return NotFound();
            }

            return Ok(compras);
        }

        // PUT: api/Compras/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutCompra([FromRoute] int id, [FromBody] Compra compra)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != compra.Id)
            {
                return BadRequest();
            }

            _context.Entry(compra).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!CompraExists(id))
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

        // POST: api/Compras
        [HttpPost]
        public async Task<IActionResult> PostCompra([FromBody] Compra compra)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            _context.Compra.Add(compra);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetCompra", new { id = compra.Id }, compra);
        }

        // DELETE: api/Compras/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteCompra([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var compra = await _context.Compra.SingleOrDefaultAsync(m => m.Id == id);
            if (compra == null)
            {
                return NotFound();
            }

            _context.Compra.Remove(compra);
            await _context.SaveChangesAsync();

            return Ok(compra);
        }

        private bool CompraExists(int id)
        {
            return _context.Compra.Any(e => e.Id == id);
        }
    }
}