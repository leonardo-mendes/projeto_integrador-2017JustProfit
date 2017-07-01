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
@WebServlet("/ListaTodosNomesFornecedores")
public class ListaTodosNomesFornecedoresSvlt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String NomeForn = "NUll";
	
	public ListaTodosNomesFornecedoresSvlt() {
		super();
    }
	
	//idProduto, Nome, NomeFornecedor, PrecoProd, DescMax
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8"); 
		int idVendedor = Integer.parseInt(request.getParameter("CodVend"));
		Fornecedor fornecedor;
		VendedorDAO vendDAO = new VendedorDAO();
		
		
		try {
			LinkedList<String> cods = vendDAO.buscaNomesFornecedoresAssociadosAoVendedor(idVendedor);
			String retornaFront = "Selecione um Fornecedor,";
			for(int i=0; i<cods.size(); i++){
				retornaFront+= cods.get(i)+",";
			}
			response.getWriter().print(retornaFront);
		} catch (SQLException e) {
			response.getWriter().print(NomeForn);
			e.printStackTrace();
		}

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
