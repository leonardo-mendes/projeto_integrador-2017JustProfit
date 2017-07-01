package br.com.justprofit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import br.com.justprofit.model.dao.util.Constants;
import br.com.justprofit.model.dao.util.FabricaDeConexoes;
import br.com.justprofit.model.domain.Cliente;
import br.com.justprofit.model.domain.CrudDAO;
import br.com.justprofit.model.domain.Endereco;
import br.com.justprofit.model.domain.Usuario;

public class ClienteDAO implements CrudDAO{

	public ClienteDAO() {}
	
	
	public void salva(Usuario usu) throws SQLException{
		
		Cliente cliente = (Cliente) usu;
		
        if(cliente.getCodusu()== null){
            this.insere(cliente);
        }
        else{
            this.atualiza(cliente);
        }
    }
	
	public void insere(Usuario usu) throws SQLException{
		Cliente cliente = (Cliente) usu;
		Connection conexao = FabricaDeConexoes.getConnection();

		RegiaoDAO regDao = new RegiaoDAO();
        EstadoDAO estadoDao = new EstadoDAO();
        CidadeDAO cidadeDao = new CidadeDAO();
        BairroDAO bairroDao = new BairroDAO();
        EnderecoDAO endDao = new EnderecoDAO(); 
        
        regDao.InsertReg(cliente);
        estadoDao.insertEstado(cliente);
        cidadeDao.insertCidade(cliente);
        bairroDao.insertBairro(cliente);
        endDao.insertLogr(cliente);
        
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_INSERT_USER);
        stmt.setString(1,cliente.getNome());
        stmt.setString(2,cliente.getCnpj_cpf());
        stmt.setString(3,cliente.getIe_rg());
        stmt.setString(4,cliente.getTelefone());
        stmt.setString(5,cliente.getEmail());
        stmt.setInt(6,endDao.buscaCodLogr(cliente));
        stmt.setInt(7,cliente.getEndereco().getNumero());
        stmt.setString(8,cliente.getSegmento());
        stmt.execute();        
        stmt.close();
        
        Integer codLogr = endDao.buscaCodLogr(cliente);
		cliente.getEndereco().setCodend(codLogr);
        
        conexao.close();
	}
	
	public void atualiza(Usuario usu) throws SQLException{
		Cliente cliente = (Cliente) usu;
        Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_UPDATE_USER);
        stmt.setString(1,cliente.getNome());
        stmt.setString(2,cliente.getCnpj_cpf());
        stmt.setString(3,cliente.getIe_rg());
        stmt.setString(4,cliente.getTelefone());
        stmt.setString(5,cliente.getEmail());
        stmt.setInt(6,cliente.getEndereco().getCodend());
        stmt.setInt(7,cliente.getEndereco().getNumero());
        stmt.setString(8,cliente.getSegmento());
        stmt.setInt(9,cliente.getCodusu());
        
        stmt.execute();        
        stmt.close();
        conexao.close();
    }

    public void exclui(Usuario usu) throws SQLException{
    	Cliente cliente = (Cliente) usu;
        Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_DELETE_USER);
        stmt.setInt(1,cliente.getCodusu());
        
        stmt.execute();
        stmt.close();
        conexao.close();
    }
    
    public static LinkedList<Cliente> buscaTodos() throws SQLException{
        Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_SELECT_ALL_CLIENTE);;              
        ResultSet resultado = stmt.executeQuery();
        
        LinkedList<Cliente> reslist = new LinkedList<Cliente>();
        
        while(resultado.next()){            
            Integer resid = resultado.getInt("CODUSU");
            reslist.add(ClienteDAO.buscaClientePorId(resid));
        }
        
        stmt.close();
        conexao.close();
        return reslist;
    }
    
    public static Cliente buscaClientePorId(Integer id) throws SQLException{
        Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_SELECT_CLIENTE_BY_ID);
        stmt.setInt(1,id);
        ResultSet resultado = stmt.executeQuery();

        Integer tempId = null;
        String tempNome = null, tempCnpj = null, tempIe = null, tempTelefone = null, tempEmail = null,tempSeg = null;
        
        while(resultado.next()){
            tempId = resultado.getInt("CODUSU");
            tempNome = resultado.getString("NOME");
            tempCnpj = resultado.getString("CNPJ");
            tempIe = resultado.getString("IE");
            tempTelefone = resultado.getString("TELEFONE");
            tempEmail = resultado.getString("EMAIL");
            tempSeg = resultado.getString("SEGMENTO");
        }
        EnderecoDAO endDao = new EnderecoDAO();
        Endereco endereco = endDao.BuscaEnderecoPorID(tempId);
        
        Cliente cliente = new Cliente(tempId, tempNome, tempCnpj, tempIe, endereco, tempTelefone, tempEmail, tempSeg);
        
        stmt.close();
        conexao.close();
        return cliente;
    }
    
    public static Cliente buscaClientePorIdVendedor(Integer id) throws SQLException{
        Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_SELECT_CLIENTE_BY_ID_VENDEDOR);
        stmt.setInt(1,id);
        ResultSet resultado = stmt.executeQuery();

        Integer tempId = null;
        String tempNome = null, tempCnpj = null, tempIe = null, tempTelefone = null, tempEmail = null,tempSeg = null;
        
        while(resultado.next()){
            tempId = resultado.getInt("CODUSU");
            tempNome = resultado.getString("NOME");
            tempCnpj = resultado.getString("CNPJ");
            tempIe = resultado.getString("IE");
            tempTelefone = resultado.getString("TELEFONE");
            tempEmail = resultado.getString("EMAIL");
            tempSeg = resultado.getString("SEGMENTO");
        }
        EnderecoDAO endDao = new EnderecoDAO();
        Endereco endereco = endDao.BuscaEnderecoPorID(tempId);
        Cliente cliente = new Cliente(tempId, tempNome, tempCnpj, tempIe, endereco, tempTelefone, tempEmail, tempSeg);
        
        stmt.close();
        conexao.close();
        return cliente;
    }
	
}
