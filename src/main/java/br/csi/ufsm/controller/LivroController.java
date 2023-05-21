package br.csi.ufsm.controller;


import br.csi.ufsm.dao.Livrodao;
import br.csi.ufsm.model.Livro;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/livros")
    public ArrayList<Livro> getLivros(){
        return new Livrodao().getLivros();
    }

    @PostMapping("/save")
    public Livro savelivro(@RequestBody Livro livro){
        System.out.println("Funcionario que será criado:"+livro.getNome_livro());
        System.out.println("Funcionario que será criado:"+livro.getAutor());
        System.out.println("Funcionario que será criado:"+livro.getEditora());
        System.out.println("Funcionario que será criado:"+livro.getGenero());
        System.out.println("Funcionario que será criado:"+livro.getData_lanc());
        System.out.println("Funcionario que será criado:"+livro.getNum_paginas());
        System.out.println("Funcionario que será criado:"+livro.getEstoque_liv());


        //  System.out.println("id:"+autor.getId_aut());
        return new Livrodao().setLivro(livro);
    }

    @PutMapping("/editar")
    public Livro editarlivro(@RequestBody Livro livro){
        System.out.println("Nome livro :"+livro.getNome_livro());
        System.out.println("Nome livro:"+livro.getAutor());
        System.out.println("Nome livro:"+livro.getData_lanc());
        System.out.println("Nome livro:"+livro.getEstoque_liv());
        System.out.println("Nome livro:"+livro.getNum_paginas());
        System.out.println("Nome livro:"+livro.getEditora());
        return new Livrodao().editar(livro);
    }


    @DeleteMapping("/delete/{id_liv}")
    public Livro deletelivro(@PathVariable("id_liv") Integer id_liv){

        return new Livrodao().excluir(id_liv);
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
