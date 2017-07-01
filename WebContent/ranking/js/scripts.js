var ctx = document.getElementById("ChartDesempenhoMensal").getContext('2d');
function gerarGrafico(){
	var myChart = new Chart(ctx, {
	    type: 'line',
	    data: {
	        labels: ["Janeiro", "Fevereiro", "Marco", "Abril", "Maio", "Junho"],
	        datasets: [{
	            label: 'Meta/Mes',
	            data: [$("#v5").text(),$("#v4").text(),$("#v3").text(),$("#v2").text(),$("#v1").text(),$("#v0").text()],
	            backgroundColor: [
	                'rgba(255, 99, 132, 0.2)'
	            ],
	            borderColor: [
	                'rgba(255,99,132,1)'
	            ],
	            borderWidth: 1
	        }]
	    },
	            options: {
	                responsive: true,
	                title:{
	                    display:false,
	                    text:'Titulo'
	                },
	                tooltips: {
	                    mode: 'index',
	                    intersect: false
	                },
	                hover: {
	                    mode: 'nearest',
	                    intersect: true
	                },
	                scales: {
	                    xAxes: [{
	                        display: true,
	                        scaleLabel: {
	                            display: true,
	                            labelString: 'Historico dos Ultimos 6 meses'
	                        }
	                    }],
	                    yAxes: [{
	                        display: true,
	                        scaleLabel: {
	                            display: true,
	                            labelString: 'Meta'
	                        }
	                    }]
	                }
	            }
	});
}


function listaNomeFornecedores(CodVend) {
	var xhttp;
		if (window.XMLHttpRequest) {
			xhttp = new XMLHttpRequest();
			} else {
			xhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				insereNomesFornecedores(this.responseText);
			}
		};
		
		xhttp.open("GET","ListaTodosNomesFornecedores?CodVend=" + CodVend, true);
		xhttp.send();
}

function listaNomeRegioes() {
	var xhttp;
		if (window.XMLHttpRequest) {
			xhttp = new XMLHttpRequest();
			} else {
			xhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				insereNomesRegioes(this.responseText);
			}
		};
		
		xhttp.open("GET","ListaTodosNomesRegioes", true);
		xhttp.send();
}


function insereNomesFornecedores(Lista){
	var nomes = Lista.split(",");
	var options = "";
	for(var i=0; i< (nomes.length-1); i++){
		options += "<BR><option value='"+nomes[i]+"'>" + nomes[i] +"</option><BR>";
	}
	document.getElementById("ListaForn").innerHTML = options;

}

function insereNomesRegioes(Lista){
	var nomes = Lista.split(",");
	var options = "";
	for(var i=0; i< (nomes.length-1); i++){
		options += "<BR><option value='"+nomes[i]+"'>" + nomes[i] +"</option><BR>";
	}
	document.getElementById("ListaReg").innerHTML = options;
	
}

function buscaFornecedorSelecionado(codVend){
	var nome = document.getElementById("ListaForn").value;
	carregaDadosDoFornecedor(nome,codVend);
}

function buscaRegiaoSelecionado(codForn){
	var nome = document.getElementById("ListaReg").value;
	geraDadosRanginkRegiaoFornecedor(nome,codForn);
}

function carregaDadosDoFornecedor(Nome,codVend) {
	var xhttp;
		if (window.XMLHttpRequest) {
			xhttp = new XMLHttpRequest();
			} else {
			xhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				insereDadosRankFornecedore(this.responseText);
			}
		};
		
		xhttp.open("GET","GeraDadosRankingVendedor?Nome=" + Nome+"&codVend="+codVend, true);
		xhttp.send();
}

function geraDadosRanginkRegiaoFornecedor(Nome,codForn) {
	var xhttp;
		if (window.XMLHttpRequest) {
			xhttp = new XMLHttpRequest();
			} else {
			xhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				insereDadosRankingFornecedoreRegiao(this.responseText);
			}
		};
		
		xhttp.open("GET","GeraDadosRankingFornecedor?Nome=" + Nome+"&codForn="+codForn, true);
		xhttp.send();
}

function insereDadosRankFornecedore(Dados){
	var dadosFront = Dados.split("#");
	var valorMes = dadosFront[0];
	var dadosRank = dadosFront[1];
	var dadosRank2 = dadosRank.split(",");
	
	var historicoPos = dadosFront[2];
	var historicoPos2 = historicoPos.split("/");
	
	var dadosGrafico = dadosFront[3];
	var dadosGrafico2 = dadosGrafico.split("/");
	
	var ranking="<tr><td style='background-color:green; font-weight:bold; color:white;'colspan='2'>Ranking de Vendedores do Fornecedor</td></tr><tr style='font-weight:bold;'><td>Pos</td><td>Valor / Vendedor</td></tr>";
	for(var i=0;i<(dadosRank2.length-1);i++){
			
			var valor= dadosRank2[i];
			ranking += "<tr><td>"+(i+1)+"</td><td>"+ valor +"</td></tr>";

	}
	
	var valor = "R$ "+valorMes;
	document.getElementById("vlrVendaMes").innerHTML = valor;
	document.getElementById("tableRanking").innerHTML = ranking;
	document.getElementById("pos2").innerHTML = historicoPos2[0];
	document.getElementById("pos1").innerHTML = historicoPos2[1];
	var grafico5 = dadosGrafico2[5].replace("]","");
	var grafico0 = dadosGrafico2[0].replace("[","");
	document.getElementById("v5").innerHTML = grafico5;
	document.getElementById("v4").innerHTML = dadosGrafico2[4];
	document.getElementById("v3").innerHTML = dadosGrafico2[3];
	document.getElementById("v2").innerHTML = dadosGrafico2[2];
	document.getElementById("v1").innerHTML = dadosGrafico2[1];
	document.getElementById("v0").innerHTML = grafico0;
	gerarGrafico();
}


function insereDadosRankingFornecedoreRegiao(Dados){
	debugger;
	var dadosFront = Dados.split("#");
	var valorMes = dadosFront[0];
	var dadosRank = dadosFront[1];
	var dadosRank2 = dadosRank.split(",");
	var historicoPos = dadosFront[2];
	var historicoPos2 = historicoPos.split("/");
	
	var dadosGrafico = dadosFront[3];
	var dadosGrafico2 = dadosGrafico.split("/");
	
	var ranking="<tr><td style='background-color:green; font-weight:bold; color:white;'colspan='2'>Ranking de Vendedores por Região</td></tr><tr style='font-weight:bold;'><td>Pos</td><td>Valor / Vendedor</td></tr>";
	for(var i=0;i<(dadosRank2.length-1);i++){
			
			var valor= dadosRank2[i];
			ranking += "<tr><td>"+(i+1)+"</td><td>"+ valor +"</td></tr>";

	}
	
	var valor = "R$ "+valorMes;
	document.getElementById("vlrVendaMes").innerHTML = valor;
	document.getElementById("tableRanking").innerHTML = ranking;
	if(historicoPos.length < 2){
		document.getElementById("pos2").innerHTML = "0";
		document.getElementById("pos1").innerHTML = "0";
	}else{
		if(historicoPos2[0] == null){
			historicoPos2[0] = "";
		}
		document.getElementById("pos2").innerHTML = historicoPos2[0];
		document.getElementById("pos1").innerHTML = historicoPos2[1];
	}

	var grafico5 = dadosGrafico2[5].replace("]","");
	var grafico0 = dadosGrafico2[0].replace("[","");
	document.getElementById("v5").innerHTML = grafico5;
	document.getElementById("v4").innerHTML = dadosGrafico2[4];
	document.getElementById("v3").innerHTML = dadosGrafico2[3];
	document.getElementById("v2").innerHTML = dadosGrafico2[2];
	document.getElementById("v1").innerHTML = dadosGrafico2[1];
	document.getElementById("v0").innerHTML = grafico0;
	gerarGrafico();
}
