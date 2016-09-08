package br.com.sysresto.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sysresto.bo.SecaoBO;
import br.com.sysresto.filter.SecaoFilter;
import br.com.sysresto.model.Secao;
import br.com.sysresto.model.Status;
import br.com.sysresto.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ConsultaSecaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Secao secao;
	private SecaoFilter secaoFilter;
	private List<Secao> secoes;
	private Secao secaoSelecionada;

	@Inject
	private SecaoBO secaoBO;

	public ConsultaSecaoBean() {
		limpar();
	}

	@PostConstruct
	public void init() {
		pesquisar();

	}

	public void remover() {
		secaoBO.remover(secaoSelecionada);
		secoes.remove(secaoSelecionada);
		FacesUtil.addInfoMessage("Seção "
				+ secaoSelecionada.getDescricao()
				+ " excluída com sucesso!");
	}

	public void modificarStatus() {
		if(secao.getStatus() == Status.ATIVO) {
			secao.setStatus(Status.INATIVO);
		} else {
			secao.setStatus(Status.ATIVO);
		}
		
		secaoBO.salvarOuAtualizar(secao);
		FacesUtil.addInfoMessage("Seção modificada com sucesso!");
	}
	
	public void pesquisar() {
		secoes = secaoBO.pesquisar(secaoFilter);
	}

	private void limpar() {
		secao = new Secao();
		secaoFilter = new SecaoFilter();
		secaoSelecionada = new Secao();
	}

	public Secao getSecao() {
		return secao;
	}

	public void setSecao(Secao secao) {
		this.secao = secao;
	}

	public SecaoFilter getSecaoFilter() {
		return secaoFilter;
	}

	public void setSecaoFilter(SecaoFilter secaoFilter) {
		this.secaoFilter = secaoFilter;
	}

	public List<Secao> getSecoes() {
		return secoes;
	}

	public void setSecoes(List<Secao> secoes) {
		this.secoes = secoes;
	}

	public Secao getSecaoSelecionada() {
		return secaoSelecionada;
	}

	public void setSecaoSelecionada(Secao secaoSelecionada) {
		this.secaoSelecionada = secaoSelecionada;
	}

}
