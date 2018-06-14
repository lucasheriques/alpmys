import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { RestProvider } from '../../providers/rest/rest';

/**
 * Generated class for the EventoComprasPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@Component({
  selector: 'page-evento-compras',
  templateUrl: 'evento-compras.html',
})
export class EventoComprasPage {
  comprasApi: any;
  compras:any;
  constructor(public navCtrl: NavController, public navParams: NavParams, public restProvider: RestProvider) {
    this.getComprasEvento(navParams.get("id"));
    
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad EventoComprasPage');
  }
  getComprasEvento(id) {
    this.restProvider.getComprasEvento(id)
      .then(data => {
        this.comprasApi = data;
        this.compras=this.comprasApi;
        console.log(this.comprasApi);
      });
  }
  initializeItems() {
    this.compras= this.comprasApi;
  }
  getItems(ev: any) {
    // Reset items back to all of the items
    this.initializeItems();

    // set val to the value of the searchbar
    const val = ev.target.value;

    // if the value is an empty string don't filter the items
    if (val && val.trim() != '') {
      this.compras = this.compras.filter((item) => {
        return (item.usuario.nome.toLowerCase().indexOf(val.toLowerCase()) > -1);
      })
    }
  }

}
