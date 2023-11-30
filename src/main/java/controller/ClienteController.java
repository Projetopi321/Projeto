package controller;

import model.ClienteBean;
import util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PAULO
 */
public class ClienteController {

    Connection connection;
    PreparedStatement ps;
    ResultSet rs;
    ArrayList<ClienteBean> lista = new ArrayList<>();
    boolean resultado;

    public void salvar(ClienteBean cli) {
        String sql = "INSERT INTO cliente (nome,endereco,uf,telefone,cpf,email) VALUES (?,?,?,?,?,?)";
        try {
            connection = new ConnectionFactory().getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
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

    public ArrayList<ClienteBean> obterClientes() {
        connection = new ConnectionFactory().getConnection(); // CRIANDO A CONEXÃO

        String url = "SELECT * FROM cliente ORDER BY nome"; // STRING DA CONSULTA SQL

        try {
            ps = connection.prepareStatement(url); 
            rs = ps.executeQuery();

            lista = new ArrayList<>();    //CRIANDO UMA NOVA LISTA
            while (rs.next()) {
                ClienteBean clDTO = new ClienteBean();
                clDTO.setId(rs.getInt("id"));
                clDTO.setNome(rs.getString("nome"));      // CRIANDO O OBJETO QUE RETORNA DO BANCO DE DADOS
                clDTO.setEndereco(rs.getString("endereco"));
                clDTO.setUf(rs.getString("uf"));
                clDTO.setTelefone(rs.getString("telefone"));
                clDTO.setCpf(rs.getString("cpf"));
                clDTO.setEmail(rs.getString("email"));

                lista.add(clDTO);   //ADICIONANDO CADA LINHA QUE RETORNA DO BANCO DE DADOS
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar Clientes");
        } finally {
            ConnectionFactory.closeConnection(connection, ps, rs);
        }
        return lista;  //RETORNANDO A LISTA PARA A CLASSE BEANS.
    }
    
     public void update(ClienteBean cliente) {
        
        String sql = "UPDATE cliente SET  nome = ?, endereco = ?, uf = ?, telefone = ?, cpf = ?, email = ? WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
            statement.setString(3, cliente.getNome());
            statement.setString(4, cliente.getEndereco());
            statement.setString(5, cliente.getUf());
            statement.setString(6, cliente.getTelefone());
            statement.setString(7, cliente.getCpf());
            statement.setString(8, cliente.getEmail());
            statement.setInt(9, cliente.getId());
            
            statement.execute();
            
        } catch (Exception ex) {
            
            throw new RuntimeException("Erro ao atualizar Cliente");
        }     
    }
           
     public void removeById (int Id) {
        
         String sql = "DELETE FROM cliente WHERE id = ?";
         
         Connection connection = null;
         PreparedStatement statement = null;
         
         try {
             
             connection = ConnectionFactory.getConnection();
             statement = connection.prepareStatement(sql);
             
             statement.setInt(1, Id);
             statement.execute();
                
         } catch (Exception ex) {
             
             throw new RuntimeException("Erro ao deletar Cliente");
             
         } finally {
             
             ConnectionFactory.closeConnection(connection, statement);
             
         }    
    } 
     
     public ClienteBean getById(int Id) {
         
        String sql = "SELECT * FROM cliente where id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        ClienteBean clienteEx = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Id);
            resultSet = statement.executeQuery();
            
        while(resultSet.next()) { 
            
            ClienteBean cliente = new ClienteBean();
            
            cliente.setId(resultSet.getInt("id"));
            cliente.setNome(resultSet.getString("nome"));
            cliente.setEndereco(resultSet.getString("endereco"));
            cliente.setUf(resultSet.getString("uf"));
            cliente.setTelefone(resultSet.getString("telefone"));
            cliente.setCpf(resultSet.getString("cpf"));
            cliente.setEmail(resultSet.getString("email"));        
            
            clienteEx = cliente;
        }    
            
        } catch (Exception ex) {
             throw new RuntimeException("Erro ao trazer cliente " + ex.getMessage() + ex );            
        } finally {           
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        } 

        return clienteEx;          
     }
     

     public List<ClienteBean> getAll() {
              
        String sql = "SELECT * FROM cliente";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        List<ClienteBean> clientes = new ArrayList<ClienteBean>();
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
        while(resultSet.next()) {
            
            ClienteBean cliente = new ClienteBean();
            
            cliente.setId(resultSet.getInt("id"));
            cliente.setNome(resultSet.getString("nomeCliente"));
            cliente.setEndereco(resultSet.getString("endereco"));
            cliente.setUf(resultSet.getString("uf"));
            cliente.setTelefone(resultSet.getString("telefone"));
            cliente.setCpf(resultSet.getString("cpf"));
            cliente.setEmail(resultSet.getString("email"));

            clientes.add(cliente);            
        }    
            
        } catch (Exception ex) {
             throw new RuntimeException("Erro ao trazer clientes " + ex.getMessage() + ex );            
        } finally {           
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }      
        return clientes;
    }   
     
      public int getIdByName(String nome) {
              
        String sql = "SELECT id FROM cliente WHERE nomeCliente = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        int id = 0;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, nome);
            resultSet = statement.executeQuery();
            
        while(resultSet.next()) {
            
            id = resultSet.getInt("id"); 
            
        }    
            
        } catch (Exception ex) {
             throw new RuntimeException("Erro ao trazer clientes " + ex.getMessage() + ex );            
        } finally {           
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }   
        
        return id;
    }  
      
        public int getIdByCpf(String cpf) {
              
        String sql = "SELECT id FROM cliente WHERE cpf = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        int id = 0;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, cpf);
            resultSet = statement.executeQuery();
            
        while(resultSet.next()) {
            
            id = resultSet.getInt("id"); 
            
        }    
            
        } catch (Exception ex) {
             throw new RuntimeException("Erro ao trazer clientes " + ex.getMessage() + ex );            
        } finally {           
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }   
        
        return id;
    }  
     
        public void getAllNames() {
              
        String sql = "SELECT nomeCliente FROM cliente";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
        while(resultSet.next()) {
                              
        }    
            
        } catch (Exception ex) {
             throw new RuntimeException("Erro ao trazer veiculos para o comboBox " + ex.getMessage() + ex );            
        } finally {           
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }      
    }   
     
     public List<ClienteBean> getAllByCpfOrName(String data) {
              
        String sql = "SELECT * FROM cliente where cpf LIKE ? || nomeCliente LIKE ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        List<ClienteBean> clientes = new ArrayList<ClienteBean>();
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, data + "%" );
            statement.setString(2, data + "%");
            resultSet = statement.executeQuery();
            
        while(resultSet.next()) {
            
            ClienteBean cliente = new ClienteBean();
            
            cliente.setId(resultSet.getInt("id"));
            cliente.setNome(resultSet.getString("nomeCliente"));
            cliente.setEndereco(resultSet.getString("endereco"));
            cliente.setUf(resultSet.getString("uf"));
            cliente.setTelefone(resultSet.getString("telefone"));
            cliente.setCpf(resultSet.getString("cpf"));
            cliente.setEmail(resultSet.getString("email"));

            clientes.add(cliente);            
        }    
            
        } catch (Exception ex) {
             throw new RuntimeException("Erro ao trazer clientes " + ex.getMessage() + ex );            
        } finally {           
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }      
        return clientes;
    }   
    
}
