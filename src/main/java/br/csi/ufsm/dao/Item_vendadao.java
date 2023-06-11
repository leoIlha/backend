package br.csi.ufsm.dao;

import br.csi.ufsm.model.Item_venda;

import java.sql.*;

public class Item_vendadao {
    private String sql;
    private PreparedStatement preparedStatement;
    private Statement stmt;
    private ResultSet resultSet;
    private String status;

    public Item_venda setiem_venda(Item_venda venda) {

        try (Connection connection = new ConectaDBPostgres().getConexao()) {
            this.sql = "INSERT INTO item_venda (id_liv, id_venda, quantidade, valor_livro) VALUES (?, ?, ?, ?)";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setInt(1, venda.getId_liv());
            this.preparedStatement.setInt(2, venda.getId_venda());
            this.preparedStatement.setInt(3, venda.getQuantidade());
            this.preparedStatement.setFloat(4, venda.getValor_livro());
            this.preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
