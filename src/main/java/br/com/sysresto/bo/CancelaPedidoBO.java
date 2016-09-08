package br.com.sysresto.bo;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.sysresto.dao.PedidoDAO;
import br.com.sysresto.exception.NegocioException;
import br.com.sysresto.model.Pedido;
import br.com.sysresto.model.StatusMesa;
import br.com.sysresto.model.StatusPedido;
import br.com.sysresto.util.jpa.Transactional;

public class CancelaPedidoBO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PedidoDAO pedidoDAO;
	
	@Inject
	private MesaBO mesaBO;
	
	@Transactional
	public void cancelar(Pedido pedido) {

		pedido = pedidoDAO.getPedidoPorId(pedido.getId());

		if (pedido.getId() == null) {
			throw new NegocioException("Pedido da mesa "
					+ pedido.getMesa().getNumeroMesa()
					+ " ainda não foi realizado!");
		}
		pedido.getMesa().setStatusMesa(StatusMesa.LIVRE);
		mesaBO.salvarOuAtualizar(pedido.getMesa());
		pedido.setStatusPedido(StatusPedido.CANCELADO);
		pedidoDAO.salvarOuAtualizar(pedido);
	}

}
