package br.com.sysresto.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sysresto.bo.FornecedorBO;
import br.com.sysresto.bo.ProdutoBO;
import br.com.sysresto.bo.SecaoBO;
import br.com.sysresto.model.Fornecedor;
import br.com.sysresto.model.Produto;
import br.com.sysresto.model.Secao;
import br.com.sysresto.model.Status;
import br.com.sysresto.model.TipoUnidade;
import br.com.sysresto.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Produto produto;

	@Inject
	private ProdutoBO produtoBO;
	
	@Inject
	private SecaoBO secaoBO;
	
	@Inject
	private FornecedorBO fornecedorBO;

	public CadastroProdutoBean() {
		limpar();
	}

	public void salvar() {
		if (produto.getId() == null) {
			produto = produtoBO.salvarOuAtualizar(produto);

			FacesUtil.addInfoMessage("Produto " + produto.getNome()
					+ " cadastrado com sucesso!");
		} else {
			produto = produtoBO.salvarOuAtualizar(produto);

			FacesUtil.addInfoMessage("Produto " + produto.getNome()
					+ " alterado com sucesso!");
		}

		limpar();
	}

	public Status[] getStatus() {
		return Status.values();
	}
	
	public TipoUnidade[] getUnidades() {
		return TipoUnidade.values();
	}
	
	public List<Secao> getListaSecoes() {
		return secaoBO.pesquisar(null);
	}
	
	public List<Fornecedor> getListaFornecedores() {
		return fornecedorBO.pesquisar(null);
	}

	private void limpar() {
		produto = new Produto();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}


}
