import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Contact} from "../../data/contact";
import {environment} from "../../../environments/environment";
import {User} from "../../data/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl = environment.baseUrl;
  private request = "/userEmail";

  constructor(private httpClient: HttpClient) { }

  getUserByEmail(email:string):Observable<User[]>{
    console.log("GET Contact");
    return this.httpClient.get<User[]>(`${this.baseUrl}`+this.request+"/"+email);
  }
}
