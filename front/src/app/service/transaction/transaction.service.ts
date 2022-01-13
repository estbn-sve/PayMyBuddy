import {EventEmitter, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable, Subject} from "rxjs";
import {Transaction} from "../../data/transaction";
import {User} from "../../data/user";

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  transactionSubject = new Subject<Transaction[]>();

  private transaction : Transaction [] = [];


  private baseUrl = environment.baseUrl;
  private request = "/transaction";
  private soldeTo:number=0;
  private soldeApp:number=0;

  constructor(private httpClient: HttpClient) {}

  emitTransactionSubject(){
    this.transactionSubject.next(this.transaction.slice());
  }

  getTransactionsToBdd(){
    console.log("GET all Transaction")
    this.httpClient.get<Transaction[]>(`${this.baseUrl}`+this.request)
      .subscribe({
        next : response => {
          console.log('GET ok '+response);
          this.transaction = response;
          this.emitTransactionSubject();
        }, error : e => {
          console.error('GET Error : '+e);
        }
      })
  }

  newSendToBdd(idTo:String, soldeFrom:number, event:EventEmitter<void>){
    this.soldeApp = soldeFrom*0.05;
    this.soldeTo = soldeFrom-this.soldeApp;
    console.log("Post new Transaction")
    console.log(`${this.baseUrl}`+this.request+'/')
    this.httpClient.post<Transaction>(`${this.baseUrl}`+'/newTransaction/', {
      id_user_from: "1",
      id_user_to: idTo,
      solde_from:soldeFrom,
      solde_to:this.soldeTo,
      solde_app:this.soldeApp
    }).subscribe({
      next: data => {
      alert(data)
        console.log("data envoyer : \n"+data)
        event.emit();
      },
      error: error => {
        alert("Erreur : " + error)
        console.error("Erreur : " + error)
      }}
    )
    //   .subscribe(data =>{
    //   this.postId = data.id;
    // })
  }
}