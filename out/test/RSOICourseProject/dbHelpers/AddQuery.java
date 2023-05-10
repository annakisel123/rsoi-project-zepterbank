
package dbHelpers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Clients;

/**
 *
 * @author 
 */
public class AddQuery {
   private Connection conn;
   private ResultSet results;   
    
   public AddQuery(){
        Properties props = new Properties();
        InputStream instr = getClass().getResourceAsStream("dbConn.properties");
        try {
            props.load(instr);
        } catch (IOException ex) {
            Logger.getLogger(AddQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            instr.close();
        } catch (IOException ex) {
            Logger.getLogger(AddQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String driver = props.getProperty("driver.name");
        String url = props.getProperty("server.name");
        String username = props.getProperty("user.name");
        String password = props.getProperty("user.password");
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            conn=DriverManager.getConnection (url, username, password);
        } catch (SQLException ex) {
            Logger.getLogger(AddQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   public void doAdd(Clients client){
       
       try {
           String query="INSERT INTO client(name, surname, fname, birthdate, spp, npp, whogive, whengive, cityborn, "
                   + "city, address, mobilenum, homenum, email, job, post, citydoc, marriage, "
                   + "nat, invalid, old, money, ipp) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
           
           
           PreparedStatement ps = conn.prepareStatement(query);
           
           System.out.println(query);
           
           ps.setString(1, client.getName());
           ps.setString(2, client.getSurname());
           ps.setString(3, client.getFname());
           ps.setString(4, client.getBirthdate());
           ps.setString(5, client.getSpp());
           ps.setString(6, client.getNpp());
           ps.setString(7, client.getWhogive());
           ps.setString(8, client.getWhengive());
           ps.setString(9, client.getCityborn());
           ps.setString(10, client.getCity());
           ps.setString(11, client.getAddress());
           ps.setString(12, client.getMobilenum());
           ps.setString(13, client.getHomenum());
           ps.setString(14, client.getEmail());
           ps.setString(15, client.getJob());
           ps.setString(16, client.getPost());
           ps.setString(17, client.getCitydoc());
           ps.setString(18, client.getMarriage());        
           ps.setString(19, client.getNat());
           ps.setString(20, client.getInvalid());
           ps.setString(21, client.getOld());
           ps.setInt(22, client.getMoney());
           ps.setString(23, client.getIpp());



           ps.executeUpdate();

           
    
                      
       }catch (SQLException ex) {
           Logger.getLogger(AddQuery.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       
       
   }
   
}
