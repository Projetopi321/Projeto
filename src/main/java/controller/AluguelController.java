package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.AluguelBean;
import util.ConnectionFactory;

/**
 *
 * @author Matheus
 */
public class AluguelController {

    Connection connection;
    PreparedStatement ps;
    ResultSet rs;
    ArrayList<AluguelBean> lista = new ArrayList<>();
    boolean resultado;

    public boolean salvar(AluguelBean aluguel) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = null;

        try {

            connection = ConnectionFactory.getConnection();

            sql = "SELECT cpf FROM cliente WHERE cpf = ?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, aluguel.getCliente().getCpf());

            resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return false;

            } else {

                sql = "INSERT INTO aluguel (idVeiculo, dataAluguel, dataEntrega, idCliente, entregue, observacao, valorPago, cpfCliente, nomeCliente, placaVeiculo) VALUES (?,?,?,?,?,?,?,?,?,?)";

                statement = connection.prepareStatement(sql);

                DateFormat formatoSaida = new SimpleDateFormat("yyyy-MM-dd");

                String dataConvertida = formatoSaida.format(aluguel.getDataAluguel());
                String dataConvertida2 = formatoSaida.format(aluguel.getDataEntrega());

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                java.util.Date dataFormatada = null;
                java.util.Date dataFormatada2 = null;

                try {
                    dataFormatada = sdf.parse(dataConvertida);
                    dataFormatada2 = sdf.parse(dataConvertida2);
                } catch (ParseException ex) {
                    System.out.println("Erro ao Converter a data");
                }

                statement.setInt(1, aluguel.getVeiculo().getId());
                statement.setDate(2, new java.sql.Date(dataFormatada.getTime()));
                statement.setDate(3, new java.sql.Date(dataFormatada2.getTime()));
                statement.setInt(4, aluguel.getCliente().getId());
                statement.setString(5, aluguel.getEntregue());
                statement.setString(6, aluguel.getObservacao());
                statement.setFloat(7, aluguel.getValorPago());
                statement.setString(8, aluguel.getCpfCliente());
                statement.setString(9, aluguel.getNomeCliente());
                statement.setString(10, aluguel.getPlacaVeiculo());

                statement.execute();

                return true;
            }
        } catch (SQLException ex) {

            throw new RuntimeException("Erro ao salvar Aluguel");

        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
    }

    public void update(AluguelBean aluguel) {

        String sql = "UPDATE aluguel SET idVeiculo = ?, dataAluguel = ?, dataEntrega = ?, idCliente = ?, entregue = ?, observacao = ? , valorPago = ? WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            DateFormat formatoSaida = new SimpleDateFormat("yyyy-MM-dd");

            String dataConvertida = formatoSaida.format(aluguel.getDataAluguel());
            String dataConvertida2 = formatoSaida.format(aluguel.getDataEntrega());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            java.util.Date dataFormatada = null;
            java.util.Date dataFormatada2 = null;

            try {
                dataFormatada = sdf.parse(dataConvertida);
                dataFormatada2 = sdf.parse(dataConvertida2);
            } catch (ParseException ex) {
                System.out.println("Erro ao converter data");
            }

            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setInt(1, aluguel.getVeiculo().getId());
            statement.setDate(2, new java.sql.Date(dataFormatada.getTime()));
            statement.setDate(3, new java.sql.Date(dataFormatada2.getTime()));
            statement.setInt(4, aluguel.getCliente().getId());
            statement.setString(5, aluguel.getEntregue());
            statement.setString(6, aluguel.getObservacao());
            statement.setFloat(7, aluguel.getValorPago());
            statement.setInt(8, aluguel.getId());

            statement.execute();

        } catch (Exception ex) {

            throw new RuntimeException("Erro ao atualizar Aluguel");
        }
    }

    public ArrayList<AluguelBean> obterAlugueis() throws ParseException {
        connection = new ConnectionFactory().getConnection(); // CRIANDO A CONEXÃO
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String url = "SELECT * FROM aluguel"; // STRING DA CONSULTA SQL

        try {
            ps = connection.prepareStatement(url);
            rs = ps.executeQuery();

            lista = new ArrayList<>();    //CRIANDO UMA NOVA LISTA
            while (rs.next()) {
                AluguelBean algBean = new AluguelBean();

                ClienteController clienteController = new ClienteController();
                VeiculoController veiculoController = new VeiculoController();

                algBean.getCliente().setNome(clienteController.getById(rs.getInt("idCliente")).getNome());
                algBean.setId(rs.getInt("idAluguel"));
                String dataAluguelA = sdf.format(rs.getDate("dataAluguel"));
                String dataEntregaA = sdf.format(rs.getDate("dataAluguel"));
                algBean.setDataAluguelString(dataAluguelA);
                algBean.setDataEntregaString(dataEntregaA);
                algBean.getVeiculo().setPlaca(rs.getString("placaVeiculo"));
                algBean.getCliente().setNome(rs.getString("nomeCliente"));
                algBean.setEntregue(rs.getString("entregue"));
                algBean.setObservacao(rs.getString("observacao"));
                algBean.setValorPago(rs.getFloat("valorPago"));

                lista.add(algBean);   //ADICIONANDO CADA LINHA QUE RETORNA DO BANCO DE DADOS
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar Alugueis");
        } finally {
            ConnectionFactory.closeConnection(connection, ps, rs);
        }
        return lista;  //RETORNANDO A LISTA PARA A CLASSE BEANS.
    }

    public void removeById(int Id) {

        String sql = "DELETE FROM aluguel WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setInt(1, Id);
            statement.execute();

        } catch (Exception ex) {

            throw new RuntimeException("Erro ao deletar Aluguel");

        } finally {

            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public List<AluguelBean> getAll() {

        String sql = "SELECT * FROM aluguel";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        List<AluguelBean> alugueis = new ArrayList<AluguelBean>();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                AluguelBean aluguel = new AluguelBean();
                ClienteController clienteController = new ClienteController();
                VeiculoController veiculoController = new VeiculoController();

                aluguel.getCliente().setNome(clienteController.getById(resultSet.getInt("idCliente")).getNome());
                aluguel.getVeiculo().setModelo(veiculoController.getById(resultSet.getInt("idVeiculo")).getModelo());

                aluguel.setId(resultSet.getInt("id"));
                aluguel.getVeiculo().setId(resultSet.getInt("idVeiculo"));
                aluguel.setDataAluguel((Date) resultSet.getDate("dataAluguel"));
                aluguel.setDataEntrega((Date) resultSet.getDate("dataEntrega"));
                aluguel.getCliente().setId(resultSet.getInt("idCliente"));
                aluguel.setEntregue(resultSet.getString("entregue"));
                aluguel.setObservacao(resultSet.getString("observacao"));
                aluguel.setValorPago(resultSet.getFloat("valorPago"));

                alugueis.add(aluguel);
            }

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao trazer Alugueis " + ex.getMessage() + ex);

        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
        return alugueis;
    }

    public List<AluguelBean> getAllByNome(String nome) {

        String sql = "SELECT * FROM Cliente C Join Aluguel A on C.id = A.idCliente where C.nomeCliente LIKE ?";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        VeiculoController veiculoController = new VeiculoController();

        List<AluguelBean> alugueis = new ArrayList<AluguelBean>();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, nome + "%");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                AluguelBean aluguel = new AluguelBean();

                aluguel.getVeiculo().setModelo(veiculoController.getById(resultSet.getInt("idVeiculo")).getModelo());

                aluguel.getCliente().setNome(resultSet.getString("C.nomeCliente"));
                aluguel.getVeiculo().setId(resultSet.getInt("idVeiculo"));
                aluguel.setDataAluguel((Date) resultSet.getDate("dataAluguel"));
                aluguel.setDataEntrega((Date) resultSet.getDate("dataEntrega"));
                aluguel.getCliente().setId(resultSet.getInt("idCliente"));
                aluguel.setEntregue(resultSet.getString("entregue"));
                aluguel.setObservacao(resultSet.getString("observacao"));
                aluguel.setValorPago(resultSet.getFloat("valorPago"));

                alugueis.add(aluguel);
            }

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao trazer Alugueis" + ex.getMessage() + ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
        return alugueis;
    }

    public List<AluguelBean> getAllByEntregue(String situacao) {

        String sql = "SELECT * FROM Cliente C Join Aluguel A on C.id = A.idCliente where A.entregue = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        VeiculoController veiculoController = new VeiculoController();

        List<AluguelBean> alugueis = new ArrayList<AluguelBean>();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, situacao);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                AluguelBean aluguel = new AluguelBean();

                aluguel.getVeiculo().setModelo(veiculoController.getById(resultSet.getInt("idVeiculo")).getModelo());
                aluguel.getCliente().setNome(resultSet.getString("C.nomeCliente"));
                aluguel.getVeiculo().setId(resultSet.getInt("idVeiculo"));
                aluguel.setDataAluguel((Date) resultSet.getDate("dataAluguel"));
                aluguel.setDataEntrega((Date) resultSet.getDate("dataEntrega"));
                aluguel.getCliente().setId(resultSet.getInt("idCliente"));
                aluguel.setEntregue(resultSet.getString("entregue"));
                aluguel.setObservacao(resultSet.getString("observacao"));
                aluguel.setValorPago(resultSet.getFloat("valorPago"));

                alugueis.add(aluguel);
            }

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao trazer Alugueis" + ex.getMessage() + ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
        return alugueis;
    }

    public float ExibirFaturamento(java.util.Date dataInicio, java.util.Date dataFim) {

        String sql = "SELECT sum(valorPago) FROM aluguel where dataAluguel >= ? AND dataAluguel <= ?";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setDate(1, new java.sql.Date(dataInicio.getTime()));
            statement.setDate(2, new java.sql.Date(dataFim.getTime()));
            resultSet = statement.executeQuery();

            float valor = 0f;

            while (resultSet.next()) {

                valor = resultSet.getFloat("sum(valorPago)");
            }

            return valor;
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao trazer faturamento" + ex.getMessage() + ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
    }
}
