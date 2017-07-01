package br.com.justprofit.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.justprofit.model.dao.VendedorDAO;
import br.com.justprofit.model.domain.Vendedor;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/CarregaDadosVendedor")
public class CarregaDadosVendedorSvlt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Integer idVend = null;
	private String razaoSocial = "NUll";
	private String codVend = "NUll";
	private String ie_rg = "NUll";
	private String email = "NUll";
	private String codUsu = "NUll";
	private String cnpj_cpf = "NUll";
	private String telefone = "NUll";
	private Integer qtdFornAtend = null;
	
	private String regiao = "NUll";
	private String cidade = "NUll";
	private String logradouro = "NUll";
	private String estado = "NUll";
	private String bairro = "NUll";
	private String cep = "NUll";

	public CarregaDadosVendedorSvlt() {
		super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8"); 
		idVend = Integer.parseInt(request.getParameter("vend"));
		Vendedor vend;
		VendedorDAO vendDAO = new VendedorDAO();
		try {
			vend = VendedorDAO.buscaVendedorPorId(idVend);
			qtdFornAtend = vendDAO.buscaTotalDeFornecedoresAssociado(vend.getCodVend());
			response.getWriter().print(vend.getNome() + ","+ vend.getCodVend()+ ","+ vend.getIe_rg()+ ","+ vend.getEmail() + ","+ vend.getCodusu() + ","+ vend.getCnpj_cpf() + ","+ vend.getTelefone() + ","+ qtdFornAtend + "," +vend.getEndereco().getCidade().getEstado().getRegiao().getNome() + ","+ vend.getEndereco().getCidade().getNome()+ ","+ vend.getEndereco().getLogradouro() + ","+ vend.getEndereco().getCidade().getEstado().getNome()+ ","+ vend.getEndereco().getBairro().getNome() + ","+ vend.getEndereco().getCep());
		} catch (SQLException e) {
			response.getWriter().print(razaoSocial + ","+ codVend+ ","+ ie_rg + ","+ email + ","+ codUsu + ","+ cnpj_cpf + ","+ telefone + ","+ qtdFornAtend + "," +regiao + ","+ cidade+ ","+ logradouro + ","+ estado + ","+ bairro + ","+ cep);
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
