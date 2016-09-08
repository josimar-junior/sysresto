package br.com.sysresto.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sysresto.bo.FuncionarioBO;
import br.com.sysresto.model.Funcionario;
import br.com.sysresto.model.Perfil;
import br.com.sysresto.model.Status;
import br.com.sysresto.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroFuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Funcionario funcionario;

	@Inject
	private FuncionarioBO funcionarioBO;

	public CadastroFuncionarioBean() {
		limpar();
	}

	public void salvar() {
		if (funcionario.getId() == null) {
			funcionario = funcionarioBO.salvarOuAtualizar(funcionario);

			FacesUtil.addInfoMessage("Funcionário(a) " + funcionario.getNome()
					+ " cadastrado(a) com sucesso!");
		} else {
			funcionario = funcionarioBO.salvarOuAtualizar(funcionario);

			FacesUtil.addInfoMessage("Funcionário(a) " + funcionario.getNome()
					+ " alterado(a) com sucesso!");
		}

		limpar();
	}

		public Perfil[] getPerfis() {
		return Perfil.values();
	}

	public Status[] getStatus() {
		return Status.values();
	}

	private void limpar() {
		funcionario = new Funcionario();
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}


}
