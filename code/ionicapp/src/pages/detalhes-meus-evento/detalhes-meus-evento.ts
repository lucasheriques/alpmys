import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { EventoComprasPage } from '../evento-compras/evento-compras';

/**
 * Generated class for the DetalhesMeusEventoPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@Component({
  selector: 'page-detalhes-meus-evento',
  templateUrl: 'detalhes-meus-evento.html',
})
export class DetalhesMeusEventoPage {
  usuario:any
  evento:any;
  constructor(public navCtrl: NavController, public navParams: NavParams) {
    this.usuario=navParams.get('usuario');
    this.evento=navParams.get('evento');
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad DetalhesMeusEventoPage');
  }
  comprasEvento() {
    // That's right, we're pushing to ourselves!
    this.navCtrl.push(EventoComprasPage, {
      id:this.evento.id
    });

  }

}
