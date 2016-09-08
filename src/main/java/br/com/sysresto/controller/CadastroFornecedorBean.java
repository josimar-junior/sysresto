package br.com.sysresto.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sysresto.bo.FornecedorBO;
import br.com.sysresto.model.Endereco;
import br.com.sysresto.model.Fornecedor;
import br.com.sysresto.model.SiglaEstadoBrasileiro;
import br.com.sysresto.model.Status;
import br.com.sysresto.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroFornecedorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Fornecedor fornecedor;

	@Inject
	private FornecedorBO fornecedorBO;

	public CadastroFornecedorBean() {
		limpar();
	}

	public void salvar() {
		if (fornecedor.getId() == null) {
			fornecedor = fornecedorBO.salvarOuAtualizar(fornecedor);

			FacesUtil.addInfoMessage("Fornecedor " + fornecedor.getRazaoSocial()
					+ " cadastrado com sucesso!");
		} else {
			fornecedor = fornecedorBO.salvarOuAtualizar(fornecedor);

			FacesUtil.addInfoMessage("Fornecedor " + fornecedor.getRazaoSocial()
					+ " alterado com sucesso!");
		}

		limpar();
	}

	public Status[] getStatus() {
		return Status.values();
	}
	
	public SiglaEstadoBrasileiro[] getSiglaEstadoBrasileiros() {
		return SiglaEstadoBrasileiro.values();
	}

	private void limpar() {
		fornecedor = new Fornecedor();
		fornecedor.setEndereco(new Endereco());
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}


}
