package model;

import controller.VeiculoController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

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
    private int anoModelo;
    private int qtdPortas;
    private String acessorios;
    ArrayList<VeiculoBean> lista = new ArrayList<>();
    private VeiculoBean itemSelecionado;
    VeiculoBean veiculoB;
    VeiculoController veiculoDao = new VeiculoController();
    
    @PostConstruct
    public void init() {
        // Defina um valor inicial para placa
        
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String idParam = externalContext.getRequestParameterMap().get("id");
        
      if ( idParam != null ) {
        
       int id = Integer.parseInt(idParam);
       
       placa = buscarVeiculo(id).getPlaca();
       fabricante = buscarVeiculo(id).getFabricante();
       modelo = buscarVeiculo(id).getModelo();
       anoModelo = buscarVeiculo(id).getAnoModelo();
       qtdPortas = buscarVeiculo(id).getQtdPortas();
       acessorios = buscarVeiculo(id).getAcessorios();
     }
    }

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
    
     public void atualizar() {
        veiculoB = new VeiculoBean();
        
        id = veiculoDao.getIdByPlaca(placa);
        
        veiculoB.setId(id);
        veiculoB.setPlaca(placa);
        veiculoB.setFabricante(fabricante);
        veiculoB.setModelo(modelo);
        veiculoB.setAnoModelo(anoModelo);
        veiculoB.setQtdPortas(qtdPortas);
        veiculoB.setAcessorios(acessorios);

        veiculoDao.update(veiculoB);
    }
    
    public void deletar(int id) {
        veiculoDao.removeById(id);
    }
    
    public VeiculoBean buscarVeiculo(int idVeiculo) {    
        return veiculoDao.getById(idVeiculo);  
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
