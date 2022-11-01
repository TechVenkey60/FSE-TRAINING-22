import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { TodoAdd } from 'src/app/actions/todo.action';
import { ToDo } from 'src/app/models/ToDo';

@Component({
  selector: 'app-add-todo',
  templateUrl: './add-todo.component.html',
  styleUrls: ['./add-todo.component.css']
})
export class AddTodoComponent implements OnInit {

  constructor(private store:Store<{todo:ToDo[]}>) {

   }

   AddTodo(todo:string){
     const newTodo = new ToDo();
     newTodo.title = todo;
     this.store.dispatch(new TodoAdd(newTodo));
   }

  ngOnInit(): void {
  }

}
