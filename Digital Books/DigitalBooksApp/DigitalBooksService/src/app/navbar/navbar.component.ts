import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserServiceService } from 'src/services/user-service.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  
  readerAccess=false;
  authorAccess = false;
  user:any;
  disabled="disabled";

  constructor(private userService : UserServiceService,private router : Router) { }

  ngOnInit(): void {
    this.user = this.userService.getUserData();
    console.log(this.user);
    
    if(this.user == 'author'){
        this.authorAccess=true;
    }else if (this.user == 'reader') {
      this.readerAccess = true;
    }
  }

}
