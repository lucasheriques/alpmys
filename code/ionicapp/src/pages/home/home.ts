import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { RestProvider } from '../../providers/rest/rest';

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

}
