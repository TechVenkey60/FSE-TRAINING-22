function onBluerEvent() {
    let data = document.getElementById("blur").value;
    data = data.toUpperCase();
}

function onChange() {

    let display = document.getElementById("food").value;
    document.getElementById("demo").innerHTML = "Selected Item is "+display;
    
}

function onFocusEvent(x) {
    document.getElementById(x).style.background = "#9b1ac0";
}