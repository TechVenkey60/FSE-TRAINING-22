import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from '@angular/common/http';
import { MockUsersComponent } from './mock-users/mock-users.component';
import { FormsModule } from '@angular/forms';
import { LibraryBooksComponent } from './library-books/library-books.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FormsComponent } from './forms/forms.component';
import { EntityComponent } from './entity/entity.component';
import { ListgroupComponent } from './listgroup/listgroup.component';
import { ContactUsComponent } from './contact-us/contact-us.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { PipesDemoComponent } from './pipes-demo/pipes-demo.component';
import { EllipsePipe } from './ellipse.pipe';

@NgModule({
  declarations: [
    AppComponent,
    MockUsersComponent,
    LibraryBooksComponent,
    NavbarComponent,
    FormsComponent,
    EntityComponent,
    ListgroupComponent,
    ContactUsComponent,
    AboutUsComponent,
    PipesDemoComponent,
    EllipsePipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
