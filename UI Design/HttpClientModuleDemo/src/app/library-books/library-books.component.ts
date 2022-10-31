import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { UsersystemService } from '../Services/usersystem.service';

@Component({
  selector: 'app-library-books',
  templateUrl: './library-books.component.html',
  styleUrls: ['./library-books.component.css']
})
export class LibraryBooksComponent implements OnInit {

  libraryBook : any;

  bookId=1;

  getBook(){
    let books = this.userDetails.getLibraryBookById(this.bookId);
  
    books.subscribe(data => {
      this.libraryBook=data
      console.log(this.libraryBook);
    });

    let allBooks = this.userDetails.getLibraryBooks();
    allBooks
    allBooks.subscribe(data => {
       console.log(data);
    });
  }

  constructor(private httpClient : HttpClient,private userDetails:UsersystemService) { }

  ngOnInit(): void {
    
  }

}
