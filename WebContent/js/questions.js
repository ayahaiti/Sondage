function getCookie(cname) {
	var name = cname + "=";
	var ca = document.cookie.split(';');
	for(var i = 0; i < ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) == ' ') {
			c = c.substring(1);
		}
		if (c.indexOf(name) == 0) {
			return c.substring(name.length, c.length);
		}
	}
	return "";
}
function encryptString(text) {
	var password = 'l5JmP+G0/1zB%;r8B8?2?2pcqGcL^3';
	var encrypted = CryptoJS.AES.encrypt(text, password);
	return encrypted;
}
var h=$.ajax({
	type : "GET",
	url : document.URL.replace(/\/[^\/]*$/,"/rest/user/getMedianOfAnswers"),
	dataType : "json",
	success : function(response) {
			listeOthersDegrees = encryptString(JSON
					.stringify(response));
			localStorage.setItem('othersdegrees', listeOthersDegrees);
	},
	error : function() {
		document.getElementById('index-message').innerHTML = " Something went wrong.";
	}
});
function addAnswers(arrayAnswers){
	var url = document.URL.replace(/\/[^\/]*$/,"/rest/user/reponse");
	$.ajax({
		type : "POST",
		url : url,
		contentType: 'application/json',
		dataType: "text",
		data: JSON.stringify(arrayAnswers),
		success : function(response) {
			responseOk(response);
		},
		error : responseKo
	});
}
function responseOk(response) {
	console.log(response);
}
function responseKo() {
	console.log("something went wrong in question");
}