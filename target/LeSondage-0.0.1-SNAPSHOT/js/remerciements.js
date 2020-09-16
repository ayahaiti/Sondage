var count = 0;
window.onload = function () {
	if (typeof history.pushState === "function") { 
		history.pushState("back", null, null);          
		window.onpopstate = function () {
			history.pushState('back', null, null);              
			if(count == 1){
				var url = document.URL;
				url = url.replace(/\/[^\/]*$/, '/remerciements.html');
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
var id=localStorage.getItem("uids");
if (id == null) {
	var url = document.URL;
	url = url.replace(/\/[^\/]*$/, '/arret.html');
	location.replace(url);
}
var u=$.ajax({
	type : "POST",
	url : document.URL.replace(/\/[^\/]*$/,"/rest/user/getanswers"),
	contentType: 'application/json',
	data:id,
	dataType: "text",
	success : function(response) {
		listeDesDegreDeSimilarite =encryptString(response);
		localStorage.setItem("degrees", listeDesDegreDeSimilarite);
	},
	error : function(response){
		document.getElementById('error-message').innerHTML = " Something went wrong.";
	}
});
function goToAcceuil() {
	var url="";
	url= document.URL.replace(/\/[^\/]*$/, '/index.html');
	location.replace(url);
}
function fin() {
	if (num == 20) {
		document.getElementById("quessuiv").style.display = "none";
		document.getElementById("fin").style.display = "block";
	} else {
		document.getElementById("quessuiv").style.display = "block";
		document.getElementById("fin").style.display = "none";
	}
}

function start() {
	if (num == 1 || num == null) {
		document.getElementById("quesprec").style.display = "none";
	} else {
		document.getElementById("quesprec").style.display = "block";
	}
}

function goToResults(){
	var questionnaire=localStorage.getItem("questionnaire");
	if(questionnaire=="Enfants"){
		var url="";
		url= document.URL.replace(/\/[^\/]*$/, '/resultsEnfants.html');
		location.replace(url);
	}
	else{
		var url="";
		url= document.URL.replace(/\/[^\/]*$/, '/resultsTouristes.html');
		location.replace(url);
	}
}