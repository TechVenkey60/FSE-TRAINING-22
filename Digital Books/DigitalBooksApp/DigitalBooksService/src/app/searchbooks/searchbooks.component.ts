import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { DigitalBooksOperationsService } from 'src/services/digital-books-operations.service';

@Component({
  selector: 'app-searchbooks',
  templateUrl: './searchbooks.component.html',
  styleUrls: ['./searchbooks.component.css']
})
export class SearchbooksComponent implements OnInit {



  bookTitle:string="";
  bookCategory:string="";
  bookAuthor:string="";
  bookPrice:number=0;
  bookPublisher:string="";
  digitalBooks:any;
  response:any; 
  errorResponse:any;
  errorMessage:any;

  searchFormBook!:FormGroup;
  submitted = false;
  hasBookData= false;

  constructor(private formBuilder : FormBuilder,
              private digitalBookService : DigitalBooksOperationsService,
              private router : Router) { }

  ngOnInit(): void {
    this.searchFormBook = this.formBuilder.group({
      title : ['',Validators.required],
      category:['',Validators.required],
      author:['',Validators.required],
      price:['',Validators.required],
      publisher:['',Validators.required]
    });
  }

  findBook(){
    if(this.searchFormBook.invalid){
      this.submitted = true;
      return;
    }

    this.bookTitle = this.searchFormBook.value.title;
    this.bookCategory = this.searchFormBook.value.category;
    this.bookAuthor = this.searchFormBook.value.author;
    this.bookPrice = this.searchFormBook.value.price;
    this.bookPublisher = this.searchFormBook.value.publisher;

    this.digitalBookService.searchBookWithInput(this.bookTitle,this.bookCategory,this.bookAuthor,this.bookPrice,this.bookPublisher)
            .subscribe(data => {

              this.digitalBooks = data;

             this.response = JSON.parse(this.digitalBooks);
             console.log(this.response.bookId);
              this.hasBookData=true;

            },error => {

             this.errorResponse = error.error;
             this.errorMessage = JSON.parse(this.errorResponse);
             console.log(this.errorMessage.message);
             alert(this.errorMessage.message);
              
            });

  }

}
