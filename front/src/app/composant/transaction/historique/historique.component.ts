import {Component, Input, OnInit} from '@angular/core';
import {Transaction} from "../../../data/transaction";
import {TransactionService} from "../../../service/transaction/transaction.service";
import {Observable, Subscription} from "rxjs";
import {AuthService} from "../../../service/auth/auth.service";


@Component({
  selector: 'app-historique',
  templateUrl: './historique.component.html',
  styleUrls: ['../transaction.component.css']
})
export class HistoriqueComponent implements OnInit {
  @Input() newTransactionEvent: Observable<void> = new Observable<void>();

  user = this.authService.user;

  transactions : Transaction[] = []

  transactionSubscription: Subscription = new Subscription();

  constructor(private transactionService: TransactionService,
              private authService : AuthService) {
  }

  ngOnInit(): void {
    this.getTransaction();
    this.newTransactionEvent.subscribe(()=>this.getTransaction())
  }

  private getTransaction(){
    this.transactionSubscription = this.transactionService.transactionSubject.subscribe(
      (transactions: Transaction[])=>{
        this.transactions = transactions;
      }
    );
    this.transactionService.getTransactionsToBdd(this.user.id)
  }

  isCredit(transaction:Transaction):Boolean{
    if(transaction.id_user_from.id == null){
      return false;
    } else if (transaction.id_user_to.id == null){
      return true;
    }else {
      return transaction.id_user_from.id != this.user.id;
    }
  }

}
