package br.com.sysresto.filter;

import java.io.Serializable;

public class MesaFilter implements Serializable{

	private static final long serialVersionUID = 1L;

	private String numero;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
}
