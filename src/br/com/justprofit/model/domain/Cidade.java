package br.com.justprofit.model.domain;

import java.util.LinkedList;

public class Cidade {
    private Integer codcid;
    private String nome;
    private Estado estado;	
    private LinkedList<Endereco> enderecos = new LinkedList<Endereco>();
    private LinkedList<Bairro> bairros = new LinkedList<Bairro>();

    public Cidade(Integer codcid, String nome, Estado estado) {
        this.codcid = codcid;
        this.nome = nome;
        this.estado = estado;
        estado.associaCidade(this);
    }

    public Integer getCodCid() {
        return codcid;
    }
    
	public String getNome() {
		return nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public LinkedList<Endereco> getEnderecos() {
		return enderecos;
	}

	public LinkedList<Bairro> getBairros() {
		return bairros;
	}

	public void associaEndereco(Endereco endereco){
        this.enderecos.add(endereco);
    }
    
    public void associaBairro(Bairro bairro){
        this.bairros.add(bairro);
    }
    
    public void removeEndereco(Endereco endereco){
        for(int i=0; i<this.enderecos.size(); i++){
            if(endereco.getCodend()==this.enderecos.get(i).getCodend()){
                this.enderecos.remove(i);
                break;
            }
        }
    }
    
    public void removeBairro(Bairro bairro){
        for(int i=0; i<this.bairros.size(); i++){
            if(bairro.getCodbairro()==this.bairros.get(i).getCodbairro()){
                this.bairros.remove(i);
                break;
            }
        }
    }
    
    public String toString(){
    String info="";
        info+="\nCodigo: "+this.codcid;
        info+="\nCidade: "+this.nome;
        info+="\nEstado: "+this.estado;
    return info;
    }
}
