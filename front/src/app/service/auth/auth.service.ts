import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../../data/user";
import {environment} from "../../../environments/environment";
import {UserLogin} from "../../data/userLogin";
import {Router, RouterModule} from "@angular/router";
import {UserSignIn} from "../../data/userSignIn";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  user : User;
  private baseUrl = environment.baseUrl;
  private request = "/user";
  private loginRequest ="/login";
  private singInRequest = "/signin";
  isLogin : boolean = false;

  constructor(private httpClient: HttpClient, private route: Router) { }

  login(userLogin : UserLogin){
    console.log("GET user")
    this.httpClient.post<User>(`${this.baseUrl}`+this.request+this.loginRequest, userLogin)
      .subscribe({
        next : response => {
          console.log('GET ok ' + response);
          this.user = response;
          this.isLogin = true;
          this.route.navigate(["profil"])
        }, error : e => {
          console.error('GET Error : '+e);
        }
      })
  }

  signIn(userSignIn : UserSignIn){
    console.log("Post user")
    this.httpClient.post<User>(`${this.baseUrl}`+this.request+this.singInRequest, userSignIn)
      .subscribe({
        next : response => {
          console.log("Post ok" + response);
          this.user = response;
          this.isLogin = true;
          this.route.navigate(["transaction"])
        }, error : e => {
          console.error("Post Error : "+e);
        }
      })
  }

  getUser(){
    return this.user;
  }

  getAuth(){
    return this.isLogin;
  }
}
