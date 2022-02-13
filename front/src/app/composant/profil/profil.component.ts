import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../service/auth/auth.service";
import {Router} from "@angular/router";
import {User} from "../../data/user";

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css']
})
export class ProfilComponent implements OnInit {

  user:User= this.authService.user

  constructor(private authService : AuthService,
              private route : Router) {
  }

  ngOnInit(): void {
    if (this.authService.isLogin == false){
      this.route.navigate(["auth"])
    }
  }

}
