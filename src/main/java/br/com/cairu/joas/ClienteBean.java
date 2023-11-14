/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cairu.joas;

import javax.faces.bean.ManagedBean;


/**
 *
 * @author PAULO
 */
@ManagedBean
public class ClienteBean {

    private String nome;
    private String email;
    private String endereco;
    ClienteBean cliB;
    ClienteDAO dao = new ClienteDAO();
    
    public void inserir(){
    cliB = new ClienteBean(); 
    cliB.setNome(nome);
    cliB.setEmail(email);
    cliB.setEndereco(endereco);
    dao.salvar(cliB);
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
