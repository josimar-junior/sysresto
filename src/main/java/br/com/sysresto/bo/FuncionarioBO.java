package br.com.sysresto.bo;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.sysresto.dao.FuncionarioDAO;
import br.com.sysresto.filter.FuncionarioFilter;
import br.com.sysresto.model.Funcionario;
import br.com.sysresto.util.jpa.Transactional;

public class FuncionarioBO implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private FuncionarioDAO funcionarioDAO;
	
	@Transactional
	public Funcionario salvarOuAtualizar(Funcionario funcionario) {
		return funcionarioDAO.salvarOuAtualizar(funcionario);
	}

	public List<Funcionario> pesquisar(FuncionarioFilter funcionario) {
		return funcionarioDAO.funcionariosFiltrados(funcionario);
	}
	
	@Transactional
	public void remover(Funcionario funcionario) {
		funcionarioDAO.remover(funcionario);
	}
	
	public Funcionario getFuncionarioPorLogin(String id) {
		return funcionarioDAO.getFuncionarioPorLogin(id);
	}
}
