package br.pucminas.alpmysapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Local implements Serializable{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("capacidadeDePessoas")
    @Expose
    private Integer capacidadeDePessoas;
    @SerializedName("descricao")
    @Expose
    private String descricao;
    @SerializedName("enderecoId")
    @Expose
    private Integer enderecoId;
    @SerializedName("endereco")
    @Expose
    private Endereco endereco;
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

    public Integer getCapacidadeDePessoas() {
        return capacidadeDePessoas;
    }

    public void setCapacidadeDePessoas(Integer capacidadeDePessoas) {
        this.capacidadeDePessoas = capacidadeDePessoas;
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

    public Integer getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(Integer enderecoId) {
        this.enderecoId = enderecoId;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}