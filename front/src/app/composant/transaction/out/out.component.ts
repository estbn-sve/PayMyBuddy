import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../../service/auth/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-out',
  templateUrl: './out.component.html',
  styleUrls: ['../transaction.component.css']
})
export class OutComponent implements OnInit {

  constructor(private authService : AuthService,
              private route : Router) {
  }

  ngOnInit(): void {
    if (this.authService.isLogin == false){
      this.route.navigate(["auth"])
    }
  }

}
