package br.csi.ufsm.dao;

import br.csi.ufsm.model.Livro;
import br.csi.ufsm.model.Venda;

import java.sql.*;
import java.util.ArrayList;

public class Vendadao {
    private String sql;
    private PreparedStatement preparedStatement;
    private Statement stmt;
    private ResultSet resultSet;
    private String status;


    public ArrayList<Venda> getVendas(){
        ArrayList<Venda> vendas = new ArrayList<>();
        try (Connection connection = new ConectaDBPostgres().getConexao()){
            this.sql = "select id_venda,data_venda,forma_pagamento,valor_total_venda,id_func,id_cli from Venda";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){
                Venda venda = new Venda();
                venda.setId_venda(this.resultSet.getInt("id_venda"));
                venda.setData_venda(this.resultSet.getDate("data_venda"));
                venda.setForma_pagamento(this.resultSet.getString("forma_pagamento"));
                venda.setValor_total_venda(this.resultSet.getFloat("valor_total_venda"));
                venda.setId_func(this.resultSet.getInt("id_func"));
                venda.setId_cli(this.resultSet.getInt("id_cli"));
                vendas.add(venda);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vendas;
    }

    public int setvendas(Venda venda) {
        int idVendaGerado = -1; // Valor padrão caso não seja gerado um ID válido

        try (Connection connection = new ConectaDBPostgres().getConexao()) {
            this.sql = "INSERT INTO Venda (data_venda, forma_pagamento, valor_total_venda, id_func, id_cli) VALUES (?, ?, ?, ?, ?)";
            this.preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setDate(1, venda.getData_venda());
            this.preparedStatement.setString(2, venda.getForma_pagamento());
            this.preparedStatement.setFloat(3, venda.getValor_total_venda());
            this.preparedStatement.setInt(4, venda.getId_func());
            this.preparedStatement.setInt(5, venda.getId_cli());
            this.preparedStatement.execute();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                idVendaGerado = generatedKeys.getInt(1); // Obtém o valor do ID gerado
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idVendaGerado;
    }


    public Livro getLivrobyid(int id) {
        try (Connection connection = new ConectaDBPostgres().getConexao()){
            this.sql = "SELECT * FROM Livro WHERE id_liv = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){
               Livro livro = new Livro();
                livro.setPreco(this.resultSet.getInt("preco"));
                livro.setEstoque_liv(this.resultSet.getInt("estoque_liv"));
                livro.setId_liv(this.resultSet.getInt("id_liv"));
                return livro;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
