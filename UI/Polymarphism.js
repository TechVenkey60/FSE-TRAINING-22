

class Animal{
    constructor(name) {
        this.name = name;
    }

    eats(){
        console.log(this.name +" eat foods");
    }
}

class Lion extends Animal{
    eats(){
        console.log(this.name+ " eats meat");
    }
}

let jinny = new Lion("Jinny");

jinny.eats();