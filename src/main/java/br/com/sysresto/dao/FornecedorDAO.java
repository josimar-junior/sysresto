package br.com.sysresto.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.sysresto.filter.FornecedorFilter;
import br.com.sysresto.model.Fornecedor;

public class FornecedorDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	public Fornecedor salvarOuAtualizar(Fornecedor fornecedor) {
		return manager.merge(fornecedor);
	}
	
	@SuppressWarnings("unchecked")
	public List<Fornecedor> fornecedoresFiltrados(FornecedorFilter fornecedorFilter) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Fornecedor.class);
		try {

			if (StringUtils.isNotBlank(fornecedorFilter.getNomeFantasia())) {
				criteria.add(Restrictions.ilike("nomeFantasia", fornecedorFilter.getNomeFantasia(),
						MatchMode.ANYWHERE));
			}

			if (StringUtils.isNotBlank(fornecedorFilter.getRazaoSocial())) {
				criteria.add(Restrictions.ilike("razaoSocial", fornecedorFilter.getRazaoSocial(),
						MatchMode.START));
			}

			return criteria.addOrder(Order.asc("razaoSocial")).list();
		} catch (NullPointerException e) {
			return criteria.addOrder(Order.asc("razaoSocial")).list();
		}

	}
	
	public void remover(Fornecedor fornecedor) {
		fornecedor = getFornecedorPorId(fornecedor.getId());
		manager.remove(fornecedor);
		manager.flush();
	}
	
	public Fornecedor getFornecedorPorId(Long id) {
		return manager.find(Fornecedor.class, id);
	}
}
