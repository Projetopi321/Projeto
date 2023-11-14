/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cairu.joas;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3806/projetobd";
    public static final String USER = "root";
    public static final String PASS = "";
    
    public static Connection conectaBD() {
        
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception ex) {
  
            throw new RuntimeException("Erro ao tentar conexão com o banco de dados");
        }
     
    }
    
    
}