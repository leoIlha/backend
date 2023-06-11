package br.csi.ufsm.controller;

import br.csi.ufsm.dao.Funcionariodao;
import br.csi.ufsm.dao.Item_vendadao;
import br.csi.ufsm.dao.Livrodao;
import br.csi.ufsm.dao.Vendadao;
import br.csi.ufsm.model.Funcionario;
import br.csi.ufsm.model.Item_venda;
import br.csi.ufsm.model.Livro;
import br.csi.ufsm.model.Venda;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/carrinho")
public class VendaController {


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/carrinhos")
    public ArrayList<Venda> getVendas(){
        return new Vendadao().getVendas();
    }

    @PostMapping("/save")
    public Venda savevenda(@RequestBody Venda venda1) {
        Funcionario funcionario=new Funcionariodao().getUsuariobyemail(venda1.getEmail());
        venda1.setId_func(funcionario.getId_func());

        //REALIZANDO A VENDA
        int vendaaux = new Vendadao().setvendas(venda1);


        System.out.println("ID VENDA GERADO: " + vendaaux);
        System.out.println("ID LIVRO VENDA: " + venda1.getId_liv());
        int id;
        id=venda1.getId_liv();
        Livro livroaux= new Vendadao().getLivrobyid(id);
        System.out.println("PREÇO VENDA: " + livroaux.getPreco());

        int numlivro=venda1.getQuantidade();
        int total;
        total=livroaux.getEstoque_liv()-numlivro;

        System.out.println("QUANTIDADE DA VENDA:"+numlivro);
        System.out.println("QUANTIDADE DO ESTOQUE DO LIVRO:"+livroaux.getEstoque_liv());
        System.out.println("NOVO ESTOQUE:"+total);
        new Livrodao().diminuiquantidade(total,livroaux.getId_liv());

        Item_venda itemVenda = new Item_venda();
        itemVenda.setId_venda(vendaaux);
        itemVenda.setQuantidade(venda1.getQuantidade());
        itemVenda.setValor_livro(livroaux.getPreco());
        itemVenda.setId_liv(id);
        new Item_vendadao().setiem_venda(itemVenda);



        return venda1;
    }

}
