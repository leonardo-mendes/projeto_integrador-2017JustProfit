package br.com.justprofit.model.domain;

import java.sql.SQLException;

public interface CrudDAO {

	void salva(Usuario usu) throws SQLException;
	
	void insere(Usuario usu) throws SQLException;
	
	void atualiza(Usuario usu) throws SQLException;
	
	void exclui(Usuario usu) throws SQLException;
	
	//LinkedList<E> buscaTodos() throws SQLException;
}
