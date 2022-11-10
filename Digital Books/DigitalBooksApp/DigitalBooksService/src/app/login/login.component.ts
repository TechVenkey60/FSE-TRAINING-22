import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup,Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { observable, Observable } from 'rxjs';
import { UserServiceService } from 'src/services/user-service.service';

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
              private router : Router,private userService:UserServiceService) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email:['',[Validators.required,Validators.email]],
      password:['',[Validators.required]]
    })
  }

  loginUser(){
    if(this.loginForm.invalid){
      this.submitted = true;
      return
    }
    
    this.userService.userSignIn(this.loginForm.value)
    .subscribe(data => {
      this.loggedInUser = data;
      let value = JSON.parse(this.loggedInUser);
      this.userService.setUserData(value);
      this.loginForm.reset();
      alert("User logged in successful!");
      this.router.navigate(['/homePage']);
    },error=>{
      console.log("===========================");
      this.errorMessage = JSON.parse(error.error);
      this.loginFailedError = this.errorMessage.message;
      this.isLoginFailed=true;
      setTimeout(()=>{location.reload()},2000);
      this.router.navigate(['login']);
      console.log("===========================");
      
    });

  }

}
