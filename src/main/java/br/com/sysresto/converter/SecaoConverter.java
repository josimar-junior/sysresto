package br.com.sysresto.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sysresto.dao.SecaoDAO;
import br.com.sysresto.model.Secao;
import br.com.sysresto.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Secao.class)
public class SecaoConverter implements Converter {

	private SecaoDAO secaoDAO;

	public SecaoConverter() {
		secaoDAO = CDIServiceLocator.getBean(SecaoDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Secao retorno = null;
		if (value != null) {
			Long id = new Long(value);
			retorno = secaoDAO.getSecaoPorId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Secao secao = (Secao) value;
			return secao.getId() == null ? null : secao.getId().toString();
		}
		return "";
	}

}
