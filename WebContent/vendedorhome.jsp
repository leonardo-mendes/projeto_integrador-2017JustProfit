<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Beite - Free Responsive Portfolio Bootstrap3 Template | PCMShaper</title>
	
	<!-- Needed CSS & Font Files -->
	<link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
	<link rel="stylesheet" type="text/css" href="assets/css/prettyPhoto.css">
	<link rel="stylesheet" href="//fonts.googleapis.com/css?family=Open+Sans:300,400,600,700">
	<link rel="stylesheet" type="text/css" href="assets/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="assets/css/presets/preset1.css">
	<link rel="stylesheet" type="text/css" href="assets/css/responsive.css">
        <script src="assets/js/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="assets/js/events.js" type="text/javascript"></script>
        <script src="assets/js/scriptCarregaDados.js" type="text/javascript"></script>
</head>
<body onload="carrregaDadosVendedorHome(2)">
	
	<!-- Header Start -->
	<div id="header">
	
		
	</div>
	<!-- Header End -->
	
	<!-- Meus Dados Starts -->		
	<section id="portfolio">
		<div class="container">
			<div class="row">
				<div class="title text-center col-sm-12">
					<h2>Meus Dados</h2>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				
			  		  <!-- Nav tabs -->
					  <ul class="nav nav-tabs" role="tablist">
						<li role="presentation" class="active"><a href="#informacoes" aria-controls="home" role="tab" data-toggle="tab">Informações</a></li>
						<li role="presentation"><a href="#endereco" aria-controls="profile" role="tab" data-toggle="tab">Endereço</a></li>
					  </ul>
			
					  <!-- Tab panes -->
					  <div class="tab-content">
							<div role="tabpanel" class="tab-pane active" id="informacoes"><br>
								<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
									<form id="formedit" >
									  <div class="form-group">
									    <label for="razaosocial">Razão Social</label>
									    <input type="text" class="form-control" id="razaoSocial" placeholder="Razão Social" disabled="disabled">
									  </div>
									  <div class="form-group">
									    <label for="codvend">Código de Vendedor</label>
									    <input type="text" class="form-control" id="codvend" placeholder="Código de Vendedor" disabled="disabled">
									  </div>		
									  <div class="form-group">
									    <label for="ierg">Inscrição Estadual / RG</label>
									    <input type="text" class="form-control" id="ierg" placeholder="IE / RG" disabled="disabled">
									  </div>	
									  <div class="form-group">
									    <label for="email">Email</label>
									    <input type="text" class="form-control" id="email" placeholder="email" disabled="disabled">
									  </div>
									</form>
								</div>
								<div id="respSrvl"></div>
								
								<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
									<form id="formedit">
									  <div class="form-group">
									    <label for="codusu">Código de Usuário</label>
									    <input type="text" class="form-control" id="codusu" placeholder="Código de Usuário" disabled="disabled">
									  </div>
									  <div class="form-group">
									    <label for="cnpj">CNPJ / CPF</label>
									    <input type="text" class="form-control" id="cnpj" placeholder="CNPJ / CPF" disabled="disabled">
									  </div>
									  <div class="form-group">
									    <label for="telefone">Telefone</label>
									    <input type="text" class="form-control" id="telefone" placeholder="Telefone" disabled="disabled">
									  </div>
									  <div class="form-group">
									    <label for="qtdforn">Quantidade de Fornecedores Atendidos</label>
									    <input type="text" class="form-control" id="qtdforn"" placeholder="Quantidade de Fornecedores Atendidos" disabled="disabled">
									  </div>
									</form>
								</div>
							</div>
							<div role="tabpanel" class="tab-pane" id="endereco"><br>
								<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
									<form id="formedit">
									  <div class="form-group">
									    <label for="regiao">Região</label>
									    <input type="text" class="form-control" id="regiao" placeholder="Região" disabled="disabled">
									  </div>
									  <div class="form-group">
									    <label for="cidade">Cidade</label>
									    <input type="text" class="form-control" id="cidade" placeholder="Cidade" disabled="disabled">
									  </div>		
									  <div class="form-group">
									    <label for="logradouro">Logradouro</label>
									    <input type="text" class="form-control" id="logradouro" placeholder="Logradouro" disabled="disabled">
									  </div>							  
									</form>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
									<form id="formedit">
									  <div class="form-group">
									    <label for="estado">Estado</label>
									    <input type="text" class="form-control" id="estado" placeholder="Estado" disabled="disabled">
									  </div>
									  <div class="form-group">
									    <label for="bairro">Bairro</label>
									    <input type="text" class="form-control" id="bairro" placeholder="Bairro" disabled="disabled">
									  </div>
									  <div class="form-group">
									    <label for="cep">CEP</label>
									    <input type="text" class="form-control" id="cep" placeholder="CEP" disabled="disabled">
									  </div>
									</form>
								</div>
							</div>
						
						</div>

						<form style="text-align: center; margin-top: 10px;" method="get">
							<input type="button" class="btn btn-primary" value="Atualizar Dados">
						</form>
					  </div>
				</div>
			</div>
		</div>
	</section>
	<!-- Meus Dados End -->
	
	<!-- Footer Start -->
	<footer id="footer">

	</footer>
	<!-- Footer End -->
	
	<!-- Needed jQuery Files -->
	<script src="assets/js/jquery.min.js" type="text/javascript"></script>
	<script src="assets/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="assets/js/jquery.prettyPhoto.js" type="text/javascript"></script>
	<script src="assets/js/main.js" type="text/javascript"></script>
</body>
</html>