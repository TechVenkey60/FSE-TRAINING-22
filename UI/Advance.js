console.log("Classes and Constructors");

class Employee{
    // constructor(name,id,designation){
    //   this.name = name;
    //   this.id = id;
    //   this.designation = designation;
    // }

    setEmployee(name,id,designation){
        this.name = name;
        this.id = id;
        this.designation = designation;
      }

      getName(){
          return this.name;
      }

      getId(){
          return this.id;
      }

      getDeSignation(){
          return this.getDeSignation;
      }
}

// let emp1 = new Employee("Suman",12,"Pega DEV");
// let emp2 = new Employee("Venkat",343,"FSE");

let emp1 = new Employee();
let emp2 = new Employee();

emp1.setEmployee("Suman",12,"Pega DEV");
emp2.setEmployee("Venkatesh Kokkanti",343,"FSE");

console.log(emp1.name);
console.log(emp2.name);
