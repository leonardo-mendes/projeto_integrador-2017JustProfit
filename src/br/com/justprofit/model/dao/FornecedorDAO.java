package br.com.justprofit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import br.com.justprofit.model.dao.util.Constants;
import br.com.justprofit.model.dao.util.FabricaDeConexoes;
import br.com.justprofit.model.domain.CrudDAO;
import br.com.justprofit.model.domain.Endereco;
import br.com.justprofit.model.domain.Fornecedor;
import br.com.justprofit.model.domain.Usuario;
import br.com.justprofit.model.domain.Vendedor;

public class FornecedorDAO implements CrudDAO{

	public FornecedorDAO(){}
	
	public void salva(Usuario usu) throws SQLException {
		Fornecedor fornecedor = (Fornecedor) usu;
        if(fornecedor.getCodusu()== null){
            this.insere(fornecedor);
        }
        else{
            this.atualiza(fornecedor);
        }
	}

	public void insere(Usuario usu) throws SQLException {
		Fornecedor fornecedor = (Fornecedor) usu;
		Connection conexao = FabricaDeConexoes.getConnection();

		RegiaoDAO regDao = new RegiaoDAO();
        EstadoDAO estadoDao = new EstadoDAO();
        CidadeDAO cidadeDao = new CidadeDAO();
        BairroDAO bairroDao = new BairroDAO();
        EnderecoDAO endDao = new EnderecoDAO(); 
        
        regDao.InsertReg(fornecedor);
        estadoDao.insertEstado(fornecedor);
        cidadeDao.insertCidade(fornecedor);
        bairroDao.insertBairro(fornecedor);
        endDao.insertLogr(fornecedor);
        
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_INSERT_USER);
        stmt.setString(1,fornecedor.getNome());
        stmt.setString(2,fornecedor.getCnpj_cpf());
        stmt.setString(3,fornecedor.getIe_rg());
        stmt.setString(4,fornecedor.getTelefone());
        stmt.setString(5,fornecedor.getEmail());
        stmt.setInt(6,endDao.buscaCodLogr(fornecedor));
        stmt.setInt(7,fornecedor.getEndereco().getNumero());
        stmt.setString(8,fornecedor.getSegmento());
        stmt.execute();
        
        UsuarioDAO usuDAO = new UsuarioDAO();
        Integer codUsu = usuDAO.buscaCodUsu(fornecedor);
        Integer codReg = regDao.BuscaIDRegiaoPorNome(fornecedor.getEndereco().getCidade().getEstado().getRegiao().getNome());
        fornecedor.setCodusu(codUsu);
        fornecedor.getEndereco().getCidade().getEstado().getRegiao().setCodreg(codReg);
        Integer codLogr = endDao.buscaCodLogr(fornecedor);
        fornecedor.getEndereco().setCodend(codLogr);
       
        stmt.close();
        conexao.close();
	}

	public void atualiza(Usuario usu) throws SQLException {
		Fornecedor fornecedor = (Fornecedor) usu;
		EnderecoDAO endDao = new EnderecoDAO();
		Integer codLogr = endDao.buscaCodLogr(fornecedor);
        fornecedor.getEndereco().setCodend(codLogr);
        Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_UPDATE_USER);
        stmt.setString(1,fornecedor.getNome());
        stmt.setString(2,fornecedor.getCnpj_cpf());
        stmt.setString(3,fornecedor.getIe_rg());
        stmt.setString(4,fornecedor.getTelefone());
        stmt.setString(5,fornecedor.getEmail());
        stmt.setInt(6,fornecedor.getEndereco().getCodend());
        stmt.setInt(7,fornecedor.getEndereco().getNumero());
        stmt.setString(8,fornecedor.getSegmento());
        stmt.setInt(9,fornecedor.getCodusu());
        
        stmt.execute();        
        stmt.close();
        conexao.close();
	}

	public void exclui(Usuario usu) throws SQLException {
		Fornecedor fornecedor = (Fornecedor) usu;
        Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_DELETE_USER);
        stmt.setInt(1,fornecedor.getCodusu());
        
        stmt.execute();
        stmt.close();
        conexao.close();
	}
	
	public static Fornecedor buscaFornecedorPorId(Integer id) throws SQLException{
        Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_SELECT_FORNECEDOR_BY_ID);
        stmt.setInt(1,id);
        ResultSet resultado = stmt.executeQuery();

        Integer tempId = null;
        String tempNome = null, tempCnpj = null, tempIe = null, tempTelefone = null, tempEmail = null;
        
        while(resultado.next()){
            tempId = resultado.getInt("CODUSU");
            tempNome = resultado.getString("NOME");
            tempCnpj = resultado.getString("CNPJ");
            tempIe = resultado.getString("IE");
            tempTelefone = resultado.getString("TELEFONE");
            tempEmail = resultado.getString("EMAIL");
        }
        EnderecoDAO endDao = new EnderecoDAO();
        Endereco endereco = endDao.BuscaEnderecoPorID(tempId);
        RegiaoDAO regDao = new RegiaoDAO();
        
        Fornecedor forne = new Fornecedor(tempId,tempNome,tempCnpj,tempIe,endereco,tempTelefone, tempEmail);
        
        //FornecedorDAO.associaVendedorInserindoContrato();
        
        Integer codReg = regDao.BuscaIDRegiaoPorNome(forne.getEndereco().getCidade().getEstado().getRegiao().getNome());
        forne.getEndereco().getCidade().getEstado().getRegiao().setCodreg(codReg);
        
        stmt.close();
        conexao.close();
        return forne;
    }
    
    public LinkedList<Fornecedor> buscaTodos() throws SQLException{
        Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_SELECT_ALL_FORNECEDOR);;              
        ResultSet resultado = stmt.executeQuery();
        
        LinkedList<Fornecedor> reslist = new LinkedList<Fornecedor>();
        
        while(resultado.next()){            
            Integer resid = resultado.getInt("CODUSU");
            reslist.add(FornecedorDAO.buscaFornecedorPorId(resid));
        }
        
        stmt.close();
        conexao.close();
        return reslist;
    }
	
    public static Fornecedor buscaFornecedorPorNome(String nomeFornecedor) throws SQLException{
        Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_SELECT_FORNECEDOR_BY_NAME);
        stmt.setString(1,nomeFornecedor);
        ResultSet resultado = stmt.executeQuery();

        Integer tempId = null;
        String tempNome = null, tempCnpj = null, tempIe = null, tempTelefone = null, tempEmail = null;
        
        while(resultado.next()){
            tempId = resultado.getInt("CODUSU");
            tempNome = resultado.getString("NOME");
            tempCnpj = resultado.getString("CNPJ");
            tempIe = resultado.getString("IE");
            tempTelefone = resultado.getString("TELEFONE");
            tempEmail = resultado.getString("EMAIL");
        }
        EnderecoDAO endDao = new EnderecoDAO();
        Endereco endereco = endDao.BuscaEnderecoPorID(tempId);
        RegiaoDAO regDao = new RegiaoDAO();
        
        Fornecedor forne = new Fornecedor(tempId,tempNome,tempCnpj,tempIe,endereco,tempTelefone, tempEmail);
        
        Integer codReg = regDao.BuscaIDRegiaoPorNome(forne.getEndereco().getCidade().getEstado().getRegiao().getNome());
        forne.getEndereco().getCidade().getEstado().getRegiao().setCodreg(codReg);
        
        stmt.close();
        conexao.close();
        return forne;
    }
    
    public static Fornecedor buscaFornecedorPorIdVendedor(Integer idVendedor) throws SQLException{
        Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_SELECT_FORNECEDOR_BY_ID_VENDEDOR);
        stmt.setInt(1,idVendedor);
        ResultSet resultado = stmt.executeQuery();

        Integer tempId = null;
        String tempNome = null, tempCnpj = null, tempIe = null, tempTelefone = null, tempEmail = null;
        
        while(resultado.next()){
            tempId = resultado.getInt("CODUSU");
            tempNome = resultado.getString("NOME");
            tempCnpj = resultado.getString("CNPJ");
            tempIe = resultado.getString("IE");
            tempTelefone = resultado.getString("TELEFONE");
            tempEmail = resultado.getString("EMAIL");
        }
        EnderecoDAO endDao = new EnderecoDAO();
        Endereco endereco = endDao.BuscaEnderecoPorID(tempId);
        RegiaoDAO regDao = new RegiaoDAO();
        
        Fornecedor forne = new Fornecedor(tempId,tempNome,tempCnpj,tempIe,endereco,tempTelefone, tempEmail);
        
        Integer codReg = regDao.BuscaIDRegiaoPorNome(forne.getEndereco().getCidade().getEstado().getRegiao().getNome());
        forne.getEndereco().getCidade().getEstado().getRegiao().setCodreg(codReg);
        
        stmt.close();
        conexao.close();
        return forne;
    }
    
    public void associaVendedorInserindoContrato(int Codfornecedor, int Codvend) throws SQLException {
		Connection conexao = FabricaDeConexoes.getConnection();
		Double Saldo=null;
		Saldo = VendedorDAO.buscaSaldoVendedor(Codvend);
		Fornecedor fonecedor = FornecedorDAO.buscaFornecedorPorId(Codfornecedor);
		Vendedor vendedor = VendedorDAO.buscaVendedorPorIdVendedor(Codvend);
		fonecedor.associaVendedor(vendedor);
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_INSERE_CONTRATO);
        stmt.setInt(1,Codvend);
        stmt.setInt(2,Codfornecedor);
       // stmt.setDouble(3,Saldo);
        
        stmt.execute();
        stmt.close();
        conexao.close();
	}
    
    public void associaVendedorInserindoContrato(int Codfornecedor, int Codvend, Double saldo) throws SQLException {
		Connection conexao = FabricaDeConexoes.getConnection();
		Fornecedor fonecedor = FornecedorDAO.buscaFornecedorPorId(Codfornecedor);
		Vendedor vendedor = VendedorDAO.buscaVendedorPorIdVendedor(Codvend);
		fonecedor.associaVendedor(vendedor);
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_INSERE_CONTRATO);
        stmt.setInt(1,Codvend);
        stmt.setInt(2,Codfornecedor);
        //stmt.setDouble(3,saldo);
        
        stmt.execute();
        stmt.close();
        conexao.close();
	}
    
    
    public LinkedList<Integer> buscaVendedoresAssociadosAoFornecedor(int Codfornecedor) throws SQLException {
		Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_SELECT_VENDEDORES_ASSOSIADOS_AO_FORNECEDOR);
        stmt.setInt(1,Codfornecedor);
        ResultSet resultado = stmt.executeQuery();

        LinkedList<Integer> cods = new LinkedList<Integer>();
        
        while(resultado.next()){
      	  cods.add(resultado.getInt("CODUSU"));
        	
        }
        
        
        stmt.execute();
        stmt.close();
        conexao.close();
		return cods;
	}
    
    public Integer buscaTotalDeVendedoresAssociado(int Codfornecedor) throws SQLException {
		Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_SELECT_TOTAL_VEND_ASSOCIADOS_AO_FORNECEDOR);
        stmt.setInt(1,Codfornecedor);
        ResultSet resultado = stmt.executeQuery();

        Integer total = 0;
        
        while(resultado.next()){
        	total++;
        }
        
        stmt.execute();
        stmt.close();
        conexao.close();
		return total;
	}
    
}
