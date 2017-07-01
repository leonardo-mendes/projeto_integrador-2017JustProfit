package br.com.justprofit.test;

import java.sql.SQLException;

import br.com.justprofit.model.dao.ClienteDAO;
import br.com.justprofit.model.dao.EnderecoDAO;
import br.com.justprofit.model.dao.FornecedorDAO;
import br.com.justprofit.model.dao.VendedorDAO;
import br.com.justprofit.model.domain.Bairro;
import br.com.justprofit.model.domain.Cidade;
import br.com.justprofit.model.domain.Cliente;
import br.com.justprofit.model.domain.Endereco;
import br.com.justprofit.model.domain.Estado;
import br.com.justprofit.model.domain.Fornecedor;
import br.com.justprofit.model.domain.Regiao;
import br.com.justprofit.model.domain.Vendedor;

public class mainVendedor {

	public static void main(String[] args) {
		
		VendedorDAO venddao = new VendedorDAO();
    	EnderecoDAO endDao = new EnderecoDAO();
    	Regiao r2 = new Regiao(null, "Sudeste_T042");
        Estado uf2 = new Estado(null, "Minas Gerais_T042", r2);
        Cidade cid2 = new Cidade(null,"Uberlândia_T042",uf2);
        Bairro bairro2 = new Bairro(null,"Sant. Monica_T042",cid2);
        Endereco end2 = new Endereco(null, "Av. Segismundo Pereira42",40, "38414-5051", cid2, bairro2);
    	
        Regiao r = new Regiao(null, "Sudeste_T045");
        Estado uf = new Estado(null, "Minas Gerais_T045", r);
        Cidade cid = new Cidade(null,"Uberlândia_T045",uf);
        Bairro bairro = new Bairro(null,"Sant. Monica_T45",cid);
        Endereco end = new Endereco(null, "Av. Segismundo Pereira45",33, "38414-5051", cid, bairro);
        FornecedorDAO forn = new FornecedorDAO();
        /*
        Fornecedor forne = new Fornecedor(null,"FonecedorTeste2","75605318000104","IE_Fic_Vend", end2,  "99133-3333", "teste@hotmail.com");
        try {
        	forn.salva(forne);
			
			System.out.println("FORNECEDOR SALVOS!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        */
        //Vendedor vendedor = new Vendedor(null, 200.00, forne, null, "Vendedor222", "75605318000103","IE_Fic_Vend", end2, "99133-3333", "teste@hotmail.com");
        Vendedor vendedor2 = new Vendedor(null, 222.20, 98 , null, "Vendedor 232", "88885318000103","IE_Fic_Vend2", end,  "99155-5555", "teste@hotmail.com");
        
        
        try {
        	venddao.salva(vendedor2);
			
			System.out.println("Vendedor SALVOS!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
        /*
        try {
        	venddao.salva(vendedor2);
			
			System.out.println("CLIENTES ATUALIZADO!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
        */
        /*
        try {
        	venddao.exclui(vendedor2);
			
			System.out.println("CLIENTES EXCLUIDO!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
        
        System.out.println("BUSCANDO VENDEDOR DE ID= 85");
        System.out.println();
       
        try {
			Vendedor venByID = VendedorDAO.buscaVendedorPorId(85);
			 System.out.print(venByID);
			 System.out.println(" CodVend: "+ venByID.getCodVend());
			 System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
		/*
       
        System.out.println("BUSCANDO TODOS OS VENDEDORES DA BASE:");
        System.out.println();
        try {
        	VendedorDAO vendDAO = new VendedorDAO();
			for(Vendedor v: vendDAO.buscaTodos()){
				System.out.println(v);
				System.out.println(" CodVend: "+ v.getCodVend());
				System.out.println();
			}
			
        }
		 catch (SQLException e) {
			e.printStackTrace();
		}
        */
	}

}
