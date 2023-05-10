/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbHelpers.credits;

import dbHelpers.AddQuery;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import dbHelpers.UpdateQuery;
import model.Clients;
import model.Credits;
import model.OC;

/**
 *
 * @author 
 */
public class QueryCredit {

    private Connection conn;
    private ResultSet results;
    public QueryCredit() {

        Properties props = new Properties();
        InputStream instr = getClass().getResourceAsStream("dbConn.properties");
        try {
            props.load(instr);
        } catch (IOException ex) {
            Logger.getLogger(QueryCredit.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            instr.close();
        } catch (IOException ex) {
            Logger.getLogger(QueryCredit.class.getName()).log(Level.SEVERE, null, ex);
        }

        String driver = props.getProperty("driver.name");
        String url = props.getProperty("server.name");
        String username = props.getProperty("user.name");
        String password = props.getProperty("user.password");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QueryCredit.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            Logger.getLogger(QueryCredit.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void doReadCredit() {

        try {
            String query = "Select * from credits";
            PreparedStatement ps = conn.prepareStatement(query);
            this.results = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(QueryCredit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public void doReadOC() {
//
//        try {
//            String query = "Select * from givecr, clients WHERE clients.clientID=givecr.clientID";
//            PreparedStatement ps = conn.prepareStatement(query);
//            this.results = ps.executeQuery();
//        } catch (SQLException ex) {
//            Logger.getLogger(QueryCredit.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public void doReadOC() {
        try {
            new UpdateQuery().updateCreditsAccumulation();
            String query = "Select * from givecr, client WHERE client.id=givecr.clientId";
            PreparedStatement ps = conn.prepareStatement(query);
            this.results = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(QueryCredit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void doDelete(int DogID) {
        try {
            String query = "DELETE FROM givecr WHERE dogID= ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, DogID);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QueryCredit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getHTMLtableOC() throws ParseException {

        String table = "";
        try {

            while (this.results.next()) {

                OC oc = new OC();
                Clients client = new Clients();
                oc.setDogId(this.results.getInt("dogID"));
                oc.setClientId(this.results.getInt("clientID"));
                client.setName(this.results.getString("name"));
                client.setSurname(this.results.getString("surname"));
                oc.setCrName(this.results.getString("crName"));
                oc.setCurName(this.results.getString("curName"));
                oc.setSumma(this.results.getFloat("summa"));
                oc.setPeriod(this.results.getInt("period"));
                oc.setDateOfStartS(this.results.getString("dataOfStart"));
                oc.setDateOfFinishS(this.results.getString("dataOfFinish"));
                oc.setPercent(this.results.getFloat("percent"));
                oc.setAccumulation(this.results.getFloat("accumulation"));

                table += "<tr>";

                table += "<td>";
                String addNUL = Integer.toString(oc.getDogId());
                while (addNUL.length() < 3) {
                    addNUL = "0" + addNUL;
                }
                table += addNUL;
                table += "</td>";

                table += "<td>";
                addNUL = Integer.toString(oc.getClientId());
                while (addNUL.length() < 3) {
                    addNUL = "0" + addNUL;
                }
                table += addNUL;
                table += "</td>";

                table += "<td>";
                table += client.getName();
                table += "</td>";

                table += "<td>";
                table += client.getSurname();
                table += "</td>";

                table += "<td>";
                table += oc.getCrName();
                table += "</td>";

                table += "<td>";
                table += oc.getCurName();
                table += "</td>";

                table += "<td>";
                table += oc.getSumma();
                table += "</td>";

                table += "<td>";
                table += oc.getPercent();
                table += "%";
                table += "</td>";

                table += "<td>";
                table += oc.getPeriod();
                table += " мес.";
                table += "</td>";

                table += "<td>";
                table += oc.getDateOfStart();
                table += "</td>";

                table += "<td>";
                table += oc.getDateOfFinish();
                table += "</td>";

                table += "<td>";
                table += oc.getAccumulation();
                table += "</td>";

                table += "<td>";
                table += "<input type=\"button\" name=\"button\" value=\"Закрыть\" onclick=\"return confirmClose(" + oc.getDogId() + ")\">";
                table += "</td>";

                table += "</tr>";

            }

        } catch (SQLException ex) {
            Logger.getLogger(QueryCredit.class.getName()).log(Level.SEVERE, null, ex);
        }
        table += "</table>";
        return table;
    }

      public String getHTMLtable() {
        String table = "";
        try {
            while (this.results.next()) {

                Credits credit = new Credits();

                credit.setCreditID(this.results.getInt("creditID"));
                credit.setCreditName(this.results.getString("creditName"));
                credit.setPBYN(this.results.getDouble("pBYN"));
                credit.setPUSD(this.results.getDouble("pUSD"));
                credit.setPEUR(this.results.getDouble("pEUR"));
                credit.setPRUB(this.results.getDouble("pRUB"));

                table += "<tr>";

                table += "<td>";
                String addNUL = Integer.toString(credit.getCreditID());
                while (addNUL.length() < 3) {
                    addNUL = "0" + addNUL;
                }
                table += addNUL;
                table += "</td>";

                table += "<td>";
                table += credit.getCreditName();
                table += "</td>";


                table += "<td>";
                table += credit.getPBYN();
                table += "</td>";

                table += "<td>";
                table += credit.getPEUR();
                table += "</td>";

                table += "<td>";
                table += credit.getPRUB();
                table += "</td>";

                table += "<td>";
                table += credit.getPUSD();
                table += "</td>";
                table += "</tr>";
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryCredit.class.getName()).log(Level.SEVERE, null, ex);
        }

        table += "</table>";
        return table;
    } 
    
    
    public void doAdd(OC oc) {

        try {
            String query = "INSERT INTO givecr(clientId, summa, crName, curName, dataOfStart, dataOfFinish, period, percent, "
                    + "accumulation) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, oc.getClientId());
            ps.setFloat(2, oc.getSumma());
            ps.setString(3, oc.getCrName());
            ps.setString(4, oc.getCurName());
            ps.setString(5, oc.getDateOfStartS());
            ps.setString(6, oc.getDateOfFinishS());
            ps.setInt(7, (int) oc.getPeriod());
            ps.setFloat(8, oc.getPercent());
            ps.setFloat(9, oc.getAccumulation());

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AddQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
