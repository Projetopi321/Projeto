package model;

import controller.VeiculoController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author matheus
 */

@ManagedBean
@ViewScoped
public class VeiculoBean {

    private int id;
    private String placa;
    private String fabricante;
    private String modelo;
    private Date dataTeste;
    private String dataString;

    public String getDataString() {
        return dataString;
    }

    public void setDataString(String dataString) {
        this.dataString = dataString;
    }

    public Date getDataTeste() {
        return dataTeste;
    }

    public void setDataTeste(Date dataTeste) {
        this.dataTeste = dataTeste;
    }
    private int anoModelo;
    private int qtdPortas;
    private String acessorios;
    ArrayList<VeiculoBean> lista = new ArrayList<>();
    private VeiculoBean itemSelecionado;

    public VeiculoBean getItemSelecionado() {
        return itemSelecionado;
    }

    public void setItemSelecionado(VeiculoBean itemSelecionado) {
        this.itemSelecionado = itemSelecionado;
    }
    
    VeiculoBean veiculoB;
    VeiculoController veiculoDao = new VeiculoController();
    
    public void inserir() { 
        veiculoB = new VeiculoBean();
        veiculoB.setPlaca(placa);
        veiculoB.setFabricante(fabricante);
        veiculoB.setModelo(modelo);
        veiculoB.setAnoModelo(anoModelo);
        veiculoB.setQtdPortas(qtdPortas);
        veiculoB.setAcessorios(acessorios);

        veiculoDao.salvar(veiculoB);
    }
       
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(int anoModelo) {
        this.anoModelo = anoModelo;
    }

    public int getQtdPortas() {
        return qtdPortas;
    }

    public void setQtdPortas(int qtdPortas) {
        this.qtdPortas = qtdPortas;
    }

    public String getAcessorios() {
        return acessorios;
    }

    public void setAcessorios(String acessorios) {
        this.acessorios = acessorios;
    }
    
        // METÓDO PARA CHAMAR OS DADOS DO BANCO.
    public ArrayList<VeiculoBean> obterLista() {
        lista = veiculoDao.obterVeiculos(); // CRIANDO A LISTA PARA MANIPULAR OS DADOS
        return lista;       // RETORNANDO PRO FRONT
    }  
    
}
