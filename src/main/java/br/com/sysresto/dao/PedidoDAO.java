package br.com.sysresto.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;


import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Order;

import br.com.sysresto.filter.PedidoFilter;
import br.com.sysresto.model.Mesa;
import br.com.sysresto.model.Pedido;

public class PedidoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Pedido salvarOuAtualizar(Pedido pedido) {
		return this.manager.merge(pedido);
	}

	public void cancelarPedido(Pedido pedido) {
		pedido = getPedidoPorId(pedido.getId());
		manager.remove(pedido);
		manager.flush();
	}

	public Pedido getPedidoPorId(Long id) {
		return this.manager.find(Pedido.class, id);
	}

	public Pedido getPedidoPorMesa(Mesa mesa) {
		return manager
				.createQuery(
						"FROM Pedido p WHERE p.statusPedido = 'ABERTO' AND p.mesa.id = :codigo",
						Pedido.class).setParameter("codigo", mesa.getId())
				.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Pedido> getPedidosFiltrados(PedidoFilter filtro) {
		Session session = this.manager.unwrap(Session.class);

		Criteria criteria = session.createCriteria(Pedido.class).createAlias(
				"garcom", "g");

		if (filtro.getDataPedidoDe() != null) {
			criteria.add(Restrictions.ge("dataPedido",
					filtro.getDataPedidoDe()));
		}

		if (filtro.getDataPedidoAte() != null) {
			criteria.add(Restrictions.le("dataPedido",
					filtro.getDataPedidoAte()));
		}

		if (StringUtils.isNotBlank(filtro.getNomeGarcom())) {
			criteria.add(Restrictions.ilike("g.nome", filtro.getNomeGarcom(),
					MatchMode.ANYWHERE));
		}

		if (filtro.getStatusPedido() != null && filtro.getStatusPedido().length > 0) {
			criteria.add(Restrictions.in("statusPedido", filtro.getStatusPedido()));
		}

		return criteria.addOrder(Order.asc("id")).list();
	}

}
