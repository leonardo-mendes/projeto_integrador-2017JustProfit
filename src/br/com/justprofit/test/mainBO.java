package br.com.justprofit.test;

import br.com.justprofit.control.UsuarioBO;
import br.com.justprofit.model.domain.Bairro;
import br.com.justprofit.model.domain.Cidade;
import br.com.justprofit.model.domain.Cliente;
import br.com.justprofit.model.domain.Endereco;
import br.com.justprofit.model.domain.Estado;
import br.com.justprofit.model.domain.Fornecedor;
import br.com.justprofit.model.domain.Regiao;
import br.com.justprofit.model.domain.Vendedor;

public class mainBO {

	public static void main(String[] args) {
		UsuarioBO userBO = new UsuarioBO();
		Regiao r = new Regiao(null, "Sudeste_TC1");
        Estado uf = new Estado(null, "Minas Gerais_TC1", r);
        Cidade cid = new Cidade(null,"Uberlândia_TC1",uf);
        Bairro bairro = new Bairro(null,"Sant. Monica_TC1",cid);
        Endereco end = new Endereco(null, "Av. Segismundo PereiraC1", 61, "38414-5051", cid, bairro);
		Cliente cliente = new Cliente(48, "Cliente_2C", "75605318000103", "IE_Fic_2C", end, "99133-3333", "teste@hotmail.com", "C");
		Fornecedor fornecedor = new Fornecedor(49,"Fonecedor2","75605318000103","IE_Fic_Forn2", end,  "99133-3333", "teste@hotmail.com");
		Vendedor vendedor = new Vendedor(10, 222.20, fornecedor, 46, "Vendedor Teste_Atualiza", "88885318000103","IE_Fic_Vend2", end,  "99155-5555", "teste@hotmail.com");
        
		userBO.validaDados(cliente);
		userBO.validaDados(fornecedor);
		userBO.validaDados(vendedor);
	}

}
