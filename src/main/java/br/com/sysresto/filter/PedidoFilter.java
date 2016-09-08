package br.com.sysresto.filter;

import java.io.Serializable;
import java.util.Date;

import br.com.sysresto.model.StatusPedido;

public class PedidoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date dataPedidoDe;
	private Date dataPedidoAte;
	private StatusPedido[] statusPedido;
	private String nomeGarcom;

	public Date getDataPedidoDe() {
		return dataPedidoDe;
	}

	public void setDataPedidoDe(Date dataCriacaoDe) {
		this.dataPedidoDe = dataCriacaoDe;
	}

	public Date getDataPedidoAte() {
		return dataPedidoAte;
	}

	public void setDataPedidoAte(Date dataCriacaoAte) {
		this.dataPedidoAte = dataCriacaoAte;
	}

	public StatusPedido[] getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(StatusPedido[] statusPedido) {
		this.statusPedido = statusPedido;
	}

	public String getNomeGarcom() {
		return nomeGarcom;
	}

	public void setNomeGarcom(String nomeGarcom) {
		this.nomeGarcom = nomeGarcom;
	}

}
