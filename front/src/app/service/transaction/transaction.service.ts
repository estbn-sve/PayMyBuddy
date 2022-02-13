import {EventEmitter, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable, Subject} from "rxjs";
import {Transaction} from "../../data/transaction";
import {User} from "../../data/user";
import {AuthService} from "../auth/auth.service";

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

  constructor(private httpClient: HttpClient,
              private authService:AuthService) {}

  emitTransactionSubject(){
    this.transactionSubject.next(this.transaction.slice());
  }

  getTransactionsToBdd(id:number){
    console.log("GET Transaction")
    this.httpClient.get<Transaction[]>(`${this.baseUrl}`+"/sendTransaction/"+id)
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
    //TODO a metre dans le back
    this.soldeApp = soldeFrom*0.005;
    this.soldeTo = soldeFrom-this.soldeApp;
    console.log("Post new Transaction")
    console.log(`${this.baseUrl}`+this.request+'/')
    this.httpClient.post<Transaction>(`${this.baseUrl}`+'/newTransaction/', {
      id_user_from: this.authService.user.id,
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
