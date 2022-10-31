import { Action } from "@ngrx/store";
import { TodoActionType } from "../shared/todo-actions-type-enum";



export class ActionParent implements Action{
    type : any;
    payLoad : any;
}


export class TodoAdd implements ActionParent{
    type : TodoActionType.Add;
    constructor(public payLoad : any){}
}