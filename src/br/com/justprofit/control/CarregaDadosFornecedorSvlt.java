package br.com.justprofit.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.justprofit.model.dao.FornecedorDAO;
import br.com.justprofit.model.domain.Fornecedor;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/CarregaDadosFornecedor")
public class CarregaDadosFornecedorSvlt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Integer idfornecedor = null;
	private String razaoSocial = "NUll";
	private String codFornecedor = "NUll";
	private String ie_rg = "NUll";
	private String email = "NUll";
	private String codUsu = "NUll";
	private String cnpj_cpf = "NUll";
	private String telefone = "NUll";
	private Integer qtdVend = null;
	
	private String regiao = "NUll";
	private String cidade = "NUll";
	private String logradouro = "NUll";
	private String estado = "NUll";
	private String bairro = "NUll";
	private String cep = "NUll";

	public CarregaDadosFornecedorSvlt() {
		super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8"); 
		int idForne = Integer.parseInt(request.getParameter("forn"));
		Fornecedor fornecedor;
		FornecedorDAO forDAO = new FornecedorDAO();
		try {
			fornecedor = FornecedorDAO.buscaFornecedorPorId(idForne);
			qtdVend = forDAO.buscaTotalDeVendedoresAssociado(fornecedor.getCodusu());
			response.getWriter().print(fornecedor.getNome() + ","+ fornecedor.getCodusu()+ ","+ fornecedor.getIe_rg()+ ","+ fornecedor.getEmail() + ","+ fornecedor.getCodusu() + ","+ fornecedor.getCnpj_cpf() + ","+ fornecedor.getTelefone() + ","+ qtdVend + "," +fornecedor.getEndereco().getCidade().getEstado().getRegiao().getNome() + ","+ fornecedor.getEndereco().getCidade().getNome()+ ","+ fornecedor.getEndereco().getLogradouro() + ","+ fornecedor.getEndereco().getCidade().getEstado().getNome()+ ","+ fornecedor.getEndereco().getBairro().getNome() + ","+ fornecedor.getEndereco().getCep());
		} catch (SQLException e) {
			response.getWriter().print(razaoSocial + ","+ codFornecedor+ ","+ ie_rg + ","+ email + ","+ codUsu + ","+ cnpj_cpf + ","+ telefone + ","+ qtdVend + "," +regiao + ","+ cidade+ ","+ logradouro + ","+ estado + ","+ bairro + ","+ cep);
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
