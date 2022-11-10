import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { CreateBookComponent } from './create-book/create-book.component';
import { GetAllBooksComponent } from './get-all-books/get-all-books.component';
import { SearchbooksComponent } from './searchbooks/searchbooks.component';
import { AuthorBooksComponent } from './author-books/author-books.component';
import { UpdateBooksComponent } from './update-books/update-books.component';
import { SubscribebooksComponent } from './subscribebooks/subscribebooks.component';
import { ReaderSubscribedBooksComponent } from './reader-subscribed-books/reader-subscribed-books.component';
import { ReadContentComponent } from './read-content/read-content.component';
import { HomeComponent } from './home/home.component';
import { FooterComponent } from './footer/footer.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    NavbarComponent,
    CreateBookComponent,
    GetAllBooksComponent,
    SearchbooksComponent,
    AuthorBooksComponent,
    UpdateBooksComponent,
    SubscribebooksComponent,
    ReaderSubscribedBooksComponent,
    ReadContentComponent,
    HomeComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    RouterModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
