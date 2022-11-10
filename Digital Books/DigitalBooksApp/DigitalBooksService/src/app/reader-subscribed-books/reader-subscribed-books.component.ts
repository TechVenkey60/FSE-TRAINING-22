import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DigitalBooksOperationsService } from 'src/services/digital-books-operations.service';
import { UserServiceService } from 'src/services/user-service.service';

@Component({
  selector: 'app-reader-subscribed-books',
  templateUrl: './reader-subscribed-books.component.html',
  styleUrls: ['./reader-subscribed-books.component.css']
})
export class ReaderSubscribedBooksComponent implements OnInit {

  userInfo:any;
  emailId:string="";
  readerSubscribedBooks:any;
  errorResponse:any;
  errorMessage:any;
  hasTableData=false;
  subscriptionId:number=0;
  readerMailId:string="";

  constructor(private userService: UserServiceService,
              private digitalBookService : DigitalBooksOperationsService,
              private router : Router) { }

  ngOnInit(): void {

    this.userInfo = this.userService.getUserData();
    this.emailId=this.userInfo.email;

    this.digitalBookService.getAllReaderSubscribedBooks(this.emailId)
        .subscribe(data => {
          this.readerSubscribedBooks = data;
          if(this.readerSubscribedBooks.length !=0){
            this.hasTableData = true;
          }
        },error => {
          this.errorResponse = JSON.parse(error.error);
          this.errorMessage = this.errorResponse.message;

          alert(this.errorMessage)
        });
  }



  cancelBookSubscription(subscriptionId:number,readerMailId:string){
    
    this.digitalBookService.removeSubscription(subscriptionId,readerMailId)
        .subscribe(data => {
          this.readerSubscribedBooks = data;
          console.log(this.readerSubscribedBooks);
          
          if(this.readerSubscribedBooks.length !=0){
            this.hasTableData = true;
          }
        },error => {
          this.errorResponse = JSON.parse(error.error);
          this.errorMessage = this.errorResponse.message;
          alert(this.errorMessage)
        });
  }

  getBookContent(subscriptionId:number){
    console.log("----------------");
    
    console.log(subscriptionId);
    console.log("----------------");
    this.router.navigate(['/readContent',subscriptionId]);
  }

}
