import {UserLogin} from "./userLogin";
import {Contact} from "./contact";

export interface User{
  id : number;
  lastName:String;
  firstName:String;
  email:String;
  solde:number;
  iban:String;
  login:UserLogin;
  friendList:Contact[];
}
