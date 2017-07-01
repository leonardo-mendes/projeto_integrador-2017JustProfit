package br.com.justprofit.model.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.justprofit.model.dao.util.Constants;
import br.com.justprofit.model.dao.util.FabricaDeConexoes;
import br.com.justprofit.model.domain.Estado;
import br.com.justprofit.model.domain.Regiao;
import br.com.justprofit.model.domain.Usuario;

public class EstadoDAO {

    public EstadoDAO() {}
    
    public Estado BuscaEstadoPorID(Integer id) throws SQLException {
		Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_SELECT_ESTADO);
        stmt.setInt(1,id);
        ResultSet resultado = stmt.executeQuery();
    	String UF = "";
    	Integer codUf = null,codReg = null;
        
        while(resultado.next()){
        	codUf = resultado.getInt("CODUF");
        	UF = resultado.getString("UF");
        	codReg = resultado.getInt("CODREG");
        }
        
        RegiaoDAO regDao = new RegiaoDAO();
		Regiao reg = regDao.BuscaRegiaoPorID(codReg);
		Estado estado = new Estado(codUf, UF, reg);
		
		conexao.close();
		return estado;
	}
    
    public void insertEstado(Usuario usu) throws SQLException{
    	Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_INSERT_UF);
        stmt.setString(1,usu.getEndereco().getCidade().getEstado().getNome());
        stmt.setString(2,usu.getEndereco().getCidade().getEstado().getRegiao().getNome());
        
        stmt.execute();        
        stmt.close();
        conexao.close();
    }
    
}
