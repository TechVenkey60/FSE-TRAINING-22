import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DigitalBooksOperationsService } from 'src/services/digital-books-operations.service';
import { UserServiceService } from 'src/services/user-service.service';

@Component({
  selector: 'app-author-books',
  templateUrl: './author-books.component.html',
  styleUrls: ['./author-books.component.css']
})
export class AuthorBooksComponent implements OnInit {

  userInfo:any;
  authorId:number=0;
  submitted=false;
  digitalBooks:any;

  hasTableData=false;
  isAuthorIssue=false;
  errorMessage:any
  errorResponse:any;

  constructor(private userService:UserServiceService,
    private digitalBookService:DigitalBooksOperationsService,
    private router: Router) { }

  ngOnInit(): void {

    this.userInfo = this.userService.getUserData();
    this.authorId=this.userInfo.userId;

    this.digitalBookService.getBooksByAuthorId(this.authorId)
            .subscribe(data =>{
              this.digitalBooks = data;
              if(this.digitalBooks.length != 0){
                this.hasTableData = true;
              }
            },error => {
              console.log("===========================");
               this.errorMessage = JSON.parse(error.error);
              this.isAuthorIssue = true;
              this.errorResponse = this.errorMessage.message;
              console.log("===========================");
            });
  }


  updateBook(authorId:number,bookId:number){
    this.router.navigate(['/updateBook',authorId,bookId]);
  }

  blockBook(authorId:number,bookId:number){
    this.digitalBookService.blockDigitalBook(authorId,bookId)
        .subscribe(data => {
          this.digitalBooks = data;
          if(this.digitalBooks.length != 0){
            this.hasTableData = true;
          }
        },error => {
          console.log("===========================");
          this.errorMessage = JSON.parse(error.error);
         this.isAuthorIssue = true;
         this.errorResponse = this.errorMessage.message;
         console.log("===========================");
        });
  }

  unBlockBook(authorId:number,bookId:number){
    this.digitalBookService.unBlockDigitalBook(authorId,bookId)
         .subscribe(data => {
          this.digitalBooks = data;
          if(this.digitalBooks.length != 0){
            this.hasTableData = true;
          }
         },error => {
          console.log("===========================");
          this.errorMessage = JSON.parse(error.error);
         this.isAuthorIssue = true;
         this.errorResponse = this.errorMessage.message;
         console.log("===========================");
         });
  }

}
