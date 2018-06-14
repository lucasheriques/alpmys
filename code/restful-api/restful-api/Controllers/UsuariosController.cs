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
    [Route("api/Usuarios")]
    public class UsuariosController : Controller
    {
        private readonly AlpmysContext _context;

        public UsuariosController(AlpmysContext context)
        {
            _context = context;
        }

        // GET: api/Usuarios
        [HttpGet]
        public IEnumerable<Usuario> GetUsuario()
        {

            return _context.Usuario;
        }

        // GET: api/Usuarios/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetUsuario([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }
            var evento = from e in _context.Evento
                         join l in _context.Local on e.LocalId equals l.Id
                         where e.UsuarioId == id
                         select new
                         {
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
                             local = e.Local
                         };
            var compra = from c in _context.Compra where c.UsuarioId==id
                         select new
                         {
                             id = c.Id,
                             valor = c.Valor,
                             dataCompra = c.DataCompra,
                             ingressoId = c.IngressoId,
                             ingresso = (from i in _context.Ingresso
                                         where c.IngressoId == i.Id
                                         select new
                                         {
                                             id = i.Id,
                                             tipoIngreso = i.TipoIngreso,
                                             valor = i.Valor,
                                             eventoId = i.EventoId,
                                             evento = (from e in _context.Evento
                                                       where i.EventoId == e.Id
                                                       select new
                                                       {
                                                           id = e.Id,
                                                           nome = e.Nome,
                                                           descricao = e.Descricao,
                                                           data = e.Data,
                                                           duracao = e.Duracao,
                                                           linkImagem = e.LinkImagem,
                                                           linkPagina = e.LinkPagina,
                                                           localId = e.LocalId,
                                                           local = (from l in _context.Local
                                                                    where e.LocalId == l.Id
                                                                    select new
                                                                    {
                                                                        nome = l.Nome,
                                                                        descricao = l.Descricao,
                                                                        cep = l.Cep,
                                                                        rua = l.Rua,
                                                                        numero = l.Numero,
                                                                        complemento = l.Complemento,
                                                                        cidade = l.Cidade,
                                                                        uf = l.Uf
                                                                    }).ToList()[0],
                                                       }).ToList()[0],
                                         }).ToList()[0]


                         };
            var usuarioEvento = from u in _context.Usuario
                                where u.Id == id
                                select new
                                {
                                    id = u.Id,
                                    nome = u.Nome,
                                    email = u.Email,
                                    senha = u.Senha,
                                    celular = u.Celular,
                                    eventos = evento,
                                    compras = compra

                                };
            var eventos = usuarioEvento.ToList()[0];
            if (eventos == null)
            {
                return NotFound();
            }

            return Ok(eventos);
        }
        // PUT: api/Usuarios/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutUsuario([FromRoute] int id, [FromBody] Usuario usuario)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != usuario.Id)
            {
                return BadRequest();
            }

            _context.Entry(usuario).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!UsuarioExists(id))
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

        // POST: api/Usuarios
        [HttpPost]
        public async Task<IActionResult> PostUsuario([FromBody] Usuario usuario)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            _context.Usuario.Add(usuario);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetUsuario", new { id = usuario.Id }, usuario);
        }

        // DELETE: api/Usuarios/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteUsuario([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var usuario = await _context.Usuario.SingleOrDefaultAsync(m => m.Id == id);
            if (usuario == null)
            {
                return NotFound();
            }

            _context.Usuario.Remove(usuario);
            await _context.SaveChangesAsync();

            return Ok(usuario);
        }

        private bool UsuarioExists(int id)
        {
            return _context.Usuario.Any(e => e.Id == id);
        }
    }
}