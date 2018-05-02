package br.pucminas.alpmysapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Usuario {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("sobrenome")
    @Expose
    private String sobrenome;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("senha")
    @Expose
    private String senha;
    @SerializedName("organizador")
    @Expose
    private Boolean organizador;
    @SerializedName("cpf")
    @Expose
    private String cpf;
    @SerializedName("dataNascimento")
    @Expose
    private String dataNascimento;
    @SerializedName("cartaoDeCredito")
    @Expose
    private List<CartaoDeCredito> cartaoDeCredito = null;
    @SerializedName("pontosId")
    @Expose
    private Integer pontosId;
    @SerializedName("pontos")
    @Expose
    private Pontos pontos;
    @SerializedName("historico_EventosId")
    @Expose
    private Integer historicoEventosId;
    @SerializedName("historico_Eventos")
    @Expose
    private HistoricoEventos historicoEventos;
    @SerializedName("wishList")
    @Expose
    private WishList wishList;
    @SerializedName("wishListId")
    @Expose
    private Integer wishListId;

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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getOrganizador() {
        return organizador;
    }

    public void setOrganizador(Boolean organizador) {
        this.organizador = organizador;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<CartaoDeCredito> getCartaoDeCredito() {
        return cartaoDeCredito;
    }

    public void setCartaoDeCredito(List<CartaoDeCredito> cartaoDeCredito) {
        this.cartaoDeCredito = cartaoDeCredito;
    }

    public Integer getPontosId() {
        return pontosId;
    }

    public void setPontosId(Integer pontosId) {
        this.pontosId = pontosId;
    }

    public Pontos getPontos() {
        return pontos;
    }

    public void setPontos(Pontos pontos) {
        this.pontos = pontos;
    }

    public Integer getHistoricoEventosId() {
        return historicoEventosId;
    }

    public void setHistoricoEventosId(Integer historicoEventosId) {
        this.historicoEventosId = historicoEventosId;
    }

    public HistoricoEventos getHistoricoEventos() {
        return historicoEventos;
    }

    public void setHistoricoEventos(HistoricoEventos historicoEventos) {
        this.historicoEventos = historicoEventos;
    }

    public WishList getWishList() {
        return wishList;
    }

    public void setWishList(WishList wishList) {
        this.wishList = wishList;
    }

    public Integer getWishListId() {
        return wishListId;
    }

    public void setWishListId(Integer wishListId) {
        this.wishListId = wishListId;
    }

}
