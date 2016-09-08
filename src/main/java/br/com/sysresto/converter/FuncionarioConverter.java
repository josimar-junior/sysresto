package br.com.sysresto.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sysresto.dao.FuncionarioDAO;
import br.com.sysresto.model.Funcionario;
import br.com.sysresto.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Funcionario.class)
public class FuncionarioConverter implements Converter {

	private FuncionarioDAO funcionarioDAO;

	public FuncionarioConverter() {
		funcionarioDAO = CDIServiceLocator.getBean(FuncionarioDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Funcionario retorno = null;
		if (value != null) {
			Long id = new Long(value);
			retorno = funcionarioDAO.getFuncionarioPorId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Funcionario funcionario = (Funcionario) value;
			return funcionario.getId() == null ? null : funcionario.getId().toString();
		}
		return "";
	}

}
