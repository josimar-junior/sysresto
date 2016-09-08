package br.com.sysresto.model;

public enum StatusPedido {

	ABERTO("Aberto"), 
	FECHADO("Fechado"),
	CANCELADO("Cancelado");
	
	private String descricao;
	
	StatusPedido(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}