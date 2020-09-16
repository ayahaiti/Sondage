var count = 0;

window.onload = function () { 
	if (typeof history.pushState === "function") { 
		history.pushState("back", null, null);        
		window.onpopstate = function () {
			history.pushState('back', null, null);              
			if(count == 1){
				var url = document.URL;
				url = url.replace(/\/[^\/]*$/, '/activites_category.html');
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

var a = $.ajax({
	type : "GET",
	url : document.URL.replace(/\/[^\/]*$/,"/rest/user/questionsactivites"),
	dataType : "json",
	success : function(response) {
			var encryptedresponse = encryptString(JSON.stringify(response));
			localStorage.setItem('questionsactivites', encryptedresponse);
	},
	error : function() {
		document.getElementById('index-message').innerHTML = " Something went wrong.";
	}
});

function startQuestions() {
	var url = document.URL;
	url = url.replace(/\/[^\/]*$/, '/questions_activites.html');
	location.replace(url);
}