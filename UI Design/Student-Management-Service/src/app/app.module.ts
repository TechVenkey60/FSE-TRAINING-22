import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { StudentsProductsComponent } from './students-products/students-products.component';
import { NgModelDataComponent } from './ng-model-data/ng-model-data.component';
import { ProductDetailsService } from './product-details.service';

@NgModule({
  declarations: [
    AppComponent,
    UserDetailsComponent,
    StudentsProductsComponent,
    NgModelDataComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [ProductDetailsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
