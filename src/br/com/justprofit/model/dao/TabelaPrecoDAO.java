package br.com.justprofit.model.dao;

import java.util.Date;
import java.util.LinkedList;

public class TabelaPrecoDAO {
	
	private Integer codTab;
	private Date dtTabela;
	private RegiaoDAO regiao;
	private LinkedList<ProdutoDAO> produtos = new LinkedList<ProdutoDAO>();
	private FornecedorDAO fornecedor;
	
	public TabelaPrecoDAO(Integer codTab, Date dtTabela, RegiaoDAO regiao, FornecedorDAO fornecedor) {
		this.codTab = codTab;
		this.dtTabela = dtTabela;
		this.regiao = regiao;
		this.fornecedor = fornecedor;
	}
	
	/**
	 * Metodo responsavel por realizar a associação com a classe Produto
	 * Adiciona um produto a lista de produtos da TabelaPreco
	 * @param produto
	 */
	public void associaProd(ProdutoDAO produto){
		produtos.add(produto);
	}

	public String toString(){
		String info = "TABELA PRECO";
		
		info += "CodTab: " + this.codTab;
		info += "\nDtTabela: " + this.dtTabela;
		info += "\nRegiao: " + this.regiao;
		info += "\nProdutos: " ;
		for(ProdutoDAO produto: produtos){
			info+="\n"+produto.toString();
		}
		info += "\nFornecedor: " + this.fornecedor;
		return info;
	}
	
	public Integer getCodTab() {
		return codTab;
	}

	public Date getDtTabela() {
		return dtTabela;
	}

	public RegiaoDAO getRegiao() {
		return regiao;
	}
	
	public LinkedList<ProdutoDAO> getProdutos() {
		return produtos;
	}

	public FornecedorDAO getFornecedor() {
		return fornecedor;
	}
}
