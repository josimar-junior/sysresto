package br.com.sysresto.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sysresto.dao.FornecedorDAO;
import br.com.sysresto.model.Fornecedor;
import br.com.sysresto.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Fornecedor.class)
public class FornecedorConverter implements Converter {

	private FornecedorDAO fornecedorDAO;

	public FornecedorConverter() {
		fornecedorDAO = CDIServiceLocator.getBean(FornecedorDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Fornecedor retorno = null;
		if (value != null) {
			Long id = new Long(value);
			retorno = fornecedorDAO.getFornecedorPorId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Fornecedor fornecedor = (Fornecedor) value;
			return fornecedor.getId() == null ? null : fornecedor.getId().toString();
		}
		return "";
	}

}
