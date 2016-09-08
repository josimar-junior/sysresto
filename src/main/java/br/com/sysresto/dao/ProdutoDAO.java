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

import br.com.sysresto.filter.ProdutoFilter;
import br.com.sysresto.model.Produto;

public class ProdutoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Produto salvarOuAtualizar(Produto produto) {
		return manager.merge(produto);
	}

	@SuppressWarnings("unchecked")
	public List<Produto> produtosFiltrados(ProdutoFilter produtoFilter) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Produto.class);
		try {

			if (StringUtils.isNotBlank(produtoFilter.getNome())) {
				criteria.add(Restrictions.ilike("nome",
						produtoFilter.getNome(), MatchMode.ANYWHERE));
			}

			if (StringUtils.isNotBlank(produtoFilter.getMarca())) {
				criteria.add(Restrictions.ilike("marca",
						produtoFilter.getMarca(), MatchMode.START));
			}

			return criteria.addOrder(Order.asc("nome")).list();
		} catch (NullPointerException e) {
			return criteria.addOrder(Order.asc("nome")).list();
		}

	}

	public List<Produto> getProdutosPorNome(String nome) {
		return manager
				.createQuery("from Produto p where upper(p.nome) like :nome AND p.status = 'ATIVO'",
						Produto.class)
				.setParameter("nome", nome.toUpperCase() + "%").getResultList();
	}

	public void remover(Produto produto) {
		produto = getProdutoPorId(produto.getId());
		manager.remove(produto);
		manager.flush();
	}

	public Produto getProdutoPorId(Long id) {
		return manager.find(Produto.class, id);
	}
}
