package br.com.justprofit.model.domain;

import java.util.LinkedList;

public class Cliente extends Usuario{

	private LinkedList<Pedido> pedidos = new LinkedList<Pedido>();
	
	public Cliente(Integer codusu, String nome, String cnpj_cpf, String Ie_rg, Endereco endereco, String telefone, String email, String segmento) {
            super(codusu, nome, cnpj_cpf, Ie_rg, endereco, telefone, email, "C");
            
        }
	
	public void associaPedido(Pedido pedido){
		this.pedidos.add(pedido);
	}
	
	
	public LinkedList<Pedido> getPedidos() {
		return pedidos;
	}

	public String toString(){
		String info = "CODUSU: " +this.getCodusu();
		info+= ", Nome: " + this.getNome();
		info+= ", CNPJ/CPF: " + this.getCnpj_cpf();
		info+= ", Ie_Rg: " + this.getIe_rg();
		info+= ", Telefone: " + this.getTelefone();
		info+= ", Email: " + this.getEmail();
		info+= ", Segmento: " + this.getSegmento();
		info+= ", Cod_Endereco: " + this.getEndereco().getCodend();
		info+= " /Cidade: " + this.getEndereco().getCidade();
		info+= " /Bairro: " + this.getEndereco().getBairro();
		info+= " /Log: " + this.getEndereco().getLogradouro();
		info+= " /N° " + this.getEndereco().getNumero();
		info+= " /CEP: " + this.getEndereco().getCep();
		/**
		info += "\nPedidos: ";
		for(Pedido pedido: pedidos){
			info+="\n"+pedido.toString();
		}
		**/
		return info;
	}
	
}
