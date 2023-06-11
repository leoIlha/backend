package br.csi.ufsm.model;

public class Item_venda {

    private int id_liv;
    private int id_venda;
    private int quantidade;
    private float valor_livro;

    public int getId_liv() {
        return id_liv;
    }

    public void setId_liv(int id_liv) {
        this.id_liv = id_liv;
    }

    public int getId_venda() {
        return id_venda;
    }

    public void setId_venda(int id_venda) {
        this.id_venda = id_venda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getValor_livro() {
        return valor_livro;
    }

    public void setValor_livro(float valor_livro) {
        this.valor_livro = valor_livro;
    }
}
