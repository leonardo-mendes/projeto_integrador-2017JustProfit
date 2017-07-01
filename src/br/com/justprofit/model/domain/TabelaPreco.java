package br.com.justprofit.model.domain;

import java.util.Date;
import java.util.LinkedList;

public class TabelaPreco {
	
	private Integer codTab;
	private Date dtTabela;
	private Regiao regiao;
	private LinkedList<ProdutoTabela> produtos = new LinkedList<ProdutoTabela>();
	private Fornecedor fornecedor;
	
	public TabelaPreco(Integer codTab, Date dtTabela, Regiao regiao, Fornecedor fornecedor) {
		this.codTab = codTab;
		this.dtTabela = dtTabela;
		this.regiao = regiao;
		this.fornecedor = fornecedor;
	}
	
	/**
	 * Metodo responsavel por realizar a associa��o com a classe Produto
	 * Adiciona um produto a lista de produtos da TabelaPreco
	 * @param produto
	 */
	public void associaProd(ProdutoTabela produto){
		produtos.add(produto);
	}

	public String toString(){
		String info = "TABELA PRECO";
		
		info += "CodTab: " + this.codTab;
		info += "\nDtTabela: " + this.dtTabela;
		info += "\nRegiao: " + this.regiao;
		info += "\nProdutos: " ;
		for(ProdutoTabela produto: produtos){
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

	public Regiao getRegiao() {
		return regiao;
	}
	
	public LinkedList<ProdutoTabela> getProdutos() {
		return produtos;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}
}
