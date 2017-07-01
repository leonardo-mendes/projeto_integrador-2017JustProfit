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

public class VendedorDAO implements CrudDAO{
    
    public VendedorDAO() {}
    
    public void salva(Usuario usu) throws SQLException{
    	Vendedor vendedor = (Vendedor) usu;
        if(vendedor.getCodusu()== null){
            this.insere(vendedor);
        }
        else{
            this.atualiza(vendedor);
        }
    }
    
    public void insere(Usuario usu) throws SQLException{
    	Vendedor vendedor = (Vendedor) usu;
		Connection conexao = FabricaDeConexoes.getConnection();

		RegiaoDAO regDao = new RegiaoDAO();
        EstadoDAO estadoDao = new EstadoDAO();
        CidadeDAO cidadeDao = new CidadeDAO();
        BairroDAO bairroDao = new BairroDAO();
        EnderecoDAO endDao = new EnderecoDAO(); 
        
        regDao.InsertReg(vendedor);
        estadoDao.insertEstado(vendedor);
        cidadeDao.insertCidade(vendedor);
        bairroDao.insertBairro(vendedor);
        endDao.insertLogr(vendedor);
        
        //insere dados do vendedor na tabela jpusu
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_INSERT_USER);
        stmt.setString(1,vendedor.getNome());
        stmt.setString(2,vendedor.getCnpj_cpf());
        stmt.setString(3,vendedor.getIe_rg());
        stmt.setString(4,vendedor.getTelefone());
        stmt.setString(5,vendedor.getEmail());
        stmt.setInt(6,endDao.buscaCodLogr(vendedor));
        stmt.setInt(7,vendedor.getEndereco().getNumero());
        stmt.setString(8,vendedor.getSegmento());
        stmt.execute();
        
        //Define os codigos de endereco e usuario gerados no banco para o objeto java
        UsuarioDAO usuDAO = new UsuarioDAO();
        Integer codUsu = usuDAO.buscaCodUsu(vendedor);
        Integer codReg = regDao.BuscaIDRegiaoPorNome(vendedor.getEndereco().getCidade().getEstado().getRegiao().getNome());
        vendedor.setCodusu(codUsu);
        vendedor.getEndereco().getCidade().getEstado().getRegiao().setCodreg(codReg);
        Integer codLogr = endDao.buscaCodLogr(vendedor);
		vendedor.getEndereco().setCodend(codLogr);
       
        //insere dados do vendedor na tabela jpven
        PreparedStatement stmt2 = conexao.prepareStatement(Constants.SCRIPT_INSERT_VEND);
        stmt2.setInt(1,vendedor.getCodusu());
        stmt2.setInt(2,vendedor.getEndereco().getCidade().getEstado().getRegiao().getCodreg());
        
        
        stmt2.execute();
        stmt2.close();
        stmt.close();
        
        //set a variavel codVend com o cod gerado no sql
        Integer codVend = VendedorDAO.buscaCodVend(vendedor.getCodusu(), codReg);
        vendedor.setCodVend(codVend);
        
        //Insere dados do contrato do vendedor
        FornecedorDAO fornDAO = new FornecedorDAO();
        //fornDAO.associaVendedorInserindoContrato(vendedor.getFornecedor().getCodusu(), vendedor.getCodVend(),vendedor.getSaldo());
        Integer codUsuForn = usuDAO.buscaCodUsu(vendedor.getFornecedor());
        vendedor.getFornecedor().setCodusu(codUsuForn);
        PreparedStatement stmt3 = conexao.prepareStatement(Constants.SCRIPT_INSERE_CONTRATO);
        stmt3.setInt(1,vendedor.getCodVend());
        stmt3.setInt(2,vendedor.getFornecedor().getCodusu());
        //stmt3.setDouble(3,vendedor.getSaldo());
        
        stmt3.execute();
        stmt3.close();
        
        conexao.close();
        
        
	}
	
	public void atualiza(Usuario usu) throws SQLException{
		Vendedor vendedor = (Vendedor) usu;
        Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_UPDATE_USER);
        stmt.setString(1,vendedor.getNome());
        stmt.setString(2,vendedor.getCnpj_cpf());
        stmt.setString(3,vendedor.getIe_rg());
        stmt.setString(4,vendedor.getTelefone());
        stmt.setString(5,vendedor.getEmail());
        stmt.setInt(6,vendedor.getEndereco().getCodend());
        stmt.setInt(7,vendedor.getEndereco().getNumero());
        stmt.setString(8,vendedor.getSegmento());
        stmt.setInt(9,vendedor.getCodusu());
        stmt.execute();        
        
        PreparedStatement stmt2 = conexao.prepareStatement(Constants.SCRIPT_UPDATE_VEND);
        Integer codVend = VendedorDAO.buscaCodVend(vendedor.getCodusu(), vendedor.getEndereco().getCidade().getEstado().getRegiao().getCodreg());
        vendedor.setCodVend(codVend);
        stmt2.setInt(1, vendedor.getEndereco().getCidade().getEstado().getRegiao().getCodreg());
        stmt2.setInt(2, vendedor.getCodVend());
        stmt2.setInt(3,vendedor.getCodusu());
        
        PreparedStatement stmt3 = conexao.prepareStatement(Constants.SCRIPT_INSERE_CONTRATO);
        stmt3.setInt(1,vendedor.getCodVend());
        stmt3.setInt(2,vendedor.getFornecedor().getCodusu());
        //stmt3.setDouble(3,vendedor.getSaldo());
        
        stmt3.execute();
        stmt3.close();
        
        stmt2.execute();
        stmt.close();
        stmt2.close();
        conexao.close();
    }

    public void exclui(Usuario usu) throws SQLException{
    	Vendedor vendedor = (Vendedor) usu;
    	String sql1 = Constants.SCRIPT_DELETE_VEND;
    	
        Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(sql1);
        stmt.setInt(1,vendedor.getCodusu());
        stmt.setInt(2,vendedor.getCodVend());
        
        PreparedStatement stmt2 = conexao.prepareStatement(Constants.SCRIPT_DELETE_USER);
        stmt2.setInt(1,vendedor.getCodusu());
        
        PreparedStatement stmt3 = conexao.prepareStatement(Constants.SCRIPT_DELETE_CONTRATO);
        stmt3.setInt(1,vendedor.getCodVend());
        
        
        stmt.execute();  
        stmt2.execute();
        stmt3.execute();
        stmt.close();
        stmt2.close();
        stmt3.close();
        conexao.close();
    }
    
    public static Integer buscaCodVend(Integer codUsu, Integer codReg) throws SQLException{
        Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_BUSCA_IDVEND);
        stmt.setInt(1,codUsu);
        stmt.setInt(2,codReg);
        ResultSet resultado = stmt.executeQuery();
        Integer codVend = null;
        
        while(resultado.next()){
        	codVend = resultado.getInt("CODVEND");
        }
        
        return codVend;
    }
    
    public static Vendedor buscaVendedorPorId(Integer id) throws SQLException{
        Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_SELECT_VENDEDOR_BY_ID);
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
        //Busca o fornecedor do por ID(idusu) do vendedor 
        Integer codRegFor = regDao.BuscaIDRegiaoPorNome(endereco.getCidade().getEstado().getRegiao().getNome());
        Fornecedor forne = FornecedorDAO.buscaFornecedorPorIdVendedor(VendedorDAO.buscaCodVend(id, codRegFor));
       // Fornecedor forne = FornecedorDAO.buscaFornecedorPorIdVendedor(id);
        
        Vendedor vendedor = new Vendedor(null, null, forne ,tempId,tempNome,tempCnpj,tempIe,endereco,tempTelefone, tempEmail);
        
        Integer codReg = regDao.BuscaIDRegiaoPorNome(vendedor.getEndereco().getCidade().getEstado().getRegiao().getNome());
        Integer codVend = VendedorDAO.buscaCodVend(vendedor.getCodusu(), codReg);
        vendedor.getEndereco().getCidade().getEstado().getRegiao().setCodreg(codReg);
        vendedor.setCodVend(codVend);
        Double saldo = VendedorDAO.buscaSaldoVendedor(vendedor.getCodVend());
        vendedor.setSaldo(saldo);
        
        stmt.close();
        conexao.close();
        return vendedor;
    }
    
    public static Vendedor buscaVendedorPorIdVendedor(Integer id) throws SQLException{
        Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_SELECT_VENDEDOR_BY_IDVENDEDOR);
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
        //Busca o fornecedor do por ID(idusu) do vendedor 
        //Integer codRegFor = regDao.BuscaIDRegiaoPorNome(endereco.getCidade().getEstado().getRegiao().getNome());
       // Fornecedor forne = FornecedorDAO.buscaFornecedorPorIdVendedor(VendedorDAO.buscaCodVend(id, codRegFor));
        Fornecedor forne = FornecedorDAO.buscaFornecedorPorIdVendedor(id);
        
        Vendedor vendedor = new Vendedor(null, null, forne ,tempId,tempNome,tempCnpj,tempIe,endereco,tempTelefone, tempEmail);
        
        Integer codReg = regDao.BuscaIDRegiaoPorNome(vendedor.getEndereco().getCidade().getEstado().getRegiao().getNome());
        Integer codVend = VendedorDAO.buscaCodVend(vendedor.getCodusu(), codReg);
        vendedor.getEndereco().getCidade().getEstado().getRegiao().setCodreg(codReg);
        vendedor.setCodVend(codVend);
        Double saldo = VendedorDAO.buscaSaldoVendedor(vendedor.getCodVend());
        vendedor.setSaldo(saldo);
        
        stmt.close();
        conexao.close();
        return vendedor;
    }
    
    public static Vendedor buscaVendedorPorIdVendedor(Integer idVendedor, Integer idFornecedor) throws SQLException{
        Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_SELECT_VENDEDOR_BY_IDVENDEDOR);
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
        //Busca o fornecedor do por ID(idusu) do vendedor 
        //Integer codRegFor = regDao.BuscaIDRegiaoPorNome(endereco.getCidade().getEstado().getRegiao().getNome());
       // Fornecedor forne = FornecedorDAO.buscaFornecedorPorIdVendedor(VendedorDAO.buscaCodVend(id, codRegFor));
        Fornecedor forne = FornecedorDAO.buscaFornecedorPorId(idFornecedor);
        
        Vendedor vendedor = new Vendedor(null, null, forne ,tempId,tempNome,tempCnpj,tempIe,endereco,tempTelefone, tempEmail);
        
        Integer codReg = regDao.BuscaIDRegiaoPorNome(vendedor.getEndereco().getCidade().getEstado().getRegiao().getNome());
        Integer codVend = VendedorDAO.buscaCodVend(vendedor.getCodusu(), codReg);
        vendedor.getEndereco().getCidade().getEstado().getRegiao().setCodreg(codReg);
        vendedor.setCodVend(codVend);
        Double saldo = VendedorDAO.buscaSaldoVendedor(vendedor.getCodVend());
        vendedor.setSaldo(saldo);
        
        stmt.close();
        conexao.close();
        return vendedor;
    }
    
    public LinkedList<Vendedor> buscaTodos() throws SQLException{
        Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_SELECT_ALL_VEND);         
        ResultSet resultado = stmt.executeQuery();
        
        LinkedList<Vendedor> reslist = new LinkedList<Vendedor>();
        
        while(resultado.next()){            
            Integer resid = resultado.getInt("CODUSU");
            reslist.add(VendedorDAO.buscaVendedorPorId(resid));
        }
        
        stmt.close();
        conexao.close();
        return reslist;
    }
    
    public static Double buscaSaldoVendedor(int codVend) throws SQLException{
        Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_SELECT_SALDO_VEND);
        stmt.setInt(1,codVend);
        ResultSet resultado = stmt.executeQuery();
        
        Double saldo = null;
        
        while(resultado.next()){            
            saldo = resultado.getDouble("VLRSALDO");
        }
        
        stmt.close();
        conexao.close();
        return saldo;
    }

    public Integer buscaTotalDeFornecedoresAssociado(int Codvendedor) throws SQLException {
		Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_SELECT_TOTAL_FORNECEDORES_ASSOSIADOS_AO_VEND);
        stmt.setInt(1,Codvendedor);
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
    
    public LinkedList<Integer> buscaFornecedoresAssociadosAoVendedor(int CodfVendedor) throws SQLException {
  		Connection conexao = FabricaDeConexoes.getConnection();
          PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_SELECT_FORNECEDORES_ASSOSIADOS_AO_VENDEDOR);
          stmt.setInt(1,CodfVendedor);
          ResultSet resultado = stmt.executeQuery();

          LinkedList<Integer> cods = new LinkedList<Integer>();
          
          while(resultado.next()){
        	  cods.add(resultado.getInt("CODFORN"));
          	
          }
          
          stmt.execute();
          stmt.close();
          conexao.close();
  		return cods;
  	}
    
    public LinkedList<String> buscaNomesFornecedoresAssociadosAoVendedor(int CodVendedor) throws SQLException {
  		Connection conexao = FabricaDeConexoes.getConnection();
          PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_SELECT_NOME_FORNECEDORES_ASSOSIADOS_AO_VENDEDOR);
          stmt.setInt(1,CodVendedor);
          ResultSet resultado = stmt.executeQuery();

          LinkedList<String> cods = new LinkedList<String>();
          
          while(resultado.next()){
        	  cods.add(resultado.getString("NOME"));
          }
          
          stmt.execute();
          stmt.close();
          conexao.close();
  		return cods;
  	}
    
    public Double geraValorTotal(int codVend, int codForn) throws SQLException {
		Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_SELECT_VALOR_MENSAL_VENDEDOR);
        stmt.setInt(1,codVend);
        stmt.setInt(2,codForn);
        ResultSet resultado = stmt.executeQuery();

        Double total = 0.0;
        
        while(resultado.next()){
        	total = resultado.getDouble("VLRTOTAL");
        }
        
        stmt.execute();
        stmt.close();
        conexao.close();
		return total;
	}

    public LinkedList<String> geraRanking (int codForn) throws SQLException {
		Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_SELECT_RANKING_MENSAL);
        stmt.setInt(1,codForn);
        ResultSet resultado = stmt.executeQuery();
        LinkedList<String> cods = new LinkedList<String>();
        
        while(resultado.next()){
        	String linha = "";
        	linha+= resultado.getString("VLRTOTAL");
        	linha+= ",";	
        	
        	cods.add(linha);
        }
        
        stmt.execute();
        stmt.close();
        conexao.close();
		return cods;
	}
    
    public LinkedList<String> geraHistoricoPos(int codVend) throws SQLException {
		Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_SELECT_HISTORICO_POS);
        stmt.setInt(1,codVend);
        stmt.setInt(2,codVend);
        ResultSet resultado = stmt.executeQuery();

        LinkedList<String> cods = new LinkedList<String>();
        
        while(resultado.next()){
        	String linha = resultado.getString("PRIMEIRO");
        	cods.add(linha);
        }
        
        stmt.execute();
        stmt.close();
        conexao.close();
		return cods;
	}
    
    public LinkedList<String> geraDadosGrafico(int codVend, int codForn) throws SQLException {
		Connection conexao = FabricaDeConexoes.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_SELECT_RANKING_GRAFICO);
        stmt.setInt(1,codVend);
        stmt.setInt(2,codForn);
        ResultSet resultado = stmt.executeQuery();

        LinkedList<String> cods = new LinkedList<String>();
        
        while(resultado.next()){
        	String linha = resultado.getString("6_MESES");
        	 linha += "/"+ resultado.getString("5_MESES");
        	 linha += "/"+resultado.getString("4_MESES");
        	 linha += "/"+resultado.getString("3_MESES");
        	 linha += "/"+resultado.getString("2_MESES");
        	 linha += "/"+resultado.getString("1_MESES");
        	 cods.add(linha);
        }
        
        stmt.execute();
        stmt.close();
        conexao.close();
		return cods;
	}
    
    
}
