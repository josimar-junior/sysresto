package br.com.sysresto.bo;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.sysresto.dao.MesaDAO;
import br.com.sysresto.filter.MesaFilter;
import br.com.sysresto.model.Mesa;
import br.com.sysresto.util.jpa.Transactional;

public class MesaBO implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private MesaDAO mesaDAO;
	
	@Transactional
	public Mesa salvarOuAtualizar(Mesa mesa) {
		return mesaDAO.salvarOuAtualizar(mesa);
	}

	public List<Mesa> pesquisar(MesaFilter mesa) {
		return mesaDAO.mesasFiltradas(mesa);
	}
	
	@Transactional
	public void remover(Mesa mesa) {
		mesaDAO.remover(mesa);
	}
}
