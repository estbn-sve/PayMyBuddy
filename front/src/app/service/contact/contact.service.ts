import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Transaction} from "../../data/transaction";
import {Contact} from "../../data/contact";

@Injectable({
  providedIn: 'root'
})
export class ContactService {
  private baseUrl = environment.baseUrl;
  private request = "/contact";

  constructor(private httpClient: HttpClient) { }

  getContactToBdd(id:number):Observable<Contact[]>{
    console.log("GET Contact");
    return this.httpClient.get<Contact[]>(`${this.baseUrl}`+this.request+"/"+id);
  }
}
