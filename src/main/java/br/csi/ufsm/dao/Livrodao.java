package br.csi.ufsm.dao;

import br.csi.ufsm.model.Funcionario;
import br.csi.ufsm.model.Livro;


import java.sql.*;
import java.util.ArrayList;

public class Livrodao {

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

    public ArrayList<Livro> getLivros(){
        ArrayList<Livro> livros = new ArrayList<>();
        try (Connection connection = new ConectaDBPostgres().getConexao()){
            this.sql = "select id_liv,nome_livro,autor,editora,genero,num_paginas,data_lanc,estoque_liv,preco from Livro";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){
                Livro livro = new Livro();
                livro.setId_liv(this.resultSet.getInt("id_liv"));
                livro.setNome_livro(this.resultSet.getString("nome_livro"));
                livro.setAutor(this.resultSet.getString("autor"));
                livro.setEditora(this.resultSet.getString("editora"));
                livro.setGenero(this.resultSet.getString("genero"));
                livro.setNum_paginas(this.resultSet.getInt("num_paginas"));
                livro.setData_lanc(this.resultSet.getDate("data_lanc"));
                livro.setEstoque_liv(this.resultSet.getInt("estoque_liv"));
                livro.setPreco(this.resultSet.getFloat("preco"));

                livros.add(livro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livros;
    }

    public Livro setLivro(Livro livro) {
        try (Connection connection = new ConectaDBPostgres().getConexao()) {
            this.sql = "INSERT INTO Livro (nome_livro,autor,editora,genero,num_paginas,data_lanc,estoque_liv,preco) VALUES (?, ?, ?,?,?,?,?,?)";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setString(1, livro.getNome_livro());
            this.preparedStatement.setString(2, livro.getAutor());
            this.preparedStatement.setString(3, livro.getEditora());
            this.preparedStatement.setString(4,  livro.getGenero());
            this.preparedStatement.setInt(5, livro.getNum_paginas());
            this.preparedStatement.setDate(6,livro.getData_lanc());
            this.preparedStatement.setInt(7, livro.getEstoque_liv());
            this.preparedStatement.setFloat(8, livro.getPreco());

            this.preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livro;
    }
    public Livro editar(Livro livro) {
        System.out.println("vindo do controller:");
        System.out.println(livro.getNome_livro());
        System.out.println(livro.getAutor());
        try (Connection connection = new ConectaDBPostgres().getConexao()) {
            this.sql = "update Livro set nome_livro = ?, autor = ?, editora = ?,genero = ?," +
                    "num_paginas=?,data_lanc=?,estoque_liv=?, preco=? where id_liv = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, livro.getNome_livro());
            this.preparedStatement.setString(2, livro.getAutor());
            this.preparedStatement.setString(3, livro.getEditora());
            this.preparedStatement.setString(4, livro.getGenero());
            this.preparedStatement.setInt(5, livro.getNum_paginas());
            this.preparedStatement.setDate(6, livro.getData_lanc());
            this.preparedStatement.setInt(7, livro.getEstoque_liv());
            this.preparedStatement.setFloat(8, livro.getPreco());
            this.preparedStatement.setInt(9, livro.getId_liv());
            this.preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livro;
    }

    public Livro excluir(Integer id_liv) {
        try (Connection connection = new ConectaDBPostgres().getConexao()) {

            this.sql = "delete from Livro where id_liv = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id_liv);
            this.preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public void diminuiquantidade(int q) {
//        try (Connection connection = new ConectaDBPostgres().getConexao()) {
//            String sql = "UPDATE Livro SET estoque_liv = estoque_liv - ? WHERE id = ?";
//
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, q);
//            preparedStatement.setInt(2, livro.getId()); // Substitua "livro.getId()" pelo ID do livro que deseja atualizar
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public Livro diminuiquantidade(int total,int id) {
        try (Connection connection = new ConectaDBPostgres().getConexao()) {
            String sql = "UPDATE Livro SET estoque_liv = ? WHERE id_liv = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, total);
            preparedStatement.setInt(2, id); // Substitua "livro.getId()" pelo ID do livro que deseja atualizar

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
