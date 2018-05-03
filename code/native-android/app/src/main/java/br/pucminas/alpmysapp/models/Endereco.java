package br.pucminas.alpmysapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Endereco {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("logradouro")
    @Expose
    private String logradouro;
    @SerializedName("numero")
    @Expose
    private int numero;
    @SerializedName("complemento")
    @Expose
    private String complemento;
    @SerializedName("bairro")
    @Expose
    private String bairro;
    @SerializedName("cidade")
    @Expose
    private String cidade;
    @SerializedName("uf")
    @Expose
    private String uf;
    @SerializedName("cep")
    @Expose
    private String cep;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        if(logradouro.isEmpty())
            throw new IllegalArgumentException();
        else
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
      if(complemento.isEmpty())
          throw new IllegalArgumentException();
      else
      this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        if(bairro.isEmpty())
            throw new IllegalArgumentException();
        else
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        if(cidade.isEmpty())
            throw  new IllegalArgumentException();
        else
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        if(uf.isEmpty())
            throw new IllegalArgumentException();
        else
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        if(cep.isEmpty())
            throw  new IllegalArgumentException();
        this.cep = cep;
    }
}
