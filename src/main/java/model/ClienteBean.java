package model;

import controller.ClienteController;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


/**
 *
 * @author PAULO
 */
@ManagedBean
@ViewScoped
public class ClienteBean {

    private int id;
    private String login;
    private String senha;
    private String nome;
    private String endereco;
    private String uf;
    private String telefone;
    private String cpf;
    private String email;
    private boolean resultado;
    ArrayList<ClienteBean> lista = new ArrayList<>();
    ClienteBean cliB;
    ClienteController clienteDao = new ClienteController();
    
       @PostConstruct
    public void init() {
        // Defina um valor inicial para placa
        
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String idParam = externalContext.getRequestParameterMap().get("id");
        
      if ( idParam != null ) {
        
       int id = Integer.parseInt(idParam);
       
       login = buscarCliente(id).getLogin();
       senha = buscarCliente(id).getSenha();
       nome = buscarCliente(id).getNome();
       endereco = buscarCliente(id).getEndereco();
       uf = buscarCliente(id).getUf();
       telefone = buscarCliente(id).getTelefone();
       cpf = buscarCliente(id).getCpf();
       email = buscarCliente(id).getEmail();
     }
    }

    public void inserir() {
        cliB = new ClienteBean();
        cliB.setLogin(login);
        cliB.setSenha(senha);
        cliB.setNome(nome);
        cliB.setEmail(email);
        cliB.setEndereco(endereco);
        cliB.setUf(uf);
        cliB.setTelefone(telefone);
        cliB.setCpf(cpf);
        cliB.setEmail(email);
        clienteDao.salvar(cliB);
    }
    
        public void atualizar() {
        cliB = new ClienteBean();
        
        id = clienteDao.getIdByCpf(cpf);
        
        cliB.setId(id);
        cliB.setLogin(login);
        cliB.setSenha(senha);
        cliB.setNome(nome);
        cliB.setEmail(email);
        cliB.setEndereco(endereco);
        cliB.setUf(uf);
        cliB.setTelefone(telefone);
        cliB.setCpf(cpf);
        cliB.setEmail(email);
        clienteDao.update(cliB);
    }
        
    public void deletar(int id) {
        clienteDao.removeById(id);
    }

    public boolean verificaLogin() {
        cliB = new ClienteBean();

        cliB.setLogin(login);
        cliB.setSenha(senha);

        resultado = clienteDao.verificaLogin(cliB);

        return resultado;
    }
   
    public ArrayList<ClienteBean> obterLista() {
        lista = clienteDao.obterClientes(); // CRIANDO A LISTA PARA MANIPULAR OS DADOS
        return lista;       // RETORNANDO PRO FRONT
    }
    
     public ClienteBean buscarCliente(int idCliente) {    
        return clienteDao.getById(idCliente);  
    }
    
      public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isResultado() {
        return resultado;
    }

    public void setResultado(boolean resultado) {
        this.resultado = resultado;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
    
    
}
