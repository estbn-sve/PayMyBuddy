import {User} from "./user";

export interface Transaction{
  id : number;
  solde_to : number;
  solde_from : number;
  solde_app : number;
  date : Date;
  id_user_to: User;
  id_user_from: User;
}
