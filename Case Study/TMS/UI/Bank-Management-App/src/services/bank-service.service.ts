import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';


const LOGIN_URL = "http://localhost:9966/api/vrk/tms/login";
const CREATE_NEW_USER_URL = "http://localhost:9966/api/vrk/tms/newUserAccount";
const APPLY_FOR_LOAN ="http://localhost:9966/api/vrk/tms/save/transaction";
const USER_LOAN_DETAILS = "http://localhost:9966/api/vrk/tms/user/";
const USER_DETAILS = "http://localhost:9966/api/vrk/tms/registered/";
const UPDATE_ACCOUNT_DETAILS = "http://localhost:9966/api/vrk/tms/update/accountDetails";
const GET_USER_TRANSACTION_DETAILS="http://localhost:9966/api/vrk/tms/user/"

const AWS_LOGIN_URL = "http://ec2-13-230-99-99.ap-northeast-1.compute.amazonaws.com:9966/api/vrk/tms/login";
const AWS_CREATE_NEW_USER_URL = "http://ec2-13-230-99-99.ap-northeast-1.compute.amazonaws.com:9966/api/vrk/tms/newUserAccount";
const AWS_APPLY_FOR_LOAN ="http://ec2-13-230-99-99.ap-northeast-1.compute.amazonaws.com:9966/api/vrk/tms/save/transaction";
const AWS_USER_LOAN_DETAILS = "http://ec2-13-230-99-99.ap-northeast-1.compute.amazonaws.com:9966/api/vrk/tms/user/";
const AWS_USER_DETAILS = "http://ec2-13-230-99-99.ap-northeast-1.compute.amazonaws.com:9966/api/vrk/tms/registered/";
const AWS_UPDATE_ACCOUNT_DETAILS = "http://ec2-13-230-99-99.ap-northeast-1.compute.amazonaws.com:9966/api/vrk/tms/update/accountDetails";
const AWS_GET_USER_TRANSACTION_DETAILS="http://ec2-13-230-99-99.ap-northeast-1.compute.amazonaws.com:9966/api/vrk/tms/user/"


@Injectable({
  providedIn: 'root'
})
export class BankServiceService {
 
 
  user:any;
  constructor(private httpClient : HttpClient) { }

  userSignIn(loginData: any) {
    return this.httpClient.post(AWS_LOGIN_URL,loginData,{responseType:'text' as 'json'});
  }

  userSignUp(newUserData: any) {
    return this.httpClient.post(AWS_CREATE_NEW_USER_URL,newUserData,{responseType:'text' as 'json'});
  }

  applyForLoan(loanInfo: any) {
    return this.httpClient.post(AWS_APPLY_FOR_LOAN,loanInfo,{responseType:'text' as 'json'});
  }


  getAppliedLoanDetails(accountNumber: any) {
    return this.httpClient.get(AWS_USER_LOAN_DETAILS+accountNumber,{responseType:'text' as 'json'});
  }

  editAccountDetails(userAccountDetails: any) {
    return this.httpClient.put(AWS_UPDATE_ACCOUNT_DETAILS,userAccountDetails,{responseType:'text' as 'json'});
  }


  getUserDetails(accountNumber: any) {
    return this.httpClient.get(AWS_USER_DETAILS+accountNumber,{responseType:'text' as 'json'});
  }

  sortTransactionDetails(accountNumber: any, sortBy: string) {
    return this.httpClient.get(AWS_GET_USER_TRANSACTION_DETAILS+accountNumber+"?sortBy="+sortBy,{responseType:'text' as 'json'});
  }

  public storeUserData(userData:any){
     this.user = userData;
  }

  public getUserData(){
    return this.user;
  }


  public isUserLoggedIn():boolean{
    if(this.getDataFromSession()){
        return true;
    }
    return false
  }

  public storeDataInSession(userData:any){
    localStorage.setItem("user",JSON.stringify(userData));
  }

  public getDataFromSession(){
    return localStorage.getItem("user");
  }

  public clear() {
    localStorage.clear();
  }


}
