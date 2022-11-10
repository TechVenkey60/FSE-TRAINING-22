import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserServiceService } from 'src/services/user-service.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  public signUpForm !: FormGroup;

  submitted=false;
  userData:any;
  isSignUpFailed=false;
  singUpFailedError:any;
  errorMessage:any;

  constructor(private userService:UserServiceService,private formBuilder : FormBuilder,
              private router : Router) { }

  ngOnInit(): void {
    this.signUpForm = this.formBuilder.group({
      userName:['',[Validators.required]],
      email:['',[Validators.required,Validators.email]],
      role:['',[Validators.required]],
      password:['',[Validators.required,Validators.minLength(8)]]
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
      this.router.navigate(['login']);
    },error => {
      console.log("===========================");
      this.errorMessage = JSON.parse(error.error);
      this.singUpFailedError = this.errorMessage.message;
      this.isSignUpFailed=true;
      setTimeout(()=>{location.reload()},3000);
      this.router.navigate(['signup']);
      console.log("===========================");
    });
  }

}
