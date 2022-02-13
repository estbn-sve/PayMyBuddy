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
    this.transactionService.getTransactionsToBdd(this.authService.user.id)
  }

}
