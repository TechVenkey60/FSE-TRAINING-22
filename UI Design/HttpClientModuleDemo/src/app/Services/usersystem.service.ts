import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import UserData from '../entity/UserData';

const BASE_URL = "http://localhost:5555/UserDetails";

@Injectable({
  providedIn: 'root'
})
export class UsersystemService {

  saveUserData(userData:{
    firstName:string,
    lastName:string,
    email:string,
    gender:string,
    phone:string,
    id:string

  }){
      return this.httpClient.post(BASE_URL,userData);
  }

  getAllUserDetails(){
     return this.httpClient.get(BASE_URL);
  }

  getLibraryBookById(bookId:number){
    return this.httpClient.get("http://localhost:9966/library/read/"+bookId);
  }
  
  getLibraryBooks(){
    return this.httpClient.get("http://localhost:9966/library/allBooks");
  }



  deletelUserDetails(userData:UserData){
     return this.httpClient.delete(BASE_URL+"/"+userData.id);
  }
  constructor(public httpClient : HttpClient) { }
}
