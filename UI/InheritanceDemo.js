
class Car {
    setName(name){
        this.name = name;
      console.log("Car name is "+name);
    }

    drive(){
        console.log("Car "+this.name+" is running...");
    }

    stop(){
        console.log("Stopped "+this.name+" !");
    }
}

class TataCar extends Car{
    driver(driverName){
     console.log("Car is driven by "+driverName);
    }
}

let child = new TataCar();
child.setName("RR CAR");
child.drive();
child.stop();

child.driver("Venkat");