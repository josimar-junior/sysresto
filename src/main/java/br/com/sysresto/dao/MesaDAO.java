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

import br.com.sysresto.filter.MesaFilter;
import br.com.sysresto.model.Mesa;

public class MesaDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Mesa salvarOuAtualizar(Mesa mesa) {
		return manager.merge(mesa);
	}

	@SuppressWarnings("unchecked")
	public List<Mesa> mesasFiltradas(MesaFilter mesaFilter) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Mesa.class);
		try {

			if (StringUtils.isNotBlank(mesaFilter.getNumero())) {
				criteria.add(Restrictions.ilike("numeroMesa",
						mesaFilter.getNumero(), MatchMode.ANYWHERE));
			}

			return criteria.addOrder(Order.asc("numeroMesa")).list();
		} catch (NullPointerException e) {
			return criteria.addOrder(Order.asc("numeroMesa")).list();
		}

	}

	public void remover(Mesa mesa) {
		mesa = getMesaPorId(mesa.getId());
		manager.remove(mesa);
		manager.flush();
	}

	public Mesa getMesaPorId(Long id) {
		return manager.find(Mesa.class, id);
	}
}
