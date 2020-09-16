var count = 0;
window.onload = function () { 
	if (typeof history.pushState === "function") { 
		history.pushState("back", null, null);          
		window.onpopstate = function () { 
			history.pushState('back', null, null);              
			if(count == 1){
				var url = document.URL;
				url = url.replace(/\/[^\/]*$/, '/index.html');
				location.replace(url);}
		}; 
	}
};
var a = $.ajax({
	type : "GET",
	url : document.URL.replace(/\/[^\/]*$/,"/rest/user/questionstransport"),
	dataType : "json",
	success : function(response) {
			var encryptedresponse = encryptString(JSON
					.stringify(response));
			localStorage.setItem('questionstransport', encryptedresponse);
			console.log(encryptedresponse);
	},
	error : function() {
		document.getElementById('questionnaire-message').innerHTML = " Something went wrong.";
	}
});
var a = $.ajax({
	type : "GET",
	url : document.URL.replace(/\/[^\/]*$/,"/rest/user/questionsLieux"),
	dataType : "json",
	success : function(response) {
			var encryptedresponse = encryptString(JSON
					.stringify(response));
			localStorage.setItem('questionslieux', encryptedresponse);
	},
	error : function() {
		document.getElementById('questionnaire-message').innerHTML = " Something went wrong.";
	}
});
setTimeout(function(){count = 1;},200);

function encryptString(text) {
	var password = 'l5JmP+G0/1zB%;r8B8?2?2pcqGcL^3';
	var encrypted = CryptoJS.AES.encrypt(text, password);
	return encrypted;
}

var questionnaire;

function activateenfants() {
	var elementenfants = document.getElementById("boutonenfants");
	elementenfants.classList.add('active');
	var elementouristes = document.getElementById("boutontouriste");
	elementouristes.classList.remove('active');
}
function activatetouristes() {
	var elementouristes = document.getElementById("boutontouriste");
	elementouristes.classList.add('active');
	var elementenfants = document.getElementById("boutonenfants");
	elementenfants.classList.remove('active');
}
$(document).ready(function() {
	$('[data-toggle="tooltip"]').tooltip();
});

function startQuestions(questionnaire){
	if (questionnaire == 'Touristes') {
		var url = document.URL;
		url = url.replace(/\/[^\/]*$/, '/lieux_category.html');
		location.replace(url);
	} else {
		var url = document.URL;
		url = url.replace(/\/[^\/]*$/, '/modes_de_transport_category.html');
		location.replace(url);
	}
}
function checkInfo() {
	var questionnaire = $(".btn-group1 button.active").text(); 
	if (questionnaire == "") {
		document.getElementById('questionnaire-message').innerHTML = "Veuillez choisir un questionnaire.";
	} else {
		localStorage.setItem("questionnaire", questionnaire);
		startQuestions(questionnaire);
	}
}
