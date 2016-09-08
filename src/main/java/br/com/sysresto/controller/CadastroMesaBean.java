package br.com.sysresto.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sysresto.bo.MesaBO;
import br.com.sysresto.model.Mesa;
import br.com.sysresto.model.StatusMesa;
import br.com.sysresto.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroMesaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Mesa mesa;

	@Inject
	private MesaBO mesaBO;

	public CadastroMesaBean() {
		limpar();
	}

	public void salvar() {
		if (mesa.getId() == null) {
			mesa = mesaBO.salvarOuAtualizar(mesa);

			FacesUtil.addInfoMessage("Mesa de número " + mesa.getNumeroMesa()
					+ " cadastrada com sucesso!");
		} else {
			mesa = mesaBO.salvarOuAtualizar(mesa);

			FacesUtil.addInfoMessage("Mesa de número " + mesa.getNumeroMesa()
					+ " alterada com sucesso!");
		}

		limpar();
	}

	public StatusMesa[] getStatusMesa() {
		return StatusMesa.values();
	}
	
	private void limpar() {
		mesa = new Mesa();
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

}
