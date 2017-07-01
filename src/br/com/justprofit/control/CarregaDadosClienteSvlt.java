package br.com.justprofit.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.justprofit.model.dao.ClienteDAO;
import br.com.justprofit.model.dao.FornecedorDAO;
import br.com.justprofit.model.domain.Cliente;
import br.com.justprofit.model.domain.Fornecedor;
import br.com.justprofit.model.domain.Vendedor;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/CarregaDadosCliente")
public class CarregaDadosClienteSvlt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CarregaDadosClienteSvlt() {
		super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8"); 
		int idVend = Integer.parseInt(request.getParameter("codVend"));
		Cliente cliente;
		ClienteDAO cliDAO = new ClienteDAO();
		try {
			cliente = ClienteDAO.buscaClientePorIdVendedor(idVend);
			response.getWriter().print(cliente.getNome() + ","+ cliente.getCnpj_cpf() +","+ cliente.getIe_rg()+ ","+ cliente.getTelefone() + ","+ cliente.getEmail() +  ","+ cliente.getEndereco().getCidade().getEstado().getRegiao().getNome());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
