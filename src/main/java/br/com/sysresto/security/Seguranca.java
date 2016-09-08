package br.com.sysresto.security;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Named
@RequestScoped
public class Seguranca {

	public String getNomeUsuario() {
		String nome = null;
		FuncionarioSistema usuarioLogado = getUsuarioLogado();

		if (usuarioLogado != null) {
			nome = usuarioLogado.getFuncionario().getNome();
		}
		return nome;
	}

	public FuncionarioSistema getUsuarioLogado() {
		FuncionarioSistema usuario = null;

		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) FacesContext
				.getCurrentInstance().getExternalContext().getUserPrincipal();
		
		if(auth != null && auth.getPrincipal() != null) {
			usuario = (FuncionarioSistema) auth.getPrincipal();
		}

		return usuario;
	}

}
