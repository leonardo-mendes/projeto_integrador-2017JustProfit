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
import br.com.justprofit.model.dao.RegiaoDAO;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/GeraDadosRankingFornecedor")
public class GeraDadosRankingFornecedorSvlt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String NomeVend = "NUll";
	
	public GeraDadosRankingFornecedorSvlt() {
		super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8"); 
		String nomeReg = request.getParameter("Nome");
		Integer codForn = Integer.parseInt(request.getParameter("codForn"));
		Double vlrTotal = 0.0;
		String dadosRank = "";
		String resposta = "";
		
		//Fornecedor fornecedor;
		FornecedorDAO forDAO = new FornecedorDAO();
		LinkedList<String> listaRank = new LinkedList<String>();
		LinkedList<String> historicoPos = new LinkedList<String>();
		LinkedList<String> dadosGrafico = new LinkedList<String>();
		RegiaoDAO regDao = new RegiaoDAO();
		Integer idReg;
		
		try {
			idReg = regDao.BuscaIDRegiaoPorNome(nomeReg);
			vlrTotal = forDAO.geraValorTotalRegiao(idReg, codForn);
			listaRank = forDAO.geraRanking(codForn, idReg);
			historicoPos = forDAO.geraHistoricoPosProduto(idReg, codForn);
			String historico = "";
			dadosGrafico = forDAO.geraDadosGraficoFornecedor(idReg, codForn);
			
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
			e.printStackTrace();
		}

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
