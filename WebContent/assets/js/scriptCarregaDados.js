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
