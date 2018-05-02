package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Evento {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("descricao")
    @Expose
    private String descricao;
    @SerializedName("horarioInicio")
    @Expose
    private String horarioInicio;
    @SerializedName("horarioTermino")
    @Expose
    private String horarioTermino;
    @SerializedName("linkPagina")
    @Expose
    private String linkPagina;

    /**
     * No args constructor for use in serialization
     *
     */
    public Evento() {
    }

    /**
     *
     * @param id
     * @param horarioTermino
     * @param data
     * @param linkPagina
     * @param nome
     * @param horarioInicio
     * @param descricao
     */
    public Evento(Long id, String nome, String descricao, String data, String horarioInicio, String horarioTermino, String linkPagina) {
        super();
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.horarioInicio = horarioInicio;
        this.horarioTermino = horarioTermino;
        this.linkPagina = linkPagina;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        if(linkPagina.isEmpty())
            throw  new IllegalArgumentException();
        else

        this.linkPagina = linkPagina;
    }

}