package model;

import java.util.Date;
import controller.AluguelController;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Matheus
 */
@ManagedBean
@ViewScoped
public class AluguelBean implements Serializable {

    int id;
    int idVeiculo;
    int idCliente;
    float valorPago;
    String dataAluguelString;
    String dataEntregaString;
    String placaVeiculo;
    String cpfCliente;
    String nomeCliente;
    Date dataAluguel;
    Date dataEntrega;
    String entregue;
    String observacao;
    VeiculoBean veiculo;
    ClienteBean cliente;
    

    ArrayList<AluguelBean> lista = new ArrayList<>();
    private AluguelBean itemSelecionado;

    public AluguelBean() {
        this.veiculo = new VeiculoBean();
        this.cliente = new ClienteBean();
    }

    AluguelBean alugB;
    AluguelController aluguelController = new AluguelController();

    public void inserir() {
        alugB = new AluguelBean();

        String dataString = dataAluguelString;
        String dataString2 = dataEntregaString;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dataFormatada = null;
        Date dataFormatada2 = null;

        try {
            dataFormatada = sdf.parse(dataString);
            dataFormatada2 = sdf.parse(dataString2);
        } catch (ParseException ex) {
            System.out.println("ERRO AO CONVERTER A DATA");
        }

        alugB.setId(id);
        alugB.setVeiculo(veiculo);
        alugB.setCliente(cliente);
        alugB.setDataAluguel(dataFormatada);
        alugB.setDataEntrega(dataFormatada2);
        alugB.setEntregue(entregue);
        alugB.setObservacao(observacao);
        alugB.setValorPago(valorPago);
        alugB.setCpfCliente(cpfCliente);
        alugB.setPlacaVeiculo(placaVeiculo);
        alugB.setNomeCliente(nomeCliente);
        alugB.setIdCliente(cliente.getId());
        alugB.setIdVeiculo(veiculo.getId());

        alugB.getVeiculo().setId(getIdVeiculo());
        alugB.getCliente().setId(getIdCliente());

        alugB.setDataEntregaString(dataEntregaString);
        alugB.setDataAluguelString(dataAluguelString);

        aluguelController.salvar(alugB);
    }
    public void deletar(int id){
        AluguelController dao = new AluguelController();
        dao.removeById(id);
    }

    public AluguelBean getItemSelecionado() {
        return itemSelecionado;
    }

    public void setItemSelecionado(AluguelBean itemSelecionado) {
        this.itemSelecionado = itemSelecionado;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
    
    public void onRowSelect(SelectEvent<ClienteBean> event) {
        FacesMessage msg = new FacesMessage("Item Selecionado", event.getObject().getLogin());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public ArrayList<AluguelBean> obterLista() throws ParseException {
        
        
        lista = aluguelController.obterAlugueis();        
        return lista;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }
    
    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public String getDataEntregaString() {
        return dataEntregaString;
    }

    public void setDataEntregaString(String dataEntregaString) {
        this.dataEntregaString = dataEntregaString;
    }

    public String getDataAluguelString() {
        return dataAluguelString;
    }

    public void setDataAluguelString(String dataAluguelString) {
        this.dataAluguelString = dataAluguelString;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public VeiculoBean getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(VeiculoBean veiculo) {
        this.veiculo = veiculo;
    }

    public Date getDataAluguel() {
        return dataAluguel;
    }

    public void setDataAluguel(Date dataAluguel) {
        this.dataAluguel = dataAluguel;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public ClienteBean getCliente() {
        return cliente;
    }

    public void setCliente(ClienteBean cliente) {
        this.cliente = cliente;
    }

    public String getEntregue() {
        return entregue;
    }

    public void setEntregue(String entregue) {
        this.entregue = entregue;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public float getValorPago() {
        return valorPago;
    }

    public void setValorPago(float valorPago) {
        this.valorPago = valorPago;
    }

}
