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
  
  loanForm !: FormGroup;
  submitted=false;
  accountNumber:any;
  user:any;
  loanDetails:any;
  loans:any;
  hasTableData=true;
  
  constructor(private userService : BankServiceService,private formBuilder : FormBuilder,
    private router : Router) { }

  ngOnInit(): void {
    
    this.user = this.userService.getUserData();
    this.accountNumber = this.user.accountNumber;

    this.getAllLoanDetails();
       
    this.loanForm = this.formBuilder.group({
      accountNumber:['',[Validators.required]],
      loanType:['',[Validators.required]],
      loanAmount:['',[Validators.required]],
      loanAppliedDate:['',[Validators.required]],
      roi:['',[Validators.required]],
      durationOfLoan:['',[Validators.required]]
    })
  }



  applyLoan(){
    if(this.loanForm.invalid){
      this.submitted=true;
      return;
    }

    this.userService.applyForLoan(this.loanForm.value)
        .subscribe(data => {
          alert("Loan Applied Successfully...");
          this.getAllLoanDetails();
          this.loanForm.reset();
        }, error => {
          console.log(error.error);
        })
  }


  getAllLoanDetails(){
    this.userService.getAppliedLoanDetails(this.accountNumber)
    .subscribe(data => {
     this.loans = data;
     this.loanDetails = JSON.parse(this.loans);
     if(this.loanDetails.length !==0){
       this.hasTableData = false;
     }
    },error => {
      console.log(error.error);
    });

  }

}
