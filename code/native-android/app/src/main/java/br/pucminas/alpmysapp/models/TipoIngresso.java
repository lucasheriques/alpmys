package br.pucminas.alpmysapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TipoIngresso {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("valor")
    @Expose
    private Integer valor;
    @SerializedName("tipo")
    @Expose
    private String tipo;
    @SerializedName("taxa")
    @Expose
    private Integer taxa;
    @SerializedName("quantidade")
    @Expose
    private Integer quantidade;
    @SerializedName("numeroLote")
    @Expose
    private Integer numeroLote;
    @SerializedName("dataInicio")
    @Expose
    private String dataInicio;
    @SerializedName("ingressosRestante")
    @Expose
    private Integer ingressosRestante;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getTaxa() {
        return taxa;
    }

    public void setTaxa(Integer taxa) {
        this.taxa = taxa;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getNumeroLote() {
        return numeroLote;
    }

    public void setNumeroLote(Integer numeroLote) {
        this.numeroLote = numeroLote;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Integer getIngressosRestante() {
        return ingressosRestante;
    }

    public void setIngressosRestante(Integer ingressosRestante) {
        this.ingressosRestante = ingressosRestante;
    }

}
