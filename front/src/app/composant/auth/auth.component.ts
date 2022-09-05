import { Component, OnInit } from '@angular/core';
import {User} from "../../data/user"
import {AuthService} from "../../service/auth/auth.service";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {

  isCo: boolean;
  isSign: boolean;
  isForm:boolean;
  userLogName: string = "";
  userLogPassword : string = "";

  firstNameSign : string ="";
  lastNameSign : string ="";
  userNameSign : string ="";
  emailSign : string ="";
  passwordSign : string ="";

  sign : FormGroup = new FormGroup(
    {
      firstName: new FormControl(""),
      lastName:new FormControl(""),
      email:new FormControl(""),
      user:new FormControl(""),
      mdp:new FormControl("")
    }
  );


  user:User[] = [];

  constructor( private authService : AuthService) {
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
    let dummy = {
      user: this.userLogName,
      mdp: this.userLogPassword
    }
    this.authService.login(dummy);
  }

  onSignSubmit(){
    let dummy = {
      firstName: this.sign.get("firstName")?.value,
      lastName:this.lastNameSign,
      email:this.emailSign,
      user:this.userNameSign,
      mdp:this.passwordSign
    }
    this.authService.signIn(dummy)
  }

  onLogUserName(value : string){
    this.userLogName = value;
  }

  onLogUserPassword(value : string){
    this.userLogPassword = value;
  }

  onSignFirstName(value : string){
    this.firstNameSign = value;
  }
  onSingLastName(value : string){
    this.lastNameSign = value;

  }
  onSingUserName(value : string){
    this.userNameSign = value;

  }
  onSignEmail(value : string){
    this.emailSign = value;

  }
  onSignPassword(value : string){
    this.passwordSign = value;
  }
}
