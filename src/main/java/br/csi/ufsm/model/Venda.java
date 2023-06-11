package br.csi.ufsm.model;

import java.sql.Date;

public class Venda {
    private int id_venda;
    private float valor_total_venda;
    private java.sql.Date data_venda;
    private String forma_pagamento;
    private int id_cli;
    private int id_func;

    private int id_liv;

    private String email;
    private int quantidade;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId_venda() {
        return id_venda;
    }

    public void setId_venda(int id_venda) {
        this.id_venda = id_venda;
    }

    public float getValor_total_venda() {
        return valor_total_venda;
    }

    public void setValor_total_venda(float valor_total_venda) {
        this.valor_total_venda = valor_total_venda;
    }

    public java.sql.Date getData_venda() {
        return data_venda;
    }

    public void setData_venda(Date data_venda) {
        this.data_venda = data_venda;
    }

    public String getForma_pagamento() {
        return forma_pagamento;
    }

    public void setForma_pagamento(String forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
    }

    public int getId_cli() {
        return id_cli;
    }

    public void setId_cli(int id_cli) {
        this.id_cli = id_cli;
    }

    public int getId_func() {
        return id_func;
    }

    public void setId_func(int id_func) {
        this.id_func = id_func;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getId_liv() {
        return id_liv;
    }

    public void setId_liv(int id_liv) {
        this.id_liv = id_liv;
    }
}
