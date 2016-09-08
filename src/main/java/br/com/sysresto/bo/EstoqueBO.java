package br.com.sysresto.bo;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.sysresto.dao.PedidoDAO;
import br.com.sysresto.model.ItemPedido;
import br.com.sysresto.model.Pedido;
import br.com.sysresto.util.jpa.Transactional;

public class EstoqueBO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PedidoDAO pedidoDAO;
	
	@Transactional
	public void baixarItensEstoque(Pedido pedido) {
		pedido = pedidoDAO.getPedidoPorId(pedido.getId());
		
		for(ItemPedido item : pedido.getItens()) {
			item.getProduto().baixarEstoque(item.getQuantidade());
		}
	}
}
