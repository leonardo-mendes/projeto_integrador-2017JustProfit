package br.com.justprofit.test;

import java.sql.SQLException;

import br.com.justprofit.model.dao.EnderecoDAO;
import br.com.justprofit.model.dao.FornecedorDAO;
import br.com.justprofit.model.domain.Bairro;
import br.com.justprofit.model.domain.Cidade;
import br.com.justprofit.model.domain.Endereco;
import br.com.justprofit.model.domain.Estado;
import br.com.justprofit.model.domain.Fornecedor;
import br.com.justprofit.model.domain.Regiao;
import br.com.justprofit.model.domain.Vendedor;

public class mainForncedor {
	
	public static void main(String[] args) {
		FornecedorDAO fordao = new FornecedorDAO();
		EnderecoDAO endDao = new EnderecoDAO();
		/*
	    Regiao r = new Regiao(null, "Sudeste_T50");
	    Estado uf = new Estado(null, "Minas Gerais_T50", r);
	    Cidade cid = new Cidade(null,"Uberlândia_T50",uf);
	    Bairro bairro = new Bairro(null,"Sant. Monica_T50",cid);
	    Endereco end = new Endereco(null, "Av. Segismundo Pereira50",110, "38414-5051", cid, bairro);
	    Fornecedor forne = new Fornecedor(null,"Fonecedor11","75605318000103","IE_Fic_Forn0", end,  "99133-3333", "teste@hotmail.com");
	    
	    Fornecedor forne2 = new Fornecedor(49,"Fonecedor123","75605318000103","IE_Fic_Forn2", end,  "99133-3333", "teste@hotmail.com");
	    */
		
		 try {
		    	fordao.associaVendedorInserindoContrato(98,44);
		    	
				System.out.println("Vendedores associados");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		/*
	    try {
	    	fordao.salva(forne);
			
			System.out.println("FORNECEDOR SALVOS!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	    
	    try {
	    	fordao.salva(forne2);
			
			System.out.println("FORNECEDOR ATUALIZADO!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	   
	    try {
	    	fordao.exclui(forne2);
			
			System.out.println("FORNECEDOR EXCLUIDO!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	    */
	    
	    /*
	    System.out.println("BUSCANDO FORNECEDOR DE ID= 50");
	    System.out.println();
	   
	    try {
			Fornecedor forByID = FornecedorDAO.buscaFornecedorPorId(50);
			 System.out.print(forByID);
			 System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	    
	    System.out.println("BUSCANDO TODOS OS FORNECEDOR DA BASE:");
	    System.out.println();
	    try {
	    	FornecedorDAO fordao2 = new FornecedorDAO();
			for(Fornecedor f: fordao2.buscaTodos()){
				System.out.println(f);
				System.out.println();
			}
			
	    }
		 catch (SQLException e) {
			e.printStackTrace();
		}
	    */
	}
}
