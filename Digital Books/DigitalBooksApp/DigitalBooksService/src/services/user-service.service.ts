import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

const SIGN_IN_URL = "http://localhost:9977/api/v1/digitalbooks/sign-in";
const SIGN_UP_URL = "http://localhost:9977/api/v1/digitalbooks/sign-up";

const AWS_SIGN_UP_URL = "https://xa3tf7p2qe.execute-api.ap-northeast-1.amazonaws.com/dev/user-service";
const AWS_SIGN_IN_URL = "https://xa3tf7p2qe.execute-api.ap-northeast-1.amazonaws.com/dev/user-service/user-service-login";
@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  user:any;
  role:any;

  constructor(private httpClient : HttpClient) { }

  setUserData(userData:any){
    this.user= userData;
  }
  getUserData(){
    return this.user;
  }
  
  userSignUp(uSingUp:any){
    return this.httpClient.post(SIGN_UP_URL,uSingUp,{responseType:'text' as 'json'});
  }
  
  userSignIn(uSingIn:any){
    return this.httpClient.post(SIGN_IN_URL,uSingIn,{responseType:'text' as 'json'});
  }


}
