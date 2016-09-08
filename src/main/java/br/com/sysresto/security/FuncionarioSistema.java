package br.com.sysresto.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.com.sysresto.model.Funcionario;

public class FuncionarioSistema extends User {

	private static final long serialVersionUID = 1L;

	private Funcionario funcionario;
	
	public FuncionarioSistema(Funcionario funcionario,
			Collection<? extends GrantedAuthority> authorities) {
		super(funcionario.getLogin(), funcionario.getSenha(), authorities);
		this.funcionario = funcionario;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}
}