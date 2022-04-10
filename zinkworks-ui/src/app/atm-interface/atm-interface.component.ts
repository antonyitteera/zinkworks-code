import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AtmService } from '../service/atm.service';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-atm-interface',
  templateUrl: './atm-interface.component.html',
  styleUrls: ['./atm-interface.component.css']
})
export class AtmInterfaceComponent implements OnInit {
  retrievedData:any;
  currentScreen = 0;
  currentBalance = 0;
  amountToBeRetrieved = 0;
  constructor(private loginService: LoginService, private router: Router, private atmService: AtmService,private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    if (this.loginService.token == '') {
       this.router.navigateByUrl('/login') 
    }
  }

  withdraw() {
    this.currentScreen = 2;
  }

  cancel(){
    this.loginService.token="";
    this.router.navigateByUrl("/login")

  }
  continue() {
    console.log('continue');
    this.atmService.withdraw(this.amountToBeRetrieved).subscribe((i: any) => {
      console.log('dasd');
      console.log(i);
      this.currentScreen=3;
      this.retrievedData=i;
      this.retrievedData.availableBalance = parseFloat(this.retrievedData.availableBalance); 
    }, err => {
      console.log(err);
      this._snackBar.open(err.error.message);
    })

  }


  checkBalance() {
    this.currentScreen = 1;
    this.atmService.getBalance().subscribe((i: any) => {
      console.log('dasd');
      console.log(i);
      this.currentBalance = i.message;
      
    }, err => {
      console.log(err);

    })
  }

}
