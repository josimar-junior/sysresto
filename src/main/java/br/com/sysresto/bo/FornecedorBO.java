package br.com.sysresto.bo;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.sysresto.dao.FornecedorDAO;
import br.com.sysresto.filter.FornecedorFilter;
import br.com.sysresto.model.Fornecedor;
import br.com.sysresto.util.jpa.Transactional;

public class FornecedorBO implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private FornecedorDAO fornecedorDAO;
	
	@Transactional
	public Fornecedor salvarOuAtualizar(Fornecedor fornecedor) {
		return fornecedorDAO.salvarOuAtualizar(fornecedor);
	}

	public List<Fornecedor> pesquisar(FornecedorFilter fornecedor) {
		return fornecedorDAO.fornecedoresFiltrados(fornecedor);
	}
	
	@Transactional
	public void remover(Fornecedor fornecedor) {
		fornecedorDAO.remover(fornecedor);
	}
}
