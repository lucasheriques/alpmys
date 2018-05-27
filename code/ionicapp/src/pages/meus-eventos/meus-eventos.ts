import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { RestProvider } from '../../providers/rest/rest';
import { DetalhesEventoPage } from '../detalhes-evento/detalhes-evento';
import { CadastrarEventoPage } from '../cadastrar-evento/cadastrar-evento';
/**
 * Generated class for the MeusEventosPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@Component({
  selector: 'page-meus-eventos',
  templateUrl: 'meus-eventos.html',
})
export class MeusEventosPage {
  usuario: any;
  constructor(public navCtrl: NavController, public navParams: NavParams, public restProvider: RestProvider) {
    this.getUsuario(1);
    console.log("usuario nome"+this.usuario.nome)
  }
  getUsuario(id) {
    this.restProvider.getUsuario(id)
      .then(data => {
        this.usuario = data;
        console.log(this.usuario);
      });
  }
  itemTapped(event, evento) {
    // That's right, we're pushing to ourselves!
    this.navCtrl.push(DetalhesEventoPage, {
      evento: evento
    });
  }
  addEvento(event) {
    // That's right, we're pushing to ourselves!
    this.navCtrl.push(CadastrarEventoPage, {
    });
  }
  ionViewDidLoad() {
    console.log('ionViewDidLoad MeusEventosPage');
  }


}
