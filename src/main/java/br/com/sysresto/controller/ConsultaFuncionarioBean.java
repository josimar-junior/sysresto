package br.com.sysresto.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sysresto.bo.FuncionarioBO;
import br.com.sysresto.filter.FuncionarioFilter;
import br.com.sysresto.model.Funcionario;
import br.com.sysresto.model.Status;
import br.com.sysresto.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ConsultaFuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Funcionario funcionario;
	private FuncionarioFilter funcionarioFilter;
	private List<Funcionario> funcionarios;
	private Funcionario funcionarioSelecionado;

	@Inject
	private FuncionarioBO funcionarioBO;

	public ConsultaFuncionarioBean() {
		limpar();
	}

	@PostConstruct
	public void init() {
		pesquisar();

	}

	public void remover() {
		funcionarioBO.remover(funcionarioSelecionado);
		funcionarios.remove(funcionarioSelecionado);
		FacesUtil.addInfoMessage("Funcionário(a) "
				+ funcionarioSelecionado.getNome()
				+ " excluído(a) com sucesso!");
	}

	public void modificarStatus() {
		if(funcionario.getStatus() == Status.ATIVO) {
			funcionario.setStatus(Status.INATIVO);
		} else {
			funcionario.setStatus(Status.ATIVO);
		}
		
		funcionarioBO.salvarOuAtualizar(funcionario);
		FacesUtil.addInfoMessage("Funcionário modificado com sucesso!");
	}
	
	public void pesquisar() {
		funcionarios = funcionarioBO.pesquisar(funcionarioFilter);
	}

	private void limpar() {
		funcionario = new Funcionario();
		funcionarioFilter = new FuncionarioFilter();
		funcionarioSelecionado = new Funcionario();
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public FuncionarioFilter getFuncionarioFilter() {
		return funcionarioFilter;
	}

	public void setFuncionarioFilter(FuncionarioFilter funcionarioFilter) {
		this.funcionarioFilter = funcionarioFilter;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Funcionario getFuncionarioSelecionado() {
		return funcionarioSelecionado;
	}

	public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
		this.funcionarioSelecionado = funcionarioSelecionado;
	}

}
