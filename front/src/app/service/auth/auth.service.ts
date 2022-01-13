import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../../data/user";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private user : User[] = []
  private baseUrl = environment.baseUrl;
  private request = "/user";
  private slash ="/";
  private id: string = "";


  constructor(private httpClient: HttpClient) { }

  getUsertoBdd(){
    console.log("GET user")
    this.httpClient.get<User[]>(`${this.baseUrl}`+this.request+this.slash+this.id)
      .subscribe({
        next : response => {
          console.log('GET ok ' + response);
          this.user = response;
        }, error : e => {
          console.error('GET Error : '+e);
        }
      })
  }
}
