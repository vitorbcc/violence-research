/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prefeitura;
   
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateDB {
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver"; 
    private static final String URL = "jdbc:derby:rmepesq;create=true;user=prefeitura;password=semecjose";
    private static final String USR ="semec";
    private static final String PWD ="semecjose";
    

     static Connection getcon() throws ClassNotFoundException, SQLException{
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(URL);
            return con;
    }
   
}