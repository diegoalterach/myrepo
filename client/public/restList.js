const nationalities = new Map();
    $.ajax({
        url: "http://localhost:8080/nationalities"
    }).then(function(data) {
       $.each(data,function(){
        	nationalities.set(this.id,this.name);
		})
    });

const places = new Map();
    $.ajax({
        url: "http://localhost:8080/places"
    }).then(function(data) {
       $.each(data,function(){
        	places.set(this.id,this.name);
		})
    });

const sex = new Map();
sex.set(0,"Sem declarar");
sex.set(1,"Masculino");
sex.set(2,"Feminino");

function loadTable() {

	var table = $('#personTable');
	table.find("tbody tr").remove();
	$.ajax({
    	url: "http://localhost:8080/persons/"
	}).then(function(data) {
		$.each(data, function() {
			let content ="<tr id='row"+ this.id +"'><td>"
			 + this.id + "</td><td>" 
			+ this.name + "</td><td>"
			+ this.cpf + "</td><td>"
			+ this.email + "</td><td>"
			+ returnSex(this.sex) + "</td><td>"
			+ returnNationality(this.nationalityId)+"</td><td>" 
			+ returnPlace(this.birthPlaceId) + "</td> <td>"+
			"<form><input type='button' value='Editar' onclick='editPerson("+ this.id +")' /></form> "+
			 "</td><td>	"+		
			"<form><input type='button' value='Apagar' onclick='newDeletePerson("+ this.id +")' /></form> "+
			"</td></tr>" ;
			
			table.append(content);
		})
	});
}



function returnNationality(id){
	return nationalities.get(id);
}

function returnPlace(id){
	return places.get(id);
}

function returnSex(id){
	return sex.get(id);
}

$(document).ready(loadTable);

function newDeletePerson(id){
	fetch('http://localhost:8080/deleteperson/'+id, {
			method: 'get'
		}).then(function(response) {                      // first then()
				if (response.ok) {
					loadTable();
				}
				throw new Error('Something went wrong.');
			});
}

function editPerson(id){
     window.location = "new.html?id="+id;
}
