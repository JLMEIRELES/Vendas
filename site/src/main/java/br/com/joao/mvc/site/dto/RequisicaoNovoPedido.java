package br.com.joao.mvc.site.dto;

import javax.validation.constraints.NotBlank;

import br.com.joao.mvc.site.model.Pedido;
import br.com.joao.mvc.site.model.StatusPedido;

public class RequisicaoNovoPedido {
	
	@NotBlank
	String nomeProduto;
	@NotBlank
	String urlProduto;
	@NotBlank
	String urlImagem;
	@NotBlank
	String descricaoProduto;
	
	
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public String getUrlProduto() {
		return urlProduto;
	}
	public void setUrlProduto(String urlProduto) {
		this.urlProduto = urlProduto;
	}
	public String getUrlImagem() {
		return urlImagem;
	}
	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}
	public String getDescricaoProduto() {
		return descricaoProduto;
	}
	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}
	
	public Pedido toPedido() {
		
		Pedido pedido = new Pedido();
		
		pedido.setDescricao(descricaoProduto);
		
		pedido.setUrlImagem(urlImagem);
		
		pedido.setNome(nomeProduto);
		
		pedido.setUrlProduto(urlProduto);
		
		pedido.setStatus(StatusPedido.AGUARDANDO);
		
		return pedido;
	}
	
	
	
}
