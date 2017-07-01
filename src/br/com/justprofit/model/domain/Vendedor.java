package br.com.justprofit.model.domain;


import java.sql.SQLException;
import java.util.LinkedList;

import br.com.justprofit.model.dao.FornecedorDAO;

public class Vendedor extends Usuario{
	private Integer codVend;
    private Double saldo;
    private Fornecedor fornecedor;
    private LinkedList<Pedido> pedidos = new LinkedList<Pedido>();

    public Vendedor(Integer codVend, Double saldo, Fornecedor fornecedor , Integer codusu, String nome, String cnpj_cpf, String Ie_rg, Endereco endereco, String telefone, String email) {
        super(codusu, nome, cnpj_cpf, Ie_rg, endereco, telefone, email, "V");
        this.saldo = saldo;
        this.fornecedor=fornecedor;
        fornecedor.associaVendedor(this);
    }
    
    public Vendedor(Integer codVend, Double saldo, Integer fornecedor , Integer codusu, String nome, String cnpj_cpf, String Ie_rg, Endereco endereco, String telefone, String email) {
        super(codusu, nome, cnpj_cpf, Ie_rg, endereco, telefone, email, "V");
        this.saldo = saldo;
        Fornecedor forne = null;
		try {
			forne = FornecedorDAO.buscaFornecedorPorId(fornecedor);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        this.fornecedor=forne;
        forne.associaVendedor(this);
    }
    
    public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public LinkedList<Pedido> getPedidos() {
		return pedidos;
	}

	public void setCodVend(Integer codVend) {
    	if(codVend != null){
    		this.codVend = codVend;
    	}
	}

	public Integer getCodVend() {
		return codVend;
	}

	public void associaPedido(Pedido pedido){
        this.pedidos.add(pedido);
    }    

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
      
}
