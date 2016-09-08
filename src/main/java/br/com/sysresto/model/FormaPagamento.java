package br.com.sysresto.model;

public enum FormaPagamento {
	
	DINHEIRO("Dinheiro"),
	CARTAO_CREDITO("Cartão de Crédito"), 
	CARTAO_DEBITO("Cartão de débito"),
	CHEQUE("Cheque");
	
	private String descricao;

	private FormaPagamento(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
