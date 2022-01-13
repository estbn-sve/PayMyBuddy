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


@NgModule({
  declarations: [
    AppComponent,
    TransactionComponent,
    HistoriqueComponent,
    InComponent,
    MenuComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot([
      {path: 'transaction', component: TransactionComponent}
      // {path: 'profil', component: Pr}
    ], {useHash: true})
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
