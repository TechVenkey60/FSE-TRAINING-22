import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-forms',
  templateUrl: './forms.component.html',
  styleUrls: ['./forms.component.css']
})
export class FormsComponent implements OnInit {

  userDetails = {
    fullName:"",
    birthDate:"",
    email:"",
    phone:"",
    gender:"",
    Occupation:"",
    idType:"",
    idNumber:"",
    authority:"",
    issuedState:"",
    issuedDate:"",
    expiryDate:""
  }

  saveUser(){
    console.log(this.userDetails);
    
  }
  constructor() { }

  ngOnInit(): void {
  }

}
