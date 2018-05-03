package br.pucminas.alpmysapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Evento {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("descricao")
    @Expose
    private String descricao;
    @SerializedName("data")
    @Expose
    private String data;
    @SerializedName("horarioInicio")
    @Expose
    private String horarioInicio;
    @SerializedName("horarioTermino")
    @Expose
    private String horarioTermino;
    @SerializedName("linkPagina")
    @Expose
    private String linkPagina;
    @SerializedName("localId")
    @Expose
    private Integer localId;
    @SerializedName("local")
    @Expose
    private Local local;
    @SerializedName("organizadorId")
    @Expose
    private Integer organizadorId;
    @SerializedName("organizador")
    @Expose
    private Organizador organizador;
    @SerializedName("tipoDeEventoId")
    @Expose
    private Integer tipoDeEventoId;
    @SerializedName("tipoDeEvento")
    @Expose
    private TipoDeEvento tipoDeEvento;
    @SerializedName("cidade")
    @Expose
    private String cidade;

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(nome.isEmpty())
            throw new IllegalArgumentException();
        else
            this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if(descricao.isEmpty())
            throw new IllegalArgumentException();
        else
            this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public String getHorarioTermino() {
        return horarioTermino;
    }

    public void setHorarioTermino(String horarioTermino) {
        this.horarioTermino = horarioTermino;
    }

    public String getLinkPagina() {
        return linkPagina;
    }

    public void setLinkPagina(String linkPagina) {
        this.linkPagina = linkPagina;
    }

    public Integer getLocalId() {
        return localId;
    }

    public void setLocalId(Integer localId) {
        this.localId = localId;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
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

    public Integer getTipoDeEventoId() {
        return tipoDeEventoId;
    }

    public void setTipoDeEventoId(Integer tipoDeEventoId) {
        this.tipoDeEventoId = tipoDeEventoId;
    }

    public TipoDeEvento getTipoDeEvento() {
        return tipoDeEvento;
    }

    public void setTipoDeEvento(TipoDeEvento tipoDeEvento) {
        this.tipoDeEvento = tipoDeEvento;
    }

}
