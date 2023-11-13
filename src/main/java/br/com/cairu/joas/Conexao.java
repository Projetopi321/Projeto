/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cairu.joas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexao {

    public Connection conectaBD() {
        Connection con = null;

        try {
            String url = "jdbc:mysql://localhost:3306/projetobd?user=root&password=root";
            con = DriverManager.getConnection(url);
        } catch (SQLException erro) {
            JOptionPane.showConfirmDialog(null," ConexaoDAO"+ erro.getMessage());
        }
        return con;
    }
}