function carrregaDadosVendedorHome(vend) {
			var xhttp;
				if (window.XMLHttpRequest) {
					xhttp = new XMLHttpRequest();
					} else {
					xhttp = new ActiveXObject("Microsoft.XMLHTTP");
					}
				xhttp.onreadystatechange = function() {
				
					if (this.readyState == 4 && this.status == 200) {
						insereNosCampVendHome(this.responseText);
					}
				};
				
				xhttp.open("GET","CarregaDadosVendedor?vend=" + vend, true);
				xhttp.send();
}

function carrregaDadosFornecedorHome(forne) {
	
	var xhttp;
		if (window.XMLHttpRequest) {
			xhttp = new XMLHttpRequest();
			} else {
			xhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
		xhttp.onreadystatechange = function() {
		
			if (this.readyState == 4 && this.status == 200) {
				insereNosCampFornHome(this.responseText);
			}
		};
		
		xhttp.open("GET","CarregaDadosFornecedor?forn=" + forne, true);
		xhttp.send();
}

function insereNosCampVendHome(temp){
	var dados = temp.split(",");
	var tempRazaoSocial = dados[0];
	var tempcodVend = dados[1];
	var tempie_rg = dados[2];
	var tempemail = dados[3];
	var tempcodUsu = dados[4];
	var tempcnpj_cpf = dados[5];
	var temptelefone = dados[6];
	var tempqtdFornAtend = dados[7];
	
	var tempregiao = dados[8];
	var tempcidade = dados[9];
	var templogradouro = dados[10];
	var tempestado = dados[11];
	var tempbairro = dados[12];
	var tempcep = dados[13];
	
	
	
	document.getElementById("razaoSocial").value = tempRazaoSocial;
	document.getElementById("codvend").value = tempcodVend;
	document.getElementById("ierg").value = tempie_rg;
	document.getElementById("email").value = tempemail;
	document.getElementById("codusu").value = tempcodUsu;
	document.getElementById("cnpj").value = tempcnpj_cpf;
	document.getElementById("telefone").value = temptelefone;
	document.getElementById("qtdforn").value = tempqtdFornAtend;
	
	document.getElementById("regiao").value = tempregiao;
	document.getElementById("cidade").value = tempcidade;
	document.getElementById("logradouro").value = templogradouro;
	document.getElementById("estado").value = tempestado;
	document.getElementById("bairro").value = tempbairro;
	document.getElementById("cep").value = tempcep;
}

function insereNosCampFornHome(tempForn){
	var dados = tempForn.split(",");
	var tempRazaoSocial = dados[0];
	var tempcodForn = dados[1];
	var tempie_rg = dados[2];
	var tempemail = dados[3];
	var tempcodUsu = dados[4];
	var tempcnpj_cpf = dados[5];
	var temptelefone = dados[6];
	var tempqtdVendAtend = dados[7];
	
	var tempregiao = dados[8];
	var tempcidade = dados[9];
	var templogradouro = dados[10];
	var tempestado = dados[11];
	var tempbairro = dados[12];
	var tempcep = dados[13];
	
	
	
	document.getElementById("razaoSocial").value = tempRazaoSocial;
	document.getElementById("codForn").value = tempcodForn;
	document.getElementById("ierg").value = tempie_rg;
	document.getElementById("email").value = tempemail;
	document.getElementById("codusu").value = tempcodUsu;
	document.getElementById("cnpj").value = tempcnpj_cpf;
	document.getElementById("telefone").value = temptelefone;
	document.getElementById("qtdvend").value = tempqtdVendAtend;
	
	document.getElementById("regiao").value = tempregiao;
	document.getElementById("cidade").value = tempcidade;
	document.getElementById("logradouro").value = templogradouro;
	document.getElementById("estado").value = tempestado;
	document.getElementById("bairro").value = tempbairro;
	document.getElementById("cep").value = tempcep;
}


function listaProdutos(codVend) {
	var xhttp;
		if (window.XMLHttpRequest) {
			xhttp = new XMLHttpRequest();
			} else {
			xhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
		xhttp.onreadystatechange = function() {
		
			if (this.readyState == 4 && this.status == 200) {
				insereDadosProdutos(this.responseText);
			}
		};
		
		xhttp.open("GET","ListaTodosProdutos?codVend="+codVend, true);
		xhttp.send();
}


function insereDadosProdutos(Lista){
	//idProduto, Nome, NomeFornecedor, PrecoProd, DescMax
	var dados = Lista.split(";");
	var linhaProduto = "";
	for(var i=0; i<(dados.length-1); i++){
		if(i == dados.length-2){
			document.getElementById("vlrSaldo").value = dados[i];
		}else{
			var dados2 = dados[i].split(",");
			var codProd = dados2[0];
			var NomeProdr = dados2[1];
			var NomeFornecedor = dados2[2];
			var PrecoProd = dados2[3];
			var DescMax = dados2[4];
			linhaProduto += "<tr ><td >"+codProd+"</td><td >"+NomeProdr+"</td><td >"+NomeFornecedor+"</td><td >"+PrecoProd+"</td><td >"+
			DescMax+"</td></tr>";
		}
	}
	
	document.getElementById("listaProdutos").innerHTML = linhaProduto;
    
    $('.valor').priceFormat({
        prefix: 'R$ ',
        centsSeparator: ',',
        limit: 10,
        thousandsSeparator: '.',
        centsLimit: 1
    });

$('table#tabelaProdutos tbody tr td:nth-child(4)').priceFormat({
    prefix: 'R$ ',
    centsSeparator: ',',
    thousandsSeparator: '.',
    centsLimit: 1
});

$('table#tabelaProdutos tbody tr').click(function (){
    $('table#tabelaProdutos tbody tr').removeClass('select');
    $('table#tabelaProdutos tbody tr').css('background','#fff');
    $('table#tabelaProdutos tbody tr').css('color','#000');
    $(this).addClass('select');
    $('.textoinvalido').css('display','none');
    $('tr.select').css('background','#7B68EE');
    $('tr.select').css('color','#fff');
    /*alert("p");
    $('#preco').val("1");
    var p = $('tr.select').text();
    alert(p);*/
    var preco = $('tr.select').children('td:nth-child(4)').text();
    $('#preco').val(preco);
    var nomeProd = $('tr.select').children('td:nth-child(2)').text();
    $('#nomeProd').val(nomeProd);
    var nomeForn = $('tr.select').children('td:nth-child(3)').text();
    $('#nomeForn').val(nomeForn);
    $('#vlrTotal').val(preco);
    $('#desc').val("0");
    $('#qtd').val("1");
});

function descontoNoti(){
    var desc = $('tr.select').children('td:nth-child(5)').text();
    var a = parseInt($('#desc').val());

    if(a>desc){
       var options = {
            'title': 'Ops !',
            'style': 'error',
            'message': 'Porcentagem maxima de desconto excedida!',
            'icon': 'warning',
        };
        var n = new notify(options);
        n.show();
        return true;
    }
    else{
        return false;
    }

}

$('#btnFinalizarPedido').click(function(){
    var cont=0; 
    var vetor = [];
    $('#tabelaPedidos tbody tr').each(function () {
        var colunas = $(this).children();
        if(cont==0){
           }  
        else{
            var juncao=
                $(colunas[0]).find("span").text() + ',' + 
                $(colunas[2]).find("span").text() + ',' + 
                $(colunas[5]).find("span").text()+ '#';
            vetor.push(juncao);
        }
        cont++;
    }); 
    
    var tosca="";
    for(var i=0; i<(vetor.length); i++){
        tosca+=vetor[i];
    }
    alert(tosca);
    return tosca;
        
});

$('#tabelaPedidos').on('click', 'button.btn-danger', function(){
    $(this).closest ('tr').remove ();

    $(function() {
        var vlrPedido = 0;
        $('#tabelaPedidos tbody tr td:nth-child(7)').each( function() {
            vlrPedido += parseFloat($(this).text());
        });
        $('#vlrPedido').val( vlrPedido );
    });

    $(function() {
        var vlrDesconto = 0;
        $('#tabelaPedidos tbody tr td:nth-child(4)').each( function() {
            vlrDesconto += parseFloat($(this).text());
        });
        $('#vlrDesconto').val( vlrPedido );
    });
});


$('#btnInserirProduto').click(function(){
    var nomeProd = $('#nomeProd').val();
    var preco = parseFloat($('#preco').val().replace("R$ ", ""));
    var vlrtable = parseFloat($('tr.select').children('td:nth-child(5)').text());
    var id = $('tr.select').children('td:nth-child(1)').text();
    var desc = $('#desc').val();
    var qtd = parseInt($('#qtd').val());
    var vlrTotal = $('#vlrTotal').val();
    var totaldesc = (parseFloat(preco)-parseFloat($('tr.select').children('td:nth-child(4)').text().replace("R$ ", "")))*(-1);
    var Tdesc = qtd * totaldesc;

    var isContains = $('#tabelaPedidos tbody tr td:nth-child(1)').text().indexOf(id) > -1;

    descMaximo = descontoNoti();

    if (nomeProd == ""){
        var options = {
            'title': 'Ops !',
            'style': 'error',
            'message': 'Selecione um item!',
            'icon': 'warning',
        };
        var s = new notify(options);
        s.show();
    }
    else if (isContains == true){
        var options = {
            'title': 'Ops !',
            'style': 'error',
            'message': 'Este item ja está no seu pedido!',
            'icon': 'warning',
        };
        var n = new notify(options);
        n.show();
    }
    else if(isContains == false){
        if(descMaximo = true){
            $('#tabelaPedidos tbody tr:last').after('<tr><td><span>' + id + '</span></td><td>' + nomeProd + '</td><td><span>' + preco + '</span></td><td>' + desc + ' %</td><td>' +  Tdesc.toFixed(2) + '</td><td><span>' + qtd + '</span></td><td>' + vlrTotal + '</td><td class="text-center"><button class="btn btn-danger"><i class="fa fa-trash"></i></button></td></tr>');
        }
    }

    $(function() {
        var vlrPedido = 0;
        $('#tabelaPedidos tbody tr td:nth-child(7)').each( function() {
            vlrPedido += parseFloat($(this).text());
        });
        $('#vlrPedido').val(vlrPedido);
        
        var vlrDesconto = 0;
        $('#tabelaPedidos tbody tr td:nth-child(5)').each( function() {
            vlrDesconto += parseFloat($(this).text());
        });
        $('#vlrDesconto').val(vlrDesconto);
    });

    $(function() {  
        var vlrSoma = parseFloat($('#vlrDesconto').val()) - parseFloat($('#vlrSaldoHidden').val());
        $('#vlrSaldo').val(parseFloat(parseInt(vlrSoma)*-1));
        $('#vlrSaldo').priceFormat({
            prefix: 'R$ ',
            centsSeparator: ',',
            thousandsSeparator: '.',
            centsLimit: 1
        });
    });

});	

$("#qtd").keyup(function(){
    //var desc = $("#desc").text();
    //  alert(desc);
    var preco = parseFloat($('#preco').val());
    //var desco = preco * (parseFloat(desc)/100);
    var qtd = parseInt($('#qtd').val());
    //$('#vlrTotal').val(a.toString);
    $('#vlrTotal').val(Math.round((preco*qtd) * 100) / 100);

    var valor = $('#vlrTotal').val();
    if (isNaN(valor)) {
        $('#vlrTotal').val("0");
    }
});

$("#desc").blur(function(){
    var desc = $('tr.select').children('td:nth-child(5)').text();
    var a = parseInt($('#desc').val().replace("R$ ",""));

    if(a>desc){
       var options = {
            'title': 'Ops !',
            'style': 'error',
            'message': 'Porcentagem maxima de desconto excedida!',
            'icon': 'warning',
        };
        var n = new notify(options);
        n.show();
    }
    else{
        var qtd = parseInt($('#qtd').val());
        var preco = parseInt($('tr.select').children('td:nth-child(4)').text().replace("R$ ",""));
        var precoTotal = preco - (preco*(a/100));
        $('#preco').val(precoTotal);
        $('#vlrTotal').val(parseInt(precoTotal)*qtd);
    }

    var valor = $('#vlrTotal').val();
    if (isNaN(valor)) {
        $('#vlrTotal').val("0");
    }

    valor();
});

$("#preco").blur(function(){
    var desc = parseInt($('tr.select').children('td:nth-child(5)').text());
    var preco = parseFloat($('tr.select').children('td:nth-child(4)').text());
    preco = preco.replace("R$ ", "");
    var minimo = preco - (preco*(desc/100));
    var qtd = parseInt($('#qtd').val());
    var precoAtual= parseFloat($('#preco').val().replace("R$ ", ""));
    precoAtual = precoAtual.replace("R$ ", "");
    var porc = parseFloat(((((precoAtual/preco)*100)-100)*(-1)));

    if(precoAtual<minimo){
        $('#preco').val(0);
        $('#qtd').val(0);
        $('#desc').val(0);
        $('#vlrTotal').val(0);
        $('#nomeProd').val("");
        $('#nomeForn').val("");
        $('.textoinvalido').css('display','block');
       }
    else{
        $('.textoinvalido').css('display','none');
        var T = precoAtual*qtd;
        $('#vlrTotal').val(T.toFixed(2));
        $('#desc').val(porc); 
    }

    /*var valor = $('#vlrTotal').val();
    if (isNaN(valor)) {
        $('#vlrTotal').val("0,00");
    }*/
});

/*
$('#element').priceFormat({
    suffix: '€',
    prefix: '',
    clearPrefix: true,
    clearSuffix: true,
    thousandsSeparator: '',
    clearOnEmpty: true,
    limit: 2,
    centsLimit: 1,
    allowNegative: true
});
$("#button").click(function() {
    alert($('#element').unmask());
});
*/

// Price Format

// Masks
/*$('.data').mask('00/00/0000', {clearIfNotMatch: true});
$('.tempo').mask('00:00:00', {clearIfNotMatch: true});
$('.data_tempo').mask('00/00/0000 00:00:00', {clearIfNotMatch: true});
$('.cep').mask('00000-000', {clearIfNotMatch: true});
$('.fone').mask('(00) 00000-0000', {clearIfNotMatch: true});
$('.foneFixo').mask('(00) 0000-0000', {clearIfNotMatch: true});
$('.cpf').mask('000.000.000-00', {clearIfNotMatch: true});
$('.cnpj').mask('00.000.000/0000-00', {clearIfNotMatch: true});
$('.valor').mask("#.##0,00", {reverse: true});*/

// DataTable
$('table').DataTable({
    responsive: true,
    processing: true,
    /*ajax: {
        url: "",
        type: 'POST'
    },*/
    order: [ 0, 'asc' ],
    "oLanguage": {
        "sEmptyTable": "Nenhum registro encontrado",
        "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
        "sInfoEmpty": "Mostrando 0 até 0 de 0 registros",
        "sInfoFiltered": "(Filtrados de _MAX_ registros)",
        "sInfoPostFix": "",
        "sInfoThousands": ".",
        "sLengthMenu": "_MENU_ resultados por página",
        "sLoadingRecords": "Carregando...",
        "sProcessing": "Processando...",
        "sZeroRecords": "Nenhum registro encontrado",
        "sSearch": "Pesquisar",
        "oPaginate": {
            "sNext": "Próximo",
            "sPrevious": "Anterior",
            "sFirst": "Primeiro",
            "sLast": "Último"
        },
        "oAria": {
            "sSortAscending": ": Ordenar colunas de forma ascendente",
            "sSortDescending": ": Ordenar colunas de forma descendente"
        }   
    }
});

}

function carregaCliente(codVend) {
	var xhttp;
		if (window.XMLHttpRequest) {
			xhttp = new XMLHttpRequest();
			} else {
			xhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
		xhttp.onreadystatechange = function() {
		
			if (this.readyState == 4 && this.status == 200) {
				insereDadosCliente(this.responseText);
			}
		};
		
		xhttp.open("GET","CarregaDadosCliente?codVend="+codVend, true);
		xhttp.send();
}


function insereDadosCliente(Lista){
	//idProduto, Nome, NomeFornecedor, PrecoProd, DescMax
	var dados = Lista.split(",");
	var nome = dados[0];
	var cnpj = dados[1];
	var ie = dados[2];
	var telefone = dados[3];
	var email = dados[4];
	var regiao = dados[5];
	var linhaBase = "<tr  class='active'><td >Razão Social</td><td > CNPJ</td><td >Inscrição Estadual</td><td >Telefone</td><td >Email</td><td>Região</td></tr>";
	var linha = "<tr ><td >"+nome+"</td><td >"+cnpj+"</td><td >"+ie+"</td><td >"+telefone+"</td><td >"+
	email+"</td><td>"+regiao+"</td></tr>";
	document.getElementById("tabelaCliente").innerHTML = linhaBase + linha;
	
}


function carrregaDadosVendedorFornecedor(forne) {
	var xhttp;
		if (window.XMLHttpRequest) {
			xhttp = new XMLHttpRequest();
			} else {
			xhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
		xhttp.onreadystatechange = function() {
		
			if (this.readyState == 4 && this.status == 200) {
				insereNosCampFornecedorVendedor(this.responseText);
			}
		};
		
		xhttp.open("GET","CarregaDadosVendForneSvlt?forne=" + forne, true);
		xhttp.send();
}

function insereNosCampFornecedorVendedor(temp){
	var dados = temp.split(";");//divide os objetos
	var linhaBase = "<tr  class='active'><td >Razão Social</td><td > CNPJ</td><td >Inscrição Estadual</td><td >Telefone</td><td >Email</td><td>Região</td></tr>";
	var linhaProduto= "";
	for(var i=0; i<(dados.length-1); i++){
			var dados2 = dados[i].split(",");
			var nome = dados2[0];
			var cnpj = dados2[1];
			var ie = dados2[2];
			var telefone = dados2[3];
			var email = dados2[4];
			var reg = dados2[5];
			
			linhaProduto += "<tr ><td >"+nome+"</td><td >"+cnpj+"</td><td >"+ie+"</td><td >"+telefone+"</td><td >"+
			email+"</td><td>"+reg +"</td></tr>";
	}
	
	document.getElementById("recebeDadosVendedor").innerHTML = linhaBase + linhaProduto;
}

function habilitaEdicao(){
	var inputs1 = document.getElementById("formedit1");
	var inputs2 = document.getElementById("formedit2");
	var inputs3 = document.getElementById("formedit3");
	var inputs4 = document.getElementById("formedit4");
	for(var i = 0; i < inputs1.length; i++) {
		if(inputs1[i] == inputs1[1]){
		}else{
			inputs1[i].disabled = false;
		}
	}
	for(var i = 0; i < inputs2.length; i++) {
		if(inputs2[i] == inputs2[0] || inputs2[i] == inputs2[3]){
		}else{
			inputs2[i].disabled = false;
		}
	}
	for(var i = 0; i < inputs3.length; i++) {
		inputs3[i].disabled = false;
	}
	for(var i = 0; i < inputs4.length; i++) {
		inputs4[i].disabled = false;
	}
	
	document.getElementById("acoes").innerHTML = "<input type='button' class='btn btn-primary' value='Atualizar Dados' onclick='habilitaEdicao()'>  <input type='button' class='btn btn-primary' value='Salvar Dados' onclick='atualizaDadosFornecedorHome()'>";
}


function atualizaDadosFornecedorHomeSvlt(CodFornm, dadosParaAtualizar){
	var xhttp;
	if (window.XMLHttpRequest) {
		xhttp = new XMLHttpRequest();
		} else {
		xhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
	xhttp.onreadystatechange = function() {
	
		if (this.readyState == 4 && this.status == 200) {
			mostraMSGSvlt(this.responseText);
		}
	};
	
	xhttp.open("GET","AtualizaDadosFornecedor?forn=" + CodFornm +"&dados=" + dadosParaAtualizar, true);
	xhttp.send();
}

function atualizaDadosFornecedorHome(){
	var tempRazaoSocial = document.getElementById("razaoSocial").value;
	var tempcodForn = document.getElementById("codForn").value;
	var tempie_rg = document.getElementById("ierg").value;
	var tempemail = document.getElementById("email").value;
	var tempcodUsu = document.getElementById("codusu").value;
	var tempcnpj_cpf = document.getElementById("cnpj").value;
	var temptelefone = document.getElementById("telefone").value;
	var tempqtdVendAtend = document.getElementById("qtdvend").value;

	var tempregiao = document.getElementById("regiao").value
	var tempcidade = document.getElementById("cidade").value
	var templogradouro = document.getElementById("logradouro").value
	var tempestado = document.getElementById("estado").value
	var tempbairro = document.getElementById("bairro").value
	var tempcep = document.getElementById("cep").value
	
	var mandaSvlt = tempRazaoSocial + "," + tempcodForn+ "," + tempie_rg+ "," + tempemail+ "," + tempcodUsu + "," +tempcnpj_cpf + "," +temptelefone + "," +tempqtdVendAtend+ "," +
	tempregiao + "," +tempcidade + "," +templogradouro + "," +tempestado + "," +tempbairro+ "," + tempcep;
	atualizaDadosFornecedorHomeSvlt(tempcodForn,mandaSvlt);
}

function mostraMSGSvlt(msg){
	alert(msg);
}

function pegaCepInformado(){
	var cep = document.getElementById("cep").value;
	carregaEndByCEP(cep);
	
}

function carregaEndByCEP(CEP) {
	var xhttp;
		if (window.XMLHttpRequest) {
			xhttp = new XMLHttpRequest();
			} else {
			xhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
		xhttp.onreadystatechange = function() {
		
			if (this.readyState == 4 && this.status == 200) {
				window.location.href = "cadastronovo.html";
			}
		};
		
		xhttp.open("GET","CarregaEnderecoByCep?cep="+CEP, true);
		xhttp.send();
}

function recebeDadosCep() {
	var xhttp;
		if (window.XMLHttpRequest) {
			xhttp = new XMLHttpRequest();
			} else {
			xhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
		xhttp.onreadystatechange = function() {
		
			if (this.readyState == 4 && this.status == 200) {
				preencheEndByCepCendCliente(this.responseText);
			}
		};
		
		xhttp.open("GET","CarregaEnderecoByCep2", true);
		xhttp.send();
}

function preencheEndByCepCendCliente(DadosEnd){
	if(DadosEnd != null){
		var Dados = DadosEnd.split(",");
		var tempregiao = Dados[0];
		var tempestado = Dados[1];
		var tempcidade = Dados[2];
		var tempbairro = Dados[3];
		var tempcep = Dados[4];
		var templogradouro = Dados[5];
		document.getElementById("regiaoT").innerHTML = "<option>"+tempregiao+"</option>";
		document.getElementById("estadoT").innerHTML = "<option>"+tempestado+"</option>";
		document.getElementById("cidade").value = tempcidade;
		document.getElementById("bairro").value = tempbairro;
		document.getElementById("cep").value = tempcep;
		document.getElementById("logradouro").value = templogradouro;
		document.getElementById("segmento").value = "CLIENTE";
	}else{
		
	}
	
}