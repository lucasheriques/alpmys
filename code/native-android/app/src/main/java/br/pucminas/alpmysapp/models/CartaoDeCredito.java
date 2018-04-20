package br.pucminas.alpmysapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartaoDeCredito {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("bandeira")
    @Expose
    private String bandeira;
    @SerializedName("numeroDoCartao")
    @Expose
    private String numeroDoCartao;
    @SerializedName("nomeImpressoCartao")
    @Expose
    private String nomeImpressoCartao;
    @SerializedName("validade")
    @Expose
    private String validade;
    @SerializedName("codigoDeSeguranca")
    @Expose
    private String codigoDeSeguranca;
    @SerializedName("compraID")
    @Expose
    private Integer compraID;
    @SerializedName("compra")
    @Expose
    private Compra compra;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getNumeroDoCartao() {
        return numeroDoCartao;
    }

    public void setNumeroDoCartao(String numeroDoCartao) {
        this.numeroDoCartao = numeroDoCartao;
    }

    public String getNomeImpressoCartao() {
        return nomeImpressoCartao;
    }

    public void setNomeImpressoCartao(String nomeImpressoCartao) {
        this.nomeImpressoCartao = nomeImpressoCartao;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getCodigoDeSeguranca() {
        return codigoDeSeguranca;
    }

    public void setCodigoDeSeguranca(String codigoDeSeguranca) {
        this.codigoDeSeguranca = codigoDeSeguranca;
    }

    public Integer getCompraID() {
        return compraID;
    }

    public void setCompraID(Integer compraID) {
        this.compraID = compraID;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }
}