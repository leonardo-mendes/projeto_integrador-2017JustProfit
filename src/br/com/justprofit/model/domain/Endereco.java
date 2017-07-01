package br.com.justprofit.model.domain;

public class Endereco {
    private Integer codend;
    private String logradouro;
    private Integer numero;
    private String cep;
    private Cidade cidade;
    private Bairro bairro;

    public Endereco(Integer codend, String logradouro, Integer numero, String cep2, Cidade cidade, Bairro bairro) {
        this.codend = codend;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cep = cep2;
        this.cidade=cidade;
        this.bairro = bairro;
        bairro.associaEndereco(this);
        cidade.associaEndereco(this);
    }

 
    
    public void setCodend(Integer codend) {
    	if(codend != null){
    		this.codend = codend;
    	}
	}



	public Integer getCodend() {
		return codend;
	}



	public String getCep() {
		return cep;
	}



	public String getLogradouro() {
		return logradouro;
	}



	public Integer getNumero() {
		return numero;
	}



	public Cidade getCidade() {
		return cidade;
	}


	public Bairro getBairro() {
		return bairro;
	}
	
	
	public String toString(){
    String info="";
        info+="\nCodigo: "+this.codend;
        info+="\nLogradouro: "+this.logradouro+" Número: "+this.numero+"";
        info+="\nCidade: "+this.cidade;
    return info;
    }
}
