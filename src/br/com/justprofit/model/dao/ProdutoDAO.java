package br.com.justprofit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import br.com.justprofit.model.dao.util.Constants;
import br.com.justprofit.model.dao.util.FabricaDeConexoes;
import br.com.justprofit.model.domain.Endereco;
import br.com.justprofit.model.domain.Fornecedor;
import br.com.justprofit.model.domain.Pedido;
import br.com.justprofit.model.domain.Produto;
import br.com.justprofit.model.domain.Vendedor;

public class ProdutoDAO {

     public ProdutoDAO() {}
        
     public void adicionaProdutoPedido(Produto prod, Pedido pedido) throws SQLException{
    	 Connection conexao = FabricaDeConexoes.getConnection();
        /* PreparedStatement stmt = conexao.prepareStatement();
         stmt.setInt(1,);
         
         //Select
        ResultSet resultado = stmt.executeQuery();
        Integer codVend = null;
        while(resultado.next()){
        	codVend = resultado.getInt("CODVEND");
        }
        
         
         stmt.execute();  
         stmt.close();
         conexao.close();*/
 		
 	}
     
     
     public void removeProdutoPedido(Produto prod, Pedido pedido) throws SQLException{
    	 /* PreparedStatement stmt = conexao.prepareStatement();
         stmt.setInt(1,);
         
         //Select
        ResultSet resultado = stmt.executeQuery();
        Integer codVend = null;
        while(resultado.next()){
        	codVend = resultado.getInt("CODVEND");
        }
         
         stmt.execute();  
         stmt.close();
         conexao.close();*/

  	}
     
     public static Produto buscaProdutoPorID(Integer id) throws SQLException{
         Connection conexao = FabricaDeConexoes.getConnection();
         PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_SELECT_PRODUTO_POR_ID);
         stmt.setInt(1,id);
         ResultSet resultado = stmt.executeQuery();

         Integer tempId = null,tempCodForn = null,tempDesc = null;
         Double tempVlr = null;
         String tempNome = null;
         
         while(resultado.next()){
             tempId = resultado.getInt("CODPROD");
             tempNome = resultado.getString("NOMEPROD");
             tempVlr = resultado.getDouble("VLRTOT");
             tempDesc = resultado.getInt("DESCMAX");
             tempCodForn = resultado.getInt("CODFORN");
         }
         
         Fornecedor forn = FornecedorDAO.buscaFornecedorPorId(tempCodForn);
         Produto produto = new Produto(tempNome, tempVlr, tempDesc, tempId, forn);
         
         stmt.close();
         conexao.close();
         return produto;
     }
     
     
     public LinkedList<Produto> buscaTodosProdutosDoVendedor(int codVend) throws SQLException{
         Connection conexao = FabricaDeConexoes.getConnection();
         PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_SELECT_ALL_PRODUTO);  
         stmt.setInt(1,codVend);
         ResultSet resultado = stmt.executeQuery();
         LinkedList<Produto> reslist = new LinkedList<Produto>();
         
         while(resultado.next()){            
             Integer resid = resultado.getInt("CODPROD");
             reslist.add(ProdutoDAO.buscaProdutoPorID(resid));
         }
         
         stmt.close();
         conexao.close();
         return reslist;
     }
     
}
