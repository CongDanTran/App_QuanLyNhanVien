/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CongDan
 */
public class DAO {
    public  String url;
    
    public DAO(String url) {
        this.url = url;
    }
   
      public  Connection getConnect() throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        
            Connection conn = null;
            String username = "root";
            String password ="";
            String url = "jdbc:mysql://localhost:3306/qlnhanvien";
            return DriverManager.getConnection(url, username, password);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
          public  boolean Lenh(String caulenh,Connection conn){
     
             try {
               
                String sql =caulenh;
                Statement statement = conn.createStatement();
                int x = statement.executeUpdate(sql);
                if (x > 0) {
                  return true;
                }
            } catch (Exception ex) {
                 System.out.println("" +ex.toString());
            }
             return false;
    }
}
