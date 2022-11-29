import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';


const LOGIN_URL = "http://localhost:9966/api/vrk/bank/login";
const CREATE_NEW_USER_URL = "http://localhost:9966/api/vrk/bank/newUserAccount";
const APPLY_FOR_LOAN ="http://localhost:9966/api/vrk/bank/applyLoan";
const USER_LOAN_DETAILS = "http://localhost:9966/api/vrk/bank/loans/";
const UPDATE_ACCOUNT_DETAILS = "http://localhost:9966/api/vrk/bank/update/accountDetails";


const AWS_LOGIN_URL = "https://0hds0sbd2l.execute-api.ap-northeast-1.amazonaws.com/dev/signin";
const AWS_CREATE_NEW_USER_URL = "https://0hds0sbd2l.execute-api.ap-northeast-1.amazonaws.com/dev";
const AWS_APPLY_FOR_LOAN ="https://0hds0sbd2l.execute-api.ap-northeast-1.amazonaws.com/dev/applyloan";
const AWS_USER_LOAN_DETAILS = "https://0hds0sbd2l.execute-api.ap-northeast-1.amazonaws.com/dev/";
const AWS_UPDATE_ACCOUNT_DETAILS = "https://0hds0sbd2l.execute-api.ap-northeast-1.amazonaws.com/dev/";

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

  editAccountDetails(userAccountDetails: any) {
    return this.httpClient.put(UPDATE_ACCOUNT_DETAILS,userAccountDetails,{responseType:'text' as 'json'});
  }

  public storeUserData(userData:any){
     this.user = userData;
  }

  public getUserData(){
    return this.user;
  }

  public isUserLoggedIn(){
    if(this.user === 'undefined') {
        return false;
    }
    return true;
  }

  public storeDataInSession(){
    localStorage.setItem("user",JSON.stringify(this.getUserData()));
  }

  public getDataFromSession(){
    return localStorage.getItem("user");
  }


}
