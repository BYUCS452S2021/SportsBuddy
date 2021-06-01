import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  public userId;
  public userToken;
  public userName;
  public url = "http://localhost:1010/user";

  constructor(private http: HttpClient) { }

  public loadUser(token) {
    // var header = {
    //   headers: new HttpHeaders()
    //     .set('Authorization',  token)
    // }
    // return this.http.get(`${this.url}user`, header);
    return this.http.get(`${this.url}?${token}`);
  }

}
