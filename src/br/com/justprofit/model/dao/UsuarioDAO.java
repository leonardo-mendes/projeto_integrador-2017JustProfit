package br.com.justprofit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.justprofit.model.dao.util.Constants;
import br.com.justprofit.model.dao.util.FabricaDeConexoes;
import br.com.justprofit.model.domain.Usuario;

public class UsuarioDAO {
	
    public UsuarioDAO() {}
    
    public Integer buscaCodUsu(Usuario usu) throws SQLException{
        Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_SELECT_CODUSU);
        EnderecoDAO endDao = new EnderecoDAO();
        try {
			Integer codLogr = endDao.buscaCodLogr(usu);
			usu.getEndereco().setCodend(codLogr);
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
        stmt.setString(1,usu.getCnpj_cpf());
        stmt.setInt(2,usu.getEndereco().getCodend());
        ResultSet resultado = stmt.executeQuery();
        
        Integer codUSU = null;
        while(resultado.next()){            
        	codUSU = resultado.getInt("CODUSU");
        }
        
        stmt.close();
        conexao.close();
        return codUSU;
    }
    
}
