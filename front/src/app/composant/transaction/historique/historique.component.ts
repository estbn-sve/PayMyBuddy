import {Component, Input, OnInit} from '@angular/core';
import {Transaction} from "../../../data/transaction";
import {TransactionService} from "../../../service/transaction/transaction.service";
import {Observable, Subscription} from "rxjs";


@Component({
  selector: 'app-historique',
  templateUrl: './historique.component.html',
  styleUrls: ['../transaction.component.css']
})
export class HistoriqueComponent implements OnInit {
  @Input() newTransactionEvent: Observable<void> = new Observable<void>();

  transactions : Transaction[] = []

  transactionSubscription: Subscription = new Subscription();

  constructor(private transactionService: TransactionService) {
  }

  ngOnInit(): void {
    this.getTransaction();
    this.newTransactionEvent.subscribe(()=>this.getTransaction())
   // this.getTransaction()
  }

  // private getTransaction() {
  //   this.transactionService.getTransactionsToBdd()
  //     .subscribe(data => {
  //         this.transactions=data
  //         console.log("data reçus")
  //       },error => {
  //         alert("Erreur : " + error)
  //         console.log("Erreur : " + error)
  //       }
  //     )
  // }

  private getTransaction(){
    this.transactionSubscription = this.transactionService.transactionSubject.subscribe(
      (transactions: Transaction[])=>{
        this.transactions = transactions;
      }
    );
    //this.transactionService.emitTransactionSubject();
    this.transactionService.getTransactionsToBdd()
  }

}
