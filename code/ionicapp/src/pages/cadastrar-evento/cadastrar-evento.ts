import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { RestProvider } from '../../providers/rest/rest';
import { FormControl, FormGroup, Validators } from '@angular/forms';

/**
 * Generated class for the CadastrarEventoPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@Component({
  selector: 'page-cadastrar-evento',
  templateUrl: 'cadastrar-evento.html',
})
export class CadastrarEventoPage {
  evento = { nome: '', descricao: '', data: '', duracao: '', linkImagem: '', linkPagina: '', local: { nome: '', descricao: '', cep: '', rua: '', numero: '', complemento: '', cidade: '', uf: '' }, usuarioId: '1', ingressos: [] };
  eventoForm: FormGroup;
  quantidade;
  inputs: Array<{ tipoIngresso: '', quantidade: '', valor: '' }>;
  ingresso: Array<{ tipoIngreso: '', disponivel: boolean, valor: '' }>;
  constructor(public navCtrl: NavController, public navParams: NavParams, public restProvider: RestProvider) {

    if (navParams.get('evento') != null) {
      this.evento = navParams.get('evento');
    }
    this.inputs = [{ tipoIngresso: '', quantidade: '', valor: '' }];
    this.ingresso = [{ tipoIngreso: '', disponivel: true, valor: '' }];
    this.eventoForm = new FormGroup({
      nome: new FormControl('', [Validators.required, Validators.minLength(1), Validators.maxLength(50)]),
      descricao: new FormControl(''),
      data: new FormControl('', [Validators.required]),
      duracao: new FormControl('',[Validators.maxLength(10)]),
      linkImagem: new FormControl('', [Validators.required,Validators.pattern('(?:(?:(?:ht|f)tp)s?://)?[\\w_-]+(?:\\.[\\w_-]+)+([\\w.,@?^=%&:/~+#-]*[\\w@?^=%&/~+#-])?')]),
      linkPagina: new FormControl('', [Validators.pattern('(?:(?:(?:ht|f)tp)s?://)?[\\w_-]+(?:\\.[\\w_-]+)+([\\w.,@?^=%&:/~+#-]*[\\w@?^=%&/~+#-])?')]),
      local: new FormGroup({
        nome: new FormControl('', [Validators.required, Validators.minLength(1), Validators.maxLength(50)]),
        descricao: new FormControl(''),
        cep: new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(8)]),
        rua: new FormControl('', [Validators.required, Validators.minLength(1), Validators.maxLength(50)]),
        numero: new FormControl('', [Validators.required, Validators.minLength(1), Validators.maxLength(9)]),
        complemento: new FormControl(''),
        cidade: new FormControl('', [Validators.required, Validators.minLength(1), Validators.maxLength(40)]),
        uf: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z ]*'), Validators.minLength(2), Validators.maxLength(2)])
      }),
      ingressos: new FormGroup({
        tipoIngreso: new FormControl('', [Validators.required, Validators.minLength(1), Validators.maxLength(40)]),
        quantidade: new FormControl('', [Validators.required, Validators.minLength(1), Validators.maxLength(10)]),
        valor: new FormControl('', [Validators.required, Validators.minLength(1), Validators.maxLength(10)])
      })
    });
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad CadastrarEventoPage');
  }
  postEvento() {
    this.addIngressos();
    this.restProvider.postEventos(this.evento).then((result) => {
      console.log(result);
      this.navCtrl.pop();
    }, (err) => {
      console.log(err);
    });

  }
  addIngressos() {
    console.log(this.inputs.length);
    for (var i = 0; i < this.inputs.length; i++) {
      this.ingresso[i].tipoIngreso = this.inputs[i].tipoIngresso;
      this.ingresso[i].disponivel = true;
      this.ingresso[i].valor = this.inputs[i].valor;

      for (var j = 0; j < parseInt(this.inputs[i].quantidade); j++) {
        this.evento.ingressos.push(this.ingresso[i]);
      }
    }

  }
  addCampo() {
    this.inputs.push({ tipoIngresso: '', quantidade: '', valor: '' });
    this.ingresso.push({ tipoIngreso: '', disponivel: true, valor: '' });
  }
  deleteCampo() {
    this.inputs.pop();
    this.ingresso.pop();
  }
}
