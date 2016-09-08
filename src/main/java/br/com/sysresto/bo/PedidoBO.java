package br.com.sysresto.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.com.sysresto.dao.PedidoDAO;
import br.com.sysresto.exception.NegocioException;
import br.com.sysresto.filter.PedidoFilter;
import br.com.sysresto.model.Pedido;
import br.com.sysresto.model.StatusMesa;
import br.com.sysresto.model.StatusPedido;
import br.com.sysresto.util.jpa.Transactional;

public class PedidoBO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PedidoDAO pedidoDAO;
	
	@Inject
	private MesaBO mesaBO;
	
	@Transactional
	public Pedido salvar(Pedido pedido) {
		if (pedido.isNovo()) {
			pedido.setDataPedido(new Date());
			pedido.setStatusPedido(StatusPedido.ABERTO);
			pedido.getMesa().setStatusMesa(StatusMesa.OCUPADA);
			mesaBO.salvarOuAtualizar(pedido.getMesa());
		}
		
		pedido.recalcularValorTotal();
		
		if(pedido.getItens().isEmpty()) {
			throw new NegocioException("O pedido deve possuir pelo menos um item!");
		}
		if(pedido.isValorNegativo()) {
			throw new NegocioException("Valor total do pedido não pode ser negativo!");
		}
		
		pedido = this.pedidoDAO.salvarOuAtualizar(pedido);
		return pedido;
	}
	
	public List<Pedido> getPedidosFiltrados(PedidoFilter pedido) {
		return pedidoDAO.getPedidosFiltrados(pedido);
	}
}
