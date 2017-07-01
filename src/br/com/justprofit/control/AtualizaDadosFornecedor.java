package br.com.justprofit.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.justprofit.model.dao.EnderecoDAO;
import br.com.justprofit.model.dao.FornecedorDAO;
import br.com.justprofit.model.domain.Endereco;
import br.com.justprofit.model.domain.Fornecedor;

/**
 * Servlet implementation class AtualizaDadosFornecedor
 */
@WebServlet("/AtualizaDadosFornecedor")
public class AtualizaDadosFornecedor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AtualizaDadosFornecedor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8"); 
		int idForne = Integer.parseInt(request.getParameter("forn"));
		String dados = request.getParameter("dados");
		String array[] = new String[14];
		array = dados.split(",");
		
		Fornecedor fornecedor;
		Endereco end = null; 
		EnderecoDAO endDao = new EnderecoDAO();
		FornecedorDAO forDAO = new FornecedorDAO();
	     
		try {
			fornecedor = FornecedorDAO.buscaFornecedorPorId(idForne);
			fornecedor.setNome(array[0]);
			fornecedor.setIe_rg(array[2]);
			fornecedor.setEmail(array[3]);
			fornecedor.setCnpj_cpf(array[5]);
			fornecedor.setTelefone(array[6]);
			try {
				end = endDao.BuscaEnderecoPorID(Integer.parseInt(array[4]));
				end.setLogradouro(array[10]);
				end.setCep(array[13]);
				fornecedor.setEndereco(end);
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			forDAO.atualiza(fornecedor);
			response.getWriter().print("Fornecedor Atualizado com sucesso!");
		} catch (SQLException e) {
			response.getWriter().print("Falha na atualização!");
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
