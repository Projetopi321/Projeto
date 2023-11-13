/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cairu.joas;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author PAULO
 */
public class ClienteDAO {
    Connection c;    
    public void salvar(ClienteBean cli){
        String sql = "INSERT INTO pessoa (nome,email,endereco) VALUES (?,?,?)";
        c = new Conexao().conectaBD();
        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, cli.getNome());
            ps.setString(2, cli.getEmail());
            ps.setString(3, cli.getEndereco());
            
            ps.execute();
            ps.close();
                    
            
        } catch (Exception e) {
            System.out.println("ERRO" + e);
        }
        
    }
}
