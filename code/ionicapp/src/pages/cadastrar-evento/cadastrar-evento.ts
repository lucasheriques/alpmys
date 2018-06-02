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
  inputs: Array<{ tipoIngresso: '', quantidade: '', valor: '' }>;
  ingresso: Array<{ tipoIngreso: '', disponivel: boolean, valor: '' }>;
  constructor(public navCtrl: NavController, public navParams: NavParams, public restProvider: RestProvider) {
    console.log(navParams.get('evento'));
    if (navParams.get('evento') != null) {
      this.evento = navParams.get('evento');
      
    }
    this.inputs = [{ tipoIngresso: '', quantidade: '', valor: '' }];
    this.ingresso = [{ tipoIngreso: '', disponivel: true, valor: '' }];
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
    console.log(this.inputs.length);
    for (var i = 0; i < this.inputs.length; i++) {
      this.ingresso[i].tipoIngreso = this.inputs[i].tipoIngresso;
      this.ingresso[i].disponivel = true;
      this.ingresso[i].valor = this.inputs[i].valor;

      for (var j = 0; j < parseInt(this.inputs[i].quantidade); j++) {
        this.evento.ingressos.push(this.ingresso[i]);
      }
    }

  }
  addCampo() {
    this.inputs.push({ tipoIngresso: '', quantidade: '', valor: '' });
    this.ingresso.push({ tipoIngreso: '', disponivel: true, valor: '' });
  }
  deleteCampo() {
    this.inputs.pop();
    this.ingresso.pop();
  }
}
