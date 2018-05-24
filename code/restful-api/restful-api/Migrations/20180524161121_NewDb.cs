using Microsoft.EntityFrameworkCore.Migrations;
using System;
using System.Collections.Generic;

namespace restfulapi.Migrations
{
    public partial class NewDb : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Local",
                columns: table => new
                {
                    Id = table.Column<int>(nullable: false)
                        .Annotation("MySQL:AutoIncrement", true),
                    Descricao = table.Column<string>(nullable: true),
                    Nome = table.Column<string>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Local", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Usuario",
                columns: table => new
                {
                    Id = table.Column<int>(nullable: false)
                        .Annotation("MySQL:AutoIncrement", true),
                    Celular = table.Column<string>(nullable: true),
                    Email = table.Column<string>(maxLength: 254, nullable: false),
                    Nome = table.Column<string>(nullable: false),
                    Senha = table.Column<string>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Usuario", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Endereco",
                columns: table => new
                {
                    Id = table.Column<int>(nullable: false)
                        .Annotation("MySQL:AutoIncrement", true),
                    Cep = table.Column<string>(nullable: false),
                    Cidade = table.Column<string>(nullable: false),
                    Complemento = table.Column<string>(nullable: true),
                    LocalId = table.Column<int>(nullable: false),
                    Numero = table.Column<int>(nullable: false),
                    Rua = table.Column<string>(nullable: false),
                    Uf = table.Column<string>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Endereco", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Endereco_Local_LocalId",
                        column: x => x.LocalId,
                        principalTable: "Local",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Evento",
                columns: table => new
                {
                    Id = table.Column<int>(nullable: false)
                        .Annotation("MySQL:AutoIncrement", true),
                    Data = table.Column<DateTime>(nullable: false),
                    Descricao = table.Column<string>(nullable: false),
                    Duracao = table.Column<string>(nullable: true),
                    LinkImagem = table.Column<string>(nullable: false),
                    LinkPagina = table.Column<string>(nullable: true),
                    LocalId = table.Column<int>(nullable: false),
                    Nome = table.Column<string>(nullable: false),
                    UsuarioId = table.Column<int>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Evento", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Evento_Local_LocalId",
                        column: x => x.LocalId,
                        principalTable: "Local",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Evento_Usuario_UsuarioId",
                        column: x => x.UsuarioId,
                        principalTable: "Usuario",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Ingresso",
                columns: table => new
                {
                    Id = table.Column<int>(nullable: false)
                        .Annotation("MySQL:AutoIncrement", true),
                    Disponivel = table.Column<bool>(nullable: false),
                    EventoId = table.Column<int>(nullable: false),
                    TipoIngreso = table.Column<string>(nullable: false),
                    Valor = table.Column<double>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Ingresso", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Ingresso_Evento_EventoId",
                        column: x => x.EventoId,
                        principalTable: "Evento",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Compra",
                columns: table => new
                {
                    Id = table.Column<int>(nullable: false)
                        .Annotation("MySQL:AutoIncrement", true),
                    DataCompra = table.Column<DateTime>(nullable: false),
                    IngressoId = table.Column<int>(nullable: false),
                    UsuarioId = table.Column<int>(nullable: false),
                    Valor = table.Column<double>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Compra", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Compra_Ingresso_IngressoId",
                        column: x => x.IngressoId,
                        principalTable: "Ingresso",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Compra_Usuario_UsuarioId",
                        column: x => x.UsuarioId,
                        principalTable: "Usuario",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_Compra_IngressoId",
                table: "Compra",
                column: "IngressoId",
                unique: true);

            migrationBuilder.CreateIndex(
                name: "IX_Compra_UsuarioId",
                table: "Compra",
                column: "UsuarioId");

            migrationBuilder.CreateIndex(
                name: "IX_Endereco_LocalId",
                table: "Endereco",
                column: "LocalId",
                unique: true);

            migrationBuilder.CreateIndex(
                name: "IX_Evento_LocalId",
                table: "Evento",
                column: "LocalId");

            migrationBuilder.CreateIndex(
                name: "IX_Evento_UsuarioId",
                table: "Evento",
                column: "UsuarioId");

            migrationBuilder.CreateIndex(
                name: "IX_Ingresso_EventoId",
                table: "Ingresso",
                column: "EventoId");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Compra");

            migrationBuilder.DropTable(
                name: "Endereco");

            migrationBuilder.DropTable(
                name: "Ingresso");

            migrationBuilder.DropTable(
                name: "Evento");

            migrationBuilder.DropTable(
                name: "Local");

            migrationBuilder.DropTable(
                name: "Usuario");
        }
    }
}
