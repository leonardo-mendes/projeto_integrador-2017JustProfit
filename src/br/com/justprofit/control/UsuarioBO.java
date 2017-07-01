package br.com.justprofit.control;

import br.com.justprofit.model.domain.Cliente;
import br.com.justprofit.model.domain.Fornecedor;
import br.com.justprofit.model.domain.Usuario;
import br.com.justprofit.model.domain.Vendedor;

public class UsuarioBO {
	/**
	 * Valida os dados dos objetos usuairo
	 * Cliente/Fornecedor/Vendedor
	 * @param usuario
	 */
	public void validaDados(Usuario usuario) {
		
		//Valida o campo Codusu
		if(usuario.getCodusu() == null){
			System.out.println("Codusu é null! Valor informado:" + usuario.getCodusu());
		}
		
		//Valida o campo Nome
		if(usuario.getNome() == null){
			System.out.println("Nome é null! Valor informado:" + usuario.getNome());
		}
		
		//Valida o campo CNPJ_CPF
		if(usuario.getCnpj_cpf() == null){
			System.out.println("CNPJ_CPF é null! Valor informado:" + usuario.getCnpj_cpf());
		}else if(usuario.getCnpj_cpf().length() > 14 ){
			System.out.println("CNPJ_CPF é um valor maior que o permitido (14 caracteres)! Valor informado:" + usuario.getCnpj_cpf());
		}
		
		//Valida o campo getIe_rg
		if(usuario.getIe_rg() == null){
			System.out.println("IE_RG é null! Valor informado:" + usuario.getIe_rg());
		}else if(usuario.getIe_rg().length() > 12 ){
			System.out.println("Ie_rg é um valor maior que o permitido (12 caracteres)! Valor informado:" + usuario.getIe_rg());
		}
		
		//Valida o Endereço
		if(usuario.getEndereco() == null){
			System.out.println("Endereço é null! Valor informado:" + usuario.getEndereco());
		}else if(usuario.getEndereco().getCodend() == null){
			System.out.println("Codigo do Endereço é null! Valor informado:" + usuario.getEndereco().getCodend());
		}else if(usuario.getEndereco().getCidade() == null){
			System.out.println("A Cidade é null! Valor informado:" + usuario.getEndereco().getCidade());
		}else if(usuario.getEndereco().getCidade().getCodCid() == null){
			System.out.println("O Codigo da Cidade é null! Valor informado:" + usuario.getEndereco().getCidade().getCodCid());
		}else if(usuario.getEndereco().getBairro() == null){
			System.out.println("O Bairro é null! Valor informado:" + usuario.getEndereco().getBairro());
		}else if(usuario.getEndereco().getBairro().getCodbairro() == null){
			System.out.println("O Codigo do Bairro é null! Valor informado:" + usuario.getEndereco().getBairro().getCodbairro());
		}else if(usuario.getEndereco().getCidade().getEstado() == null){
			System.out.println("O Estado é null! Valor informado:" + usuario.getEndereco().getCidade().getEstado());
		}else if(usuario.getEndereco().getCidade().getEstado().getCoduf() == null){
			System.out.println("O Codigo do Estado é null! Valor informado:" + usuario.getEndereco().getCidade().getEstado().getCoduf());
		}else if(usuario.getEndereco().getCidade().getEstado().getRegiao() == null){
			System.out.println("A Região é null! Valor informado:" + usuario.getEndereco().getCidade().getEstado().getRegiao());
		}else if(usuario.getEndereco().getCidade().getEstado().getRegiao().getCodreg() == null){
			System.out.println("O Codigo da Região é null! Valor informado:" + usuario.getEndereco().getCidade().getEstado().getRegiao().getCodreg());
		}else if(usuario.getEndereco().getCep() == null){
			System.out.println("CEP é null! Valor informado:" + usuario.getEndereco().getCep());
		}
	    
		//Valida o Telefone
		if(usuario.getTelefone() == null){
			System.out.println("Telefone é null! Valor informado:" + usuario.getTelefone());
		}else if(usuario.getTelefone().length() > 15 ){
			System.out.println("Telefone é um valor maior que o permitido (15 caracteres)! Valor informado:" + usuario.getTelefone());
		}
		
		//Valida o email
		if(usuario.getEmail() == null){
			System.out.println("Email é null! Valor informado:" + usuario.getEmail());
		}else if(usuario.getEmail().length() > 30 ){
			System.out.println("Email é um valor maior que o permitido (30 caracteres)! Valor informado:" + usuario.getEmail());
		}
		
		//Valida o Segmento
		if(usuario.getSegmento() == null && usuario.getSegmento().length() > 1){
			System.out.println("Segmento é null! Valor informado:" + usuario.getSegmento());
		}else if(usuario.getEmail().length() > 30 ){
			System.out.println("Segmento é um valor maior que o permitido (1 caracterer)! Valor informado:" + usuario.getSegmento());
		}
	    
		//caso Vendedor valida dados especifico do mesmo
		if(usuario instanceof Vendedor){
			Vendedor vendedor = (Vendedor) usuario;
			
			if(vendedor.getCodVend() == null){
				System.out.println("Cod Vendedor é null! Valor informado:" + vendedor.getCodVend());
			}else if(vendedor.getSaldo() == null){
				System.out.println("Saldo do vendedor é null! Valor informado:" + vendedor.getSaldo());
			}
		
		}
		
		//Caso Fornecedor valida dados especifico do mesmo
		if(usuario instanceof Fornecedor){
			Fornecedor fornecedor = (Fornecedor) usuario;
			
		}
		
		//Caso Cliente valida dados especifico do mesmo
		if(usuario instanceof Cliente){
			Cliente cliente = (Cliente) usuario;
			
		}
		
		
	}
	
}
