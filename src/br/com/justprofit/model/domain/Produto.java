package br.com.justprofit.model.domain;


public class Produto {
	
    public Integer getDescMax() {
		return descMax;
	}


	private Integer codProd;
	private String nomeProd;
	private Double vlrBase;
    private Integer descMax;
	private Fornecedor fornecedor;

        public Produto(String nomeProd, Double vlrBase, Integer descMax, Integer codProd, Fornecedor fornecedor) {
            this.nomeProd = nomeProd;
            this.vlrBase = vlrBase;
            this.descMax = descMax;
            this.codProd = codProd;
            this.fornecedor = fornecedor;
        }

        public String toString(){
		String info = "PRODUTO";
		
		info += "NomeProd: " + this.nomeProd;
		info += "\nVlrBase: " + this.vlrBase;
                info += "\nDescMax: " + this.descMax;
		info += "\nCodProd: " + this.codProd;
		info += "\nFornecedor: " + this.fornecedor;
		
		return info;
	}
	
	public String getNomeProd() {
		return nomeProd;
	}


	public Double getVlrBase() {
		return vlrBase;
	}


	public Integer getCodProd() {
		return codProd;
	}


	public Fornecedor getFornecedor() {
		return fornecedor;
	}

}
