package br.csi.ufsm.model;

import java.sql.Date;

public class Livro {
    private int id_liv;
    private String nome_livro;
    private String autor;
    private String editora;
    private String genero;
    private int num_paginas;
    private Date data_lanc;
    private int estoque_liv;
    private float preco;

    private int totaldevendas;

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    private int novoestoq;

    public int getNovoestoq() {
        return novoestoq;
    }

    public void setNovoestoq(int novoestoq) {
        this.novoestoq = novoestoq;
    }

    public int getId_liv() {
        return id_liv;
    }

    public void setId_liv(int id_liv) {
        this.id_liv = id_liv;
    }

    public String getNome_livro() {
        return nome_livro;
    }

    public void setNome_livro(String nome_livro) {
        this.nome_livro = nome_livro;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getNum_paginas() {
        return num_paginas;
    }

    public void setNum_paginas(int num_paginas) {
        this.num_paginas = num_paginas;
    }


    public int getEstoque_liv() {
        return estoque_liv;
    }

    public void setEstoque_liv(int estoque_liv) {
        this.estoque_liv = estoque_liv;
    }

    public Date getData_lanc() {
        return data_lanc;
    }

    public void setData_lanc(Date data_lanc) {
        this.data_lanc = data_lanc;
    }

    public int getTotaldevendas() {
        return totaldevendas;
    }

    public void setTotaldevendas(int totaldevendas) {
        this.totaldevendas = totaldevendas;
    }
}
