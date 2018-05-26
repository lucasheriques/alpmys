import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

/**
 * Generated class for the DetalhesEventoPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@Component({
  selector: 'page-detalhes-evento',
  templateUrl: 'detalhes-evento.html',
  
})
export class DetalhesEventoPage {
  evento:any;
  constructor(public navCtrl: NavController, public navParams: NavParams) {
   this.evento= navParams.get('evento');
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad DetalhesEventoPage');
  }

}
