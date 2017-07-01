package br.com.justprofit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.justprofit.model.dao.util.Constants;
import br.com.justprofit.model.dao.util.FabricaDeConexoes;
import br.com.justprofit.model.domain.Regiao;
import br.com.justprofit.model.domain.Usuario;

public class RegiaoDAO {

    public RegiaoDAO() {}
    
    public Regiao BuscaRegiaoPorID(Integer id) throws SQLException {
    	Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_SELECT_REG);
        stmt.setInt(1,id);
        ResultSet resultado = stmt.executeQuery();
        Integer codReg = null;
        String nomeReg = "";

        while(resultado.next()){
        	codReg = resultado.getInt("CODREG");
        	nomeReg = resultado.getString("NOMEREG");
        }
    	
        Regiao regiao = new Regiao(codReg, nomeReg);
        
        conexao.close();
		return regiao;
    }
    
    
   
    public void InsertReg(Usuario usu) throws SQLException{
    	Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_INSERT_REG);
        stmt.setString(1,usu.getEndereco().getCidade().getEstado().getRegiao().getNome());
        
        stmt.execute();        
        stmt.close();
        conexao.close();
    }
    
    public Integer BuscaIDRegiaoPorNome(String nome) throws SQLException {
    	Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_SELECT_CODREG_POR_NOME);
        stmt.setString(1,nome);
        ResultSet resultado = stmt.executeQuery();
        Integer codReg = null;

        while(resultado.next()){
        	codReg = resultado.getInt("CODREG");
        }
    	
		return codReg;
    }
}
