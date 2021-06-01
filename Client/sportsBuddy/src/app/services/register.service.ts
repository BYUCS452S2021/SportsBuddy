import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  public url = "http://localhost:1010/";

  constructor(private http: HttpClient) { }

  public register(user) {
    return this.http.post(`${this.url}user/register`, JSON.stringify(user));
  }
}
