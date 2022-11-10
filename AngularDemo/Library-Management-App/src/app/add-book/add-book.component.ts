import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators} from '@angular/forms';
import { Router } from '@angular/router';
import { Book } from 'src/Services/librarybook';
import { LibrarybookactionsService } from 'src/Services/librarybookactions.service';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {

  public addNewBook:any = FormGroup;

  message:any;

  constructor(private router:Router,private formBuilder : FormBuilder,
              private libraryBookService:LibrarybookactionsService) { }

  ngOnInit(): void {
    this.addNewBook = this.formBuilder.group({
      bookName:['',[Validators.required]],
      author:['',[Validators.required]],
      genre:['',[Validators.required]],
      price:['',[Validators.required]],
      isBookBorrowed:['',[Validators.required]]
    })
  }

  createNewBook(){
    console.log(this.addNewBook.value);
     
    this.libraryBookService.createNewLibraryBook(this.addNewBook.value)
         .subscribe(data => {
           this.message = data;
           alert(data);
           this.addNewBook.reset();
           this.router.navigate(["/getBooks"]);
         })
  }

}
