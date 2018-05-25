using Microsoft.AspNetCore.Builder;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.EntityFrameworkCore;
using RestfulApi.Models;
using NSwag.AspNetCore;
using System.Reflection;
using NJsonSchema;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Newtonsoft.Json.Serialization;
using Newtonsoft.Json;

namespace RestfulApi
{
    public class Startup
    {
        public IHostingEnvironment HostingEnvironment { get; private set; }
        public IConfiguration Configuration { get; private set; }

        public Startup(IConfiguration configuration, IHostingEnvironment env)
        {
            this.HostingEnvironment = env;
            this.Configuration = configuration;
        }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddMvc().AddJsonOptions(options => {
                options.SerializerSettings.ContractResolver = new CamelCasePropertyNamesContractResolver();
                options.SerializerSettings.ReferenceLoopHandling = ReferenceLoopHandling.Ignore;
            });
            var connectionString = Configuration.GetConnectionString("AlpmysContext");
        	services.AddDbContext<AlpmysContext>(options => options.UseMySQL(connectionString));
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
