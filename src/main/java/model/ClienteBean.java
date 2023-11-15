package model;

import controller.ClienteDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author PAULO
 */
@ManagedBean
@ViewScoped
public class ClienteBean {

  private String login;
  private  String senha;
  private  String nome;
  private  String endereco;
  private  String uf;
  private  String telefone;
  private  String cpf;
  private  String email;
  private boolean resultado;

   ClienteBean cliB;
   ClienteDAO clienteDao = new ClienteDAO();
    
    public void inserir(){
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

    public boolean isResultado() {
        return resultado;
    }

    public void setResultado(boolean resultado) {
        this.resultado = resultado;
    }
    
    public boolean VerificaLogin(){
    cliB = new ClienteBean(); 
    
    cliB.setLogin(login);
    cliB.setSenha(senha);
    

    resultado = clienteDao.verificaLogin(cliB);
    
    return resultado;

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
