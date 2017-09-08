PersonsRefresh();

document.getElementById("PersonsRefresh").addEventListener("click", function ()
{
    PersonsRefresh();
});

document.getElementById("PersonAdd").addEventListener("click", function ()
{
    PersonAdd();
    document.getElementById("TablePersonsBody").innerHTML += "<tr><td>New person: </td><td>" + document.getElementById("PersonFirstName").value + "</td><td>" + document.getElementById("PersonLastName").value + "</td><td>" + document.getElementById("PersonPhoneNumber").value + "</td></tr>";
});

document.getElementById("PersonEdit").addEventListener("click", function ()
{
    PersonEdit();
    PersonsRefresh();
});

document.getElementById("PersonDelete").addEventListener("click", function ()
{
    PersonDelete();
    PersonsRefresh();
});

function PersonsRefresh()
{
    fetch("api/person/all", {method: "get"})
            .then(function (response) {
                return response.json();
            })
            .then(function (json) {
                document.getElementById("TablePersonsBody").innerHTML = "";

                var rows = "";

                for (var i in json)
                {
                    rows += '<tr>';
                    rows += '<td>' + json[i].id + '</td>';
                    rows += '<td>' + json[i].fName + '</td>';
                    rows += '<td>' + json[i].lName + '</td>';
                    rows += '<td>' + json[i].phone + '</td>';
                    rows += '<td><input type="button" onclick="PersonDeleteID(' + json[i].id + ')" value="Delete" /></td>';
//                    rows += '<td><a href="#" class="btndelete" data-personid="' + json[i].id + '">Delete</a></td>';
                    rows += '</tr>';
                }

                document.getElementById("TablePersonsBody").innerHTML = rows;
            })
            .catch(function (error) {
                alert("Unable to refresh!");
            });
}

function PersonAdd()
{
    var person = {
        fName: document.getElementById("PersonFirstName").value,
        lName: document.getElementById("PersonLastName").value,
        phone: Number(document.getElementById("PersonPhoneNumber").value)
    };

    fetch("api/person", {
        method: "post",
        body: JSON.stringify(person),
        headers: new Headers({'content-type': 'application/json'})
    })
            .then(function (response) {
                return response.json();
            })
            .then(function (json) {
                document.getElementById("PersonFirstName").value = "";
                document.getElementById("PersonLastName").value = "";
                document.getElementById("PersonPhoneNumber").value = "";
                PersonsRefresh();
            })
            .catch(function (error) {
                document.getElementById("PersonFirstName").value = "";
                document.getElementById("PersonLastName").value = "";
                document.getElementById("PersonPhoneNumber").value = "";
                alert("Person not added!");
            });
}

function PersonDelete(){
    var id = document.getElementById("PersonID").value;
    fetch("api/person"+id, {
        method: "delete",
    })
            .then(function (response) {
                return response.json();
            })
            .then(function (json) {
                document.getElementById("PersonFirstName").value = "";
                document.getElementById("PersonLastName").value = "";
                document.getElementById("PersonPhoneNumber").value = "";
                PersonsRefresh();
            })
            .catch(function (error) {
                document.getElementById("PersonFirstName").value = "";
                document.getElementById("PersonLastName").value = "";
                document.getElementById("PersonPhoneNumber").value = "";
                alert("Person not found!");
            });

}