import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Book } from './librarybook';

const ADD_BOOK_URL = "http://localhost:9966/library/add/book";
const GET_ALL_BOOKS = "http://localhost:9966/library/allBooks";
const REMOVE_BOOK_BY_ID="http://localhost:9966/library/remove/"

@Injectable({
  providedIn: 'root'
})

export class LibrarybookactionsService {
  constructor(private httpClient : HttpClient) { }

  createNewLibraryBook(librarybook:Book){
    return this.httpClient.post(ADD_BOOK_URL,librarybook,{responseType:'text' as 'json'});
  }

  getAllLibraryBooks(){
    return this.httpClient.get(GET_ALL_BOOKS);
  }

  removeLibraryBookById(bookId:number){
    return this.httpClient.delete(REMOVE_BOOK_BY_ID+bookId);
  }
}
