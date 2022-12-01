import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BankServiceService } from 'src/services/bank-service.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private userService : BankServiceService,
              private router : Router
              ) { }

  ngOnInit(): void {
  }


  isUserLoggedIn():boolean{
    return this.userService.isUserLoggedIn();
  }

  userLogout(){
    this.userService.clear();
    this.router.navigate(['/home']);
  }





}
