package model;

import controller.UsuarioController;
import java.util.ArrayList;
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
public class UsuarioBean {
    
    int id;
    String nome;
    String cargo;
    String login;
    String senha;
    String email;
    boolean resultado;

    @PostConstruct
    public void init() {
        // Defina um valor inicial para placa
        
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String idParam = externalContext.getRequestParameterMap().get("id");
        
      if ( idParam != null ) {
        
       int id = Integer.parseInt(idParam);
       
       nome = buscarUsuario(id).getNome();
       cargo = buscarUsuario(id).getCargo();
       login = buscarUsuario(id).getLogin();
       senha = buscarUsuario(id).getSenha();       
       email = buscarUsuario(id).getEmail();
     }
    }
       public UsuarioBean buscarUsuario(int idUsuario) {    
        return userDao.getById(idUsuario);
    }
    public boolean isResultado() {
        return resultado;
    }
    
    public void setResultado(boolean resultado) {
        this.resultado = resultado;
    }
    public void deletar(int id) {
        userDao.removeById(id);
    }
    ArrayList<UsuarioBean> lista = new ArrayList<>();
    UsuarioBean userB;
    UsuarioController userDao = new UsuarioController();
    
        public boolean verificaLogin() {
        userB = new UsuarioBean();

        userB.setLogin(login);
        userB.setSenha(senha);

        resultado = userDao.verificaLogin(userB);

        return resultado;
    }
        
    public void inserir() {
        userB = new UsuarioBean();
        userB.setNome(nome);
        userB.setCargo(cargo);
        userB.setLogin(login);
        userB.setSenha(senha);
        userB.setEmail(email);
        
        userDao.save(userB);
    }
    
        public void atualizar() {
        userB = new UsuarioBean();
        
        id = userDao.getIdByLogin(login);
        userB.setId(id);
        
        userB.setNome(nome);
        userB.setCargo(cargo);
        userB.setLogin(login);
        userB.setSenha(senha);
        userB.setEmail(email);
        
        userDao.update(userB);
    }
    
     public ArrayList<UsuarioBean> obterLista() {
        lista = userDao.obterUsuarios(); // CRIANDO A LISTA PARA MANIPULAR OS DADOS
        return lista;       // RETORNANDO PRO FRONT
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}