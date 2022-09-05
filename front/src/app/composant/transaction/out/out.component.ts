import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {AuthService} from "../../../service/auth/auth.service";
import {Router} from "@angular/router";
import {Subscription} from "rxjs";
import {User} from "../../../data/user";
import {Contact} from "../../../data/contact";
import {ContactService} from "../../../service/contact/contact.service";
import {TransactionService} from "../../../service/transaction/transaction.service";

@Component({
  selector: 'app-out',
  templateUrl: './out.component.html',
  styleUrls: ['../transaction.component.css']
})
export class OutComponent implements OnInit {

  @Output()transactionAddEvent: EventEmitter<void>= new EventEmitter<void>();

  contactSubscription : Subscription = new Subscription();
  user: User = this.authService.user;
  contacts : Contact[]  = [];
  to:String="";
  montant:number= 0;
  event:any;

  constructor(private authService : AuthService,
              private route : Router,
              private transactionService:TransactionService){}

  ngOnInit(): void {
    if (this.authService.isLogin == false){
      this.route.navigate(["auth"])
    }
  }

  onKey(value : number){
    this.montant = value;
  }

  onContact(value : String){
    this.to= value;
  }

  onSend(){
    this.newTransaction(this.to,this.montant)
  }


  private newTransaction(to:String, montant:number){
    this.transactionService.newSendToBdd(to, montant, this.transactionAddEvent).subscribe({
      next: data => {
        console.log("TRANSACTION OK : ", data)
        this.transactionAddEvent.emit();
      },
      error: error => {
        console.error("TRANSACTION Erreur : " + error)
      }}
    )
  }

}
