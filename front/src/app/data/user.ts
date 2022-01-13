import {UserLogin} from "./userLogin";

export interface User{
  id : number;
  lastName:String;
  firstName:String;
  email:String;
  solde:number;
  iban:String;
  login:UserLogin;
}
