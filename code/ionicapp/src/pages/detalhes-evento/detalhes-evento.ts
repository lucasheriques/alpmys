import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { PayPal, PayPalPayment, PayPalConfiguration } from '@ionic-native/paypal';
import { RestProvider } from '../../providers/rest/rest';
import { NgIf } from '@angular/common';
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
  evento: any;
  ingresso: any;
  compra = { valor: '', dataCompra: '', ingressoId: '', usuarioId: '' };
  constructor(public navCtrl: NavController, public navParams: NavParams, private payPal: PayPal, public restProvider: RestProvider) {
    this.evento = navParams.get('evento');
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad DetalhesEventoPage');
  }

  comprar(ingresso) {
    this.payPal.init({
      PayPalEnvironmentProduction: 'YOUR_PRODUCTION_CLIENT_ID',
      PayPalEnvironmentSandbox: 'AZDxjDScFpQtjWTOUtWKbyN_bDt4OgqaF4eYXlewfBP4-8aqX3PiV8e1GWU6liB2CUXlkA59kJXE7M6R'
    }).then(() => {
      // Environments: PayPalEnvironmentNoNetwork, PayPalEnvironmentSandbox, PayPalEnvironmentProduction
      this.payPal.prepareToRender('PayPalEnvironmentSandbox', new PayPalConfiguration({
        // Only needed if you get an "Internal Service Error" after PayPal login!
        //payPalShippingAddressOption: 2 // PayPalShippingAddressOptionPayPal
      })).then(() => {
        let payment = new PayPalPayment(ingresso.valor, 'BRL', 'Description', 'sale');
        this.payPal.renderSinglePaymentUI(payment).then((response) => {
          console.log("Response id" + response.response.id + "\nresponse state" + response.response.state + "\ncliente plataform" + response.client.plataform+" create Time "+response.response.create_time);
          this.compra.ingressoId = ingresso.id;
          this.compra.dataCompra = response.create_time;
          this.compra.valor = ingresso.valor;
          this.compra.usuarioId = '1';
          this.postCompra(this.compra);
          // Successfully paid

          // Example sandbox response
          //
          // {
          //   "client": {
          //     "environment": "sandbox",
          //     "product_name": "PayPal iOS SDK",
          //     "paypal_sdk_version": "2.16.0",
          //     "platform": "iOS"
          //   },
          //   "response_type": "payment",
          //   "response": {
          //     "id": "PAY-1AB23456CD789012EF34GHIJ",
          //     "state": "approved",
          //     "create_time": "2016-10-03T13:33:33Z",
          //     "intent": "sale"
          //   }
          // }
        }, () => {
          console.log("Erro pra renderizar");
          ingresso.disponivel = true;
          this.editIngresso(ingresso.id, ingresso);
          // Error or render dialog closed without being successful
        });
      }, () => {
        ingresso.disponivel = true;
        this.editIngresso(ingresso.id, ingresso);
        // Error in configuration
      });
    }, () => {
      ingresso.disponivel = true;
      this.editIngresso(ingresso.id, ingresso);
      // Error in initialization, maybe PayPal isn't supported or something else
    });
    console.log("Passou");
  }
  postCompra(compra) {
    this.restProvider.postCompra(compra).then((result) => {
      console.log(result);
      this.navCtrl.pop();
    }, (err) => {
      console.log(err);
    });

  }
  getEventoTipoIngresso(id, tipoIngresso) {
    this.restProvider.getEventoTipoIngresso(id, tipoIngresso)
      .then(data => {
        this.ingresso = data;
        console.log("tipo ingresso\t" + this.ingresso.tipoIngreso + " valor\t" + this.ingresso.valor + " id\t" + this.ingresso.id);
        this.comprar(this.ingresso);
      });
  }
  editIngresso(id, ingresso) {
    this.restProvider.editIngresso(id, ingresso).then((result) => {
      console.log("editou");
    }, (err) => {
      console.log(err);
    });
  }
}
