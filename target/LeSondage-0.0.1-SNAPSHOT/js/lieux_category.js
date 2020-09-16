var questionnaire = localStorage.getItem("questionnaire");

if(questionnaire == null){
	var url = document.URL;
	url = url.replace(/\/[^\/]*$/, '/arret.html');
	location.replace(url);
}

function registerTheUser(){
	var t = $.ajax({
	type : "GET",
	url : document.URL.replace(/\/[^\/]*$/, "/rest/user/register/" + questionnaire),
	success : function(response) {
		var id = response.substr(4);
		localStorage.setItem('uids', id);
		if (!response.includes("TRUE")) {
			document.getElementById('questionnaire-message').innerHTML = " Something went wrong.";
		}
	},
	error : function(response) {
		document.getElementById('questionnaire-message').innerHTML = " Something went wrong.";
	}
});
}
var count = 0;
window.onload = function () { 
	if (typeof history.pushState === "function") { 
		history.pushState("back", null, null);          
		window.onpopstate = function () { 
			history.pushState('back', null, null);              
			if(count == 1){
				var url = document.URL;
				url = url.replace(/\/[^\/]*$/, '/choixquestionnaire.html');
				location.replace(url);}
		}; 
	}
};
setTimeout(function(){count = 1;},200);
function encryptString(text) {
	var password = 'l5JmP+G0/1zB%;r8B8?2?2pcqGcL^3';
	var encrypted = CryptoJS.AES.encrypt(text, password);
	return encrypted;
}
function startQuestions() {
	var url = document.URL;
	url = url.replace(/\/[^\/]*$/, '/questions_lieux.html');
	location.replace(url);
}