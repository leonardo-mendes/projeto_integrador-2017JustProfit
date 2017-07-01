package br.com.justprofit.model.domain;


import java.util.LinkedList;

public class Regiao {
	private Integer codreg;
    private String nome;
    private Meta meta;        
    private LinkedList<Estado> estados = new LinkedList<Estado>();

    public Regiao(Integer codreg, String nome) {
        this.codreg = codreg;
        this.nome = nome;
    }
    
    public void associaMeta(Meta meta){
        this.meta = meta;
    }
    
    public Integer getCodreg() {
		return codreg;
	}
    
    public void setCodreg(Integer codreg) {
    	if(codreg != null){
    		this.codreg = codreg;
    	}
	}

	public String getNome() {
		return nome;
	}

	public Meta getMeta() {
		return meta;
	}
	
	public LinkedList<Estado> getEstados() {
		return estados;
	}

	public void associaEstado(Estado estado){
        this.estados.add(estado);
    }
    
    public void removeEstado(Estado estado){
        for(int i=0; i<this.estados.size(); i++){
            if(estado.getCoduf()==this.estados.get(i).getCoduf()){
                this.estados.remove(i);
                break;
            }
        }
    }
    
    public String toString(){
    String info="";
        info+="\nCodigo de Regiao: "+this.codreg;
        info+="\nRegiao: "+this.nome;
        info+="Meta"+this.meta;
    return info;
    }
}
