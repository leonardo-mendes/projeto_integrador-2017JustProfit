package br.com.justprofit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.justprofit.model.dao.util.Constants;
import br.com.justprofit.model.dao.util.FabricaDeConexoes;
import br.com.justprofit.model.domain.Bairro;
import br.com.justprofit.model.domain.Cidade;
import br.com.justprofit.model.domain.Usuario;

public class BairroDAO {

	public BairroDAO() {
	}

	public Bairro BuscaBairroPorID(Integer codBairro) throws SQLException {
		Connection conexao = FabricaDeConexoes.getConnection();
		PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_SELECT_BAIRRO);
		stmt.setInt(1, codBairro);
		ResultSet resultado = stmt.executeQuery();
		String nomeBairro = "";
		Integer codCid = null;

		while (resultado.next()) {
			codBairro = resultado.getInt("CODBAI");
			nomeBairro = resultado.getString("NOMEBAI");
			codCid = resultado.getInt("CODCID");
		}
		CidadeDAO cidDao = new CidadeDAO();
		Cidade cidade = cidDao.BuscaCidadePorID(codCid);
		Bairro bairro = new Bairro(codBairro, nomeBairro, cidade);

		conexao.close();
		return bairro;
	}

	public void insertBairro(Usuario usu) throws SQLException {
		Connection conexao = FabricaDeConexoes.getConnection();
		PreparedStatement stmt = conexao.prepareStatement(Constants.SCRIPT_INSERT_BAI);
		stmt.setString(1, usu.getEndereco().getBairro().getNome());
		stmt.setString(2, usu.getEndereco().getCidade().getNome());

		stmt.execute();
		stmt.close();
		conexao.close();
	}

}
