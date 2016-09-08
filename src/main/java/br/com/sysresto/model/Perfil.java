package br.com.sysresto.model;

public enum Perfil {
	GERENTE("Gerente"), GARCOM("Garçom"), CAIXA("Caixa");
	
	private String descricao;
	
	Perfil(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
