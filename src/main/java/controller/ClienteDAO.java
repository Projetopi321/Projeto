package controller;

import model.ClienteBean;
import util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author PAULO
 */
public class ClienteDAO {
    Connection connection;    
    public void salvar(ClienteBean cli){
        String sql = "INSERT INTO cliente (login,senha,nome,endereco,uf,telefone,cpf,email) VALUES (?,?,?,?,?,?,?,?)";
        connection = new ConnectionFactory().getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, cli.getLogin());
            ps.setString(2, cli.getSenha());
            ps.setString(3, cli.getNome());
            ps.setString(4, cli.getEndereco());
            ps.setString(5, cli.getUf());
            ps.setString(6, cli.getTelefone());
            ps.setString(7, cli.getCpf());
            ps.setString(8, cli.getEmail());
            
            ps.execute();
            ps.close();
                    
            
        } catch (Exception e) {
            System.out.println("ERRO" + e);
        }
        
    }
    
     public boolean verificaLogin(ClienteBean cli)  {
               
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {  
            
         connection = new ConnectionFactory().getConnection();

         String sql = "SELECT login FROM cliente WHERE login = ? And senha = ?";
            
         statement = connection.prepareStatement(sql);
         statement.setString(1, cli.getLogin());
         statement.setString(2, cli.getSenha());
         resultSet = statement.executeQuery();

        return !!resultSet.next();
  
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro de Login");   
        } finally {        
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
    }
}
