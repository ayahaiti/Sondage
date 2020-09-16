var count = 0;
window.onload = function () {
	if (typeof history.pushState === "function") { 
		history.pushState("back", null, null);          
		window.onpopstate = function () { 
			history.pushState('back', null, null);              
			if(count == 1){
				var url = document.URL;
				url = url.replace(/\/[^\/]*$/, '/index.html');
				location.replace(url);
			}
		}; 
	}
};
setTimeout(function(){count = 1;},200);

function encryptString(text) {
	var password = 'l5JmP+G0/1zB%;r8B8?2?2pcqGcL^3';
	var encrypted = CryptoJS.AES.encrypt(text, password);
	return encrypted;
}

function test() {
	var url = document.URL;
	url = url.replace(/\/[^\/]*$/, '/choixquestionnaire.html');
	location.replace(url);
}
var questionsForEnfantsResults = $.ajax({
	type : "GET",
	url : document.URL.replace(/\/[^\/]*$/,"/rest/user/questions/enfants"),
	success : function(response) {
		lesQuestions = encryptString(JSON.stringify(response));
		localStorage.setItem("questionEnfantsForResults", lesQuestions);
	},
	error : function(response) {
		document.getElementById('index-message').innerHTML = " Something went wrong.";
	}
});
var questionsForTouristesResults = $.ajax({
	type : "GET",
	url : document.URL.replace(/\/[^\/]*$/,"/rest/user/questions/touristes"),
	success : function(response) {
		lesQuestions = encryptString(JSON.stringify(response));
		localStorage.setItem("questionTouristesForResults", lesQuestions);
	},
	error : function(response) {
		document.getElementById('index-message').innerHTML = " Something went wrong.";
	}
});
