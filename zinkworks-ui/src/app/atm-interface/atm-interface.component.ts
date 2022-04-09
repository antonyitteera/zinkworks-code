import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-atm-interface',
  templateUrl: './atm-interface.component.html',
  styleUrls: ['./atm-interface.component.css']
})
export class AtmInterfaceComponent implements OnInit {

  constructor(private loginService:LoginService,private router: Router) { }

  ngOnInit(): void {
      if(this.loginService.token == ''){
        // this.router.navigateByUrl('/login')
      }
  }

  checkBalance(){
    
  }

}
