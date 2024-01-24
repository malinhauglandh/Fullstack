var x = document.getElementById("btn");
x.addEventListener("click", function(){
    hideText();
});

function hideText() {
    var y = document.getElementById("text");
    if(y.style.display === "none"){
        y.style.display = "block";
    } else {
        y.style.display = "none";
    }
}


var stringArray = ["I like to travel", "I like to read", "I like to dance", "I like to watch movies", "I like to swim",
"I like to be with friends", "I like to eat", "I like to gym", "I like to sleep", "I like to cook", "I like to play games",
"I like to listen to music", "I like to play guitar", "I like to play piano", "I like to play football", "I like to play basketball"];

var randomString = stringArray[Math.floor(Math.random()*stringArray.length)];
var randomString2 = stringArray[Math.floor(Math.random()*stringArray.length)];
var randomString3 = stringArray[Math.floor(Math.random()*stringArray.length)];

document.getElementById("string1").innerHTML = randomString;
document.getElementById("string2").innerHTML = randomString2;
document.getElementById("string3").innerHTML = randomString3;

let count = 0;
let btn = document.getElementById("btn2");
let display = document.getElementById("display");
btn.onclick = function() {
    count ++;
    display.innerHTML = count;
}
