package br.com.sysresto.filter;

import java.io.Serializable;

public class FornecedorFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String razaoSocial;
	private String nomeFantasia;

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

}
