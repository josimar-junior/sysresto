package br.com.sysresto.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sysresto.bo.FornecedorBO;
import br.com.sysresto.filter.FornecedorFilter;
import br.com.sysresto.model.Fornecedor;
import br.com.sysresto.model.Status;
import br.com.sysresto.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ConsultaFornecedorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Fornecedor fornecedor;
	private FornecedorFilter fornecedorFilter;
	private List<Fornecedor> fornecedores;
	private Fornecedor fornecedorSelecionado;

	@Inject
	private FornecedorBO fornecedorBO;

	public ConsultaFornecedorBean() {
		limpar();
	}

	@PostConstruct
	public void init() {
		pesquisar();

	}

	public void remover() {
		fornecedorBO.remover(fornecedorSelecionado);
		fornecedores.remove(fornecedorSelecionado);
		FacesUtil.addInfoMessage("Fornecedor "
				+ fornecedorSelecionado.getRazaoSocial()
				+ " excluído com sucesso!");
	}

	public void modificarStatus() {
		if(fornecedor.getStatus() == Status.ATIVO) {
			fornecedor.setStatus(Status.INATIVO);
		} else {
			fornecedor.setStatus(Status.ATIVO);
		}
		
		fornecedorBO.salvarOuAtualizar(fornecedor);
		FacesUtil.addInfoMessage("Fornecedor modificado com sucesso!");
	}
	
	public void pesquisar() {
		fornecedores = fornecedorBO.pesquisar(fornecedorFilter);
	}

	private void limpar() {
		fornecedor = new Fornecedor();
		fornecedorFilter = new FornecedorFilter();
		fornecedorSelecionado = new Fornecedor();
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public FornecedorFilter getFornecedorFilter() {
		return fornecedorFilter;
	}

	public void setFornecedorFilter(FornecedorFilter fornecedorFilter) {
		this.fornecedorFilter = fornecedorFilter;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public Fornecedor getFornecedorSelecionado() {
		return fornecedorSelecionado;
	}

	public void setFornecedorSelecionado(Fornecedor fornecedorSelecionado) {
		this.fornecedorSelecionado = fornecedorSelecionado;
	}

}
