package br.com.sysresto.model;

public enum StatusMesa {

	LIVRE("Livre"), OCUPADA("Ocupada");

	private String descricao;

	StatusMesa(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}