import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

const BASE_URL = "http://ec2-54-199-226-55.ap-northeast-1.compute.amazonaws.com:9966/api/v1/digitalbooks/author/";
const SUBSCRIBE_URL = "http://ec2-54-199-226-55.ap-northeast-1.compute.amazonaws.com:9966/api/v1/digitalbooks/";
const ADD_NEW_BOOK_URL = "http://ec2-54-199-226-55.ap-northeast-1.compute.amazonaws.com:9966/api/v1/digitalbooks/author/";
const GET_AUTHOR_BOOKS_URL = "http://ec2-54-199-226-55.ap-northeast-1.compute.amazonaws.com:9966/api/v1/digitalbooks/author/";
const GET_BOOK_BY_AUTHOR_ID_AND_BOOK_ID = "http://ec2-54-199-226-55.ap-northeast-1.compute.amazonaws.com:9966/api/v1/digitalbooks/author/";
const UPDATE_BOOK_BY_AUTHOR_ID_AND_BOOK_ID = "http://ec2-54-199-226-55.ap-northeast-1.compute.amazonaws.com:9966/api/v1/digitalbooks/author/";

const AWS_CREATE_AUTHOR_BOOK = "http://ec2-54-199-226-55.ap-northeast-1.compute.amazonaws.com:9966/api/v1/digitalbooks/author/";
const AWS_UPDATE_AUTHOR_BOOK = "https://xa3tf7p2qe.execute-api.ap-northeast-1.amazonaws.com/test/digital-books-service/";
@Injectable({
  providedIn: 'root'
})
export class DigitalBooksOperationsService {
 
  
  constructor(private httpClient: HttpClient) { }


  getBooksByAuthorId(authorId:number){
    return this.httpClient.get(GET_AUTHOR_BOOKS_URL+authorId+"/books");
  }

  addNewBook(authorId:number,newBook:any){
    return this.httpClient.post(ADD_NEW_BOOK_URL+authorId+"/books",newBook,{responseType:'text' as 'json'});
  }


  getDigitalBookByBookIdAndAuthorId(authorId:number,bookId:number){
    return this.httpClient.get(GET_BOOK_BY_AUTHOR_ID_AND_BOOK_ID+authorId+"/books/"+bookId,{responseType:'text' as 'json'});
  }

  updateExistingBook(authorId: number, bookId: number, digitalBook: any) {
    return this.httpClient.put(UPDATE_BOOK_BY_AUTHOR_ID_AND_BOOK_ID+authorId+"/books/"+bookId,digitalBook);
  }

  blockDigitalBook(authorId: number, bookId: number) {
    return this.httpClient.get(BASE_URL+authorId+"/book/"+bookId+"?block=yes");
  }

  unBlockDigitalBook(authorId: number, bookId: number) {
    return this.httpClient.get(BASE_URL+authorId+"/book/"+bookId+"?block=no");
  }

  subscribeNewDigitalBook(bookId: number, subscribeData: any) {
    return this.httpClient.post(SUBSCRIBE_URL+bookId+"/subscribe",subscribeData,{responseType: 'text' as 'json'});
  }


  getAllReaderSubscribedBooks(emailId: string) {
    return this.httpClient.get(SUBSCRIBE_URL+"readers/"+emailId+"/books");
  }

  removeSubscription(subscriptionId: number, readerMailId: string) {
    return this.httpClient.get(SUBSCRIBE_URL+"readers/"+readerMailId+"/books/"+subscriptionId+"/cancelSubscription");
  }


  searchBookWithInput(bookTitle: string, 
                      bookCategory: string, 
                      bookAuthor: string, 
                      bookPrice: number, 
                      bookPublisher: string) {
   
    return this.httpClient.get(SUBSCRIBE_URL+"search?category="+bookCategory+"&title="+bookTitle+"&author="+bookAuthor+"&publisher="+bookPublisher+"&price="+bookPrice, {responseType:'text' as 'json'});

  }

  getReaderSubscribedContent(readerId: number) {
   return this.httpClient.get(SUBSCRIBE_URL+"books/"+readerId,{responseType:'text' as 'json'});
  }
}
