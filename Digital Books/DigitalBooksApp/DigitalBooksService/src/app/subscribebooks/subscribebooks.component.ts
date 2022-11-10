import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { DigitalBooksOperationsService } from 'src/services/digital-books-operations.service';
import { UserServiceService } from 'src/services/user-service.service';

@Component({
  selector: 'app-subscribebooks',
  templateUrl: './subscribebooks.component.html',
  styleUrls: ['./subscribebooks.component.css']
})
export class SubscribebooksComponent implements OnInit {

  subscribeErrorMessage:string="";
  errorMessage:any
  isSubscribeFailed=false;
  submitted=false;
  subscribeForm!:FormGroup;
  userInfo:any;
  authorId:number=0;
  readerMailId:string="";
  digitalBookId:number=0;
  subscribedBook:any;
  bookName:string="";
  response:any;
  errorResponse:any;
  message:any;
  readerMail:any;

  constructor(private formBuilder : FormBuilder,
              private router : Router,
              private userService : UserServiceService,
              private digitalBookService : DigitalBooksOperationsService) { }

  ngOnInit(): void {
    this.subscribeForm = this.formBuilder.group({
      emailId:['',[Validators.required,Validators.email]],
      bookId:['',[Validators.required]]
    })

    this.userInfo = this.userService.getUserData();
    this.authorId=this.userInfo.userId;
    if(this.userInfo.role === 'reader'){
      this.readerMail = this.userInfo.email;
    }
    
    console.log(this.authorId);
  }

  subscribeBook(){
    this.submitted = true;
    if(this.subscribeForm.invalid){
      this.submitted = true;
      return;
    }

    console.log("=================");
    this.digitalBookId = this.subscribeForm.value.bookId;
    console.log(this.digitalBookId);
    console.log("==================");
    
    this.digitalBookService.subscribeNewDigitalBook(this.digitalBookId,this.subscribeForm.value)
         .subscribe(data => {
          this.subscribedBook = data;
          this.response = JSON.parse(this.subscribedBook);
          this.bookName = this.response.title;
          alert("Book has been subscribed successfully");
         },error => {
           console.log(error);
           console.log("===========================");
           this.errorMessage = error.error
           this.errorResponse = JSON.parse(this.errorMessage);
           this.message = this.errorResponse.message;
          this.isSubscribeFailed=true;
           setTimeout(()=>{location.reload()},5000);
           this.router.navigate(['/subscribe']);
           console.log("===========================");
         });
  }
}
