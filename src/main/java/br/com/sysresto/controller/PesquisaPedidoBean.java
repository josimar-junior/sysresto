package br.com.sysresto.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.hibernate.Session;

import br.com.sysresto.bo.PedidoBO;
import br.com.sysresto.filter.PedidoFilter;
import br.com.sysresto.model.Pedido;
import br.com.sysresto.model.StatusPedido;
import br.com.sysresto.relatorio.ExecutorRelatorio;

@Named
@ViewScoped
public class PesquisaPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PedidoBO pedidoBO;
	
	@Inject
	private EntityManager manager;
	
	private PedidoFilter filtro;
	private List<Pedido> pedidosFiltrados;
	
	public PesquisaPedidoBean() {
		filtro = new PedidoFilter();
		pedidosFiltrados = new ArrayList<>();
	}

	public void pesquisar() {
		pedidosFiltrados = pedidoBO.getPedidosFiltrados(filtro);
	}
	
	public StatusPedido[] getStatusPedido() {
		return StatusPedido.values();
	}
	
	public void emitirRelatorio() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();

		Session session = this.manager.unwrap(Session.class);
		
		
		session.doWork(new ExecutorRelatorio(
				"/relatorios/relatorio_pedidos.jasper", this.pedidosFiltrados, null,
				response));
//		session.close();
		context.responseComplete();
	}
	
	public List<Pedido> getPedidosFiltrados() {
		return pedidosFiltrados;
	}

	public PedidoFilter getFiltro() {
		return filtro;
	}
}
