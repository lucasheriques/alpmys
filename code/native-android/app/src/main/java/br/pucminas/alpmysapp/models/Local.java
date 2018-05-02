package br.pucminas.alpmysapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Local {

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
        this.descricao = descricao;
    }

}