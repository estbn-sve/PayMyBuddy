import { Component, OnInit } from '@angular/core';
import {Subject} from "rxjs";
import {AuthService} from "../../service/auth/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent implements OnInit {

  newTransactionEvent : Subject<void> = new Subject<void>();

  constructor(private authService : AuthService,
              private route : Router) {
  }

  ngOnInit(): void {
    if (this.authService.isLogin == false){
      this.route.navigate(["auth"])
    }
  }

  newAddTransaction(){
    this.newTransactionEvent.next();
  }


}
