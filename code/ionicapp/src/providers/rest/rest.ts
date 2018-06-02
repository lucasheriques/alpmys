import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ToastController, LoadingController } from 'ionic-angular';

/*
  Generated class for the RestProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class RestProvider {
  apiUrl = 'http://192.168.1.9:45458/api';
  constructor(public http: HttpClient, private toastCtrl: ToastController,public loadingCtrl: LoadingController) {
    console.log('Hello RestProvider Provider');
  }
  deleteEvento(id) {
    return new Promise(resolve => {
      this.http.delete(this.apiUrl + '/Eventos/' + id).subscribe(data => {
        resolve(data);
        this.showToast("Evento deletado com sucesso");
      }, err => {
        this.showToast("Erro ao tentar deletar um evento");
        console.log(err);
      });
    });
  }
  getEventos() {
    let loader = this.loadingCtrl.create({
      content: "Carregando... Por favor Espere "
    });
    loader.present();
    return new Promise(resolve => {
      this.http.get(this.apiUrl + '/Eventos').subscribe(data => {
        resolve(data);
        loader.dismiss();
      }, err => {
        loader.dismiss();
        this.showToast("Falha ao conectar com Api.Por favor tente mais tarde");
        console.log(err);
      });
    });
  }
  postEventos(data) {
    let loader = this.loadingCtrl.create({
      content: "Enviando...Por favor Espere "
    });
    loader.present();
    return new Promise((resolve, reject) => {
      this.http.post(this.apiUrl + '/Eventos', JSON.stringify(data), {
        headers: new HttpHeaders().set('Content-Type', 'application/json'),
      })
        .subscribe(res => {
          loader.dismiss();
          resolve(res);
          this.showToast("Evento criado com sucesso");
        }, (err) => {
          loader.dismiss();
          this.showToast("Erro ao tentar criar um evento"); 
          reject(err);

        });
    });
  }
  getUsuario(id) {
    let loader = this.loadingCtrl.create({
      content: "Carregando... Por favor Espere "
    });
    loader.present();
    return new Promise(resolve => {
      this.http.get(this.apiUrl + '/Usuarios/' + id).subscribe(data => {
        resolve(data);
        loader.dismiss();
      }, err => {
        console.log(err);
        loader.dismiss();
        this.showToast("Falha ao conectar com Api.Por favor tente mais tarde");
      });
    });
  }
  showToast(message){
    let toast = this.toastCtrl.create({
      message: message,
      duration: 3000,
      position: 'bottom'
    });
    toast.present();
  }

}
