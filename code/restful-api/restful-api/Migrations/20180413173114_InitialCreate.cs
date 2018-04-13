using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.EntityFrameworkCore.Migrations;
using System;
using System.Collections.Generic;

namespace restfulapi.Migrations
{
    public partial class InitialCreate : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Conquistas",
                columns: table => new
                {
                    Id = table.Column<long>(nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.SerialColumn),
                    Avaliacao = table.Column<int>(nullable: false),
                    Descricao = table.Column<string>(nullable: true),
                    OrganizadorId = table.Column<long>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Conquistas", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Historico_Eventos",
                columns: table => new
                {
                    Id = table.Column<long>(nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.SerialColumn),
                    DataEvento = table.Column<DateTime>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Historico_Eventos", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Local",
                columns: table => new
                {
                    Id = table.Column<long>(nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.SerialColumn),
                    CapacidadeDePessoas = table.Column<int>(nullable: false),
                    Descricao = table.Column<string>(nullable: true),
                    Nome = table.Column<string>(nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Local", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Pontos",
                columns: table => new
                {
                    Id = table.Column<long>(nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.SerialColumn),
                    DataVencimento = table.Column<DateTime>(nullable: false),
                    PontosAExpirar = table.Column<float>(nullable: false),
                    Valor = table.Column<float>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Pontos", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Possui",
                columns: table => new
                {
                    Id = table.Column<long>(nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.SerialColumn)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Possui", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "TipoDeEvento",
                columns: table => new
                {
                    Id = table.Column<long>(nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.SerialColumn),
                    Tipo = table.Column<string>(nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_TipoDeEvento", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "TipoIngresso",
                columns: table => new
                {
                    Id = table.Column<long>(nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.SerialColumn),
                    DataInicio = table.Column<DateTime>(nullable: false),
                    IngressosRestante = table.Column<int>(nullable: false),
                    NumeroLote = table.Column<int>(nullable: false),
                    Quantidade = table.Column<int>(nullable: false),
                    Taxa = table.Column<float>(nullable: false),
                    Tipo = table.Column<string>(nullable: true),
                    Valor = table.Column<float>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_TipoIngresso", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Users",
                columns: table => new
                {
                    Id = table.Column<long>(nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.SerialColumn),
                    Email = table.Column<string>(nullable: true),
                    Name = table.Column<string>(nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Users", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "WishList",
                columns: table => new
                {
                    Id = table.Column<long>(nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.SerialColumn),
                    Nome = table.Column<string>(nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_WishList", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Events",
                columns: table => new
                {
                    Id = table.Column<long>(nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.SerialColumn),
                    Description = table.Column<string>(nullable: true),
                    Name = table.Column<string>(nullable: true),
                    UserId = table.Column<long>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Events", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Events_Users_UserId",
                        column: x => x.UserId,
                        principalTable: "Users",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "CartaoDeCredito",
                columns: table => new
                {
                    Id = table.Column<long>(nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.SerialColumn),
                    Bandeira = table.Column<string>(nullable: true),
                    CodigoDeSeguranca = table.Column<char>(nullable: false),
                    CompraID = table.Column<long>(nullable: false),
                    NomeImpressoCartao = table.Column<string>(nullable: true),
                    NumeroDoCartao = table.Column<string>(nullable: true),
                    UsuarioId = table.Column<long>(nullable: true),
                    Validade = table.Column<DateTime>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_CartaoDeCredito", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Telefone",
                columns: table => new
                {
                    Id = table.Column<long>(nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.SerialColumn),
                    ContatoId = table.Column<long>(nullable: false),
                    Ddd = table.Column<string>(nullable: true),
                    NumeroTelefone = table.Column<string>(nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Telefone", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Ingresso",
                columns: table => new
                {
                    Id = table.Column<long>(nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.SerialColumn),
                    Celular = table.Column<string>(nullable: true),
                    Email = table.Column<string>(nullable: true),
                    EventoID = table.Column<long>(nullable: false),
                    Historico_EventosId1 = table.Column<long>(nullable: true),
                    Historico_EventosIdId = table.Column<long>(nullable: true),
                    Nome = table.Column<string>(nullable: true),
                    NumeroDoIngresso = table.Column<string>(nullable: true),
                    OrganizadorId = table.Column<long>(nullable: false),
                    PromocaoId = table.Column<long>(nullable: false),
                    Sobrenome = table.Column<string>(nullable: true),
                    TipoIngressoId = table.Column<long>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Ingresso", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Ingresso_Historico_Eventos_Historico_EventosId1",
                        column: x => x.Historico_EventosId1,
                        principalTable: "Historico_Eventos",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Restrict);
                    table.ForeignKey(
                        name: "FK_Ingresso_Historico_Eventos_Historico_EventosIdId",
                        column: x => x.Historico_EventosIdId,
                        principalTable: "Historico_Eventos",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Restrict);
                    table.ForeignKey(
                        name: "FK_Ingresso_TipoIngresso_TipoIngressoId",
                        column: x => x.TipoIngressoId,
                        principalTable: "TipoIngresso",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Usuario",
                columns: table => new
                {
                    Cnpj = table.Column<string>(nullable: true),
                    OrganizadorDesde = table.Column<DateTime>(nullable: true),
                    Responsavel = table.Column<string>(nullable: true),
                    UrlSite = table.Column<string>(nullable: true),
                    Id = table.Column<long>(nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.SerialColumn),
                    Cpf = table.Column<string>(nullable: true),
                    DataNascimento = table.Column<DateTime>(nullable: false),
                    Discriminator = table.Column<string>(nullable: false),
                    Email = table.Column<string>(nullable: true),
                    Historico_EventosId = table.Column<long>(nullable: false),
                    Nome = table.Column<string>(nullable: true),
                    Organizador = table.Column<bool>(nullable: false),
                    PontosId = table.Column<long>(nullable: false),
                    Senha = table.Column<string>(nullable: true),
                    Sobrenome = table.Column<string>(nullable: true),
                    SorteioId = table.Column<long>(nullable: true),
                    WishListId = table.Column<long>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Usuario", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Usuario_Historico_Eventos_Historico_EventosId",
                        column: x => x.Historico_EventosId,
                        principalTable: "Historico_Eventos",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Usuario_Pontos_PontosId",
                        column: x => x.PontosId,
                        principalTable: "Pontos",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Usuario_WishList_WishListId",
                        column: x => x.WishListId,
                        principalTable: "WishList",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Compra",
                columns: table => new
                {
                    Id = table.Column<long>(nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.SerialColumn),
                    DataCompra = table.Column<DateTime>(nullable: false),
                    DataVencimento = table.Column<DateTime>(nullable: false),
                    FormaPagamento = table.Column<string>(nullable: true),
                    IngressoId = table.Column<long>(nullable: false),
                    QuantidadeIngressos = table.Column<int>(nullable: false),
                    Status = table.Column<string>(nullable: true),
                    UsuarioId = table.Column<long>(nullable: false),
                    ValorCompra = table.Column<float>(nullable: false),
                    ValorUnitario = table.Column<float>(nullable: false)
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

            migrationBuilder.CreateTable(
                name: "ContaBancaria",
                columns: table => new
                {
                    Id = table.Column<long>(nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.SerialColumn),
                    Agencia = table.Column<string>(nullable: true),
                    Banco = table.Column<string>(nullable: true),
                    Cnpj = table.Column<int>(nullable: false),
                    Conta = table.Column<string>(nullable: true),
                    Cpf = table.Column<string>(nullable: true),
                    Favorecido = table.Column<string>(nullable: true),
                    OrganizadorId = table.Column<long>(nullable: false),
                    Tipo = table.Column<string>(nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_ContaBancaria", x => x.Id);
                    table.ForeignKey(
                        name: "FK_ContaBancaria_Usuario_OrganizadorId",
                        column: x => x.OrganizadorId,
                        principalTable: "Usuario",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Contato",
                columns: table => new
                {
                    Id = table.Column<long>(nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.SerialColumn),
                    OrganizadorId = table.Column<long>(nullable: false),
                    Responsavel = table.Column<string>(nullable: true),
                    tipoContato = table.Column<string>(nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Contato", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Contato_Usuario_OrganizadorId",
                        column: x => x.OrganizadorId,
                        principalTable: "Usuario",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Cria",
                columns: table => new
                {
                    Id = table.Column<long>(nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.SerialColumn),
                    OrganizadorId = table.Column<long>(nullable: false),
                    UsuarioId = table.Column<long>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Cria", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Cria_Usuario_OrganizadorId",
                        column: x => x.OrganizadorId,
                        principalTable: "Usuario",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Cria_Usuario_UsuarioId",
                        column: x => x.UsuarioId,
                        principalTable: "Usuario",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Endereco",
                columns: table => new
                {
                    Id = table.Column<long>(nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.SerialColumn),
                    Bairro = table.Column<string>(nullable: true),
                    Cep = table.Column<string>(nullable: true),
                    Cidade = table.Column<string>(nullable: true),
                    Complemento = table.Column<string>(nullable: true),
                    LocalId = table.Column<long>(nullable: false),
                    Logradouro = table.Column<string>(nullable: true),
                    Numero = table.Column<int>(nullable: false),
                    Uf = table.Column<string>(nullable: true),
                    UsuarioId = table.Column<long>(nullable: false)
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
                    table.ForeignKey(
                        name: "FK_Endereco_Usuario_UsuarioId",
                        column: x => x.UsuarioId,
                        principalTable: "Usuario",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Evento",
                columns: table => new
                {
                    Id = table.Column<long>(nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.SerialColumn),
                    Data = table.Column<DateTime>(nullable: false),
                    Descricao = table.Column<string>(nullable: true),
                    HorarioInicio = table.Column<DateTime>(nullable: false),
                    HorarioTermino = table.Column<DateTime>(nullable: false),
                    LinkPagina = table.Column<string>(nullable: true),
                    LocalId = table.Column<long>(nullable: false),
                    Nome = table.Column<string>(nullable: true),
                    OrganizadorId = table.Column<long>(nullable: false),
                    TipoDeEventoId = table.Column<long>(nullable: false)
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
                        name: "FK_Evento_Usuario_OrganizadorId",
                        column: x => x.OrganizadorId,
                        principalTable: "Usuario",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Evento_TipoDeEvento_TipoDeEventoId",
                        column: x => x.TipoDeEventoId,
                        principalTable: "TipoDeEvento",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Promocao",
                columns: table => new
                {
                    Id = table.Column<long>(nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.SerialColumn),
                    CodigoPromocional = table.Column<string>(nullable: true),
                    OrganizadorId = table.Column<long>(nullable: false),
                    PorcentagemDeDesconto = table.Column<int>(nullable: false),
                    QuantidadeIngressos = table.Column<int>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Promocao", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Promocao_Usuario_OrganizadorId",
                        column: x => x.OrganizadorId,
                        principalTable: "Usuario",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Sorteio",
                columns: table => new
                {
                    Id = table.Column<long>(nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.SerialColumn),
                    DataFim = table.Column<DateTime>(nullable: false),
                    DataInicio = table.Column<DateTime>(nullable: false),
                    HorarioFim = table.Column<DateTime>(nullable: false),
                    HorarioInicio = table.Column<DateTime>(nullable: false),
                    Nome = table.Column<string>(nullable: true),
                    Observacao = table.Column<string>(nullable: true),
                    OrganizadorId = table.Column<long>(nullable: false),
                    Premios = table.Column<string>(nullable: true),
                    descricao = table.Column<string>(nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Sorteio", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Sorteio_Usuario_OrganizadorId",
                        column: x => x.OrganizadorId,
                        principalTable: "Usuario",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Contem",
                columns: table => new
                {
                    id = table.Column<long>(nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.SerialColumn),
                    IngressoId = table.Column<long>(nullable: false),
                    PromocaoId = table.Column<long>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Contem", x => x.id);
                    table.ForeignKey(
                        name: "FK_Contem_Ingresso_IngressoId",
                        column: x => x.IngressoId,
                        principalTable: "Ingresso",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Contem_Promocao_PromocaoId",
                        column: x => x.PromocaoId,
                        principalTable: "Promocao",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Participa",
                columns: table => new
                {
                    Id = table.Column<long>(nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.SerialColumn),
                    SorteioId = table.Column<long>(nullable: false),
                    UsuarioId = table.Column<long>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Participa", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Participa_Sorteio_SorteioId",
                        column: x => x.SorteioId,
                        principalTable: "Sorteio",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Participa_Usuario_UsuarioId",
                        column: x => x.UsuarioId,
                        principalTable: "Usuario",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_CartaoDeCredito_CompraID",
                table: "CartaoDeCredito",
                column: "CompraID");

            migrationBuilder.CreateIndex(
                name: "IX_CartaoDeCredito_UsuarioId",
                table: "CartaoDeCredito",
                column: "UsuarioId");

            migrationBuilder.CreateIndex(
                name: "IX_Compra_IngressoId",
                table: "Compra",
                column: "IngressoId");

            migrationBuilder.CreateIndex(
                name: "IX_Compra_UsuarioId",
                table: "Compra",
                column: "UsuarioId");

            migrationBuilder.CreateIndex(
                name: "IX_ContaBancaria_OrganizadorId",
                table: "ContaBancaria",
                column: "OrganizadorId");

            migrationBuilder.CreateIndex(
                name: "IX_Contato_OrganizadorId",
                table: "Contato",
                column: "OrganizadorId");

            migrationBuilder.CreateIndex(
                name: "IX_Contem_IngressoId",
                table: "Contem",
                column: "IngressoId");

            migrationBuilder.CreateIndex(
                name: "IX_Contem_PromocaoId",
                table: "Contem",
                column: "PromocaoId");

            migrationBuilder.CreateIndex(
                name: "IX_Cria_OrganizadorId",
                table: "Cria",
                column: "OrganizadorId");

            migrationBuilder.CreateIndex(
                name: "IX_Cria_UsuarioId",
                table: "Cria",
                column: "UsuarioId");

            migrationBuilder.CreateIndex(
                name: "IX_Endereco_LocalId",
                table: "Endereco",
                column: "LocalId");

            migrationBuilder.CreateIndex(
                name: "IX_Endereco_UsuarioId",
                table: "Endereco",
                column: "UsuarioId");

            migrationBuilder.CreateIndex(
                name: "IX_Evento_LocalId",
                table: "Evento",
                column: "LocalId");

            migrationBuilder.CreateIndex(
                name: "IX_Evento_OrganizadorId",
                table: "Evento",
                column: "OrganizadorId");

            migrationBuilder.CreateIndex(
                name: "IX_Evento_TipoDeEventoId",
                table: "Evento",
                column: "TipoDeEventoId");

            migrationBuilder.CreateIndex(
                name: "IX_Events_UserId",
                table: "Events",
                column: "UserId");

            migrationBuilder.CreateIndex(
                name: "IX_Ingresso_EventoID",
                table: "Ingresso",
                column: "EventoID");

            migrationBuilder.CreateIndex(
                name: "IX_Ingresso_Historico_EventosId1",
                table: "Ingresso",
                column: "Historico_EventosId1");

            migrationBuilder.CreateIndex(
                name: "IX_Ingresso_Historico_EventosIdId",
                table: "Ingresso",
                column: "Historico_EventosIdId");

            migrationBuilder.CreateIndex(
                name: "IX_Ingresso_OrganizadorId",
                table: "Ingresso",
                column: "OrganizadorId");

            migrationBuilder.CreateIndex(
                name: "IX_Ingresso_PromocaoId",
                table: "Ingresso",
                column: "PromocaoId");

            migrationBuilder.CreateIndex(
                name: "IX_Ingresso_TipoIngressoId",
                table: "Ingresso",
                column: "TipoIngressoId");

            migrationBuilder.CreateIndex(
                name: "IX_Participa_SorteioId",
                table: "Participa",
                column: "SorteioId");

            migrationBuilder.CreateIndex(
                name: "IX_Participa_UsuarioId",
                table: "Participa",
                column: "UsuarioId");

            migrationBuilder.CreateIndex(
                name: "IX_Promocao_OrganizadorId",
                table: "Promocao",
                column: "OrganizadorId");

            migrationBuilder.CreateIndex(
                name: "IX_Sorteio_OrganizadorId",
                table: "Sorteio",
                column: "OrganizadorId");

            migrationBuilder.CreateIndex(
                name: "IX_Telefone_ContatoId",
                table: "Telefone",
                column: "ContatoId");

            migrationBuilder.CreateIndex(
                name: "IX_Usuario_Historico_EventosId",
                table: "Usuario",
                column: "Historico_EventosId");

            migrationBuilder.CreateIndex(
                name: "IX_Usuario_PontosId",
                table: "Usuario",
                column: "PontosId");

            migrationBuilder.CreateIndex(
                name: "IX_Usuario_SorteioId",
                table: "Usuario",
                column: "SorteioId");

            migrationBuilder.CreateIndex(
                name: "IX_Usuario_WishListId",
                table: "Usuario",
                column: "WishListId");

            migrationBuilder.AddForeignKey(
                name: "FK_CartaoDeCredito_Compra_CompraID",
                table: "CartaoDeCredito",
                column: "CompraID",
                principalTable: "Compra",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_CartaoDeCredito_Usuario_UsuarioId",
                table: "CartaoDeCredito",
                column: "UsuarioId",
                principalTable: "Usuario",
                principalColumn: "Id",
                onDelete: ReferentialAction.Restrict);

            migrationBuilder.AddForeignKey(
                name: "FK_Telefone_Contato_ContatoId",
                table: "Telefone",
                column: "ContatoId",
                principalTable: "Contato",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_Ingresso_Usuario_OrganizadorId",
                table: "Ingresso",
                column: "OrganizadorId",
                principalTable: "Usuario",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_Ingresso_Promocao_PromocaoId",
                table: "Ingresso",
                column: "PromocaoId",
                principalTable: "Promocao",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_Ingresso_Evento_EventoID",
                table: "Ingresso",
                column: "EventoID",
                principalTable: "Evento",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_Usuario_Sorteio_SorteioId",
                table: "Usuario",
                column: "SorteioId",
                principalTable: "Sorteio",
                principalColumn: "Id",
                onDelete: ReferentialAction.Restrict);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Sorteio_Usuario_OrganizadorId",
                table: "Sorteio");

            migrationBuilder.DropTable(
                name: "CartaoDeCredito");

            migrationBuilder.DropTable(
                name: "Conquistas");

            migrationBuilder.DropTable(
                name: "ContaBancaria");

            migrationBuilder.DropTable(
                name: "Contem");

            migrationBuilder.DropTable(
                name: "Cria");

            migrationBuilder.DropTable(
                name: "Endereco");

            migrationBuilder.DropTable(
                name: "Events");

            migrationBuilder.DropTable(
                name: "Participa");

            migrationBuilder.DropTable(
                name: "Possui");

            migrationBuilder.DropTable(
                name: "Telefone");

            migrationBuilder.DropTable(
                name: "Compra");

            migrationBuilder.DropTable(
                name: "Users");

            migrationBuilder.DropTable(
                name: "Contato");

            migrationBuilder.DropTable(
                name: "Ingresso");

            migrationBuilder.DropTable(
                name: "Evento");

            migrationBuilder.DropTable(
                name: "Promocao");

            migrationBuilder.DropTable(
                name: "TipoIngresso");

            migrationBuilder.DropTable(
                name: "Local");

            migrationBuilder.DropTable(
                name: "TipoDeEvento");

            migrationBuilder.DropTable(
                name: "Usuario");

            migrationBuilder.DropTable(
                name: "Historico_Eventos");

            migrationBuilder.DropTable(
                name: "Pontos");

            migrationBuilder.DropTable(
                name: "Sorteio");

            migrationBuilder.DropTable(
                name: "WishList");
        }
    }
}
