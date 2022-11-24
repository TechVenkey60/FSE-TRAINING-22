import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoanDetailsComponent } from './components/loan-details/loan-details.component';
import { LoginComponent } from './components/login/login.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';

const routes: Routes = [{path:"",redirectTo:"login",pathMatch:"full"},
                        {path:"home",component:HomeComponent},
                        {path:"login",component:LoginComponent},
                        {path:"loanDetails",component:LoanDetailsComponent},
                        {path:"navbar",component:NavbarComponent},
                        {path:"register",component:SignUpComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
