package br.com.sysresto.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sysresto.dao.PedidoDAO;
import br.com.sysresto.model.Pedido;
import br.com.sysresto.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Pedido.class)
public class PedidoConverter implements Converter {

	private PedidoDAO pedidoDAO;

	public PedidoConverter() {
		pedidoDAO = CDIServiceLocator.getBean(PedidoDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Pedido retorno = null;
		if (value != null) {
			Long id = new Long(value);
			retorno = pedidoDAO.getPedidoPorId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Pedido pedido = (Pedido) value;
			return pedido.getId() == null ? null : pedido.getId().toString();
		}
		return "";
	}

}
