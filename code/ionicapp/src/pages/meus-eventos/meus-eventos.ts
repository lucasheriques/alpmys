import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { RestProvider } from '../../providers/rest/rest';
import { DetalhesEventoPage } from '../detalhes-evento/detalhes-evento';
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
  eventos:any;
  constructor(public navCtrl: NavController, public navParams: NavParams,public restProvider: RestProvider) {
  this.getEventos();
  }
  getEventos() {
    this.restProvider.getEventos()
    .then(data => {
      this.eventos = data;
      console.log(this.eventos);
    });
  }
  itemTapped(event, evento) {
    // That's right, we're pushing to ourselves!
    this.navCtrl.push(DetalhesEventoPage, {
      evento: evento
    });
  }
  ionViewDidLoad() {
    console.log('ionViewDidLoad MeusEventosPage');
  }
  

}
