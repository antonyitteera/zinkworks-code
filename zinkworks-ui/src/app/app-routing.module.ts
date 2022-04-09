import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AtmInterfaceComponent } from './atm-interface/atm-interface.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path:'', redirectTo:'/login', pathMatch:'full'},
  { path: 'atm', component: AtmInterfaceComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
