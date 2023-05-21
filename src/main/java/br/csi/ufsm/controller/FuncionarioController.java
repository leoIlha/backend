package br.csi.ufsm.controller;

import br.csi.ufsm.dao.Funcionariodao;
import br.csi.ufsm.model.Funcionario;
import br.csi.ufsm.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public ResponseEntity<Object> autenticacao(@RequestBody Funcionario funcionario){

        System.out.println(funcionario.getEmail());
        System.out.println(funcionario.getSenha());
        System.out.println(funcionario.getCargo());

        try{
            Funcionario funcionario1=new Funcionariodao().getUsuariobyemail(funcionario.getEmail());
            System.out.println("Pegando o cargo pelo email:"+funcionario1.getCargo());
            funcionario.setCargo(funcionario1.getCargo());
            System.out.println("MANDANDO PARA OUTRO:"+funcionario.getCargo());
            final Authentication autenticado= this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(funcionario.getEmail(), funcionario.getSenha()));

            if (autenticado.isAuthenticated()){
                SecurityContextHolder.getContext().setAuthentication(autenticado);
                System.out.println("Autenticado");


                System.out.println("Gerando token de autenticação ***");
                String token =new JWTUtil().geraToken(funcionario);

                funcionario.setToken(token);
                funcionario.setSenha("");

                return new ResponseEntity<>(funcionario, HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Usuário ou senha incorretos!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Usuário ou senha incorretos!", HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/funcionarios")
    public ArrayList<Funcionario> getUsuarios(){
        return new Funcionariodao().getUsers();
    }

    @PostMapping("/save")
    public Funcionario savefuncionario(@RequestBody Funcionario funcionario){
        System.out.println("Funcionario que será criado:"+funcionario.getEmail());
        System.out.println("Funcionario que será criado:"+funcionario.getNome_funcionario());
        System.out.println("Funcionario que será criado:"+funcionario.getCargo());
        System.out.println("Funcionario que será criado:"+funcionario.getTelefone());
        System.out.println("Funcionario que será criado:"+funcionario.getSalario());
        //  System.out.println("id:"+autor.getId_aut());
        return new Funcionariodao().setUser(funcionario);
    }

    @PutMapping("/editar")
    public Funcionario editarfuncionario(@RequestBody Funcionario funcionario){
        System.out.println("Nome funcionário:"+funcionario.getEmail());
        System.out.println("Nome funcionário:"+funcionario.getCargo());
        System.out.println("Nome funcionário:"+funcionario.getTelefone());
        return new Funcionariodao().editar(funcionario);
    }


    @DeleteMapping("/delete/{email}")
    public Funcionario deletefuncionario(@PathVariable("email") String email){

        return new Funcionariodao().excluir(email);
    }

   @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/ver/{email}")
    public ArrayList<Funcionario> vernome(@PathVariable("email") String email){
        System.out.println("VEEEEEEEEEEEEEgfhEER NOME"+ email);
        return new Funcionariodao().getUsuario(email);
    }


    @GetMapping("/funcionario")
    public Funcionario funcionario(){
        //return new UsuarioLogin("joao@teste", "123", "ADMIN");
        return null;
    }



}
