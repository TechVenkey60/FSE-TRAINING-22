import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup,Validators,FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public loginForm: any = FormGroup;
  constructor(private httpClient : HttpClient, private formBuilder : FormBuilder,
              private router : Router) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email:['',[Validators.required,Validators.email]],
      password:['',[Validators.required]]
    })
  }

  loginUser(){
    //this.httpClient.post("",this.loginForm.value)
    //  .subscribe(res => {
    //    alert("User login is Sucessfull.")
    //    this.loginForm.reset();
    //    this.router.navigate(['home']);
    //  })

    console.log("=========================");
    
    console.log(this.loginForm.value);

    alert("User login is Sucessfull.")
        this.loginForm.reset();
       this.router.navigate(['home']);
    
  }

}
