package br.com.sysresto.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sysresto.bo.MesaBO;
import br.com.sysresto.dao.PedidoDAO;
import br.com.sysresto.filter.MesaFilter;
import br.com.sysresto.model.Mesa;
import br.com.sysresto.model.Pedido;
import br.com.sysresto.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ConsultaMesaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Mesa mesa;
	private MesaFilter mesaFilter;
	private List<Mesa> mesas;
	private Mesa mesaSelecionada;
	private Pedido pedidoSelecionado;
	
	@Inject
	private PedidoDAO pedidoDAO;
	
	@Inject
	private MesaBO mesaBO;

	public ConsultaMesaBean() {
		limpar();
	}

	@PostConstruct
	public void init() {
		pesquisar();

	}

	public void remover() {
		mesaBO.remover(mesaSelecionada);
		mesas.remove(mesaSelecionada);
		FacesUtil.addInfoMessage("Mesa de número "
				+ mesaSelecionada.getNumeroMesa()
				+ " excluída com sucesso!");
	}

	
	public String getPaginaCadastroPedido() {
		return "/paginas/garcom/cadastro-pedido.xhtml";
	}
	
	public void pesquisar() {
		mesas = mesaBO.pesquisar(mesaFilter);
	}
	
	public void pesquisarPedidoMesa() {
		pedidoSelecionado = pedidoDAO.getPedidoPorMesa(mesaSelecionada);
	}

	private void limpar() {
		mesa = new Mesa();
		mesaFilter = new MesaFilter();
		mesaSelecionada = new Mesa();
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public MesaFilter getMesaFilter() {
		return mesaFilter;
	}

	public void setMesaFilter(MesaFilter mesaFilter) {
		this.mesaFilter = mesaFilter;
	}

	public List<Mesa> getMesas() {
		return mesas;
	}

	public void setMesas(List<Mesa> mesas) {
		this.mesas = mesas;
	}

	public Mesa getMesaSelecionada() {
		return mesaSelecionada;
	}

	public void setMesaSelecionada(Mesa mesaSelecionada) {
		this.mesaSelecionada = mesaSelecionada;
	}

	public Pedido getPedidoSelecionado() {
		return pedidoSelecionado;
	}

	public void setPedidoSelecionado(Pedido pedidoSelecionado) {
		this.pedidoSelecionado = pedidoSelecionado;
	}

	
}
