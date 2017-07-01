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
import br.com.justprofit.model.domain.Fornecedor;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/GeraDadosRankingVendedor")
public class GeraDadosRankingVendedorSvlt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String NomeVend = "NUll";
	
	public GeraDadosRankingVendedorSvlt() {
		super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8"); 
		String nomeForne = request.getParameter("Nome");
		Integer codVendedor = Integer.parseInt(request.getParameter("codVend"));
		Double vlrTotal = 0.0;
		String dadosRank = "";
		String resposta = "";
		
		Fornecedor fornecedor;
		VendedorDAO vendDAO = new VendedorDAO();
		LinkedList<String> listaRank = new LinkedList<String>();
		LinkedList<String> historicoPos = new LinkedList<String>();
		LinkedList<String> dadosGrafico = new LinkedList<String>();
		
		try {
			fornecedor = FornecedorDAO.buscaFornecedorPorNome(nomeForne);
			vlrTotal = vendDAO.geraValorTotal(codVendedor, fornecedor.getCodusu());
			listaRank = vendDAO.geraRanking(fornecedor.getCodusu());
			historicoPos = vendDAO.geraHistoricoPos(codVendedor);
			String historico = "";
			dadosGrafico = vendDAO.geraDadosGrafico(codVendedor, fornecedor.getCodusu());
			
			
			for(String l:listaRank){
				dadosRank+= l;
			}
			
			for(String pp:historicoPos){
				historico+= pp+"/";
			}
			
			resposta += vlrTotal;
			resposta += "#"+dadosRank;
			resposta += "#"+historico;
			resposta += "#"+dadosGrafico;
			
			response.getWriter().print(resposta);
		} catch (SQLException e) {
			response.getWriter().print(NomeVend);
			e.printStackTrace();
		}

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
