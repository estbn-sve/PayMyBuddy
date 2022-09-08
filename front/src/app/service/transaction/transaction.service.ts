import {EventEmitter, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable, Subject} from "rxjs";
import {Transaction} from "../../data/transaction";
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
          console.log('GET ok ',response);
          this.transaction = response;
          this.emitTransactionSubject();
        }, error : e => {
          console.error('GET Error : '+e);
        }
      })
  }

  newSendToBdd(idTo:String, soldeFrom:number, event:EventEmitter<void>){
    console.log("Post new Transaction")
    console.log(`${this.baseUrl}`+this.request+'/')
    return this.httpClient.post<Transaction>(`${this.baseUrl}`+'/newTransaction/', {
      id_user_from: this.authService.user.id,
      // @ts-ignore
      id_user_to: parseFloat(idTo),
      solde_from:soldeFrom
    });
  }
  sendOut(id:number, montant:number):Observable<any>{
    console.log("Post new send Out")
    return this.httpClient.post(`${this.baseUrl}`+'/sendOut/',{
      id:id,
      montant:montant
    })
  }
  addOut(id:number, montant:number):Observable<any>{
    console.log("Post new Add Out")
    return this.httpClient.post(`${this.baseUrl}`+'/addOut/',{
      id:id,
      montant:montant
    })
  }
}
