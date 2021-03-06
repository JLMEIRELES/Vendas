package br.com.joao.mvc.site.dto;

import javax.validation.constraints.NotBlank;

import br.com.joao.mvc.site.model.Pedido;
import br.com.joao.mvc.site.model.StatusPedido;

public class RequisicaoNovoPedido {

	@NotBlank //NotBlank.requisicaoNovoPedido.nomeProduto=não pode estar em branco
	private String nomeProduto;
	
	@NotBlank
	private String urlProduto;
	
	@NotBlank
	private String urlImagem;
	private String descricaoProduto;
	
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
	public String getDescricao() {
		return descricaoProduto;
	}
	public void setDescricao(String descricao) {
		this.descricaoProduto = descricao;
	}
	public Pedido toPedido() {
		Pedido pedido = new Pedido();
		pedido.setDescricao(descricaoProduto);
		pedido.setNome(nomeProduto);
		pedido.setUrlImagem(urlImagem);
		pedido.setUrlProduto(urlProduto);
		pedido.setStatus(StatusPedido.AGUARDANDO);
		return pedido;
	}
	
	
}