import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BankServiceService } from 'src/services/bank-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup;
  submitted = false; 
 
  loggedInUser:any;
  errorMessage:any;
  loginFailedError:any;
  isLoginFailed=false;

  constructor(private formBuilder : FormBuilder,
    private router : Router,private userService : BankServiceService) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      userName:['',[Validators.required]],
      password:['',[Validators.required,Validators.minLength(8)]]
    })
  }

  loginUser(){
    if(this.loginForm.invalid){
      this.submitted = true;
      return
    }
    
    this.userService.userSignIn(this.loginForm.value)
    .subscribe((data:any) => {
      this.loggedInUser = data;
      let value = JSON.parse(this.loggedInUser);
      this.userService.storeUserData(value);
      alert("User logged in successful!");
      this.router.navigate(['/home']);
    },error=>{
      console.log("===========================");
      this.errorMessage = JSON.parse(error.error);
      this.loginFailedError = this.errorMessage.message;
      this.isLoginFailed=true;
      this.loginForm.reset();
      setTimeout(()=>{location.reload()},3000);
      console.log("===========================");
      
    });

  }
}
