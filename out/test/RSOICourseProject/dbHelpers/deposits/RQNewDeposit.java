/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbHelpers.deposits;

import dbHelpers.AddQuery;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Clients;
import model.OD;

/**
 *
 * @author 
 */
public class RQNewDeposit {

    private Connection conn;
    private ResultSet results;

    public RQNewDeposit() {

        Properties props = new Properties();
        InputStream instr = getClass().getResourceAsStream("dbConn.properties");
        try {
            props.load(instr);
        } catch (IOException ex) {
            Logger.getLogger(RQNewDeposit.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            instr.close();
        } catch (IOException ex) {
            Logger.getLogger(RQNewDeposit.class.getName()).log(Level.SEVERE, null, ex);
        }


        String driver = props.getProperty("driver.name");
        String url = props.getProperty("server.name");
        String username = props.getProperty("user.name");
        String password = props.getProperty("user.password");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RQNewDeposit.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            Logger.getLogger(RQNewDeposit.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   public void doAdd(OD od){
       
       try {
           String query="INSERT INTO opendep(clientId, summa, depName, curName, dataOfStart, dataOfFinish, period, percent, "
                   + "accumulation) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
           
           
           PreparedStatement ps = conn.prepareStatement(query);
           
           System.out.println(query);
           System.out.println("перед вставкой1"+od.getDateOfStart());
           System.out.println("перед вставкой2"+od.getDateOfFinish());
           ps.setInt(1, od.getClientId());
           ps.setFloat(2, od.getSumma());
           ps.setString(3, od.getDepName());
           ps.setString(4, od.getCurName());
           ps.setString(5, od.getDateOfStartS());
           ps.setString(6, od.getDateOfFinishS());
           ps.setInt(7, (int) od.getPeriod());
           ps.setFloat(8, od.getPercent());
           ps.setFloat(9, od.getAccumulation());

           ps.executeUpdate();
                
       }catch (SQLException ex) {
           Logger.getLogger(AddQuery.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       
       
   }


}
