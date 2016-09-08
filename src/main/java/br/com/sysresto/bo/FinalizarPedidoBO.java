package br.com.sysresto.bo;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.sysresto.dao.PedidoDAO;
import br.com.sysresto.exception.NegocioException;
import br.com.sysresto.model.Pedido;
import br.com.sysresto.model.StatusMesa;
import br.com.sysresto.model.StatusPedido;
import br.com.sysresto.util.jpa.Transactional;

public class FinalizarPedidoBO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PedidoBO pedidoBO;

	@Inject
	private PedidoDAO pedidoDAO;
	
	@Inject
	private MesaBO mesaBO;
	
	@Inject
	private EstoqueBO estoqueBO;
	
	@Transactional
	public Pedido finalizarPedido(Pedido pedido) {
		pedido = pedidoBO.salvar(pedido);

		if (pedido.isNaoFinalizavel()) {
			throw new NegocioException(
					"Pedido não pode ser finalizado com status "
							+ pedido.getStatusPedido().getDescricao() + "!");
		}
		
		estoqueBO.baixarItensEstoque(pedido);
		
		pedido.setStatusPedido(StatusPedido.FECHADO);
		pedido.getMesa().setStatusMesa(StatusMesa.LIVRE);
		mesaBO.salvarOuAtualizar(pedido.getMesa());
		
		pedido = pedidoDAO.salvarOuAtualizar(pedido);
		
		return pedido;
	}

}
