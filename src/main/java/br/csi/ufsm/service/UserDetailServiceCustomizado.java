package br.csi.ufsm.service;

import br.csi.ufsm.dao.Funcionariodao;
import br.csi.ufsm.model.Funcionario;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceCustomizado implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Funcionario funcionario= new Funcionariodao().getUser(username); //UsuarioDao().getUsuario(username);
        System.out.println("detaailservice"+funcionario.getEmail());
        System.out.println("detaailservice"+funcionario.getCargo());
        if (funcionario == null){
            throw new UsernameNotFoundException("Usuário ou senha incorretos");
        }else {
            UserDetails user = User.withUsername(funcionario.getEmail()).password(funcionario.getSenha()).authorities(funcionario.getCargo()).build();
            return user;
        }
    }
}
