import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthorBooksComponent } from './author-books/author-books.component';
import { CreateBookComponent } from './create-book/create-book.component';
import { GetAllBooksComponent } from './get-all-books/get-all-books.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { NavbarComponent } from './navbar/navbar.component';
import { ReadContentComponent } from './read-content/read-content.component';
import { ReaderSubscribedBooksComponent } from './reader-subscribed-books/reader-subscribed-books.component';
import { SearchbooksComponent } from './searchbooks/searchbooks.component';
import { SignupComponent } from './signup/signup.component';
import { SubscribebooksComponent } from './subscribebooks/subscribebooks.component';
import { UpdateBooksComponent } from './update-books/update-books.component';

const routes: Routes = [{path:"",redirectTo:"login",pathMatch:"full"},
                        {path:"login",component:LoginComponent},
                        {path:"signup",component:SignupComponent},
                        {path:"home",component:NavbarComponent},
                        {path:"createBook",component:CreateBookComponent},
                        {path:"allBooks",component:GetAllBooksComponent},
                        {path:"searchBook",component:SearchbooksComponent},
                        {path:"authorBooks",component:AuthorBooksComponent},
                        {path:"updateBook/:authorId/:bookId",component:UpdateBooksComponent},
                        {path:"subscribe",component:SubscribebooksComponent},
                        {path:"subscribed-books",component:ReaderSubscribedBooksComponent},
                        {path:"readContent/:subscriptionId",component:ReadContentComponent},
                        {path:"homePage",component:HomeComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
