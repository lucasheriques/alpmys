import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

/*
  Generated class for the RestProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class RestProvider {
  apiUrl = 'http://localhost:64524/api';
  constructor(public http: HttpClient) {
    console.log('Hello RestProvider Provider');
  }
  getEventos() {
    return new Promise(resolve => {
      this.http.get(this.apiUrl+'/Eventos').subscribe(data => {
        resolve(data);
      }, err => {
        console.log(err);
      });
    });
  }
  getUsuario(id) {
    return new Promise(resolve => {
      this.http.get(this.apiUrl+'/Usuarios/'+id).subscribe(data => {
        resolve(data);
      }, err => {
        console.log(err);
      });
    });
  }
  postEventos(data) {
    return new Promise((resolve, reject) => {
      this.http.post(this.apiUrl+'/Eventos', JSON.stringify(data),{
        headers: new HttpHeaders().set('Content-Type', 'application/json'),
      })
        .subscribe(res => {
          resolve(res);
        }, (err) => {
          reject(err);
        });
    });
  }
}
