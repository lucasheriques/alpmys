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
    public class UsuarioControllerTest
    {
        [Fact]
        public async void ValidPost()
        {
            // Arrange
            var options = new DbContextOptionsBuilder<AlpmysContext>()
                .UseInMemoryDatabase("CreateNewUser")
                .Options;
            AlpmysContext context = new AlpmysContext(options);
            UsuariosController controller = new UsuariosController(context);
            Usuario u = new Usuario();
            u.Email = "teste@teste.com";
            
            // Act
            await controller.PostUsuario(u);
                
            // Assert
            Assert.Equal(1, context.Usuario.Count());
        }
        
        [Fact]
        public async void GetUsuarios()
        {
            // Arrange
            var options = new DbContextOptionsBuilder<AlpmysContext>()
                .UseInMemoryDatabase("GetTest")
                .Options;
            AlpmysContext context = new AlpmysContext(options);
            Usuario u = new Usuario();
            u.Email = "e@e.com";
            Usuario u2 = new Usuario();
            u2.Email = "e2@e.com";

            context.Usuario.Add(u);
            context.Usuario.Add(u2);
            await context.SaveChangesAsync();
            UsuariosController controller = new UsuariosController(context);

            // Act
            var result = await controller.GetUsuario();
            
            // Assert
            Assert.IsType<OkObjectResult>(result);
            Assert.Equal(2, context.Usuario.Count());
        }
        
        [Fact]
        public async void InvalidPost()
        {
            // Arrange
            var options = new DbContextOptionsBuilder<AlpmysContext>()
                .UseInMemoryDatabase("FailToCreate")
                .Options;
            AlpmysContext context = new AlpmysContext(options);
            UsuariosController controller = new UsuariosController(context);
            controller.ModelState.AddModelError("error", "invalid model");
            
            // Act
            var result = await controller.PostUsuario(null);
            
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
            UsuariosController controller = new UsuariosController(context);
            Usuario u = new Usuario();
            u.Id = 9;
            u.Email = "a@a.com";
            
            // Act
            await controller.PostUsuario(u);
            await controller.DeleteUsuario(9);
            
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
            UsuariosController controller = new UsuariosController(context);
            
            // Act
            var result = await controller.DeleteUsuario(10);
            
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
            UsuariosController controller = new UsuariosController(context);
            Usuario u = new Usuario();
            u.Id = 5;
            u.Email = "a@a.com";
            await controller.PostUsuario(u);

            // Act
            var result = await controller.GetUsuario(5);

            // Assert
            Assert.IsType<OkObjectResult>(result);
        }
    }
}
