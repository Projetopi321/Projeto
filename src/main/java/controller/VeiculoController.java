package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.VeiculoBean;
import util.ConnectionFactory;

/**
 *
 * @author Matheus
 */
public class VeiculoController {
    
    Connection connection;
    PreparedStatement ps;
    ResultSet rs;
    ArrayList<VeiculoBean> lista = new ArrayList<>();
    boolean resultado;

    public void salvar(VeiculoBean v) {
        String sql = "INSERT INTO veiculo (placa,fabricante,modelo,anoModelo,qtdPortas,acessorios) VALUES (?,?,?,?,?,?)";
        try {
            connection = new ConnectionFactory().getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, v.getPlaca());
            ps.setString(2, v.getFabricante());
            ps.setString(3, v.getModelo());
            ps.setInt(4, v.getAnoModelo());
            ps.setInt(5, v.getQtdPortas());
            ps.setString(6, v.getAcessorios());

            ps.execute();
            ps.close();

        } catch (Exception e) {
            System.out.println("Erro ao Salvar Veiculo" + e);
        }

    }

    public ArrayList<VeiculoBean> obterVeiculos() {
        connection = new ConnectionFactory().getConnection(); 

        String url = "SELECT * FROM veiculo ORDER BY modelo"; 

        try {
            ps = connection.prepareStatement(url); 
            rs = ps.executeQuery();

            lista = new ArrayList<>();    
            while (rs.next()) {
                VeiculoBean vDTO = new VeiculoBean();
                vDTO.setId(rs.getInt("id"));
                vDTO.setPlaca(rs.getString("placa"));
                vDTO.setFabricante(rs.getString("fabricante"));
                vDTO.setModelo(rs.getString("modelo"));
                vDTO.setAnoModelo(rs.getInt("anoModelo"));      
                vDTO.setQtdPortas(rs.getInt("qtdPortas"));
                vDTO.setAcessorios(rs.getString("acessorios"));
                
                lista.add(vDTO);   
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar Veiculos");
        } finally {
            ConnectionFactory.closeConnection(connection, ps, rs);
        }
        return lista;  
    }
    
    public void update(VeiculoBean veiculo) {
        
        String sql = "UPDATE veiculo SET  placa = ?, fabricante = ?, modelo = ?, anoModelo = ?, qtdPortas = ?, acessorios = ? WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql); 
            
            statement.setString(1, veiculo.getPlaca());
            statement.setString(2, veiculo.getFabricante());
            statement.setString(3, veiculo.getModelo());
            statement.setInt(4, veiculo.getAnoModelo());
            statement.setInt(5, veiculo.getQtdPortas());
            statement.setString(6, veiculo.getAcessorios());
            statement.setInt(7, veiculo.getId());
            
            statement.execute();
            
        } catch (Exception ex) {
            
            throw new RuntimeException("Erro ao atualizar veiculo");
        } finally {
             
             ConnectionFactory.closeConnection(connection, statement);
             
         }     
    }
        
     public void removeById (int Id) {
        
         String sql = "DELETE FROM veiculo WHERE id = ?";
         
         Connection connection = null;
         PreparedStatement statement = null;
         
         try {

             connection = ConnectionFactory.getConnection();
             statement = connection.prepareStatement(sql);
             
             statement.setInt(1, Id);
             statement.execute();

         } catch (Exception ex) {
             
             throw new RuntimeException("Erro ao deletar Veiculo");
             
         } finally {
             
             ConnectionFactory.closeConnection(connection, statement);   
         }           
    } 
     
        public List<VeiculoBean> getAll() {
              
        String sql = "SELECT * FROM veiculo";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        List<VeiculoBean> veiculos = new ArrayList<VeiculoBean>();
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
        while(resultSet.next()) {
            
            VeiculoBean veiculo = new VeiculoBean();
            
            
            veiculo.setId(resultSet.getInt("id"));
            veiculo.setPlaca(resultSet.getString("placa"));
            veiculo.setFabricante(resultSet.getString("fabricante"));
            veiculo.setModelo(resultSet.getString("modelo"));
            veiculo.setAnoModelo(resultSet.getInt("anoModelo"));
            veiculo.setQtdPortas(resultSet.getInt("qtdPortas"));
            veiculo.setAcessorios(resultSet.getString("acessorios"));

            veiculos.add(veiculo);            
        }    
            
        } catch (Exception ex) {
             throw new RuntimeException("Erro ao trazer veiculos " + ex.getMessage() + ex );            
        } finally {           
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }      
        return veiculos;
    }  
        
        public int getIdByModelo(String modelo) {
              
        String sql = "SELECT id FROM veiculo WHERE modelo = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        int id = 0;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, modelo);
            resultSet = statement.executeQuery();
            
        while(resultSet.next()) {
            
            id = resultSet.getInt("id"); 
            
        }    
            
        } catch (Exception ex) {
             throw new RuntimeException("Erro ao trazer veiculos " + ex.getMessage() + ex );            
        } finally {           
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }   
        
        return id;
    }  
    
       public void getAllModelosAlugados() {
              
        String sql = "SELECT modelo FROM Veiculo V Left Join Aluguel A On V.id = A.idVeiculo WHERE a.idVeiculo is Null";
        
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
       
       public void getAllModelos() {
              
        String sql = "SELECT modelo FROM Veiculo V Left Join Aluguel A On V.id = A.idVeiculo";
        
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
        
    
     public VeiculoBean getById(int Id) {
         
        String sql = "SELECT * FROM veiculo where id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        VeiculoBean veiculoEx = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Id);
            resultSet = statement.executeQuery();
            
        while(resultSet.next()) { 
            
            VeiculoBean veiculo = new VeiculoBean();
            
            veiculo.setId(resultSet.getInt("id"));
            veiculo.setPlaca(resultSet.getString("placa"));
            veiculo.setFabricante(resultSet.getString("fabricante"));
            veiculo.setModelo(resultSet.getString("modelo"));
            veiculo.setAnoModelo(resultSet.getInt("anoModelo"));
            veiculo.setQtdPortas(resultSet.getInt("qtdPortas"));
            veiculo.setAcessorios(resultSet.getString("acessorios"));       
            
           veiculoEx = veiculo;
        }    
            
        } catch (Exception ex) {
             throw new RuntimeException("Erro ao trazer veiculo " + ex.getMessage() + ex );            
        } finally {           
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        } 

        return veiculoEx;          
     }
     
    public List<VeiculoBean> getAllByModelo(String modelo) {
              
        String sql = "SELECT * FROM veiculo where modelo LIKE ? || fabricante LIKE ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        List<VeiculoBean> veiculos = new ArrayList<VeiculoBean>();
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, modelo + "%");
            statement.setString(2, modelo + "%");
            resultSet = statement.executeQuery();
            
        while(resultSet.next()) {
            
            VeiculoBean veiculo = new VeiculoBean();
            
            
            veiculo.setId(resultSet.getInt("id"));
            veiculo.setPlaca(resultSet.getString("placa"));
            veiculo.setFabricante(resultSet.getString("fabricante"));
            veiculo.setModelo(resultSet.getString("modelo"));
            veiculo.setAnoModelo(resultSet.getInt("anoModelo"));
            veiculo.setQtdPortas(resultSet.getInt("qtdPortas"));
            veiculo.setAcessorios(resultSet.getString("acessorios"));

            veiculos.add(veiculo);            
        }    
            
        } catch (Exception ex) {
             throw new RuntimeException("Erro ao trazer veiculos " + ex.getMessage() + ex );            
        } finally {           
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }      
        return veiculos;
    }    
    
    public boolean verificarVeiculoAlugado(VeiculoBean veiculo){
        
        String sql = "SELECT * FROM Veiculo V Join Aluguel A On ? = A.idVeiculo Where a.entregue = 'Não'";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, veiculo.getId());
            resultSet = statement.executeQuery();
            
        return resultSet.next() == true;
    
        } catch (Exception ex) {
             throw new RuntimeException("Erro ao verificar Veiculo " + ex.getMessage() + ex );            
        } finally {           
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }      
    } 
}
