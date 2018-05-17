package br.pucminas.alpmysapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class TipoIngresso {

@SerializedName("id")
@Expose
private Long id;
@SerializedName("valor")
@Expose
private Long valor;
@SerializedName("tipo")
@Expose
private String tipo;
@SerializedName("taxa")
@Expose
private Long taxa;
@SerializedName("quantidade")
@Expose
private Long quantidade;
@SerializedName("numeroLote")
@Expose
private Long numeroLote;
@SerializedName("dataInicio")
@Expose
private String dataInicio;
@SerializedName("ingressosRestante")
@Expose
private Long ingressosRestante;
@SerializedName("eventoId")
@Expose
private Long eventoId;
@SerializedName("evento")
@Expose
private Evento evento;
@SerializedName("ingressos")
@Expose
private List<Ingresso> ingressos = null;

public Long getId() {
return id;
}

public void setId(Long id) {
this.id = id;
}

public Long getValor() {
return valor;
}

public void setValor(Long valor) {
this.valor = valor;
}

public String getTipo() {
return tipo;
}

public void setTipo(String tipo) {
this.tipo = tipo;
}

public Long getTaxa() {
return taxa;
}

public void setTaxa(Long taxa) {
this.taxa = taxa;
}

public Long getQuantidade() {
return quantidade;
}

public void setQuantidade(Long quantidade) {
this.quantidade = quantidade;
}

public Long getNumeroLote() {
return numeroLote;
}

public void setNumeroLote(Long numeroLote) {
this.numeroLote = numeroLote;
}

public String getDataInicio() {
return dataInicio;
}

public void setDataInicio(String dataInicio) {
this.dataInicio = dataInicio;
}

public Long getIngressosRestante() {
return ingressosRestante;
}

public void setIngressosRestante(Long ingressosRestante) {
this.ingressosRestante = ingressosRestante;
}

public Long getEventoId() {
return eventoId;
}

public void setEventoId(Long eventoId) {
this.eventoId = eventoId;
}

public Evento getEvento() {
return evento;
}

public void setEvento(Evento evento) {
this.evento = evento;
}

public List<Ingresso> getIngressos() {
return ingressos;
}

public void setIngressos(List<Ingresso> ingressos) {
this.ingressos = ingressos;
}

}