import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DigitalBooksOperationsService } from 'src/services/digital-books-operations.service';
import { DigitalBook } from './digitalbookdata';

@Component({
  selector: 'app-update-books',
  templateUrl: './update-books.component.html',
  styleUrls: ['./update-books.component.css']
})
export class UpdateBooksComponent implements OnInit {


  digitalBook:any;
  updateFormBook!:FormGroup;
  authorId:number=0;
  bookId:number=0;
  bookInformation:any;
  bookTitile:any;
  bookCategory:any;
  bookAutor:any;
  bookPrice:any;
  bookPublisher:any;
  bookPublishedDate:any;
  bookChapter:any;
  bookActive:any;




  constructor(private digitalBookService:DigitalBooksOperationsService,
             private router : Router,
             private route: ActivatedRoute,
             private formBuilder : FormBuilder) { }

  ngOnInit(): void {
    this.updateFormBook = this.formBuilder.group({
      title : ['',Validators.required],
      category:['',Validators.required],
      author:['',Validators.required],
      price:['',Validators.required],
      publisher:['',Validators.required],
      publishedDate:['',Validators.required],
      chapter:['',Validators.required],
      active:['',Validators.required]
    });

    this.authorId = this.route.snapshot.params['authorId'];
    this.bookId = this.route.snapshot.params['bookId'];
    
    this.digitalBookService.getDigitalBookByBookIdAndAuthorId(this.authorId,this.bookId)
        .subscribe(data => {
          
          this.digitalBook = data;
          console.log(this.digitalBook);
          this.bookInformation = JSON.parse(this.digitalBook);
          this.bookTitile = this.bookInformation.title;
          this.bookCategory = this.bookInformation.category;
          this.bookAutor = this.bookInformation.author;
          this.bookPrice = this.bookInformation.price;
          this.bookPublishedDate = this.bookInformation.publishedDate;
          this.bookPublisher = this.bookInformation.publisher;
          this.bookChapter = this.bookInformation.chapter;
          this.bookActive = this.bookInformation.active;
        },error => {
          console.log(error.error);
        });
  }


  editDigitalBook(){
    this.digitalBookService.updateExistingBook(this.authorId,this.bookId,this.updateFormBook.value)
            .subscribe(data => {
               this.router.navigate(['/authorBooks']);
            }, error => {
              console.log(error);
            });
   
  }
}
