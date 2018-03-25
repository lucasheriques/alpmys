using Microsoft.AspNetCore.Builder;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.EntityFrameworkCore;
using restfulapi.Models;
using NSwag.AspNetCore;
using System.Reflection;
using NJsonSchema;

namespace restful_api
{
    public class Startup
    {
        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddDbContext<AlpmysContext>(opt => opt.UseInMemoryDatabase("Alpmys"));
            services.AddMvc();
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app)
        {
            app.UseStaticFiles();

            app.UseSwaggerUi(typeof(Startup).GetTypeInfo().Assembly, settings =>
            {
                settings.GeneratorSettings.DefaultPropertyNameHandling = PropertyNameHandling.CamelCase;
                settings.PostProcess = document =>
                {
                    document.Info.Title = "Alpmys RESTful API";
                    document.Info.Description = "Welcome! This document, using Swagger, describes the services API for our app: Alpmys.";
                };
            });

            app.UseMvc();
        }
    }
}
