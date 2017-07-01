package br.com.justprofit.model.domain;


import java.util.Date;
import java.util.LinkedList;


public class Pedido {
    private Integer nuped;
    private Double vlrtotal;
    private Date dtneg;
    private Cliente cliente;
    private Vendedor vendedor;
    private LinkedList<Produto> produtos = new LinkedList<Produto>();

    public Pedido(Integer nuped, Double vlrtotal, Date dtneg, Cliente cliente, Vendedor vendedor) {
        this.nuped = nuped;
        this.vlrtotal = vlrtotal;
        this.dtneg = dtneg;
        this.cliente = cliente;
        cliente.associaPedido(this);
        this.vendedor = vendedor;
        vendedor.associaPedido(this);
    }

    public Date getDtneg() {
		return dtneg;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public LinkedList<Produto> getProdutos() {
		return produtos;
	}

	public Integer getNuped() {
        return nuped;
    }
    
    public void associaProduto(Produto produto){
        this.produtos.add(produto);
    }
    
    public void removeProduto(Produto produto){
        for(int i=0; i<this.produtos.size(); i++){
            if(produto.getCodProd()==this.produtos.get(i).getCodProd()){
                this.produtos.remove(i);
                break;
            }
        }
    }
    
    public String toString(){
        String info = "";
            info+="\nNúmero Pedido: "+this.nuped;
            info+="\nData Pedido: "+this.dtneg;
            info+="\nValor Total: "+this.vlrtotal;
            
        return info;
    }
}
