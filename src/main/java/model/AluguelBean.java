package model;

import java.util.Date;
import controller.AluguelController;
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
public class AluguelBean {
   int id;
   VeiculoBean veiculo;
   Date dataAluguel;
   Date dataEntrega;
   ClienteBean cliente;
   String entregue;
   String observacao;
   float valorPago;
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
        
        alugB.setId(id);
        alugB.setVeiculo(veiculo);
        alugB.setDataAluguel(dataAluguel);
        alugB.setDataEntrega(dataEntrega);
        alugB.setCliente(cliente);
        alugB.setEntregue(entregue);
        alugB.setObservacao(observacao);
        alugB.setValorPago(valorPago);
        
        aluguelController.salvar(alugB);
    }
    
        public AluguelBean getItemSelecionado() {
        return itemSelecionado;
    }

    public void setItemSelecionado(AluguelBean itemSelecionado) {
        this.itemSelecionado = itemSelecionado;
    }

    public void onRowSelect(SelectEvent<ClienteBean> event) {
        FacesMessage msg = new FacesMessage("Item Selecionado", event.getObject().getLogin());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
      public ArrayList<AluguelBean> obterLista() {
        lista = aluguelController.obterAlugueis(); // CRIANDO A LISTA PARA MANIPULAR OS DADOS
        return lista;       // RETORNANDO PRO FRONT
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