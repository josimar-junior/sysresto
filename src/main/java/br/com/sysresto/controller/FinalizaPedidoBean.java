package br.com.sysresto.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sysresto.bo.FinalizarPedidoBO;
import br.com.sysresto.model.Pedido;
import br.com.sysresto.util.cdi.PedidoEdicao;
import br.com.sysresto.util.jsf.FacesUtil;

@Named
@RequestScoped
public class FinalizaPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	@PedidoEdicao
	private Pedido pedido;

	@Inject
	private FinalizarPedidoBO finalizarPedidoBO;

	public String finalizarPedido() {
		pedido.removerItemVazio();

		try {
			pedido = finalizarPedidoBO.finalizarPedido(pedido);

			FacesUtil.addInfoMessage("Pedido da mesa "
					+ pedido.getMesa().getNumeroMesa()
					+ " finalizado com sucesso!");
		} finally {
			pedido.adicionarItemVazio();
		}
		return "/paginas/garcom/lista-mesas.xhtml";
	}
}
