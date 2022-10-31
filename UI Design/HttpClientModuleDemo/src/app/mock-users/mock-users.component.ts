import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-mock-users',
  templateUrl: './mock-users.component.html',
  styleUrls: ['./mock-users.component.css']
})
export class MockUsersComponent implements OnInit {

  users : any;

  constructor(private httpClient:HttpClient) { }

  ngOnInit(): void {
    let response = this.httpClient.get("https://jsonplaceholder.typicode.com/todos");
    //response.subscribe(data => console.log(data));
    response.subscribe(data => this.users=data);
  }

}
