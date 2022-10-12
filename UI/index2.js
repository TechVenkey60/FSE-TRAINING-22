
console.log("============================================");

let number = 100;
console.log("Given input is :" + number);
console.log("PostFix Increment :" + (number++)); //100
console.log(number);
console.log("Postfix Decrement :" + (number--)); //101
console.log(number);
console.log("Prefix Increment :" + (++number)); //101
console.log(number);
console.log("PreFix Decrement :" + (--number)); //100
console.log(number);

console.log("============================================");

console.log("===============Logical Operators============");

// let hello;
// alert(!hello) // !
// hello = "Click ok to continue.";
// alert(!hello)
// alert(hello)


//logical And Operator

//alert(10 && 20);

//logical OR Operator


// alert(3 || 2 || 1);
// alert(1 || 2 || 3);
// alert(2 || 3 || 1);


// Arrays
console.log("====== Arrays ========");
const names = ["Venkat", "Suman", "Raj"];
console.log(names);


const food = [];
food[0] = "Pizza"
food[1] = "Medium Pizza"
food[2] = "Large Pizza"

console.log(food[1]);
console.log(food);


const menu = [...food, "Chicken65", "Mutton25", "Egg Rice", "Curd Rice"];
console.log(menu);

let values = [12, 21, 34, 56, 44, 5644];

let value = Math.max(...values);
console.log("Max value :" + value);


console.log("========== Map =========");

const students = new Map([
    ["Venkat", 27],
    ["Suman", 23],
    ["Hari", "25"]
]);

console.log(students);
students.set("Raj", 24);
console.log(students.get("Raj"));





console.log("============ Loops ==========");

for (let index = 0; index < menu.length; index++) {
    console.log(menu[index]);
}


console.log("======= if else ========");

if (menu.length > 10) {
    console.log("We have more than 10 food items");
} else if (menu.length == 7) {
    console.log("We have 10 food items in Menu");
} else {
    console.log("We have less than 10 food items in Menu");
}

console.log("======= Switch Case ========");

switch (menu.length) {
    case 0:
        console.log("No food items in menu");
        break;
    case 1:
        console.log("1 food items in menu");
        break;
    case 2:
        console.log("2 food items in menu");
        break;
    case 7:
        console.log("7 food items in menu");
        break;

    default:
        console.log("20 food items in menu");
        break;
}



console.log("====== Arrow Function =========");

function Area(number1, number2) {
    console.log(number1*number2);
}

Area(10,20);


area1 = (num1,num2) => {
    console.log(num1*num2);
}


area1(100,100);


console.log("===== Set is JS Collection ======");


const set = new Set([1,2,3,1,4,2,4,2,6,7,8,5]);
console.log(set);

set.add(09);
set.delete(1);
console.log(set);

console.log(set.has(090));




console.log("===============Test ===============");


const empMap = new Map([
    ["Venkatesh",[1,3]],
    ["Suman",[2,32]],
    ["Raj",[10,3]],
    ["Mohan",[3,4]]
]);


console.log(empMap);


const empIdSet = new Set(
    [1,3,46,3.5] );


console.log(empIdSet);








console.log("================== Promise ================");

let promise = new Promise(function(resolve,reject){
    var passedScore = 600;
    var securedScore = 700;

    if(passedScore <=securedScore){
        resolve()
    }else{
        reject()
    }
})

promise.then(function(){
    console.log("congrants. You cleared and good score!");
}).catch(function(){
    console.log("Better luck next time and work hard!");
})



async function sayMyName(){
    const response = await fetch("https://api.github.com/users");
    console.log("Before user resonse");
    let user = await response.json();
    console.log("User resloved");
    return user;
}

console.log("Before calling SayMyName method");
let say = sayMyName();
console.log("After calling SayMyName method");
console.log(say);
console.log("last lime of SayMyname method");
say.then(user => console.log(user))