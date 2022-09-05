import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Transaction} from "../../data/transaction";
import {Contact} from "../../data/contact";
import {User} from "../../data/user";

@Injectable({
  providedIn: 'root'
})
export class ContactService {
  private baseUrl = environment.baseUrl;
  private request = "/addContact";

  constructor(private httpClient: HttpClient) { }

  getContactToBdd(id:number):Observable<Contact[]>{
    console.log("GET Contact");
    return this.httpClient.get<Contact[]>(`${this.baseUrl}`+this.request+"/"+id);
  }

  addContactToBdd(user : User, email:string):Observable<any>{
    console.log("Add Contact");
    console.log(user);
    console.log(email);
    console.log(`${this.baseUrl}`+this.request+"/", {
      user: user,
      email: email
    });
    return this.httpClient.post(`${this.baseUrl}`+this.request, {
      id: user.id,
      email: email
    });
  }

}
