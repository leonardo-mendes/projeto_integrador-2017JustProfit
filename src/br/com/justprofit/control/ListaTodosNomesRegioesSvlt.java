package br.com.justprofit.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.justprofit.model.dao.FornecedorDAO;
import br.com.justprofit.model.dao.ProdutoDAO;
import br.com.justprofit.model.dao.VendedorDAO;
import br.com.justprofit.model.domain.Fornecedor;
import br.com.justprofit.model.domain.Produto;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/ListaTodosNomesRegioes")
public class ListaTodosNomesRegioesSvlt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ListaTodosNomesRegioesSvlt() {
		super();
    }
	
	//idProduto, Nome, NomeFornecedor, PrecoProd, DescMax
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8"); 
		FornecedorDAO forDAO = new FornecedorDAO();
		
		
		try {
			LinkedList<String> cods = forDAO.buscaNomesRegioes();
			String retornaFront = "Selecione uma Região,";
			for(int i=0; i<cods.size(); i++){
				retornaFront+= cods.get(i)+",";
			}
			response.getWriter().print(retornaFront);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
