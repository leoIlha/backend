package br.csi.ufsm.dao;

import br.csi.ufsm.model.Cliente;
import br.csi.ufsm.model.Livro;

import java.sql.*;
import java.util.ArrayList;

public class Clientedao {

    private String sql;
    private PreparedStatement preparedStatement;
    private Statement stmt;
    private ResultSet resultSet;
    private String status;

  /*  public UsuarioLogin getUsuario(String user){

        if (user.equals("leo@teste")){
            //fez consulta no BD e tvrouxe esse usuario com esses valores
            return new UsuarioLogin(user, new BCryptPasswordEncoder().encode("123"), "USER");

        }else if (user.equals("pietra@teste")){
            return new UsuarioLogin("pietra@teste", new BCryptPasswordEncoder().encode("123"), "ADMIN");
        }else{
            return null;
        }
    }*/

    public ArrayList<Cliente> getClientes(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        try (Connection connection = new ConectaDBPostgres().getConexao()){
            this.sql = "select id_cli,cpf,telefone_cliente,nome_cliente,email_cliente from Cliente";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){
               Cliente cliente = new Cliente();
                cliente.setId_cli(this.resultSet.getInt("id_cli"));
                cliente.setCpf(this.resultSet.getString("cpf"));
                cliente.setTelefone_cliente(this.resultSet.getString("telefone_cliente"));
                cliente.setNome_cliente(this.resultSet.getString("nome_cliente"));
                cliente.setEmail_cliente(this.resultSet.getString("email_cliente"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public Cliente setCliente(Cliente cliente) {
        try (Connection connection = new ConectaDBPostgres().getConexao()) {
            this.sql = "INSERT INTO Cliente (cpf,telefone_cliente,nome_cliente,email_cliente) VALUES (?, ?, ?,?)";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setString(1, cliente.getCpf());
            this.preparedStatement.setString(2, cliente.getTelefone_cliente());
            this.preparedStatement.setString(3, cliente.getNome_cliente());
            this.preparedStatement.setString(4,  cliente.getEmail_cliente());
            this.preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    public Cliente editar(Cliente cliente) {
        System.out.println("vindo do controller:");
        System.out.println(cliente.getNome_cliente());
        System.out.println(cliente.getCpf());
        try (Connection connection = new ConectaDBPostgres().getConexao()) {
            this.sql = "update Cliente set cpf = ?, nome_cliente = ?, telefone_cliente = ?,email_cliente = ? where id_cli=?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, cliente.getCpf());
            this.preparedStatement.setString(2, cliente.getNome_cliente());
            this.preparedStatement.setString(3, cliente.getTelefone_cliente());
            this.preparedStatement.setString(4, cliente.getEmail_cliente());
            this.preparedStatement.setInt(5, cliente.getId_cli());
            this.preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    public Cliente excluir(Integer id_cli) {
        try (Connection connection = new ConectaDBPostgres().getConexao()) {

            this.sql = "delete from Cliente where id_cli = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id_cli);
            this.preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }




}
