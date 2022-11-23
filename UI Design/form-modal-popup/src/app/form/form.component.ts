import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  formLogin !: FormGroup;

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
   this.formLogin = this.formBuilder.group({
     
   });
  }

  submitLogin(){

  }

}
