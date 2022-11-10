import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddBookComponent } from './add-book/add-book.component';
import { GetAllBooksComponent } from './get-all-books/get-all-books.component';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';

const routes: Routes = [{path:"",redirectTo:"homePage",pathMatch:"full"},
                        {path:"home",component:NavbarComponent},
                        {path:"homePage",component:HomeComponent},
                        {path:"addBook",component:AddBookComponent},
                        {path:"getBooks",component:GetAllBooksComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
