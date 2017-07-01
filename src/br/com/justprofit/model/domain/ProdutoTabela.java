package br.com.justprofit.model.domain;


public class ProdutoTabela {
	
        private Produto produto;
	private TabelaPreco tabela;
        private Double vlrProd;

    public ProdutoTabela(Produto produto, TabelaPreco tabela, Double vlrProd) {
        this.produto = produto;
        this.tabela = tabela;
        this.vlrProd = vlrProd;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public TabelaPreco getTabela() {
        return tabela;
    }

    public void setTabela(TabelaPreco tabela) {
        this.tabela = tabela;
    }

    public Double getVlrProd() {
        return vlrProd;
    }

    public void setVlrProd(Double vlrProd) {
        this.vlrProd = vlrProd;
    }

        

}
