package br.pucminas.alpmysapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Compra {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("valorCompra")
    @Expose
    private Integer valorCompra;
    @SerializedName("quantidadeIngressos")
    @Expose
    private Integer quantidadeIngressos;
    @SerializedName("valorUnitario")
    @Expose
    private Integer valorUnitario;
    @SerializedName("dataCompra")
    @Expose
    private String dataCompra;
    @SerializedName("dataVencimento")
    @Expose
    private String dataVencimento;
    @SerializedName("formaPagamento")
    @Expose
    private String formaPagamento;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("ingressoId")
    @Expose
    private Integer ingressoId;
    @SerializedName("ingresso")
    @Expose
    private Ingresso ingresso;
    @SerializedName("usuarioId")
    @Expose
    private Integer usuarioId;
    @SerializedName("usuario")
    @Expose
    private Usuario usuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(Integer valorCompra) {
        this.valorCompra = valorCompra;
    }

    public Integer getQuantidadeIngressos() {
        return quantidadeIngressos;
    }

    public void setQuantidadeIngressos(Integer quantidadeIngressos) {
        this.quantidadeIngressos = quantidadeIngressos;
    }

    public Integer getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Integer valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(String dataCompra) {
        this.dataCompra = dataCompra;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIngressoId() {
        return ingressoId;
    }

    public void setIngressoId(Integer ingressoId) {
        this.ingressoId = ingressoId;
    }

    public Ingresso getIngresso() {
        return ingresso;
    }

    public void setIngresso(Ingresso ingresso) {
        this.ingresso = ingresso;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}