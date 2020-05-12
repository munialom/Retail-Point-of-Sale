/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author INFOTECH
 */
public class Connect {
    
    
        Connection con=null;
   
        public static Connection ConnectDB(){
             try{
           
          Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://192.168.0.100:3307/tumaini_upgrade","root","mose@90");
         // Connection con = DriverManager.getConnection("jdbc:mysql://41.90.248.107:3307/tumaini_upgrade","root","mose@90");
        
          return con;
            
          
        }catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
          
        }      
            return null;
            
}
    
}
