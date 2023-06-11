package br.csi.ufsm.controller;

import br.csi.ufsm.dao.Clientedao;
import br.csi.ufsm.dao.Livrodao;
import br.csi.ufsm.model.Cliente;
import br.csi.ufsm.model.Livro;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/clientes")
    public ArrayList<Cliente> getClientes(){
        return new Clientedao().getClientes();
    }

    @PostMapping("/save")
    public Cliente savecliente(@RequestBody Cliente cliente){
        System.out.println("Cliente que será criado:"+cliente.getNome_cliente());
        System.out.println("Cliente que será criado:"+cliente.getEmail_cliente());
        System.out.println("Cliente que será criado:"+cliente.getTelefone_cliente());
        System.out.println("Cliente que será criado:"+cliente.getCpf());
        //  System.out.println("id:"+autor.getId_aut());
        return new Clientedao().setCliente(cliente);
    }

    @PutMapping("/editar")
    public Cliente editarcliente(@RequestBody Cliente cliente){
        System.out.println("Cliente que será criado:"+cliente.getNome_cliente());
        System.out.println("Cliente que será criado:"+cliente.getEmail_cliente());
        System.out.println("Cliente que será criado:"+cliente.getTelefone_cliente());
        System.out.println("Cliente que será criado:"+cliente.getCpf());

        return new Clientedao().editar(cliente);
    }


    @DeleteMapping("/delete/{id_cli}")
    public Cliente deletecliente(@PathVariable("id_cli") Integer id_cli){

        return new Clientedao().excluir(id_cli);
    }

//    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    @GetMapping("/ver/{email}")
//    public ArrayList<Livro> vernome(@PathVariable("email") String email){
//        System.out.println("VEEEEEEEEEEEEEEER NOME"+ email);
//        return new Funcionariodao().getUsuario(email);
//    }

//    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    @GetMapping("/funcionarios")
//    public ArrayList<Funcionario> getUsuarios(){
//        return new Funcionariodao().getUsers();
//    }

//    @GetMapping("/funcionario")
//    public Funcionario funcionario(){
//        //return new UsuarioLogin("joao@teste", "123", "ADMIN");
//        return null;
//    }


//    @PostMapping("/param")
//    public Autor param(@RequestBody Autor autor){
//        //  System.out.println("AAAAAAAAAAAAAAAUTOR:"+autor.getNome());
//        //  System.out.println("id:"+autor.getId_aut());
//        System.out.println("PARAMETRO:"+autor.getNome());
//        return null;
//    }

}
