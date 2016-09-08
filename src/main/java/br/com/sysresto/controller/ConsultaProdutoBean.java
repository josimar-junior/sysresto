package br.com.sysresto.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sysresto.bo.ProdutoBO;
import br.com.sysresto.filter.ProdutoFilter;
import br.com.sysresto.model.Produto;
import br.com.sysresto.model.Status;
import br.com.sysresto.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ConsultaProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Produto produto;
	private ProdutoFilter produtoFilter;
	private List<Produto> produtos;
	private Produto produtoSelecionado;

	@Inject
	private ProdutoBO produtoBO;

	public ConsultaProdutoBean() {
		limpar();
	}

	@PostConstruct
	public void init() {
		pesquisar();

	}

	public void remover() {
		produtoBO.remover(produtoSelecionado);
		produtos.remove(produtoSelecionado);
		FacesUtil.addInfoMessage("Produto "
				+ produtoSelecionado.getNome()
				+ " excluído com sucesso!");
	}

	public void modificarStatus() {
		if(produto.getStatus() == Status.ATIVO) {
			produto.setStatus(Status.INATIVO);
		} else {
			produto.setStatus(Status.ATIVO);
		}
		
		produtoBO.salvarOuAtualizar(produto);
		FacesUtil.addInfoMessage("Produto modificado com sucesso!");
	}
	
	public void pesquisar() {
		produtos = produtoBO.pesquisar(produtoFilter);
	}

	private void limpar() {
		produto = new Produto();
		produtoFilter = new ProdutoFilter();
		produtoSelecionado = new Produto();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ProdutoFilter getProdutoFilter() {
		return produtoFilter;
	}

	public void setProdutoFilter(ProdutoFilter produtoFilter) {
		this.produtoFilter = produtoFilter;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

}
