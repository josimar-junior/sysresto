package br.com.sysresto.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sysresto.dao.ProdutoDAO;
import br.com.sysresto.model.Produto;
import br.com.sysresto.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter {

	private ProdutoDAO produtoDAO;

	public ProdutoConverter() {
		produtoDAO = CDIServiceLocator.getBean(ProdutoDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Produto retorno = null;
		if (value != null) {
			Long id = new Long(value);
			retorno = produtoDAO.getProdutoPorId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Produto produto = (Produto) value;
			return produto.getId() == null ? null : produto.getId().toString();
		}
		return "";
	}

}
