import { Component, OnInit } from '@angular/core';
import { select, Store } from '@ngrx/store';
import { ToDo } from 'src/app/models/ToDo';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit {

  todos:ToDo[];

  constructor(private store:Store<{todos:ToDo[]}>) {
    store.pipe(select('todos')).subscribe(values => {
      this.todos = values;
      console.log(values);
    });
   }

  ngOnInit(): void {
  }

}
