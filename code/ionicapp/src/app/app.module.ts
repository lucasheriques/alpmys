import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';

import { MyApp } from './app.component';
import { HomePage } from '../pages/home/home';
import { ListPage } from '../pages/list/list';

import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { RestProvider } from '../providers/rest/rest';
import { HttpClientModule } from '@angular/common/http';
import { DetalhesEventoPage } from '../pages/detalhes-evento/detalhes-evento';
import { MeusEventosPage } from '../pages/meus-eventos/meus-eventos';
import { CadastrarEventoPage } from '../pages/cadastrar-evento/cadastrar-evento';
import { DetalhesMeusEventoPage } from '../pages/detalhes-meus-evento/detalhes-meus-evento';
import { PayPal, PayPalPayment, PayPalConfiguration } from '@ionic-native/paypal';
import { IngressosPage } from '../pages/ingressos/ingressos';
import { DetalhesIngressoPage } from '../pages/detalhes-ingresso/detalhes-ingresso';
import { EventoComprasPage } from '../pages/evento-compras/evento-compras';

@NgModule({
  declarations: [
    MyApp,
    HomePage,
    ListPage,
    DetalhesEventoPage,
    MeusEventosPage,
    CadastrarEventoPage,
    DetalhesMeusEventoPage,
    IngressosPage,
    DetalhesIngressoPage,
    EventoComprasPage
  ],
  imports: [
    BrowserModule,
    IonicModule.forRoot(MyApp),
    HttpClientModule
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    HomePage,
    ListPage,
    DetalhesEventoPage,
    MeusEventosPage,
    CadastrarEventoPage,
    DetalhesMeusEventoPage,
    IngressosPage,
    DetalhesIngressoPage,
    EventoComprasPage
  ],
  providers: [
    StatusBar,
    SplashScreen,
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    RestProvider,
    PayPal
  ]
})
export class AppModule {}
