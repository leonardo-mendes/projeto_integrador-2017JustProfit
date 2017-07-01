package br.com.justprofit.model.domain;


import java.util.LinkedList;

public class Estado {
    private Integer coduf;
    private String nome;
    private Regiao regiao;
    private LinkedList<Cidade> cidades = new LinkedList<Cidade>();

    public Estado(Integer coduf, String nome, Regiao regiao) {
        this.coduf = coduf;
        this.nome = nome;
        this.regiao = regiao;
        regiao.associaEstado(this);
    }

    public String getNome() {
		return nome;
	}

	public Regiao getRegiao() {
		return regiao;
	}

	public LinkedList<Cidade> getCidades() {
		return cidades;
	}

	public Integer getCoduf() {
        return coduf;
    }
    
    public void associaCidade(Cidade cidade){
        this.cidades.add(cidade);
    }
    
    public void removeCidade(Cidade cidade){
        for(int i=0; i<this.cidades.size(); i++){
            if(cidade.getCodCid()==this.cidades.get(i).getCodCid()){
                this.cidades.remove(i);
                break;
            }
        }
    }
    
    public String toString(){
    String info="";
        info+="\nCodigo UF: "+this.coduf;
        info+="\nEstado: "+this.nome;
        info+="\nRegião: "+this.regiao;
    return info;
    }
    
    
}
