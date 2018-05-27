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
  evento = { nome: '', descricao: '', data: '' ,duracao:'',linkImagem:'',linkPagina:'',local:{nome:'',descricao:'',cep:'',rua:'',numero:'',complemento:'',cidade:'',uf:''},usuarioId:'1'};
  constructor(public navCtrl: NavController, public navParams: NavParams,public restProvider: RestProvider) {
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad CadastrarEventoPage');
  }
  postEvento(){
      this.restProvider.postEventos(this.evento).then((result) => {
        console.log(result);
      }, (err) => {
        console.log(err);
      });
    
  }
}
