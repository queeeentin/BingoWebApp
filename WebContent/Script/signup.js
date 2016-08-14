window.onload = function (){
	document.forms[0].onsubmit = checkFormValidation; 
}

var invalidFields = [];
var errormessages = [];


function checkFormValidation (){
	var allgood = false;
	//debugger;
	//get all tag (elements), within the form element, in an array
	var alltags = document.forms[0].getElementsByTagName("*");
	for (var i = 0; i < alltags.length; i++){
		
		var thisElement = alltags[i];
		if(validBasedOnClass(thisElement)){

			allgood = true;
			}else{
			invalidFields.push(alltags[i].id);
			thisElement.className = "invalid";
			var parentElement = thisElement.parentNode;

			if(parentElement.nodeName == "LABEL"){
				parentElement.className="invalid";
				}
			}
	}
}

function validBasedOnClass(thisElement){
	var thisClassName = thisElement.className;
	var thisChecked = thisElement.checked;
	var thisValue = thisElement.value;
	var thisId = thisElement.id;
	var good = false;

	switch(thisClassName){
		case "": good=true;
		case "invalid":break;
		case "reqd":if (thisValue != "") {
			good = true;
		}
		if (thisId == "password1" ) {
			if(document.getElementById("password").value != thisValue){
				var password = document.getElementById("password")
				password.value = "";
				thisValue = "";
				good= false;
			}
		}
		if (thisId == "email"){
			//debugger;
			var invalidCharacters = "/:,:";
			thisvalue = thisValue.toString();
			if (thisValue.indexOf("@") == -1 || thisValue.indexOf(".") == -1 || thisValue.indexOf("@") == -1){
				good = false;
			}

			for (var i = 0; i < invalidCharacters.length; i++) {
				var badchar = invalidCharacters[i];
				if (thisValue.indexOf(badchar) > -1){ 
					good = false;

				}
			}
			debugger;
			if(!good){invalidFields.push("invalidEmail");}
			console.log(thisValue);
		}		



		case "checked": if (thisChecked) {
			good = true;
		}
	}
		return good;

}
