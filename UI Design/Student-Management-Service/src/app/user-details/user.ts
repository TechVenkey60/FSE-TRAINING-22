export class UserInfo {

    userId : number;
    userName : string;
    email : string;
    phone : string;
    salary: number;

   constructor(userId: number,userName: string,email: string,phone: string,salary:number){
       this.userId = userId;
       this.userName = userName;
       this.email = email;
       this.phone = phone;
       this.salary = salary;
   }
}