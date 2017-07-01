package br.com.justprofit.model.dao;

import java.util.Date;

public class MetaDAO {

	private Integer codMeta;
	private Date dtMeta;
	private Double vlrMeta; 
	private RegiaoDAO regiao;
	
	public MetaDAO(Integer codMeta, Date dtMeta, Double vlrMeta, RegiaoDAO regiao) {
		this.codMeta = codMeta;
		this.dtMeta = dtMeta;
		this.vlrMeta = vlrMeta;
		this.regiao = regiao;
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


	public RegiaoDAO getRegiao() {
		return regiao;
	}
}
