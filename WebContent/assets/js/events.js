$(document).ready(function(){ 
  $.get("menu.html", function(data) {
	  
    $("#header").html(data);
  });
  
  $.get("rodape.html", function(data) {
	    $("#footer").html(data);
	  });
}); 

