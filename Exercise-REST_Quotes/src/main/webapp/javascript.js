////var url= "api/quote/random";
//var con = { method: "get" };
//var prom = fetch(url,con); // måde1

//var prom = fetch("api/quote/random",{ method: "get"}); // måde 2

//CLICK AND SHOW ALL QUOTES
var allQuotes = document.getElementById("allQuotes").addEventListener("click", showAll);
function showAll() {
fetch("api/quote/all", { method: "get"}).then(function (response) { // måde 3
    return response.json();
}).then(function (data) {
    document.getElementById("display").innerHTML = data.Quotes;
});
}

//CLICK THE BUTTON, LOAD RANDOM QUOTE
var random = document.getElementById("random").addEventListener("click", randomQuotes);
function randomQuotes() {
    fetch("api/quote/random", { method: "get"}).then(function (response) { // måde 3
        return response.json();
    }).then(function (data) {
        document.getElementById("display").innerHTML = data.quote;
    });
}

//CLICK THE BUTTON,CREATE A NEW QUOTE 
var create = document.getElementById("create").addEventListener("click", createQuote);
function createQuote() {
    fetch("api/quote/", { method: "post"}).then(function (response) { // måde 3
        return response.json();
    }).then(function (data) {
        document.getElementById("display").innerHTML = "Ny quote text: " + data.quote;
    });
}

//EDIT QUOTE WITH NEW TEXT
var edit = document.getElementById("edit").addEventListener("click", editQuote);
function editQuote() {
    var id = document.getElementById("editId").value;
    var text = document.getElementById("editText").value;
    fetch("api/quote/" + id + "?quote=" + text, {method: "put"}).then(function (response) { // måde 3
        return response.json();
    }).then(function (data) {
        document.getElementById("display").innerHTML = "Ny quote text: " + data.quote;
    });
    document.getElementById("editId").value = '';
    document.getElementById("editText").value = '';
    showAll();
}


//DELETE QUOTE
var deleteQ = document.getElementById("delete").addEventListener("click", deleteQuote);
function deleteQuote() {
    var deleteId = document.getElementById("deleteId").value;
    fetch("api/quote/" + deleteId, { method: "delete"}).then(function (response) { // måde 3
        return response.json();
    }).then(function (data) {
        document.getElementById("display").innerHTML = "DELETED QUOTE ID " +deleteId;
    });
    document.getElementById("deleteId").value = '';
    showAll();
}