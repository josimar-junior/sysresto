package br.com.sysresto.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sysresto.bo.SecaoBO;
import br.com.sysresto.model.Secao;
import br.com.sysresto.model.Status;
import br.com.sysresto.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroSecaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Secao secao;

	@Inject
	private SecaoBO secaoBO;

	public CadastroSecaoBean() {
		limpar();
	}

	public void salvar() {
		if (secao.getId() == null) {
			secao = secaoBO.salvarOuAtualizar(secao);

			FacesUtil.addInfoMessage("Seção " + secao.getDescricao()
					+ " cadastrada com sucesso!");
		} else {
			secao = secaoBO.salvarOuAtualizar(secao);

			FacesUtil.addInfoMessage("Seção " + secao.getDescricao()
					+ " alterada com sucesso!");
		}

		limpar();
	}


	public Status[] getStatus() {
		return Status.values();
	}

	private void limpar() {
		secao = new Secao();
	}

	public Secao getSecao() {
		return secao;
	}

	public void setSecao(Secao secao) {
		this.secao = secao;
	}

}
