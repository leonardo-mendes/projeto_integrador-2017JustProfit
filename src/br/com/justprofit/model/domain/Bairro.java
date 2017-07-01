package br.com.justprofit.model.domain;

import java.util.LinkedList;

public class Bairro {
    private Integer codbairro;
    private String nome;
    private Cidade cidade;
    private LinkedList<Endereco> enderecos = new LinkedList<Endereco>();
    
    public Bairro(Integer codbairro, String nome, Cidade cidade) {
        this.codbairro = codbairro;
        this.nome = nome;
        this.cidade=cidade;
        cidade.associaBairro(this);
    }

    
    public void associaEndereco(Endereco endereco){
        this.enderecos.add(endereco);
    }
    
	public LinkedList<Endereco> getEnderecos() {
		return enderecos;
	}


	public Integer getCodbairro() {
		return codbairro;
	}

	public String getNome() {
		return nome;
	}

	public Cidade getCidade() {
		return cidade;
	}
    
	public String toString(){
	    String info="";
	        info+="\nCodigo de Bairro: "+this.codbairro;
	        info+="\nNome: "+this.nome;
	        info+="CodCid: "+this.cidade.getCodCid();
	    return info;
	    }
}
