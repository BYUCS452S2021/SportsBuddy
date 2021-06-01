import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  public url = "http://localhost:1010/";

  constructor(private http: HttpClient) { }

  login(userName, password) {
    const request = {
      userName: userName,
      password: password
    };
    return this.http.post(`${this.url}user/login`, JSON.stringify(request), { observe: 'response' });
  }
}
