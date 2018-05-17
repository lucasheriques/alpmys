package br.pucminas.alpmysapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.io.Serializable;

public class Evento implements Serializable{

    @SerializedName("id")
@Expose
private Long id;
@SerializedName("nome")
@Expose
private String nome;
@SerializedName("descricao")
@Expose
private String descricao;
@SerializedName("data")
@Expose
private String data;
@SerializedName("horarioInicio")
@Expose
private String horarioInicio;
@SerializedName("horarioTermino")
@Expose
private String horarioTermino;
@SerializedName("linkPagina")
@Expose
private String linkPagina;
@SerializedName("localId")
@Expose
private Long localId;
@SerializedName("local")
@Expose
private Local local;
@SerializedName("organizadorId")
@Expose
private Long organizadorId;
@SerializedName("organizador")
@Expose
private Organizador organizador;
@SerializedName("tipoDeEventoId")
@Expose
private Long tipoDeEventoId;
@SerializedName("tipoDeEvento")
@Expose
private TipoDeEvento tipoDeEvento;
@SerializedName("tipoIngressos")
@Expose
private List<TipoIngresso> tipoIngressos = null;

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
this.nome = nome;
}

public String getDescricao() {
return descricao;
}

public void setDescricao(String descricao) {
this.descricao = descricao;
}

public String getData() {
return data;
}

public void setData(String data) {
this.data = data;
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
this.linkPagina = linkPagina;
}

public Long getLocalId() {
return localId;
}

public void setLocalId(Long localId) {
this.localId = localId;
}

public Local getLocal() {
return local;
}

public void setLocal(Local local) {
this.local = local;
}

public Long getOrganizadorId() {
return organizadorId;
}

public void setOrganizadorId(Long organizadorId) {
this.organizadorId = organizadorId;
}

public Organizador getOrganizador() {
return organizador;
}

public void setOrganizador(Organizador organizador) {
this.organizador = organizador;
}

public Long getTipoDeEventoId() {
return tipoDeEventoId;
}

public void setTipoDeEventoId(Long tipoDeEventoId) {
this.tipoDeEventoId = tipoDeEventoId;
}

public TipoDeEvento getTipoDeEvento() {
return tipoDeEvento;
}

public void setTipoDeEvento(TipoDeEvento tipoDeEvento) {
this.tipoDeEvento = tipoDeEvento;
}

public List<TipoIngresso> getTipoIngressos() {
return tipoIngressos;
}

public void setTipoIngressos(List<TipoIngresso> tipoIngressos) {
this.tipoIngressos = tipoIngressos;
}

}
