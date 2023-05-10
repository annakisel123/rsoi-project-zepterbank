/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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


public class ReadRecord {
    
    private Connection conn;
    private ResultSet results;
    
    private Clients client = new Clients();
    private int ClientID;
    
    public ReadRecord(int ClientID){
    
    Properties props = new Properties();
        InputStream instr = getClass().getResourceAsStream("dbConn.properties");
        try {
            props.load(instr);
        } catch (IOException ex) {
            Logger.getLogger(ReadRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            instr.close();
        } catch (IOException ex) {
            Logger.getLogger(ReadRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String driver = props.getProperty("driver.name");
        String url = props.getProperty("server.name");
        String username = props.getProperty("user.name");
        String password = props.getProperty("user.password");
        
        this.ClientID = ClientID;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReadRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            conn=DriverManager.getConnection (url, username, password);
        } catch (SQLException ex) {
            Logger.getLogger(ReadRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void doRead(){
        
        try {
            String query = "Select * from client WHERE id = ?";
             PreparedStatement ps = conn.prepareStatement(query);
             ps.setInt(1, ClientID);
             this.results = ps.executeQuery(); 
             this.results.next();
             
                
                client.setId(this.results.getInt("id"));
                client.setName(this.results.getString("name"));
                client.setSurname(this.results.getString("surname"));
                client.setFname(this.results.getString("fname"));
                client.setBirthdate(this.results.getString("birthdate"));
                client.setSpp(this.results.getString("spp"));
                client.setNpp(this.results.getString("npp"));
                client.setIpp(this.results.getString("ipp"));
                client.setWhogive(this.results.getString("whogive"));
                client.setWhengive(this.results.getString("whengive"));
                client.setCityborn(this.results.getString("cityborn"));
                client.setCity(this.results.getString("city"));
                client.setAddress(this.results.getString("address"));
                client.setMobilenum(this.results.getString("mobilenum"));
                client.setHomenum(this.results.getString("homenum"));
                client.setEmail(this.results.getString("email"));
                client.setJob(this.results.getString("job"));
                client.setPost(this.results.getString("post"));
                client.setCitydoc(this.results.getString("citydoc"));
                client.setMarriage(this.results.getString("marriage"));
                client.setNat(this.results.getString("nat"));
                client.setInvalid(this.results.getString("invalid"));
                client.setOld(this.results.getString("old"));
                client.setMoney(this.results.getInt("money"));
             
             
        } catch (SQLException ex) {
            Logger.getLogger(ReadRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Clients getClient(){
        return this.client;
    }
}
