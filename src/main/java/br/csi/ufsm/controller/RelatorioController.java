package br.csi.ufsm.controller;


import br.csi.ufsm.dao.Livrodao;
import br.csi.ufsm.dao.Relatoriodao;
import br.csi.ufsm.model.Cliente;
import br.csi.ufsm.model.Funcionario;
import br.csi.ufsm.model.Livro;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/livromaisvendido")
    public ArrayList<Livro> getLivros(){
        System.out.println("to aqui");
       ArrayList<Livro> livro = new Relatoriodao().getLivromaisvendido();
        for (Livro l : livro) {
            System.out.println("livro mais vendido:"+l.getNome_livro());
            System.out.println("livro mais vendido:"+l.getTotaldevendas());
        }
        return livro;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/livrosemfalta")
    public ArrayList<Livro> getLivrosemfalta(){
        System.out.println("livros em falta");
        ArrayList<Livro> livro = new Relatoriodao().getLivrosemfalta();
        for (Livro l : livro) {
            System.out.println("livros em falta:"+l.getNome_livro());
            System.out.println("livros em falta:"+l.getEstoque_liv());
        }
        return livro;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/funcmais")
    public ArrayList<Funcionario> getFuncionariomais(){
        System.out.println("funcionariomais");
        ArrayList<Funcionario> funcionarios = new Relatoriodao().getFuncmais();
        for (Funcionario l : funcionarios) {
            System.out.println("funcionario que mais vendeu:"+l.getNome_funcionario());
            System.out.println("funcionario que mais vendeu:"+l.getTotalvendas());
        }
        return funcionarios;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/funcmenos")
    public ArrayList<Funcionario> getFuncionariomenos(){
        System.out.println("func menos");
        ArrayList<Funcionario> funcionarios = new Relatoriodao().getFuncmenos();
        for (Funcionario l : funcionarios) {
            System.out.println("funcionario que menos vendeu:"+l.getNome_funcionario());
            System.out.println("funcionario que menos vendeu:"+l.getTotalvendas());
        }
        return funcionarios;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/cli")
    public ArrayList<Cliente> getClientes(){
        System.out.println("func menos");
        ArrayList<Cliente> clientes = new Relatoriodao().getCli();
        for (Cliente l : clientes) {
            System.out.println("clientes:"+l.getNome_cliente());
            System.out.println("clientes:"+l.getTotaldecompras());
        }
        return clientes;
    }
}
