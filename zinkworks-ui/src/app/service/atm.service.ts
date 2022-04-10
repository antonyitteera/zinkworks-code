import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class AtmService {

  constructor(private http: HttpClient, private loginService: LoginService) { }


  getBalance(){
    let headers: HttpHeaders = new HttpHeaders();
    headers = headers.append('Authorization', 'Bearer ' + this.loginService.token); 
    return this.http.get(`${environment.atmServiceBaseUrl}/api/v1/retrievebalance`,{headers});
  }

  withdraw(amountToDedeuct:any){
    let headers: HttpHeaders = new HttpHeaders();
    headers = headers.append('Authorization', 'Bearer ' + this.loginService.token); 
    return this.http.get(`${environment.atmServiceBaseUrl}/api/v1/withdraw/${amountToDedeuct}`,{headers});
  }
}
