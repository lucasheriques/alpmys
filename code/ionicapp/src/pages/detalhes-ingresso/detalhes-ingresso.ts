import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

/**
 * Generated class for the DetalhesIngressoPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@Component({
  selector: 'page-detalhes-ingresso',
  templateUrl: 'detalhes-ingresso.html',
})
export class DetalhesIngressoPage {
  compra:any;
  constructor(public navCtrl: NavController, public navParams: NavParams) {
    this.compra=navParams.get('compra');
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad DetalhesIngressoPage');
  }

}
