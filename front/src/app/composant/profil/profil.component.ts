import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../service/auth/auth.service";
import {Router} from "@angular/router";
import {User} from "../../data/user";
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import {ContactService} from "../../service/contact/contact.service";
import {TransactionService} from "../../service/transaction/transaction.service";


@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css']
})
export class ProfilComponent implements OnInit {
  closeResult="";

  user:User= this.authService.user
  email:string="";
  montant:number=0;

  constructor(
    private authService : AuthService,
    private route : Router,
    private modalService: NgbModal,
    private contactService : ContactService,
    private transactionService: TransactionService
  ) {
  }

  ngOnInit(): void {
    if (this.authService.isLogin == false){
      this.route.navigate(["auth"]);
    }
  }


  // @ts-ignore
  contactModal(contentContact){
    this.modalService.open(contentContact, {ariaLabelledBy: 'modal-contact'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  // @ts-ignore
  outModal(contentTransactionOut){
    this.modalService.open(contentTransactionOut, {ariaLabelledBy: 'modal-transaction-out'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  onClickAddContact(){
    this.contactService.addContactToBdd(this.user, this.email).subscribe(()=>{});
  }
  onClickAddMoney(){
    this.transactionService.addOut(this.user.id, this.montant).subscribe(()=>{});
  }
  onClickSendMoney(){
    this.transactionService.sendOut(this.user.id, this.montant).subscribe(()=>{});
  }

  onKeyEmail(value : string){
    this.email = value;
  }

  onKeyMontant(value:number){
    this.montant = value;
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }
}
