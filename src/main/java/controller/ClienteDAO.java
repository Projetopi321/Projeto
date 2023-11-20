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
public class ClienteDAO {

    Connection connection;
    PreparedStatement ps;
    ResultSet rs;
    ArrayList<ClienteBean> lista = new ArrayList<>();
    boolean resultado;

    public void salvar(ClienteBean cli) {
        String sql = "INSERT INTO cliente (login,senha,nome,endereco,uf,telefone,cpf,email) VALUES (?,?,?,?,?,?,?,?)";
        try {
            connection = new ConnectionFactory().getConnection();
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

    public boolean verificaLogin(ClienteBean cli) {

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
                clDTO.setLogin(rs.getString("login"));
                clDTO.setSenha(rs.getString("senha"));
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
}
