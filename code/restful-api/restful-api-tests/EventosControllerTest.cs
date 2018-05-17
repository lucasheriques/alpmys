using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.InteropServices;
using System.Runtime.InteropServices.ComTypes;
using System.Text;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using RestfulApi.Controllers;
using RestfulApi.Models;
using Xunit;

namespace RestfulApiTests
{
    public class EventosControllerTest
    {
        [Fact]
        public async void ValidPost()
        {
            // Arrange
            var options = new DbContextOptionsBuilder<AlpmysContext>()
                .UseInMemoryDatabase("CreateNewEvent")
                .Options;
            AlpmysContext context = new AlpmysContext(options);
            EventosController controller = new EventosController(context);
            Evento e = new Evento();
            e.Nome = "Evento!";
            
            // Act
            await controller.PostEvento(e);
            
            // Assert
            Assert.Equal(1, context.Evento.Count());
        }
        
        [Fact]
        public async void GetEventos()
        {
            // Arrange
            var options = new DbContextOptionsBuilder<AlpmysContext>()
                .UseInMemoryDatabase("GetTest")
                .Options;
            AlpmysContext context = new AlpmysContext(options);
            Evento e = new Evento();
            e.Nome = "Evento!";
            Evento e2 = new Evento();
            e2.Nome = "Evento2";

            context.Evento.Add(e);
            context.Evento.Add(e2);
            await context.SaveChangesAsync();
            EventosController controller = new EventosController(context);

            // Act
            var result = controller.GetEvento();
            
            // Assert
            Assert.Equal(2, context.Evento.Count());
        }
        
        [Fact]
        public async void InvalidPost()
        {
            // Arrange
            var options = new DbContextOptionsBuilder<AlpmysContext>()
                .UseInMemoryDatabase("FailToCreate")
                .Options;
            AlpmysContext context = new AlpmysContext(options);
            EventosController controller = new EventosController(context);
            controller.ModelState.AddModelError("error", "invalid model");
            Evento e = new Evento();
            e.Nome = null;
            
            // Act
            var result = await controller.PostEvento(null);
            
            // Assert
            Assert.IsType<BadRequestObjectResult>(result);
        }

        [Fact]
        public async void Delete()
        {
            // Arrange
            var options = new DbContextOptionsBuilder<AlpmysContext>()
                .UseInMemoryDatabase("DeleteEvent")
                .Options;
            AlpmysContext context = new AlpmysContext(options);
            EventosController controller = new EventosController(context);
            Evento e = new Evento();
            e.Id = 9;
            e.Nome = "Evento!";
            
            // Act
            await controller.PostEvento(e);
            await controller.DeleteEvento(9);
            
            // Assert
            Assert.Equal(0, context.Evento.Count());
        }
        
        [Fact]
        public async void FailDelete()
        {
            // Arrange
            var options = new DbContextOptionsBuilder<AlpmysContext>()
                .UseInMemoryDatabase("FailDeleteEvent")
                .Options;
            AlpmysContext context = new AlpmysContext(options);
            EventosController controller = new EventosController(context);
            Evento e = new Evento();
            e.Nome = "Evento!";
            
            // Act
            await controller.PostEvento(e);
            var result = await controller.DeleteEvento(10);
            
            // Assert
            Assert.IsType<NotFoundResult>(result);
        }
        
        [Fact]
        public async void GetSpecificEvent()
        {
            // Arrange
            var options = new DbContextOptionsBuilder<AlpmysContext>()
                .UseInMemoryDatabase("UpdateEvent")
                .Options;
            AlpmysContext context = new AlpmysContext(options);
            EventosController controller = new EventosController(context);
            Evento e = new Evento();
            e.Id = 5;
            e.Nome = "Evento!";
            await controller.PostEvento(e);

            // Act
            var result = await controller.GetEvento(5);

            // Assert
            Assert.IsType<OkObjectResult>(result);
        }
    }
}
