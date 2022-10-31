import { Component, OnInit } from '@angular/core';
import UserData from '../entity/UserData';
import { UsersystemService } from '../Services/usersystem.service';

@Component({
  selector: 'app-listgroup',
  templateUrl: './listgroup.component.html',
  styleUrls: ['./listgroup.component.css']
})
export class ListgroupComponent implements OnInit {

  users : UserData[] = [];
  constructor(private userService : UsersystemService){}
  sort(){
    //
  }

  deleteUserData(userData:UserData,index){
     const respose = this.userService.deletelUserDetails(userData);

     respose.subscribe(data => {
       console.log(data);
       this.users.slice(index,1);
     })

  }
  ngOnInit(): void {
    const promise = this.userService.getAllUserDetails();
    promise.subscribe(data =>{
      console.log(data);
      this.users = data as UserData[];
    })
  }

}
