package br.com.sysresto.model;

public enum TipoUnidade {

	UNIDADE("Unidade"), CAIXA("Caixa"), FARDO("Fardo");
	
	private String descricao;
	
	TipoUnidade(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
}
