var count = 0;
window.onload = function () {
	if (typeof history.pushState === "function") {
		history.pushState("back", null, null);          
		window.onpopstate = function () { 
			history.pushState('back', null, null);              
			if(count == 1){
				var url = document.URL;
				url = url.replace(/\/[^\/]*$/, '/personalinfo.html');
				location.replace(url);}
		};
	}
};

setTimeout(function(){count = 1;},200);

var listeDesDegreDeSimilarite;
var listeOthersDegrees;



function encryptString(text) {
	var password = 'l5JmP+G0/1zB%;r8B8?2?2pcqGcL^3';
	var encrypted = CryptoJS.AES.encrypt(text, password);
	return encrypted;
}
$(".btn-group6[role='group'] button").on('click', function() {
	$(this).siblings().removeClass('active')
	$(this).addClass('active');
});

$(".btn-group2[role='group'] button").on('click', function() {
	$(this).siblings().removeClass('active')
	$(this).addClass('active');
});
$(".btn-group3[role='group'] button").on('click', function() {
	$(this).siblings().removeClass('active')
	$(this).addClass('active');
});
$(document).ready(function() {
	$('[data-toggle="tooltip"]').tooltip();
});

function encryptString(text){
	var password = 'l5JmP+G0/1zB%;r8B8?2?2pcqGcL^3';
	var encrypted = CryptoJS.AES.encrypt(text, password);
	return encrypted;
}

function checkInfo(){
	var email=$("#exampleInputEmail1").val();
	var profession=$("#professionid").val();
	var expert=$(".btn-group6 button.active").text();
	var gender = $(".btn-group2 button.active").text();
	var agegroup = $(".btn-group3 button.active").text();
	var questionnaire=localStorage.getItem("questionnaire");
	register(expert,email,profession,gender,agegroup,questionnaire);
}

function register(a,b,c,d,e,f){
	var url = document.URL.replace(/\/[^\/]*$/,"/rest/user/registeruser");
	var id=localStorage.getItem("uids");
	if (id == null) {
		var url = document.URL;
		url = url.replace(/\/[^\/]*$/, '/arret.html');
		location.replace(url);
	}
	if(id==0){
		var url = document.URL;
		url = url.replace(/\/[^\/]*$/, '/arret.html');
		location.replace(url);
	}
	var user={id:id,expert:a,email:b,profession:c,sexe:d,agegroup:e,questionnaire:f};
	var j = $.ajax({
		type : "POST",
		url : url,
		contentType: 'application/json',
		data:JSON.stringify(user),
		dataType: "text",
		success : function(response) {
			goToQuestions(response,f);
		},
		error : function(response){
			document.getElementById('error-message').innerHTML = " Something went wrong.";
		}
	});
}
function goToQuestions(response,f) {
	if (!response.includes("TRUE")) {
		document.getElementById('error-message').innerHTML = " Something went wrong.";
	}
	else{
		var url=document.URL;
		url= url.replace(/\/[^\/]*$/, '/remerciements.html');
		location.replace(url);
	}
}