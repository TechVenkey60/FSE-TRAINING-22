import { InitialState } from "@ngrx/store/src/models";
import { ActionParent } from "../actions/todo.action";
import { ToDo } from "../models/ToDo";


const inialState: ToDo[] = [
    {title:"Todo 1"},
    {title:"Todo 2"},
    {title:"Todo 3"},
    {title:"Todo 4"}
]

export function ToDoReducer(state = inialState,
                          action: ActionParent){
                              switch(action.type){
                                  default: return state;
                              }
                          }