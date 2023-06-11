package br.csi.ufsm.dao;

import br.csi.ufsm.model.Funcionario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.*;
import java.util.ArrayList;

public class Funcionariodao {
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

    public ArrayList<Funcionario> getUsers(){
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        try (Connection connection = new ConectaDBPostgres().getConexao()){
            this.sql = "select id_func,email,senha,cargo,salario,telefone,nome_funcionario from Funcionario";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){
                Funcionario funcionario = new Funcionario();
                funcionario.setId_func(this.resultSet.getInt("id_func"));
                funcionario.setEmail(this.resultSet.getString("email"));
                funcionario.setSenha(this.resultSet.getString("senha"));
                funcionario.setCargo(this.resultSet.getString("cargo"));
                funcionario.setSalario(this.resultSet.getFloat("salario"));
                funcionario.setTelefone(this.resultSet.getString("telefone"));
                funcionario.setNome_funcionario(this.resultSet.getString("nome_funcionario"));

                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionarios;
    }

    public Funcionario setUser(Funcionario funcionario) {
        try (Connection connection = new ConectaDBPostgres().getConexao()) {
            this.sql = "INSERT INTO Funcionario (email,senha,cargo,salario,telefone,nome_funcionario) VALUES (?, ?, ?,?,?,?)";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setString(1, funcionario.getEmail());

        //    funcionario.setSenha(new BCryptPasswordEncoder().encode(this.resultSet.getString("senha")));

            this.preparedStatement.setString(2, funcionario.getSenha());
         //   String senhaCriptografada = new BCryptPasswordEncoder().encode(funcionario.getSenha());
          //  this.preparedStatement.setString(2, senhaCriptografada);
            this.preparedStatement.setString(3, funcionario.getCargo());
            this.preparedStatement.setFloat(4, funcionario.getSalario());
            this.preparedStatement.setString(5, funcionario.getTelefone());
            this.preparedStatement.setString(6, funcionario.getNome_funcionario());

            this.preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionario;
    }

    public Funcionario getUser(String email) {
        try (Connection connection = new ConectaDBPostgres().getConexao()){
            this.sql = "SELECT * FROM Funcionario WHERE email = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, email);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){
                Funcionario funcionario = new Funcionario();
                funcionario.setId_func(this.resultSet.getInt("id_func"));
                funcionario.setEmail(this.resultSet.getString("email"));
                funcionario.setSenha(new BCryptPasswordEncoder().encode(this.resultSet.getString("senha")));
                funcionario.setCargo(this.resultSet.getString("cargo"));
                funcionario.setSalario(this.resultSet.getFloat("salario"));
                funcionario.setTelefone(this.resultSet.getString("telefone"));
                funcionario.setNome_funcionario(this.resultSet.getString("nome_funcionario"));

                return funcionario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public  ArrayList<Funcionario> getUsuario(String email) {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        try (Connection connection = new ConectaDBPostgres().getConexao()){
            this.sql = "SELECT * FROM Funcionario WHERE email = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, email);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){
                Funcionario funcionario = new Funcionario();
                funcionario.setId_func(this.resultSet.getInt("id_func"));
                funcionario.setEmail(this.resultSet.getString("email"));
                funcionario.setSenha(new BCryptPasswordEncoder().encode(this.resultSet.getString("senha")));
                funcionario.setCargo(this.resultSet.getString("cargo"));
                funcionario.setSalario(this.resultSet.getFloat("salario"));
                funcionario.setTelefone(this.resultSet.getString("telefone"));
                funcionario.setNome_funcionario(this.resultSet.getString("nome_funcionario"));
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionarios;
    }

    public Funcionario getUsuariobyemail(String email) {
        try (Connection connection = new ConectaDBPostgres().getConexao()){
            this.sql = "SELECT * FROM Funcionario WHERE email = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, email);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){
                Funcionario funcionario = new Funcionario();
                funcionario.setId_func(this.resultSet.getInt("id_func"));
                funcionario.setEmail(this.resultSet.getString("email"));
                funcionario.setSenha(new BCryptPasswordEncoder().encode(this.resultSet.getString("senha")));
                funcionario.setCargo(this.resultSet.getString("cargo"));
                funcionario.setSalario(this.resultSet.getFloat("salario"));
                funcionario.setTelefone(this.resultSet.getString("telefone"));
                funcionario.setNome_funcionario(this.resultSet.getString("nome_funcionario"));
                return funcionario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       return null;
    }

    public Funcionario editar(Funcionario funcionario) {
        System.out.println("vindo do controller:");
        System.out.println(funcionario.getNome_funcionario());
        System.out.println(funcionario.getEmail());
        try (Connection connection = new ConectaDBPostgres().getConexao()) {
            this.sql = "update Funcionario set email = ?, senha = ?, cargo = ?,salario = ?," +
                    "telefone=?,nome_funcionario=? where id_func = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, funcionario.getEmail());
            this.preparedStatement.setString(2, funcionario.getSenha());
            this.preparedStatement.setString(3, funcionario.getCargo());
            this.preparedStatement.setFloat(4, funcionario.getSalario());
            this.preparedStatement.setString(5, funcionario.getTelefone());
            this.preparedStatement.setString(6, funcionario.getNome_funcionario());
            this.preparedStatement.setInt(7, funcionario.getId_func());
            this.preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionario;
    }

    public Funcionario excluir(String email) {
        try (Connection connection = new ConectaDBPostgres().getConexao()) {

            this.sql = "delete from Funcionario where email = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, email);
            this.preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
