package br.com.sysresto.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.sysresto.filter.FuncionarioFilter;
import br.com.sysresto.model.Funcionario;

public class FuncionarioDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	public Funcionario salvarOuAtualizar(Funcionario funcionario) {
		return manager.merge(funcionario);
	}
	
	@SuppressWarnings("unchecked")
	public List<Funcionario> funcionariosFiltrados(FuncionarioFilter funcionarioFilter) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Funcionario.class);
		try {

			if (StringUtils.isNotBlank(funcionarioFilter.getNome())) {
				criteria.add(Restrictions.ilike("nome", funcionarioFilter.getNome(),
						MatchMode.ANYWHERE));
			}

			if (StringUtils.isNotBlank(funcionarioFilter.getCpf())) {
				criteria.add(Restrictions.ilike("cpf", funcionarioFilter.getCpf(),
						MatchMode.START));
			}

			return criteria.addOrder(Order.asc("nome")).list();
		} catch (NullPointerException e) {
			return criteria.addOrder(Order.asc("nome")).list();
		}

	}
	
	public Funcionario getFuncionarioPorLogin(String login) {
		Funcionario funcionario = null;
		try {
			funcionario = this.manager
					.createQuery("from Funcionario where login = :login",
							Funcionario.class).setParameter("login", login)
					.getSingleResult();
		} catch (NoResultException e) {

		}
		return funcionario;
	}
	
	public void remover(Funcionario funcionario) {
		funcionario = getFuncionarioPorId(funcionario.getId());
		manager.remove(funcionario);
		manager.flush();
	}
	
	public Funcionario getFuncionarioPorId(Long id) {
		return manager.find(Funcionario.class, id);
	}
}
