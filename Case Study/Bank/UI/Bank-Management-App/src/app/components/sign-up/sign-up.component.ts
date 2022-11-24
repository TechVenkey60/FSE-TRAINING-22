import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BankServiceService } from 'src/services/bank-service.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  public signUpForm !: FormGroup;

  submitted=false;
  userData:any;
  isSignUpFailed=false;
  singUpFailedError:any;
  errorMessage:any;

  constructor(private userService : BankServiceService,private formBuilder : FormBuilder,
              private router : Router) { }

  ngOnInit(): void {
    this.signUpForm = this.formBuilder.group({
      fullName:['',[Validators.required]],
      userName:['',[Validators.required]],
      email:['',[Validators.required,Validators.email]],
      password:['',[Validators.required,Validators.minLength(8)]],
      dob:['',[Validators.required]],
      accountType:['',[Validators.required]],
      panCardNumber:['',[Validators.required]],
      contactNumber:['',[Validators.required]],
      address:['',[Validators.required]],
      state:['',[Validators.required]],
      country:['',[Validators.required]]
    })
  }



  signUpUser(){
    if(this.signUpForm.invalid){
      this.submitted=true;
      return;
    }

    this.userService.userSignUp(this.signUpForm.value)
    .subscribe(data => {
      this.userData= data;
      alert("User has been registered successfully!!")
      this.signUpForm.reset();
      this.router.navigate(['/login']);
    },error => {
      console.log("===========================");
      this.errorMessage = JSON.parse(error.error);
      this.singUpFailedError = this.errorMessage.message;
      this.isSignUpFailed=true;
      setTimeout(()=>{location.reload()},3000);
      this.router.navigate(['/register']);
      console.log("===========================");
    });
  }

}
