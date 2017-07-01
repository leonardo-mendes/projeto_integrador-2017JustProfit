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
import br.com.justprofit.model.dao.VendedorDAO;
import br.com.justprofit.model.domain.Vendedor;

/**
 * Servlet implementation class CarregaDadosVendForneSvlt
 */
@WebServlet({ "/CarregaDadosVendForneSvlt", "/CarregaDadosVendedorFornecedorSvlt" })
public class CarregaDadosVendForneSvlt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarregaDadosVendForneSvlt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8"); 
		int idForne = Integer.parseInt(request.getParameter("forne"));
		Vendedor vend;
		FornecedorDAO forDAO = new FornecedorDAO();
		 new LinkedList<Integer>();
		try {
			LinkedList<Integer> listaCodVend = forDAO.buscaVendedoresAssociadosAoFornecedor(idForne);
			String retornaFront = "";
			for(int i=0; i<listaCodVend.size(); i++){
				vend = VendedorDAO.buscaVendedorPorId(listaCodVend.get(i));
				String temp = vend.getNome() + ","+ vend.getCnpj_cpf() +","+ vend.getIe_rg()+ ","+ vend.getTelefone() + ","+ vend.getEmail() +  ","+ vend.getEndereco().getCidade().getEstado().getRegiao().getNome();
				retornaFront+= temp+";";
			}
			response.getWriter().print(retornaFront);
		} catch (SQLException e) {
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
