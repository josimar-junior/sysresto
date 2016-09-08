package br.com.sysresto.bo;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.sysresto.dao.ProdutoDAO;
import br.com.sysresto.filter.ProdutoFilter;
import br.com.sysresto.model.Produto;
import br.com.sysresto.util.jpa.Transactional;

public class ProdutoBO implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private ProdutoDAO produtoDAO;
	
	@Transactional
	public Produto salvarOuAtualizar(Produto produto) {
		return produtoDAO.salvarOuAtualizar(produto);
	}

	public List<Produto> pesquisar(ProdutoFilter produto) {
		return produtoDAO.produtosFiltrados(produto);
	}
	
	@Transactional
	public void remover(Produto produto) {
		produtoDAO.remover(produto);
	}

	public List<Produto> getProdutosPorNome(String nome) {
		return produtoDAO.getProdutosPorNome(nome);
	}
}
