import { Component, OnInit } from '@angular/core';
import { UserInfo } from './user';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.scss']
})
export class UserDetailsComponent implements OnInit {

  user: UserInfo = {
    "userId" : 1214,
    "userName" : "Venkatesh Kokkanti",
    "phone" : "9877676576",
    "email" : "venkey886@gmail.com",
    "salary" : 98000
  }

  constructor() { }

  ngOnInit(): void {
  }

}
