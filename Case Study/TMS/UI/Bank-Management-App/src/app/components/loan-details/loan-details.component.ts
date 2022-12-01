import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BankServiceService } from 'src/services/bank-service.service';

@Component({
  selector: 'app-loan-details',
  templateUrl: './loan-details.component.html',
  styleUrls: ['./loan-details.component.css']
})
export class LoanDetailsComponent implements OnInit {
  
  transferForm !: FormGroup;
  submitted=false;
  accountNumber:any;
  user:any;
  loanDetails:any;
  loans:any;
  hasTableData=true;
  loggedInUser:any;
  balance:any;
  
  constructor(private userService : BankServiceService,private formBuilder : FormBuilder,
    private router : Router) { }

  ngOnInit(): void {
    
    let data:any = this.userService.getDataFromSession();
    this.user = JSON.parse(data);
    this.accountNumber = this.user.accountNumber;
    this.balance = this.user.balance;
    
    this.getAllLoanDetails();

    this.getUserDetails();
       
    this.transferForm = this.formBuilder.group({
      accountNumber:['',[Validators.required]],
      amount:['',[Validators.required]],
      trxType:['',[Validators.required]],
      trxAmount:['',[Validators.required]],
      receiverAccountNumber:['',[Validators.required]],
      date:['',[Validators.required]]
    })
  }



  transferMoney(){
    if(this.transferForm.invalid){
      this.submitted=true;
      return;
    }

    this.userService.applyForLoan(this.transferForm.value)
        .subscribe(data => {
          alert("Loan Applied Successfully...");
          this.getAllLoanDetails();
          
          this.getUserDetails();
          this.transferForm.reset();
        }, error => {
          console.log(error.error);
        })
  }


  getAllLoanDetails(){
    console.log("==========================");
    console.log(this.accountNumber);
    
    console.log("=====================");
    
    
    this.userService.getAppliedLoanDetails(this.accountNumber)
    .subscribe(data => {
     this.loans = data;
     this.loanDetails = JSON.parse(this.loans);
     console.log(this.loanDetails);
     
     if(this.loanDetails.length !==0){
       this.hasTableData = false;
     }
    },error => {
      console.log(error.error);
    });

  }

  getUserDetails(){
    this.userService.getUserDetails(this.accountNumber)
    .subscribe(data => {
      this.loggedInUser = data;
      let value = JSON.parse(this.loggedInUser);
      this.userService.storeDataInSession(value);
      this.user = value;
      this.balance = this.user.balance;
    },error => {
      console.log(error.error);
    });
  }




  sortData(e:any){
     const sortValue = e.target.value;
     if('ASC' === sortValue){
       console.log("ASC Order");
       this.userService.sortTransactionDetails(this.accountNumber,"ASC");
     }
     if('DESC' === sortValue){
       console.log("DESC Order");
    }
     
  }

}
