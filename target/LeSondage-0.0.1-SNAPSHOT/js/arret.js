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
setTimeout(function(){count = 1;},200);
function goToFirstPage() {
	var url = document.URL;
	url = url.replace(/\/[^\/]*$/, '/index.html');
	location.replace(url);
}