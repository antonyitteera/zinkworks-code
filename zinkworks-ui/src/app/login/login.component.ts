import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  account='';
  pin='';
  constructor(public loginService : LoginService,
    private router: Router) { }

  ngOnInit(): void {
  }

  login(){
    // console.log("login");
    this.loginService.login(this.account,this.pin).subscribe((i:any)=>{
      this.loginService.token = i.jwttoken;
      console.log(this.loginService.token);
      this.router.navigateByUrl("/atm");
    },err=>{
      console.log(err);
      
    })
  }

}
