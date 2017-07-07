$(document).ready(function(){ 
  $.get("menuforn.html", function(data) {
    $("#header").html(data);
    
	if($(".saberNomeHTML").html() == "fornecedorhome") {
		$(".class1").toggleClass("active");
	}else if($(".saberNomeHTML").html() == "fornecedorvendedor") {
		$(".class2").toggleClass("active");
	}else if($(".saberNomeHTML").html() == "fornecedorproduto") {
		$(".class3").toggleClass("active");
	}else if($(".saberNomeHTML").html() == "fornecedorranking") {
		$(".class4").toggleClass("active");
	}
    
  });
  
  $.get("rodape.html", function(data) {
	    $("#footer").html(data);
	  });
}); 


