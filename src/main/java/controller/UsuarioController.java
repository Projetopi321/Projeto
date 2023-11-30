/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.UsuarioBean;
import util.ConnectionFactory;

/**
 *
 * @author User
 */
public class UsuarioController {
    
    Connection connection;
    PreparedStatement ps;
    ResultSet rs;
    ArrayList<UsuarioBean> lista = new ArrayList<>();
    boolean resultado;
    
    public ArrayList<UsuarioBean> obterUsuarios() {
        connection = new ConnectionFactory().getConnection(); // CRIANDO A CONEXÃO

        String url = "SELECT * FROM usuario ORDER BY login"; // STRING DA CONSULTA SQL

        try {
            ps = connection.prepareStatement(url); 
            rs = ps.executeQuery();

            lista = new ArrayList<>();    //CRIANDO UMA NOVA LISTA
            while (rs.next()) {
                UsuarioBean usDTO = new UsuarioBean();
                            usDTO.setId(rs.getInt("id"));
            usDTO.setNome(rs.getString("nome"));
            usDTO.setCargo(rs.getString("cargo"));
            usDTO.setLogin(rs.getString("login"));
            usDTO.setSenha(rs.getString("senha"));
            usDTO.setEmail(rs.getString("email"));

                lista.add(usDTO);   //ADICIONANDO CADA LINHA QUE RETORNA DO BANCO DE DADOS
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar Usuarios");
        } finally {
            ConnectionFactory.closeConnection(connection, ps, rs);
        }
        return lista;  //RETORNANDO A LISTA PARA A CLASSE BEANS.
    }
    public UsuarioBean getById(int Id) {
         
        String sql = "SELECT * FROM usuario where id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        UsuarioBean usuarioEx = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Id);
            resultSet = statement.executeQuery();
            
        while(resultSet.next()) { 
            
            UsuarioBean cliente = new UsuarioBean();
            
            cliente.setId(resultSet.getInt("id"));
            cliente.setCargo(resultSet.getString("cargo"));            
            cliente.setNome(resultSet.getString("nome"));            
            cliente.setLogin(resultSet.getString("login"));            
            cliente.setSenha(resultSet.getString("senha"));            
            cliente.setEmail(resultSet.getString("email"));        
            
            usuarioEx = cliente;
        }    
            
        } catch (Exception ex) {
             throw new RuntimeException("Erro ao trazer usuario  " + ex.getMessage() + ex );            
        } finally {           
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        } 

        return usuarioEx;          
     }
     public int getIdByLogin(String login) {
              
        String sql = "SELECT id FROM usuario WHERE login = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        int id = 0;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            
        while(resultSet.next()) {
            
            id = resultSet.getInt("id"); 
            
        }    
            
        } catch (Exception ex) {
             throw new RuntimeException("Erro ao trazer id usuario " + ex.getMessage() + ex );            
        } finally {           
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }   
        
        return id;
    }  
    

public boolean verificaLogin(UsuarioBean user) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            connection = new ConnectionFactory().getConnection();

            String sql = "SELECT login FROM usuario WHERE login = ? And senha = ?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getSenha());
            
            System.out.println("TESTE LOGIN E SENHA" + user.getLogin() );
            System.out.println("TESTE LOGIN E SENHA" + user.getSenha() );
            
            resultSet = statement.executeQuery();
            
            

            if (resultSet.next()) {
               resultado = true;
            } else if(!resultSet.next()){
                resultado = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro de Login");
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
        return resultado;
    }
    
    public boolean save(UsuarioBean usuario) {
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = null;
        
        try {
          
        connection = ConnectionFactory.getConnection();
        
        sql = "Select login from usuario where login = ? or email = ?";    
             
        statement = connection.prepareStatement(sql);        
        statement.setString(1, usuario.getLogin());
        statement.setString(2, usuario.getEmail());
        resultSet = statement.executeQuery();  
        
        
        if (resultSet.next()) {
        
        return false; 
        
        } else {
         
        sql = "INSERT INTO usuario (nome, cargo, login, senha, email) VALUES (?, ?, ?, ?, ?)";    
            
        statement = connection.prepareStatement(sql); 
            
        statement.setString(1, usuario.getNome());
        statement.setString(2, usuario.getCargo());
        statement.setString(3, usuario.getLogin());
        statement.setString(4, usuario.getSenha());
        statement.setString(5, usuario.getEmail());
        
        statement.execute();
        
        return true;
        
        }
        
        } catch (SQLException ex) {
            
            throw new RuntimeException("Erro ao salvar Usuario");
            
        } finally {
            
            ConnectionFactory.closeConnection(connection, statement, resultSet);

        }
    }
       
     public void update(UsuarioBean usuario) {
        
        String sql = "UPDATE usuario SET nome = ?, cargo = ?, login = ?, senha = ?, email = ? WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getCargo());
            statement.setString(3, usuario.getLogin());
            statement.setString(4, usuario.getSenha());
            statement.setString(5, usuario.getEmail());
            statement.setInt(6, usuario.getId());
            
            statement.execute();
            
        } catch (Exception ex) {
            
            throw new RuntimeException("Erro ao atualizar Usuario");
        }     
    }
        
     public void removeById (int Id) {
        
         String sql = "DELETE FROM usuario WHERE id = ?";
         
         Connection connection = null;
         PreparedStatement statement = null;
         
         try {
             
             connection = ConnectionFactory.getConnection();
             statement = connection.prepareStatement(sql);
             
             statement.setInt(1, Id);
             statement.execute();
             
             
         } catch (Exception ex) {
             
             throw new RuntimeException("Erro ao deletar Usuario");
             
         } finally {
             
             ConnectionFactory.closeConnection(connection, statement);
             
         }           
    }
     
        public List<UsuarioBean> getAll() {
              
        String sql = "SELECT * FROM usuario where nome != 'admin'";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        java.util.List<UsuarioBean> usuarios = new ArrayList<UsuarioBean>();
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
        while(resultSet.next()) {
            
            UsuarioBean usuario = new UsuarioBean();

            usuario.setId(resultSet.getInt("id"));
            usuario.setNome(resultSet.getString("nome"));
            usuario.setCargo(resultSet.getString("cargo"));
            usuario.setLogin(resultSet.getString("login"));
            usuario.setSenha(resultSet.getString("senha"));
            usuario.setEmail(resultSet.getString("email"));

            usuarios.add(usuario);            
        }    
            
        } catch (Exception ex) {
             throw new RuntimeException("Erro ao trazer usuarios " + ex.getMessage() + ex );            
        } finally {           
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }      
        return usuarios;
    }    
     
    public List<UsuarioBean> getAllByNome(String data) {
              
        String sql = "SELECT * FROM usuario where nome LIKE ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        java.util.List<UsuarioBean> usuarios = new ArrayList<UsuarioBean>();
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, data + "%");
            resultSet = statement.executeQuery();
            
        while(resultSet.next()) {
            
            UsuarioBean usuario = new UsuarioBean();
            
            
            usuario.setId(resultSet.getInt("id"));
            usuario.setNome(resultSet.getString("nome"));
            usuario.setCargo(resultSet.getString("cargo"));
            usuario.setLogin(resultSet.getString("login"));
            usuario.setSenha(resultSet.getString("senha"));
            usuario.setEmail(resultSet.getString("email"));

            usuarios.add(usuario);            
        }    
            
        } catch (Exception ex) {
             throw new RuntimeException("Erro ao trazer veiculos " + ex.getMessage() + ex );            
        } finally {           
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }      
        return usuarios;
    }  
    
}