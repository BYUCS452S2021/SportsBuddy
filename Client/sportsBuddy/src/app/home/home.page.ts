import { Component, OnInit } from '@angular/core';
import { NavController } from '@ionic/angular';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage implements OnInit{
  public user:any;
  public loading = true;

  constructor(private navCtrl:NavController, private userService: UserService) {}

  ngOnInit() {
    console.log(window.sessionStorage.getItem("token"));
    if (!window.sessionStorage.getItem("token") || window.sessionStorage.getItem("token")==="") {
      console.log("something");
      this.navCtrl.navigateForward("login");
    } else {
      this.userService.loadUser(window.sessionStorage.getItem("token")).subscribe((data:any) => {
        console.log("I am here");
        console.log(data);
        this.user = data.user;
        console.log(this.user);
        this.loading = false;
      });
    }
  }

  logout() {
    window.sessionStorage.setItem("token", "");
    this.navCtrl.navigateForward("login");
  }

}
