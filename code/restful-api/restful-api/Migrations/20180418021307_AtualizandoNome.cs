using Microsoft.EntityFrameworkCore.Migrations;
using System;
using System.Collections.Generic;

namespace restfulapi.Migrations
{
    public partial class AtualizandoNome : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "Sobrenome",
                table: "Usuarios");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<string>(
                name: "Sobrenome",
                table: "Usuarios",
                nullable: true);
        }
    }
}
