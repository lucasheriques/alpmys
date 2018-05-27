import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { RestProvider } from '../../providers/rest/rest';
import { DetalhesEventoPage } from '../detalhes-evento/detalhes-evento';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {
  eventos:any;
  constructor(public navCtrl: NavController,public restProvider: RestProvider) {
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

}
