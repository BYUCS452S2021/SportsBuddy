import { Component, OnInit } from '@angular/core';
import { NavController } from '@ionic/angular';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {
    public userName;
    public password;
    public allFilled = true;

  constructor(public navCtrl: NavController, public loginService: LoginService) { }

  ngOnInit() {
  }

  goToSignUp() {
    this.navCtrl.navigateForward("register");
  }

  login() {
    if (this.userName && this.password) {
        this.allFilled = true;
        this.loginService.login(this.userName, this.password).subscribe((data:any) => {
            console.log(data);
            window.sessionStorage.setItem("token", data.body.token);
            this.navCtrl.navigateForward("home");
        });
    }
    else {
        this.allFilled = false;
    }
  }

}
