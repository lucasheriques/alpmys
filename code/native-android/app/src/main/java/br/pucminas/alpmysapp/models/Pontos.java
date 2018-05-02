package br.pucminas.alpmysapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pontos {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("valor")
    @Expose
    private Integer valor;
    @SerializedName("pontosAExpirar")
    @Expose
    private Integer pontosAExpirar;
    @SerializedName("dataVencimento")
    @Expose
    private String dataVencimento;

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

    public Integer getPontosAExpirar() {
        return pontosAExpirar;
    }

    public void setPontosAExpirar(Integer pontosAExpirar) {
        this.pontosAExpirar = pontosAExpirar;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

}
