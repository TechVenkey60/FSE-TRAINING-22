import { Component, OnInit } from '@angular/core';
import UserData from '../entity/UserData';
import { UsersystemService } from '../Services/usersystem.service';

@Component({
  selector: 'app-forms',
  templateUrl: './forms.component.html',
  styleUrls: ['./forms.component.css']
})
export class FormsComponent implements OnInit {
  

  userInfo : UserData = new UserData();

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

 

  constructor(private userService: UsersystemService) { }

  ngOnInit(): void {
  }

  saveUser(){
    const response = this.userService.saveUserData(this.userInfo);
    response.subscribe(data => {console.log(data)},function(error){
      console.log(error);
      
    })
   
  }

}
