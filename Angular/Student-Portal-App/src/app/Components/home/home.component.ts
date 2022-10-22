import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  title = "Fill Students Details";

   studentDetails = {
    firstName:"",
    lastName:"",
    email:"",
    Class:0
  }

  save(){
    console.log(this.studentDetails);
    
  }
  constructor() { }

  ngOnInit(): void {
  }

}
