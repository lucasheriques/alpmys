package br.pucminas.alpmysapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Promocao {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("quantidadeIngressos")
    @Expose
    private Integer quantidadeIngressos;
    @SerializedName("porcentagemDeDesconto")
    @Expose
    private Integer porcentagemDeDesconto;
    @SerializedName("codigoPromocional")
    @Expose
    private String codigoPromocional;
    @SerializedName("organizadorId")
    @Expose
    private Integer organizadorId;
    @SerializedName("organizador")
    @Expose
    private Organizador organizador;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantidadeIngressos() {
        return quantidadeIngressos;
    }

    public void setQuantidadeIngressos(Integer quantidadeIngressos) {
        this.quantidadeIngressos = quantidadeIngressos;
    }

    public Integer getPorcentagemDeDesconto() {
        return porcentagemDeDesconto;
    }

    public void setPorcentagemDeDesconto(Integer porcentagemDeDesconto) {
        this.porcentagemDeDesconto = porcentagemDeDesconto;
    }

    public String getCodigoPromocional() {
        return codigoPromocional;
    }

    public void setCodigoPromocional(String codigoPromocional) {
        this.codigoPromocional = codigoPromocional;
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

}
