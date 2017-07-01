package br.com.justprofit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.justprofit.model.dao.util.Constants;
import br.com.justprofit.model.dao.util.FabricaDeConexoes;
import br.com.justprofit.model.domain.Bairro;
import br.com.justprofit.model.domain.Cidade;
import br.com.justprofit.model.domain.Endereco;
import br.com.justprofit.model.domain.Usuario;

public class EnderecoDAO {

    public EnderecoDAO(){ }

    public Endereco BuscaEnderecoPorID(Integer id) throws SQLException{
    	Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_SELECT_LOG_AND_NUM_USER);
        stmt.setInt(1,id);
        ResultSet resultadoUsu = stmt.executeQuery();
        Integer codEnd = null,numero = null,codBairro=null;
        String logradouro = "",cep="";

        while(resultadoUsu.next()){
        	codEnd = resultadoUsu.getInt("CODLOGR");
        	numero = resultadoUsu.getInt("NUMEND");
        }
        
        PreparedStatement stmt2 = conexao.prepareStatement(Constants.SCRIPT_SELECT_LOGADOURO);
        stmt2.setInt(1,codEnd);
        ResultSet resultadoLog = stmt2.executeQuery();
        
        
        while(resultadoLog.next()){
        	logradouro = resultadoLog.getString("NOMELOGR");
        	codBairro = resultadoLog.getInt("CODBAI");
        	cep = resultadoLog.getString("CEP");
        }
        
        BairroDAO bairroDao = new BairroDAO();
        CidadeDAO cidDao = new CidadeDAO();
        Bairro bairro = bairroDao.BuscaBairroPorID(codBairro);
        Cidade cidade = cidDao.BuscaCidadePorID(bairro.getCidade().getCodCid());
        
        Endereco endereco = new Endereco(codEnd, logradouro, numero,cep, cidade, bairro);
        
        stmt.close();
        stmt2.close();
        conexao.close();
        return endereco;
    }
    
   
    public void insertLogr(Usuario usu) throws SQLException{
    	Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_INSERT_LOGR);
        stmt.setString(1,usu.getEndereco().getLogradouro());
        stmt.setString(2,usu.getEndereco().getBairro().getNome());
        stmt.setString(3,usu.getEndereco().getCep());
        
        stmt.execute();        
        stmt.close();
    }
    
    public Integer buscaCodLogr(Usuario usu) throws SQLException{
    	Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_BUSCA_CODLOGR_USU);
        stmt.setString(1,usu.getEndereco().getCep());
        stmt.setString(2,usu.getEndereco().getLogradouro());
        stmt.setString(3,usu.getEndereco().getBairro().getNome());
        ResultSet resultado = stmt.executeQuery();
        Integer codlogr=null;
        while(resultado.next()){
        	codlogr = resultado.getInt("CODLOGR");
        }
        stmt.close();
        return codlogr;
    }

}
