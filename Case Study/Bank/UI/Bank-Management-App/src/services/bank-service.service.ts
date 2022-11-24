import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';


const LOGIN_URL = "http://localhost:9966/api/vrk/bank/login";
const CREATE_NEW_USER_URL = "http://localhost:9966/api/vrk/bank/newUserAccount";
const APPLY_FOR_LOAN ="http://localhost:9966/api/vrk/bank/applyLoan";
const USER_LOAN_DETAILS = "http://localhost:9966/api/vrk/bank/loans/";

@Injectable({
  providedIn: 'root'
})
export class BankServiceService {
  
  user:any;
  constructor(private httpClient : HttpClient) { }

  userSignIn(loginData: any) {
    return this.httpClient.post(LOGIN_URL,loginData,{responseType:'text' as 'json'});
  }

  userSignUp(newUserData: any) {
    return this.httpClient.post(CREATE_NEW_USER_URL,newUserData,{responseType:'text' as 'json'});
  }


  applyForLoan(loanInfo: any) {
    return this.httpClient.post(APPLY_FOR_LOAN,loanInfo,{responseType:'text' as 'json'});
  }


  getAppliedLoanDetails(accountNumber: any) {
    return this.httpClient.get(USER_LOAN_DETAILS+accountNumber,{responseType:'text' as 'json'});
  }

  public storeUserData(userData:any){
     this.user = userData;
  }

  public getUserData(){
    return this.user;
  }

  public isUserLoggedIn(){
    if(this.user.userName !== null) {
        return true;
    }
    return false;
  }

}
