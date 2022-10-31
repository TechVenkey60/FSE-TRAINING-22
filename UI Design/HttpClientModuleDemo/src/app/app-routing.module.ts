import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutUsComponent } from './about-us/about-us.component';
import { ContactUsComponent } from './contact-us/contact-us.component';
import { FormsComponent } from './forms/forms.component';
import { LibraryBooksComponent } from './library-books/library-books.component';
import { ListgroupComponent } from './listgroup/listgroup.component';
import { MockUsersComponent } from './mock-users/mock-users.component';
import { PipesDemoComponent } from './pipes-demo/pipes-demo.component';

const routes: Routes = [{path:"",component:FormsComponent},
                        {path:"registry",component:MockUsersComponent},
                        {path:"listGroup",component:ListgroupComponent},
                        {path:"data",component:LibraryBooksComponent},
                        {path:"contact",component:ContactUsComponent},
                        {path:"about",component:AboutUsComponent},
                        {path:"pipe",component:PipesDemoComponent}
                      ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
