package br.com.sysresto.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sysresto.bo.PedidoBO;
import br.com.sysresto.bo.ProdutoBO;
import br.com.sysresto.model.FormaPagamento;
import br.com.sysresto.model.ItemPedido;
import br.com.sysresto.model.Pedido;
import br.com.sysresto.model.Produto;
import br.com.sysresto.security.Seguranca;
import br.com.sysresto.util.cdi.PedidoEdicao;
import br.com.sysresto.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Produces
	@PedidoEdicao
	private Pedido pedido;

	@Inject
	private ProdutoBO produtoBO;

	@Inject
	private PedidoBO pedidoBO;

	private Produto produtoLinhaEditavel;

	public CadastroPedidoBean() {
		limpar();
	}

	public void inicializar() {
		if (!FacesUtil.isPostback()) {
			System.out.println("Entrou AQUI");
			pedido.adicionarItemVazio();
			recalcularPedido();
		}
	}

	private void limpar() {
		pedido = new Pedido();
		pedido.setGarcom(new Seguranca().getUsuarioLogado().getFuncionario());
	}

	public void salvar() {
		pedido.removerItemVazio();
		try {
			pedido = pedidoBO.salvar(pedido);
			FacesUtil.addInfoMessage("Pedido salvo com sucesso!");
		} finally {
			pedido.adicionarItemVazio();
		}
	}

	public FormaPagamento[] getFormaPagamento() {
		return FormaPagamento.values();
	}

	public void recalcularPedido() {
		if (pedido != null) {
			pedido.recalcularValorTotal();
		}
	}

	public List<Produto> completarProduto(String nome) {
		return produtoBO.getProdutosPorNome(nome);
	}

	public void carregarProdutoLinhaEditavel() {
		ItemPedido item = pedido.getItens().get(0);
		if (produtoLinhaEditavel != null) {
			if (existeItemComProduto(produtoLinhaEditavel)) {
				FacesUtil
						.addErrorMessage("Já existe um item com o produto informado!");
			} else {
				item.setProduto(produtoLinhaEditavel);
				item.setValorUnitario(produtoLinhaEditavel.getValorVenda());

				pedido.adicionarItemVazio();
				produtoLinhaEditavel = null;
				pedido.recalcularValorTotal();
			}
		}
	}

	private boolean existeItemComProduto(Produto produto) {
		boolean existeItem = false;

		for (ItemPedido item : getPedido().getItens()) {
			if (produto.equals(item.getProduto())) {
				existeItem = true;
				break;
			}
		}

		return existeItem;
	}

	public void atualizarQuantidade(ItemPedido item, int linha) {
		if (item.getQuantidade() < 1) {
			if (linha == 0) {
				item.setQuantidade(1);
			} else {
				getPedido().getItens().remove(linha);
			}
		}

		pedido.recalcularValorTotal();
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProdutoLinhaEditavel() {
		return produtoLinhaEditavel;
	}

	public void setProdutoLinhaEditavel(Produto produtoLinhaEditavel) {
		this.produtoLinhaEditavel = produtoLinhaEditavel;
	}

}
