package br.com.sysresto.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sysresto.bo.CancelaPedidoBO;
import br.com.sysresto.model.Pedido;
import br.com.sysresto.util.cdi.PedidoEdicao;
import br.com.sysresto.util.jsf.FacesUtil;

@Named
@RequestScoped
public class CancelaPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CancelaPedidoBO cancelaPedidoBO;
	

	@Inject
	@PedidoEdicao
	private Pedido pedido;

	public String cancelarPedido() {
		cancelaPedidoBO.cancelar(pedido);
		FacesUtil.addInfoMessage("Pedido da mesa "
				+ pedido.getMesa().getNumeroMesa() + " cancelado com sucesso!");
		return "/paginas/garcom/lista-mesas.xhtml";
	}
	
	
}
