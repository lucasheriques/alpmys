import { Component } from '@angular/core';
import { NavController, NavParams, AlertController, LoadingController } from 'ionic-angular';
import { RestProvider } from '../../providers/rest/rest';
import { DetalhesEventoPage } from '../detalhes-evento/detalhes-evento';
import { CadastrarEventoPage } from '../cadastrar-evento/cadastrar-evento';
import { DetalhesMeusEventoPage } from '../detalhes-meus-evento/detalhes-meus-evento';
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
  usuarioApi: any;
  usuario: any;
  constructor(public navCtrl: NavController, public navParams: NavParams, public restProvider: RestProvider, public alertCtrl: AlertController) {
    
  }
  getUsuario(id) {

    this.restProvider.getUsuario(id)
      .then(data => {
        this.usuarioApi = data;
        this.usuario = this.usuarioApi.eventos;
        console.log(this.usuarioApi);
      });
  }
  deleteEvento(id) {
    this.restProvider.deleteEvento(id)
      .then(data => {
        console.log(data);
        this.getUsuario(1);
      });
  }
  itemTapped(event, usuario, evento) {
    // That's right, we're pushing to ourselves!
    this.navCtrl.push(DetalhesMeusEventoPage, {
      usuario: usuario, evento: evento
    });
  }
  addEvento() {
    // That's right, we're pushing to ourselves!
    this.navCtrl.push(CadastrarEventoPage, {
      evento: null, edit: false
    });

  }
  
  editEvento(evento) {
    this.navCtrl.push(CadastrarEventoPage, {
      evento: evento, edit: true
    });
  }
  showConfirm(id) {
    const confirm = this.alertCtrl.create({
      title: 'Deletar Evento?',
      message: 'Você tem certeza que deseja apagar esse evento?',
      buttons: [
        {
          text: 'Discordo',
          handler: () => {
            console.log('Disagree clicked');
          }
        },
        {
          text: 'Concordo',
          handler: () => {
            this.deleteEvento(id);
            console.log('Agree clicked');
          }
        }
      ]
    });
    confirm.present();
  }
  ionViewDidLoad() {
    console.log('ionViewDidLoad MeusEventosPage');

  }
  ionViewDidEnter() {
    this.getUsuario(1);
  }
  initializeItems() {
    this.usuario = this.usuarioApi.eventos;
  }
  getItems(ev: any) {
    // Reset items back to all of the items
    this.initializeItems();

    // set val to the value of the searchbar
    const val = ev.target.value;

    // if the value is an empty string don't filter the items
    if (val && val.trim() != '') {
      this.usuario = this.usuario.filter((item) => {
        return (item.nome.toLowerCase().indexOf(val.toLowerCase()) > -1);
      })

    }
  }
}


