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
import br.com.justprofit.model.domain.Produto;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/ListaTodosProdutos")
public class ListTodosProdutosSvlt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Integer codProduto = null,DescMax = null;
	private String nomeProd = "NUll";
	private String NomeForn = "NUll";
	private Double vlrTot = null;
	
	public ListTodosProdutosSvlt() {
		super();
    }
	
	//idProduto, Nome, NomeFornecedor, PrecoProd, DescMax
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8"); 
		int codVende = Integer.parseInt(request.getParameter("codVend"));
		Produto produto;
		ProdutoDAO prodDAO = new ProdutoDAO();
		Double saldo = null;
		try {
			LinkedList<Produto> reslist = prodDAO.buscaTodosProdutosDoVendedor(codVende);
			LinkedList<String> reslistFront = new LinkedList<String>();
			saldo = VendedorDAO.buscaSaldoVendedor(codVende);
			for(Produto p: reslist){
				String tempProduto = "";
				tempProduto += p.getCodProd()+","+p.getNomeProd()+","+p.getFornecedor().getNome()+","+p.getVlrBase()+","+p.getDescMax();
				reslistFront.add(tempProduto);
			}
			String saldoFinal = "";
			saldoFinal += saldo;
			reslistFront.add(saldoFinal);
			for(int i=0; i<reslistFront.size(); i++){
				response.getWriter().print(reslistFront.get(i)+";");
			}
			
			
		} catch (SQLException e) {
			response.getWriter().print(codProduto + ","+ nomeProd+ ","+ NomeForn+","+vlrTot+","+DescMax);
			e.printStackTrace();
		}

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
