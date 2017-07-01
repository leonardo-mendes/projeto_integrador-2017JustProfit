package br.com.justprofit.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import br.com.justprofit.model.dao.util.Constants;
import br.com.justprofit.model.dao.util.FabricaDeConexoes;
import br.com.justprofit.model.domain.Cliente;
import br.com.justprofit.model.domain.Fornecedor;
import br.com.justprofit.model.domain.Pedido;
import br.com.justprofit.model.domain.Produto;
import br.com.justprofit.model.domain.Vendedor;

public class PedidoDAO {
    
	public PedidoDAO() {}
	
	public void salvaPedido(Pedido pedido) throws SQLException{
		/* PreparedStatement stmt = conexao.prepareStatement();
        stmt.setInt(1,);
        
        
        
        stmt.execute();  
        stmt.close();
        conexao.close();*/
	}
	
	
	
	public void excluiPedido(Pedido pedido) throws SQLException{
		/* PreparedStatement stmt = conexao.prepareStatement();
        stmt.setInt(1,);
        
        
        
        stmt.execute();  
        stmt.close();
        conexao.close();*/
		
	}
	
	public static Pedido buscaPedidoPorID(Integer id) throws SQLException{
        Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_SELECT_PEDIDO_POR_ID);
        stmt.setInt(1,id);
        ResultSet resultado = stmt.executeQuery();

        Integer tempId = null,tempCodVend= null,tempCodConde= null,tempCodCli= null,tempCodTable= null;
        Double tempVlrTot = null;
        Date tempDataPed = null;
        
        while(resultado.next()){
            tempId = resultado.getInt("CODPED");
            tempDataPed = resultado.getDate("DTPED");
            tempCodVend = resultado.getInt("CODVEND");
            tempCodConde = resultado.getInt("CODCOND");
            tempCodCli = resultado.getInt("CODCLI");
            tempCodTable = resultado.getInt("CODTAB");
            tempVlrTot = resultado.getDouble("VLRTOTAL");
        }
        
       	Cliente cliente = ClienteDAO.buscaClientePorId(tempCodCli);
       	Vendedor vendedor = VendedorDAO.buscaVendedorPorId(tempCodVend);
        Pedido pedido = new Pedido(tempId, tempVlrTot, tempDataPed, cliente, vendedor);
        
        stmt.close();
        conexao.close();
        return pedido;
    }
	
	 public LinkedList<Pedido> buscaTodos() throws SQLException{
         Connection conexao = FabricaDeConexoes.getConnection();
         PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_SELECT_ALL_PEDIDO);         
         ResultSet resultado = stmt.executeQuery();
         
         LinkedList<Pedido> reslist = new LinkedList<Pedido>();
         
         while(resultado.next()){            
             Integer resid = resultado.getInt("CODPED");
             reslist.add(PedidoDAO.buscaPedidoPorID(resid));
         }
         
         stmt.close();
         conexao.close();
         return reslist;
     }
	
}
