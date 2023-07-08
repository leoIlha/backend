package br.csi.ufsm.dao;

import br.csi.ufsm.model.Cliente;
import br.csi.ufsm.model.Funcionario;
import br.csi.ufsm.model.Livro;

import java.sql.*;
import java.util.ArrayList;

public class Relatoriodao {

    private String sql;
    private PreparedStatement preparedStatement;
    private Statement stmt;
    private ResultSet resultSet;
    private String status;
    public ArrayList<Livro> getLivromaisvendido(){
        ArrayList<Livro> livros = new ArrayList<>();
        try (Connection connection = new ConectaDBPostgres().getConexao()){
           // this.sql = "select id_func,email,senha,cargo,salario,telefone,nome_funcionario from Funcionario";
            this.sql="SELECT L.nome_livro, SUM(IV.quantidade) AS total_vendas\n" +
                    "FROM Livro L\n" +
                    "JOIN item_venda IV ON IV.id_liv = L.id_liv\n" +
                    "GROUP BY L.nome_livro\n" +
                    "ORDER BY total_vendas DESC\n" +
                    "LIMIT 1;";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){
                Livro livro = new Livro();
              //  funcionario.setId_func(this.resultSet.getInt("id_func"));
              livro.setNome_livro(this.resultSet.getString("nome_livro"));
              livro.setTotaldevendas(this.resultSet.getInt("total_vendas"));
                livros.add(livro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livros;
    }

    public ArrayList<Livro> getLivrosemfalta(){
        ArrayList<Livro> livros = new ArrayList<>();
        try (Connection connection = new ConectaDBPostgres().getConexao()){
            // this.sql = "select id_func,email,senha,cargo,salario,telefone,nome_funcionario from Funcionario";
            this.sql="SELECT * FROM Livro WHERE estoque_liv = 0;";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){
                Livro livro = new Livro();
                //  funcionario.setId_func(this.resultSet.getInt("id_func"));
                livro.setNome_livro(this.resultSet.getString("nome_livro"));
                livro.setEstoque_liv(this.resultSet.getInt("estoque_liv"));
                livros.add(livro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livros;
    }

    public ArrayList<Funcionario> getFuncmais(){
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        try (Connection connection = new ConectaDBPostgres().getConexao()){
            // this.sql = "select id_func,email,senha,cargo,salario,telefone,nome_funcionario from Funcionario";
        this.sql = "SELECT f.nome_funcionario, SUM(iv.quantidade) AS total_vendas\n" +
                "FROM funcionario f\n" +
                "JOIN venda v ON f.id_func = v.id_func\n" +
                "JOIN item_venda iv ON v.id_venda = iv.id_venda\n" +
                "GROUP BY f.nome_funcionario\n" +
                "ORDER BY total_vendas DESC\n" +
                "LIMIT 1;\n";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){
                    Funcionario funcionario = new Funcionario();
               funcionario.setNome_funcionario(this.resultSet.getString("nome_funcionario"));
               funcionario.setTotalvendas(this.resultSet.getInt("total_vendas"));

                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionarios;
    }

    public ArrayList<Funcionario> getFuncmenos(){
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        try (Connection connection = new ConectaDBPostgres().getConexao()){
             this.sql = "SELECT F.nome_funcionario, COUNT(V.id_venda) AS total_vendas\n" +
                     "FROM Funcionario F\n" +
                     "LEFT JOIN Venda V ON V.id_func = F.id_func\n" +
                     "GROUP BY F.nome_funcionario\n" +
                     "ORDER BY total_vendas ASC\n" +
                     "LIMIT 1;";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){
                Funcionario funcionario = new Funcionario();
                funcionario.setNome_funcionario(this.resultSet.getString("nome_funcionario"));
                funcionario.setTotalvendas(this.resultSet.getInt("total_vendas"));

                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionarios;
    }

    public ArrayList<Cliente> getCli(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        try (Connection connection = new ConectaDBPostgres().getConexao()){
            // this.sql = "select id_func,email,senha,cargo,salario,telefone,nome_funcionario from Funcionario";
            this.sql="SELECT c.nome_cliente,SUM(c.id_cli) AS total_compras\n" +
                    "FROM Cliente c\n" +
                    "JOIN Venda v ON c.id_cli = v.id_cli\n" +
                    "GROUP BY c.nome_cliente\n" +
                    "ORDER BY COUNT(*) DESC\n" +
                    "LIMIT 1;";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){
                Cliente cliente = new Cliente();
                //  funcionario.setId_func(this.resultSet.getInt("id_func"));
                cliente.setNome_cliente(this.resultSet.getString("nome_cliente"));
                cliente.setTotaldecompras(this.resultSet.getInt("total_compras"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }


}
