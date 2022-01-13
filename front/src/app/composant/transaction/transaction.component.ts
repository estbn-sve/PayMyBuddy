import { Component, OnInit } from '@angular/core';
import {Subject} from "rxjs";

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent implements OnInit {

  newTransactionEvent : Subject<void> = new Subject<void>();

  constructor() {
  }

  ngOnInit(): void {
  }

  newAddTransaction(){
    this.newTransactionEvent.next();
  }


}
