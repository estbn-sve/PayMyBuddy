import { Component, OnInit } from '@angular/core';
import {User} from "../../data/user"

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {

  isCo: boolean;
  isSign: boolean;
  isForm:boolean;

  user:User[] = [];

  constructor() {
    this.isForm = true;
    this.isCo = false;
    this.isSign = false;
  }

  ngOnInit(): void {
  }

  onCo(){
    if(this.isCo == true){
      this.isCo = false;
      this.isForm= true;
    }else{
      this.isCo = true;
      this.isForm = false;
    }
  }

  onSign(){
    if(this.isSign == true){
      this.isSign=false
      this.isForm= true;
    }else{
      this.isSign=true;
      this.isForm= false;
    }
  }

  onCoSubmit(){

  }

  onSignSubmit(){

  }
}
