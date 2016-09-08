package br.com.sysresto.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sysresto.dao.MesaDAO;
import br.com.sysresto.model.Mesa;
import br.com.sysresto.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Mesa.class)
public class MesaConverter implements Converter {

	private MesaDAO mesaDAO;

	public MesaConverter() {
		mesaDAO = CDIServiceLocator.getBean(MesaDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Mesa retorno = null;
		if (value != null) {
			Long id = new Long(value);
			retorno = mesaDAO.getMesaPorId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Mesa mesa = (Mesa) value;
			return mesa.getId() == null ? null : mesa.getId().toString();
		}
		return "";
	}

}
