$('.form').find('input, textarea').on('keyup blur focus', function (e) {
  
  var $this = $(this),
      label = $this.prev('label');

	  if (e.type === 'keyup') {
			if ($this.val() === '') {
          label.removeClass('active highlight');
        } else {
          label.addClass('active highlight');
        }
    } else if (e.type === 'blur') {
    	if( $this.val() === '' ) {
    		label.removeClass('active highlight'); 
			} else {
		    label.removeClass('highlight');   
			}   
    } else if (e.type === 'focus') {
      
      if( $this.val() === '' ) {
    		label.removeClass('highlight'); 
			} 
      else if( $this.val() !== '' ) {
		    label.addClass('highlight');
			}
    }

});

$('.tab a').on('click', function (e) {
  
    e.preventDefault();
    
    $("#btPequenasEmpresas").attr("disabled", true);
    $("#btMediasEmpresas").attr("disabled", true);
    $("#btGrandesEmpresas").attr("disabled", true);
    $("#btOpcao1Vendedores").attr("disabled", true);
    $("#btOpcao2Vendedores").attr("disabled", true);
    
    var id = $(this).attr('id');
    if (id === "aIdEmpresa") {
        $("#formLinkCadastrar").parent().addClass('active');
        $("#formLinkCadastrar").parent().siblings().removeClass('active');
    }else if (id === "aIdVendedor") {
        $("#formLinkLogar").parent().addClass('active');
        $("#formLinkLogar").parent().siblings().removeClass('active');
    }else {
        $(this).parent().addClass('active');
        $(this).parent().siblings().removeClass('active');
    }
    
    target = $(this).attr('href');
    
    $('.tab-content > div').not(target).hide();
    
    $(target).fadeIn(600);
    
});

$('document').ready(
    function(){
        $('input:radio').change(
            function(){
                
                if($('input[name=segmento]:checked', '#formCadastrar').val() === "pf") {
                   
                    $("#label_cnpj_cpf").html('CPF<span class="req">*</span>');
                    $("#label_ie_rg").html('RG<span class="req">*</span>');
                    
                }else {
                    
                    $("#label_cnpj_cpf").html('CNPJ<span class="req">*</span>');
                    $("#label_ie_rg").html('Inscri&#231;&#227;o Estadual<span class="req">*</span>');
                    
                }
                
            }
        );  
    }
);