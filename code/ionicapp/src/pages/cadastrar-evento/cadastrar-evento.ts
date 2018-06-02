import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { RestProvider } from '../../providers/rest/rest';

/**
 * Generated class for the CadastrarEventoPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@Component({
  selector: 'page-cadastrar-evento',
  templateUrl: 'cadastrar-evento.html',
})
export class CadastrarEventoPage {
  evento = { nome: '', descricao: '', data: '', duracao: '', linkImagem: '', linkPagina: '', local: { nome: '', descricao: '', cep: '', rua: '', numero: '', complemento: '', cidade: '', uf: '' }, usuarioId: '1', ingressos: [] };
  quantidade;
  inputs:any;
  ingresso = { tipoIngreso: '', disponivel: 'true', valor: '' };
  constructor(public navCtrl: NavController, public navParams: NavParams, public restProvider: RestProvider) {
    console.log(navParams.get('evento'));
    if(navParams.get('evento')!=null){
      this.evento=navParams.get('evento');
    }
    this.inputs = [{ tipoIngresso: "Tipo Ingresso", quantidade: "Quantidade", valor: "Valor" }];

  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad CadastrarEventoPage');
  }
  postEvento() {
    this.addIngressos();
    this.restProvider.postEventos(this.evento).then((result) => {
      console.log(result);
      this.navCtrl.pop();
    }, (err) => {
      console.log(err);
    });

  }
  addIngressos() {
    for (var i = 0; i < this.quantidade; i++) {
      this.evento.ingressos[i] = this.ingresso;

    }
  }
  addCampo() {
    this.inputs.push({ tipoIngresso: "Tipo Ingresso", quantidade: "Quantidade", valor: "Valor" });
}
}
