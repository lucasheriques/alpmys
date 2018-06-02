import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { RestProvider } from '../../providers/rest/rest';
import { DetalhesEventoPage } from '../detalhes-evento/detalhes-evento';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {
  eventosApi:any;
  eventos: any;
  constructor(public navCtrl: NavController,public restProvider: RestProvider) {
    this.getEventos();
  }
  getEventos() {
    this.restProvider.getEventos()
    .then(data => {
      this.eventosApi = data;
      this.eventos=data;
      console.log(this.eventos);
    });
  }
  itemTapped(event, evento) {
    // That's right, we're pushing to ourselves!
    this.navCtrl.push(DetalhesEventoPage, {
      evento: evento
    });
  }
  initializeItems() {
    this.eventos = this.eventosApi;
  }
  getItems(ev: any) {
    // Reset items back to all of the items
    this.initializeItems();

    // set val to the value of the searchbar
    const val = ev.target.value;

    // if the value is an empty string don't filter the items
    if (val && val.trim() != '') {
      this.eventos = this.eventos.filter((item) => {
        return (item.nome.toLowerCase().indexOf(val.toLowerCase()) > -1);
      })
    }
  }
}


