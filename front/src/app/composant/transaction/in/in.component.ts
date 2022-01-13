import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Contact} from "../../../data/contact";
import {ContactService} from "../../../service/contact/contact.service";
import {TransactionService} from "../../../service/transaction/transaction.service";
import {Subscription} from "rxjs";


@Component({
  selector: 'app-in',
  templateUrl: './in.component.html',
  styleUrls: ['../transaction.component.css']
})
export class InComponent implements OnInit {

  @Output()transactionAddEvent: EventEmitter<void>= new EventEmitter<void>();

  contactSubscription : Subscription = new Subscription();
  contacts : Contact[]  = []
  to:String="";
  montant:number= 0;
  event:any;

  constructor(private contactService: ContactService, private transactionService:TransactionService) { }

  ngOnInit(): void {
    this.getContact()
  }

  onKey(value : number){
    this.montant = value;
  }

  onContact(value : String){
    this.to= value;
  }

  onSend(){
    alert("Envoie de "+ this.montant + "€ à "+this.to+".")
  this.newTransaction(this.to,this.montant)
  }

  private getContact() {
    this.contactService.getContactToBdd()
      .subscribe(data => {
          this.contacts=data
          console.log("data reçus")
        },error => {
          alert("Erreur : " + error)
          console.log("Erreur : " + error)
        }
      )
  }

   private newTransaction(to:String, montant:number){
    this.transactionService.newSendToBdd(to, montant, this.transactionAddEvent)
   }

}
