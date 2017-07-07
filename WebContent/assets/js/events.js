$(document).ready(function(){
	
  $.get("menu.html", function(data) {
    $("#header").html(data);
    
	if($(".saberNomeHTML").html() == "vendedorhome") {
		$(".class1").toggleClass("active");
	}else if($(".saberNomeHTML").html() == "vendedorfornecedor") {
		$(".class2").toggleClass("active");
	}else if($(".saberNomeHTML").html() == "vendedorcliente") {
		$(".class3").toggleClass("active");
	}else if($(".saberNomeHTML").html() == "vendedorranking") {
		$(".class4").toggleClass("active");
	}
	
  });
  
  
  $.get("rodape.html", function(data) {
	    $("#footer").html(data);
	  });
});