import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { TransactionComponent } from './composant/transaction/transaction.component';
import { HttpClientModule } from "@angular/common/http";
import { FormsModule} from "@angular/forms";
import { HistoriqueComponent } from './composant/transaction/historique/historique.component';
import { InComponent } from './composant/transaction/in/in.component';
import { MenuComponent } from './composant/menu/menu.component';
import {RouterModule} from "@angular/router";
import { AuthComponent } from './composant/auth/auth.component';
import {ReactiveFormsModule} from "@angular/forms";
import { OutComponent } from './composant/transaction/out/out.component';
import { ProfilComponent } from './composant/profil/profil.component';


@NgModule({
  declarations: [
    AppComponent,
    TransactionComponent,
    HistoriqueComponent,
    InComponent,
    MenuComponent,
    AuthComponent,
    OutComponent,
    ProfilComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot([
      {path: 'transaction', component: TransactionComponent},
      {path: 'auth', component: AuthComponent},
      {path: 'out', component: OutComponent},
      {path: 'profil', component: ProfilComponent}
    ], {useHash: true})
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
