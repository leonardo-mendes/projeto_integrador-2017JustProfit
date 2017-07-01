package br.com.justprofit.test;

import java.sql.SQLException;

import br.com.justprofit.model.dao.ClienteDAO;
import br.com.justprofit.model.dao.EnderecoDAO;
import br.com.justprofit.model.domain.Bairro;
import br.com.justprofit.model.domain.Cidade;
import br.com.justprofit.model.domain.Cliente;
import br.com.justprofit.model.domain.Endereco;
import br.com.justprofit.model.domain.Estado;
import br.com.justprofit.model.domain.Regiao;

public class mainCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	ClienteDAO clidao = new ClienteDAO();
    	EnderecoDAO endDao = new EnderecoDAO();
    	
        Regiao r = new Regiao(null, "Sudeste_TC1");
        Estado uf = new Estado(null, "Minas Gerais_TC1", r);
        Cidade cid = new Cidade(null,"Uberlândia_TC1",uf);
        Bairro bairro = new Bairro(null,"Sant. Monica_TC1",cid);
        Endereco end = new Endereco(null, "Av. Segismundo PereiraC1", 61, "38414-5051", cid, bairro);
        
        Cliente cliente = new Cliente(null,"Cliente_1C1","37246242000170","IE_Fic_1C1",end,"9122-2222","teste@hotmail.com","C");
        //DEFINE O CODIGO DO ENDERECO DO CLIENTE (SO TEM COMO FAZER ISSO APOS O CLIENTE SER CRIADO, DEVE SER FEITO SEMPRE QUE CRIAR UM CLIENTE!!)
        /*try {
			Integer codLogr = endDao.buscaCodLogr(cliente);
			cliente.getEndereco().setCodend(codLogr);
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}*/
        
        Cliente cliente2 = new Cliente(48, "Cliente_2C", "75605318000103", "IE_Fic_2C", end, "99133-3333", "teste@hotmail.com", "C");
        //clidao.salva(cliente2);
        
        try {
			clidao.salva(cliente);
			
			System.out.println("CLIENTES SALVOS!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        
        System.out.println("ATUALIZANDO CLIENTE");
        System.out.println();
        
        try {
			clidao.salva(cliente2);
			System.out.println("CLIENTE ATUALIZADO!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
        /*

		System.out.println("BUSCANDO TODOS OS CLIENTES DA BASE:");
        System.out.println();
        try {
			for(Cliente c: ClienteDAO.buscaTodos()){
				System.out.println(c);
				System.out.println();
			}
			
        }
		 catch (SQLException e) {
			e.printStackTrace();
		}
        
        System.out.println("BUSCANDO CLIENTE DE ID= 25");
        System.out.println();
        
        try {
			Cliente cliByID = ClienteDAO.buscaClientePorId(25);
			 System.out.println(cliByID);
			 System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        System.out.println("EXCLUINDO CLIENTE");
        System.out.println();
        
        try {
			clidao.exclui(cliente2);
			System.out.println("CLIENTE EXCLUIDO");
		} catch (SQLException e) {
			e.printStackTrace();
		}
        */
    }
    
}
