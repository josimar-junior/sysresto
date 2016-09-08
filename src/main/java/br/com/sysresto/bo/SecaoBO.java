package br.com.sysresto.bo;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.sysresto.dao.SecaoDAO;
import br.com.sysresto.filter.SecaoFilter;
import br.com.sysresto.model.Secao;
import br.com.sysresto.util.jpa.Transactional;

public class SecaoBO implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private SecaoDAO secaoDAO;
	
	@Transactional
	public Secao salvarOuAtualizar(Secao secao) {
		return secaoDAO.salvarOuAtualizar(secao);
	}

	public List<Secao> pesquisar(SecaoFilter secao) {
		return secaoDAO.secoesFiltradas(secao);
	}
	
	@Transactional
	public void remover(Secao secao) {
		secaoDAO.remover(secao);
	}
}
