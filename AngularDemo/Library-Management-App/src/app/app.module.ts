import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { AddBookComponent } from './add-book/add-book.component';
import { FooterComponent } from './footer/footer.component';
import { GetAllBooksComponent } from './get-all-books/get-all-books.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    AddBookComponent,
    FooterComponent,
    GetAllBooksComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
