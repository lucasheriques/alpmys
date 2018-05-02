package br.pucminas.alpmysapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ingresso {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("numeroDoIngresso")
    @Expose
    private String numeroDoIngresso;
    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("sobrenome")
    @Expose
    private String sobrenome;
    @SerializedName("celular")
    @Expose
    private String celular;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("historico_EventosId")
    @Expose
    private Integer historicoEventosId;
    @SerializedName("historico_Eventos")
    @Expose
    private HistoricoEventos historicoEventos;
    @SerializedName("tipoIngressoId")
    @Expose
    private Integer tipoIngressoId;
    @SerializedName("tipoIngresso")
    @Expose
    private TipoIngresso tipoIngresso;
    @SerializedName("organizadorId")
    @Expose
    private Integer organizadorId;
    @SerializedName("organizador")
    @Expose
    private Organizador organizador;
    @SerializedName("eventoID")
    @Expose
    private Integer eventoID;
    @SerializedName("evento")
    @Expose
    private Evento evento;
    @SerializedName("promocaoId")
    @Expose
    private Integer promocaoId;
    @SerializedName("promocao")
    @Expose
    private Promocao promocao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroDoIngresso() {
        return numeroDoIngresso;
    }

    public void setNumeroDoIngresso(String numeroDoIngresso) {
        this.numeroDoIngresso = numeroDoIngresso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getHistoricoEventosId() {
        return historicoEventosId;
    }

    public void setHistoricoEventosId(Integer historicoEventosId) {
        this.historicoEventosId = historicoEventosId;
    }

    public HistoricoEventos getHistoricoEventos() {
        return historicoEventos;
    }

    public void setHistoricoEventos(HistoricoEventos historicoEventos) {
        this.historicoEventos = historicoEventos;
    }

    public Integer getTipoIngressoId() {
        return tipoIngressoId;
    }

    public void setTipoIngressoId(Integer tipoIngressoId) {
        this.tipoIngressoId = tipoIngressoId;
    }

    public TipoIngresso getTipoIngresso() {
        return tipoIngresso;
    }

    public void setTipoIngresso(TipoIngresso tipoIngresso) {
        this.tipoIngresso = tipoIngresso;
    }

    public Integer getOrganizadorId() {
        return organizadorId;
    }

    public void setOrganizadorId(Integer organizadorId) {
        this.organizadorId = organizadorId;
    }

    public Organizador getOrganizador() {
        return organizador;
    }

    public void setOrganizador(Organizador organizador) {
        this.organizador = organizador;
    }

    public Integer getEventoID() {
        return eventoID;
    }

    public void setEventoID(Integer eventoID) {
        this.eventoID = eventoID;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Integer getPromocaoId() {
        return promocaoId;
    }

    public void setPromocaoId(Integer promocaoId) {
        this.promocaoId = promocaoId;
    }

    public Promocao getPromocao() {
        return promocao;
    }

    public void setPromocao(Promocao promocao) {
        this.promocao = promocao;
    }

}
