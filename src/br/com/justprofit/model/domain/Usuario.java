package br.com.justprofit.model.domain;

public class Usuario {
    private Integer codusu;
    private String nome;
    private String cnpj_cpf;
    private String Ie_rg;
    private Endereco endereco;
    private String telefone;
    private String email;
    private String segmento;

    public Usuario(Integer codusu, String nome, String cnpj_cpf, String Ie_rg, Endereco endereco, String telefone, String email, String segmento) {
        this.codusu = codusu;
        this.nome = nome;
        this.cnpj_cpf = cnpj_cpf;
        this.Ie_rg = Ie_rg;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.segmento = segmento;
    }
    
    public String toString(){
    String info="";
        info+="\nCodigo: "+this.codusu;
        info+="\nNome: "+this.nome;
        info+="\nCNPJ_CPF: "+this.cnpj_cpf;
        info+="\nInscrição Estadual_RG: "+this.Ie_rg;
        info+="\nTelefone: "+this.telefone;
        info+="\nEmail: "+this.email;
        info+="\nSegmento: "+this.segmento;
        info+="\nEndereco: "+this.endereco;
    return info;
    }

    public Integer getCodusu() {
        return codusu;
    }

    public void setCodusu(Integer codusu) {
        this.codusu = codusu;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj_cpf() {
        return cnpj_cpf;
    }

    public void setCnpj_cpf(String cnpj_cpf) {
        this.cnpj_cpf = cnpj_cpf;
    }

    public String getIe_rg() {
        return Ie_rg;
    }

    public void setIe_rg(String Ie_rg) {
        this.Ie_rg = Ie_rg;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSegmento() {
        return segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }
    

    
}
