package br.com.justprofit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.justprofit.model.dao.util.Constants;
import br.com.justprofit.model.dao.util.FabricaDeConexoes;
import br.com.justprofit.model.domain.Cidade;
import br.com.justprofit.model.domain.Estado;
import br.com.justprofit.model.domain.Usuario;

public class CidadeDAO {
    
	public CidadeDAO() {}

    public Cidade BuscaCidadePorID(Integer id) throws SQLException {
    	Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_SELECT_CIDADE);
        stmt.setInt(1,id);
        ResultSet resultado = stmt.executeQuery();
        Integer codCid = null, codUf = null;
        String nomeCid = "";

        while(resultado.next()){
        	codCid = resultado.getInt("CODCID");
        	nomeCid = resultado.getString("NOMECID");
        	codUf = resultado.getInt("CODUF");
        }
    	
        EstadoDAO estadoDao = new EstadoDAO();
        Estado estado = estadoDao.BuscaEstadoPorID(codUf);
		Cidade cidade = new Cidade(codCid, nomeCid, estado);

		conexao.close();
		return cidade;
    }
    
    public void insertCidade(Usuario usu) throws SQLException{
    	Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_INSERT_CID);
        stmt.setString(1,usu.getEndereco().getCidade().getNome());
        stmt.setString(2,usu.getEndereco().getCidade().getEstado().getNome());
        
        stmt.execute();        
        stmt.close();
        conexao.close();
    }
}
