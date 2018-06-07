import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { RestProvider } from '../../providers/rest/rest';
import { DetalhesIngressoPage } from '../detalhes-ingresso/detalhes-ingresso';

/**
 * Generated class for the IngressosPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@Component({
  selector: 'page-ingressos',
  templateUrl: 'ingressos.html',
})
export class IngressosPage {
usuario:any;
  constructor(public navCtrl: NavController, public navParams: NavParams,public restProvider:RestProvider) {
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad IngressosPage');
  }
  ionViewDidEnter() {
    this.getUsuario(1);
  }
  getUsuario(id) {

    this.restProvider.getUsuario(id)
      .then(data => {
        this.usuario = data;
        console.log(this.usuario);
      });
  }
  detalhesIngresso(compra){
    this.navCtrl.push(DetalhesIngressoPage, {
      compra:compra
    });
  }
}
