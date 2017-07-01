package br.com.justprofit.model.domain;

import java.util.LinkedList;

public class Fornecedor extends Usuario{

    private LinkedList<Vendedor> vendedores = new LinkedList<Vendedor>();
	private LinkedList<Produto> produtos = new LinkedList<Produto>();
	private LinkedList<TabelaPreco> tabelasDePreco = new LinkedList<TabelaPreco>();

    public Fornecedor(Integer codusu, String nome, String cnpj_cpf, String Ie_rg, Endereco endereco, String telefone, String email) {
        super(codusu, nome, cnpj_cpf, Ie_rg, endereco, telefone, email, "F");
    }

    /**
     * Metodo responsavel por realizar a associa��o de Produto
     * Adiciona um novo produto a lista de produtos do fornecedor
     * @param produto
     */
    public void associaProduto(Produto produto){
            produtos.add(produto);
    }

    public LinkedList<Vendedor> getVendedores() {
		return vendedores;
	}

	public LinkedList<Produto> getProdutos() {
		return produtos;
	}

	public LinkedList<TabelaPreco> getTabelasDePreco() {
		return tabelasDePreco;
	}

	/**
     * Metodo responsavel por realizar a associa��o de TabelaPreco
     * Adiciona uma nova Tabela de Preco a lista de tabelasDePreco do fornecedor
     * @param tabela
     */
    public void associaTabelaPreco(TabelaPreco tabela){
            tabelasDePreco.add(tabela);
    }

    public void associaVendedor(Vendedor vendedor){
            this.vendedores.add(vendedor);
    }


    public String printInfo(){
        String info="";
            info += "\nProdutos: ";
            for(Produto produto: produtos){
                    info+="\n"+produto.toString();
            }

            info += "\nTabelasDePreco: ";
            for(TabelaPreco tabela: tabelasDePreco){
                    info+="\n"+tabela.toString();
            }

            return info;
    }
}
