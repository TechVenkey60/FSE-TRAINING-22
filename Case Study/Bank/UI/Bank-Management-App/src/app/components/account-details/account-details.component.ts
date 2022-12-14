import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BankServiceService } from 'src/services/bank-service.service';

@Component({
  selector: 'app-account-details',
  templateUrl: './account-details.component.html',
  styleUrls: ['./account-details.component.css']
})
export class AccountDetailsComponent implements OnInit {

  user:any;
  accountDetailsForm !:FormGroup;
  submitted=false;
  updatedUserData:any;
  response:any;
  hasAccountData=false;

  constructor(private userService : BankServiceService,private formBuilder : FormBuilder,
    private router : Router) { }

  ngOnInit(): void {
    this.user = this.userService.getUserData();
    this.updatedUserData = this.userService.getUserData();
    
    if(this.updatedUserData !== null){
          this.hasAccountData = true;
    }

    console.log(this.userService.getDataFromSession());
    

     this.accountDetailsForm = this.formBuilder.group({
      accountNumber:['',[Validators.required]], 
      fullName:['',[Validators.required]],
      email:['',[Validators.required,Validators.email]],
      password:['',[Validators.required,Validators.minLength(8)]],
      contactNumber:['',[Validators.required]],
      address:['',[Validators.required]],
      state:['',[Validators.required]],
      country:['',[Validators.required]]
     });
 
  }

  updateAccountDetails(){

    if(this.accountDetailsForm.invalid){
       this.submitted=true;
       return;
    }

    this.userService.editAccountDetails(this.accountDetailsForm.value)
                   .subscribe(data => {
                    this.response = data;
                    this.user = JSON.parse(this.response);
                    alert("Updated Account Details Successfully..!");
                    this.accountDetailsForm.reset();
                   },error => {
                      console.log(error.error);
                      alert("Something went wrong. Please check.");
                   });
  }

}
