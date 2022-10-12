

// function Employee(name, id, salary){
//   this.name = name;
//   this.id = id;
//   this.salary =salary;
//   this.bonus = function (){
//       let bonus = 100000 + this.salary;
//       console.log(bonus);
//   }

//   this.getEmployees = function (){
//       console.log("employees data :"+ this.name+" "+this.id+" "+this.salary);
//   }
// }

// let emp = new Employee("Venkat",2,3232);
// emp.getEmployees();
// emp.bonus();


console.log("========Reverse An Array Values");

function reverseArrayData(){
  const names = ["Venkat", "Suman", "Raj"];
  console.log("Given input is :"+names);
  console.log(names.reverse());
}

reverseArrayData();

console.log("=====Sort values from low to high");


function sortValues(){
  const values = [2,3,1,5,6,4]
  console.log("Given input "+values);

  console.log("AFter sorting");

  console.log(values.sort());
}

sortValues();

console.log("=========== Print Negative values from an Array ===========");


function printNegativeValues(){
  const values = [2,3,1,5,6,4,-44,-99,-41]
  console.log("Given input "+values);

  values.forEach(value => {
     if(value <0){
        console.log(value);
     }
  });

}

printNegativeValues();

console.log("======== Sum of numbers from An Array of integers");

function sumOfNumbersInArray(){
  const values = [10,20,30,40,50]
  console.log("Given input "+values.reverse());
  let sum = 0;
  values.forEach(value => {
      sum = sum +value;
  });

  console.log("Sum of Total Numbers in Array :"+sum);
}

sumOfNumbersInArray();

console.log("================ Divisable by 10 =============");

function DivisableByTen(values){
  if(values / 10 ==0){
    return true;
  }else{
    return false;
  }

}

let flagValue = DivisableByTen(20);
console.log(" is 20 divisible by 10 :"+flagValue);
flagValue = DivisableByTen(7);
console.log(" is 7 divisible by 10 :"+flagValue);

console.log("============ Remove white Spaces =======");

const myname = "Venkatesh Kokkanti   @";
console.log("Given input :"+myname);
const na = myname.replace(/ /g,"");
console.log(myname.split(' ').join('@@'));

console.log(na);


