import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'PayMyBuddy';
  isAuth = false;
  isTransaction = false;

  onAuth(){
    if (this.isAuth == false){
      this.isAuth = true;
    } else {
      this.isAuth = false;
    }
  }
  onTransaction() {
    if (this.isTransaction == false) {
      this.isTransaction = true;
    } else {
      this.isTransaction = false;
    }
  }
}
