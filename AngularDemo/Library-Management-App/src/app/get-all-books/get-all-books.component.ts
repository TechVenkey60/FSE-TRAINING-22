import { Component, OnInit } from '@angular/core';
import { Book } from 'src/Services/librarybook';
import { LibrarybookactionsService } from 'src/Services/librarybookactions.service';

@Component({
  selector: 'app-get-all-books',
  templateUrl: './get-all-books.component.html',
  styleUrls: ['./get-all-books.component.css']
})
export class GetAllBooksComponent implements OnInit {
  
   libraryBooks : any;
   bookId:number=0;
   showErrorMessage:boolean=false;
  constructor(private libraryBookService:LibrarybookactionsService) { }

  ngOnInit(): void {
     this.libraryBookService.getAllLibraryBooks().subscribe(data => {
       this.libraryBooks = data;
       console.log(this.libraryBooks.length);
       
       if(this.libraryBooks.length == 0){
          this.showErrorMessage = true;
       }
     });
  }

  deleteBook(bookId:number){
    this.libraryBookService.removeLibraryBookById(bookId)
    .subscribe(data => {
      this.libraryBooks=data;
      if(this.libraryBooks.length == 0){
        this.showErrorMessage = true;
     }
    })
  }

}
