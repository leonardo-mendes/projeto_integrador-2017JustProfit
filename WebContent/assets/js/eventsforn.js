$(document).ready(function(){ 
  $.get("menuforn.html", function(data) {
    $("#header").html(data);
  });
  
  $.get("rodape.html", function(data) {
	    $("#footer").html(data);
	  });
}); 


