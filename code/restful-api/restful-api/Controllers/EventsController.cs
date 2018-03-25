using System.Collections.Generic;
using Microsoft.AspNetCore.Mvc;
using restfulapi.Models;
using System.Linq;

namespace restfulapi.Controllers
{
    [Route("api/[controller]")]
    public class EventsController : Controller
    {
        private readonly AlpmysContext _context;

        public EventsController(AlpmysContext context)
        {
            _context = context;

            if (_context.Events.Count() == 0)
            {
                _context.Events.Add(new Event { Name = "Evento 01" });
                _context.SaveChanges();
            }
        }

        // IEnumerable automatically serializes the object to JSON into the body of the response message
        // response code: 200 (success) or 5xx (internal server error - unhandled exceptions)
        [HttpGet]
        public IEnumerable<Event> GetAll()
        {
            return _context.Events.ToList();
        }

        // return either a 404 error, when no items matches the id, otherwise a 200 with a JSON response body
        [HttpGet("{id}", Name = "GetEvent")]
        public IActionResult GetById(long id)
        {
            var item = _context.Events.FirstOrDefault(i => i.Id == id);
            if (item == null)
                return NotFound();
            return new ObjectResult(item);
        }

        // returns the payload of CreatedAtRoute, which returns a 201 response (standard response for HTTP POST that creates a new resource)
        // also adds the Location header to the response, that specifies the URI of the newly created event, using the GetEvent named route to create the URL
        [HttpPost]
        public IActionResult Create([FromBody] Event item)
        {
            if (item == null)
                return BadRequest();

            _context.Events.Add(item);
            _context.SaveChanges();

            return CreatedAtRoute("GetEvent", new { id = item.Id }, item);
        }

        // 204 (No Content) response: requires the client to send the entire updated entity
        [HttpPut("{id}")]
        public IActionResult Update(long id, [FromBody] Event item)
        {
            if (item == null || item.Id != id)
                return BadRequest();

            var toBeUpdated = _context.Events.FirstOrDefault(i => i.Id == id);
            toBeUpdated.Description = item.Description;
            toBeUpdated.Name = item.Name;

            _context.Events.Update(toBeUpdated);
            _context.SaveChanges();
            return new NoContentResult();
        }

        [HttpDelete("{id}")]
        public IActionResult Delete(long id)
        {
            var item = _context.Events.FirstOrDefault(i => i.Id == id);
            if (item == null)
                return NotFound();

            _context.Events.Remove(item);
            _context.SaveChanges();
            return new NoContentResult();
        }
    }
}
