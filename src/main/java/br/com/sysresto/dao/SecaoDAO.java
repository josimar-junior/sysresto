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

import br.com.sysresto.filter.SecaoFilter;
import br.com.sysresto.model.Secao;

public class SecaoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Secao salvarOuAtualizar(Secao secao) {
		return manager.merge(secao);
	}

	@SuppressWarnings("unchecked")
	public List<Secao> secoesFiltradas(SecaoFilter secaoFilter) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Secao.class);
		try {

			if (StringUtils.isNotBlank(secaoFilter.getDescricao())) {
				criteria.add(Restrictions.ilike("descricao", secaoFilter.getDescricao(),
						MatchMode.ANYWHERE));
			}

			return criteria.addOrder(Order.asc("descricao")).list();
		} catch (NullPointerException e) {
			return criteria.addOrder(Order.asc("descricao")).list();
		}

	}

	public void remover(Secao secao) {
		secao = getSecaoPorId(secao.getId());
		manager.remove(secao);
		manager.flush();
	}

	public Secao getSecaoPorId(Long id) {
		return manager.find(Secao.class, id);
	}
}
