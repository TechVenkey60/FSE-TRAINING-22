import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  public signUpForm !: FormGroup;

  constructor(private httpClient : HttpClient, private formBuilder : FormBuilder,
              private router : Router) { }

  ngOnInit(): void {
    this.signUpForm = this.formBuilder.group({
      userName:[''],
      email:[''],
      role:[''],
      password:['']
    })
  }

  signUpUser(){
    this.httpClient.post<any>("",this.signUpForm.value)
    .subscribe(response => {
      alert("User SingUp Successfully done.");
      this.signUpForm.reset();
      this.router.navigate(['login']);
    }, err =>{
      alert("Something went wrong. Please check and provide proper values");
    })
  }

}
