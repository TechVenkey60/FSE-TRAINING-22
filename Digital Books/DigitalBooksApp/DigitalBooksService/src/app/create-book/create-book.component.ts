import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup,Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { DigitalBooksOperationsService } from 'src/services/digital-books-operations.service';
import { UserServiceService } from 'src/services/user-service.service';

@Component({
  selector: 'app-create-book',
  templateUrl: './create-book.component.html',
  styleUrls: ['./create-book.component.css']
})
export class CreateBookComponent implements OnInit {

  userInfo:any;
  authorId:number=0;
  submitted=false;
  digitalBooks:any;

  hasTableData=false;
  isAuthorIssue=false;
  errorMessage:any
  errorResponse:any;

  createFormBook!:FormGroup;

  constructor(private userService:UserServiceService,private formBuilder:FormBuilder,
              private digitalBookService: DigitalBooksOperationsService,private router:Router) { }

  ngOnInit(): void {
    
    this.createFormBook = this.formBuilder.group({
      title : ['',Validators.required],
      category:['',Validators.required],
      author:['',Validators.required],
      price:['',Validators.required],
      publisher:['',Validators.required],
      publishedDate:['',Validators.required],
      content:['',Validators.required],
      active:['',Validators.required],
      logo:['',Validators.required]
    });

    this.userInfo = this.userService.getUserData();
    this.authorId=this.userInfo.userId;
    console.log(this.authorId);
    
  }

  createNewBook(){
    
    if(this.createFormBook.invalid){
      this.submitted=true;
      return;
    }

    console.log("---------------------");
    
    console.log(this.createFormBook.value);

    console.log("---------------------");
    

    this.digitalBookService.addNewBook(this.authorId,this.createFormBook.value)
    .subscribe(data => {
      this.digitalBooks = data;
      console.log("---------------------");
    
      console.log(data);
  
      console.log("---------------------");
      
      
      alert("New Book has been added successfully!!")
      this.router.navigate(['/authorBooks']);
    },error => {
      console.log("===========================");
      this.errorMessage = JSON.parse(error.error);
      this.isAuthorIssue = true;
      this.errorResponse = this.errorMessage.message;
      console.log("===========================");
    });

    


  }
}
