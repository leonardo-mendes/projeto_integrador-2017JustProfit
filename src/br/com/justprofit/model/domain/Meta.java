package br.com.justprofit.model.domain;

import java.util.Date;

public class Meta {

	private Integer codMeta;
	private Date dtMeta;
	private Double vlrMeta; 
	private Regiao regiao;
        private Fornecedor fornecedor;
	
	public Meta(Integer codMeta, Date dtMeta, Double vlrMeta, Regiao regiao, Fornecedor fornecedor) {
		this.codMeta = codMeta;
		this.dtMeta = dtMeta;
		this.vlrMeta = vlrMeta;
		this.regiao = regiao;
                this.fornecedor=fornecedor;
	}
	
	
	public String toString(){
		String info = "DADOS META";
		
		info += "CodMeta: " + this.codMeta;
		info += "\nDtMeta: " + this.dtMeta;
		info += "\nVlrMeta: " + this.vlrMeta;
		info += "\nRegiao: " + this.regiao;
		return info;
	}
	
	public Integer getCodMeta() {
		return codMeta;
	}


	public Date getDtMeta() {
		return dtMeta;
	}


	public Double getVlrMeta() {
		return vlrMeta;
	}


	public Regiao getRegiao() {
		return regiao;
	}

        public Fornecedor getFornecedor() {
            return fornecedor;
        }

        public void setFornecedor(Fornecedor fornecedor) {
            this.fornecedor = fornecedor;
        }
        
        
}
