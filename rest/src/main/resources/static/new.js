
function loadNationalities(id) {
	var select = $('#nationalities');
    select.find("option").remove();
    $.ajax({
        url: "http://localhost:8080/nationalities"
    }).then(function(data) {
       $.each(data,function(){
		   if (this.id == id)
			   select.append("<option value=" + this.id + " selected='true'>" + this.name + "</option> ");
		   else
			   select.append("<option value=" + this.id + ">" + this.name + "</option> ");

		})
    });
}


function loadPlaces(id) {
	var select = $('#places');
    select.find("option").remove();
    $.ajax({
        url: "http://localhost:8080/places"
    }).then(function(data) {
       $.each(data,function(){
		   if (this.id == id)
			   select.append("<option value=" + this.id + "  selected='true'>" + this.name + "</option> ");
		   else
			   select.append("<option value=" + this.id + ">" + this.name + "</option> ");

		})
    });
}

function loadSexGroup(id) {
	var select = $('#sexdiv');
	if (2 == id)
		select.append("<input name='sex' value='2' type='radio'checked/>Feminino<br />");
	else
		select.append("<input name='sex' value='2' type='radio'/>Feminino<br />");
	if (1 == id)
		select.append("<input name='sex' value='1' type='radio'checked/>Masculino<br />");
	else
		select.append("<input name='sex' value='1' type='radio'/>Masculino<br />");
	if (0 == id)
		select.append("<input name='sex' value='0' type='radio' checked/>Sem Declarar<br />");
	else if (id == null)
		select.append("<input name='sex' value='0' type='radio' checked/>Sem Declarar<br />");
	else
		select.append("<input name='sex' value='0' type='radio'/>Sem Declarar<br />");
}

$(document).ready(function() {
	var urlParams = new URLSearchParams(window.location.search);
	const id=urlParams.get('id')
	if(id!=null) {   
	fetch("http://localhost:8080/personbyid/"+id, {
			method: 'get'
		}).then(function(response) {                      // first then()
				if (response.ok) {
					response.json().then(json => {
						$("#id").val(json.id);
						$("#name").val(json.name);
						$("#cpf").val(json.cpf);
						$("#email").val(json.email);
						$("#birthDate").val(json.birthDate.substring(0, 10));
						loadNationalities(json.nationalityId);
						loadPlaces(json.birthPlaceId);
						loadSexGroup(json.sex);
					});
				}
				throw new Error('Something went wrong.');
			});

	}else
	{
		loadNationalities();
		loadPlaces();
		loadSexGroup();
	}
 
});


function newSendForm() {
	if (isFormValid()) {
		let temp = { 'id': jQuery("#id").val(),'name': jQuery("#name").val(), 'cpf': jQuery("#cpf").val(), 'birthDate': jQuery("#birthDate").val(), 'email': jQuery("#email").val(), 'nationalityId': jQuery("#nationalities :selected").val(), 'birthPlaceId': jQuery("#places :selected").val(), 'sex': jQuery("input[type='radio'][name='sex']:checked").val() };
		temp = JSON.stringify(temp);

		fetch("http://localhost:8080/newperson", {
			method: 'post',
			headers: {
				'Content-Type': 'application/json'
				// 'Content-Type': 'application/x-www-form-urlencoded',
			},
			body: temp,
		})
			.then(function(response) {                      // first then()
				if (response.ok) {
					window.location = "list.html";
					//return response.text();
				}
				throw new Error('Something went wrong.');
			})
			.then(function(text) {                          // second then()
				console.log('Request successful', text);
			})
			.catch(function(error) {                        // catch
				console.log('Request failed', error);
				var ul = $('#errorList');
				ul.find("li").remove();
				$("#formErrors").text("Aconteceu um erro. Revisar seu CPF ou Email, provavelmente ja estejam cadastrados.");
			});
	}
}

function isFormValid(){
	
	let valid = true;
	const cpfRegex = "([0-9]{2}[\.]?[0-9]{3}[\.]?[0-9]{3}[\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\.]?[0-9]{3}[\.]?[0-9]{3}[-]?[0-9]{2})"
	const emailRegex = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
	const birthDateRegex = /^\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$/
	const cpf = jQuery("#cpf").val();
	const name = jQuery("#name").val();
	const email = jQuery("#email").val();
	const birthDate = jQuery("#birthDate").val();

	var ul = $('#errorList');
	ul.find("li").remove();
	if (name == "") {
		valid = false;
		ul.append("<li style='color:red'>Nome precisa um valor</li>");
	}
		
	if(cpf == ""){
		valid = false;
		ul.append("<li style='color:red'>CPF precisa um valor</li>");
	}
	
	if (!cpf.match(cpfRegex)) {
		valid = false;
		ul.append("<li style='color:red'>CPF formato nao valido XXX.YYY.ZZZ-AAA</li>");
	}	

	if(email == ""){
		valid = false;
		ul.append("<li style='color:red'>Email precisa um valor</li>");
	}
	
	if(!email.match(emailRegex)){
		valid = false;
		ul.append("<li style='color:red'>Email formato nao valido XXX@YYY.ZZZ</li>");
	}

	if (birthDate == "") {
		valid = false;
		ul.append("<li style='color:red'>Data nacimento precisa um valor</li>");
	}
	if (!birthDate.match(birthDateRegex)) {
		valid = false;
		ul.append("<li style='color:red'>Data nacimento formato nao valido YYYY-MM-DD</li>");
	}
	

	if(!valid)
		$("#formErrors").text("Erros no formulario");

	return valid;
}
