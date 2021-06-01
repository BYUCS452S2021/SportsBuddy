import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { NavController } from '@ionic/angular';
import { RegisterService } from 'src/app/services/register.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.scss'],
})
export class RegisterPage implements OnInit {
    public userName: String;
    public password: String;
    public firstName: String;
    public lastName: String;
    public gender: String;
    public email: String;
    public userSports = [];
    public location = {};
    public allFilled = true;
    public errorMessage: String;

  constructor(private registerService: RegisterService, private navCtrl: NavController, private userService: UserService) { }

  ngOnInit() {
  }

  register() {
    if (this.userName && this.password && this.firstName && this.lastName && this.gender 
        && this.email ) {
            this.allFilled = true;
            const user = {
                "userId": "12345",
                "firstName": this.firstName,
                "lastName": this.lastName,
                "gender": this.gender,
                "userName": this.userName,
                "userPassword": this.password,
                "userEmail": this.email,
                "userSports": this.userSports,
                "location": this.location
            }
            this.registerService.register(user).subscribe((data:any) => {
                if(data.success) {
                    this.userService.userId = data?.userId;
                    this.userService.userName = data?.userName;
                    this.userService.userToken = data?.token;
                    window.sessionStorage.setItem("token", data.token);
                    this.navCtrl.navigateForward('home');
                }
            });

    } else {
        this.allFilled = false;
    }
  }

  goToLogin() {
      this.navCtrl.navigateForward("login");
  }

}
