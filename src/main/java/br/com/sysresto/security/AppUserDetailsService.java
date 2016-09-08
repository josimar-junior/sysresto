package br.com.sysresto.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.sysresto.bo.FuncionarioBO;
import br.com.sysresto.model.Funcionario;
import br.com.sysresto.util.cdi.CDIServiceLocator;

public class AppUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		FuncionarioBO funcionarioBO = CDIServiceLocator
				.getBean(FuncionarioBO.class);

		Funcionario funcionario = funcionarioBO
				.getFuncionarioPorLogin(username);

		FuncionarioSistema funcionarioSistema = null;

		if (funcionario != null) {
			funcionarioSistema = new FuncionarioSistema(funcionario,
					getPermissoes(funcionario));
		}
		return funcionarioSistema;
	}

	private Collection<? extends GrantedAuthority> getPermissoes(
			Funcionario funcionario) {
		List<SimpleGrantedAuthority> permissoes = new ArrayList<>();
		permissoes.add(new SimpleGrantedAuthority(funcionario.getPerfil()
				.toString().toUpperCase()));

		return permissoes;
	}

}
