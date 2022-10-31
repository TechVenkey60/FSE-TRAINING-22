import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateBookComponent } from './create-book/create-book.component';
import { GetAllBooksComponent } from './get-all-books/get-all-books.component';
import { LoginComponent } from './login/login.component';
import { NavbarComponent } from './navbar/navbar.component';
import { SignupComponent } from './signup/signup.component';

const routes: Routes = [{path:"",redirectTo:"login",pathMatch:"full"},
                        {path:"login",component:LoginComponent},
                        {path:"signup",component:SignupComponent},
                        {path:"home",component:NavbarComponent},
                        {path:"createBook",component:CreateBookComponent},
                        {path:"allBooks",component:GetAllBooksComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
