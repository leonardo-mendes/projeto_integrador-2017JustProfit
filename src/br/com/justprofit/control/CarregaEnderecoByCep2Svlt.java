package br.com.justprofit.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.justprofit.model.dao.EnderecoDAO;
import br.com.justprofit.model.domain.Endereco;

/**
 * Servlet implementation class CarregaEnderecoByCep
 */
@WebServlet("/CarregaEnderecoByCep2")
public class CarregaEnderecoByCep2Svlt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarregaEnderecoByCep2Svlt() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8"); 
		HttpSession session = request.getSession();
		String cep = (String) session.getValue("cep");
		EnderecoDAO endDao = new EnderecoDAO();
		Endereco endereco;
		
		try {
			endereco = endDao.BuscaEnderecoPorCEP(cep);
			String dadoFinal = endereco.getCidade().getEstado().getRegiao().getNome()+","+endereco.getCidade().getEstado().getNome()+","+endereco.getCidade().getNome()+","+endereco.getBairro().getNome()+","+endereco.getCep()+","+endereco.getLogradouro();
			response.getWriter().print(dadoFinal);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
