package dbHelpers.deposits;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;

import dbHelpers.UpdateQuery;
import model.Clients;
import model.Deposits;
import model.OD;

public class ReadQueryDeposit {

    private Connection conn;
    private ResultSet results;

    public ReadQueryDeposit() {

        Properties props = new Properties();
        InputStream instr = getClass().getResourceAsStream("dbConn.properties");
        try {
            props.load(instr);
        } catch (IOException ex) {
            Logger.getLogger(ReadQueryDeposit.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            instr.close();
        } catch (IOException ex) {
            Logger.getLogger(ReadQueryDeposit.class.getName()).log(Level.SEVERE, null, ex);
        }

        String driver = props.getProperty("driver.name");
        String url = props.getProperty("server.name");
        String username = props.getProperty("user.name");
        String password = props.getProperty("user.password");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReadQueryDeposit.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            Logger.getLogger(ReadQueryDeposit.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void doReadDeposit() {

        try {
            String query = "Select * from deposits";
            PreparedStatement ps = conn.prepareStatement(query);
            this.results = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ReadQueryDeposit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public void doReadOD() {
//        try {
//            String query = "Select * from opendep, clients WHERE clients.clientID=opendep.clientID";
//            PreparedStatement ps = conn.prepareStatement(query);
//            this.results = ps.executeQuery();
//        } catch (SQLException ex) {
//            Logger.getLogger(ReadQueryDeposit.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public void doReadOD() {
        try {
            new UpdateQuery().updateDepositsAccumulation();
            String query = "Select * from opendep, client WHERE client.id=opendep.clientId";
            PreparedStatement ps = conn.prepareStatement(query);
            this.results = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ReadQueryDeposit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void doDelete(int DogID) {
        try {
            String query = "DELETE FROM opendep WHERE dogId= ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, DogID);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReadQueryDeposit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getHTMLtableOD() throws ParseException {

        String table = "";
        try {

            while (this.results.next()) {

                OD od = new OD();
                Clients client = new Clients();
                od.setDogId(this.results.getInt("dogId"));
                od.setClientId(this.results.getInt("clientId"));
                client.setName(this.results.getString("name"));
                client.setSurname(this.results.getString("surname"));
                od.setDepName(this.results.getString("depName"));
                od.setCurName(this.results.getString("curName"));
                od.setSumma(this.results.getFloat("summa"));
                od.setPeriod(this.results.getInt("period"));
                od.setDateOfStartS(this.results.getString("dataOfStart"));
                od.setDateOfFinishS(this.results.getString("dataOfFinish"));
                od.setPercent(this.results.getFloat("percent"));
                od.setAccumulation(this.results.getFloat("accumulation"));

                table += "<tr>";

                table += "<td>";
                String addNUL = Integer.toString(od.getDogId());
                while (addNUL.length() < 3) {
                    addNUL = "0" + addNUL;
                }
                table += addNUL;
                table += "</td>";

                table += "<td>";
                addNUL = Integer.toString(od.getClientId());
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
                table += od.getDepName();
                table += "</td>";

                table += "<td>";
                table += od.getCurName();
                table += "</td>";

                table += "<td>";
                table += od.getSumma();
                table += "</td>";

                table += "<td>";
                table += od.getPercent();
                table += "%";
                table += "</td>";

                table += "<td>";
                table += od.getPeriod();
                table += " мес.";
                table += "</td>";

                table += "<td>";
                table += od.getDateOfStart();
                table += "</td>";

                table += "<td>";
                table += od.getDateOfFinish();
                table += "</td>";

                table += "<td>";
                table += od.getAccumulation();
                table += "</td>";

                table += "<td>";
                table += "<input type=\"button\" name=\"button\" value=\"Закрыть\" onclick=\"return confirmClose(" + od.getDogId()+ ")\">";
                table += "</td>";

                table += "</tr>";

            }

        } catch (SQLException ex) {
            Logger.getLogger(ReadQueryDeposit.class.getName()).log(Level.SEVERE, null, ex);
        }
        table += "</table>";
        return table;
    }

    public String getHTMLtable() {
        String table = "";
        try {
            while (this.results.next()) {

                Deposits deposit = new Deposits();

                deposit.setDepositID(this.results.getInt("depositID"));
                deposit.setDepositName(this.results.getString("depositName"));
                deposit.setPBYN(this.results.getDouble("pBYN"));
                deposit.setPUSD(this.results.getDouble("pUSD"));
                deposit.setPEUR(this.results.getDouble("pEUR"));
                deposit.setPRUB(this.results.getDouble("pRUB"));

                table += "<tr>";

                table += "<td>";
                String addNUL = Integer.toString(deposit.getDepositID());
                while (addNUL.length() < 3) {
                    addNUL = "0" + addNUL;
                }
                table += addNUL;
                table += "</td>";

                table += "<td>";
                table += deposit.getDepositName();
                table += "</td>";

                table += "<td>";
                table += deposit.getPBYN();
                table += "</td>";

                table += "<td>";
                table += deposit.getPEUR();
                table += "</td>";

                table += "<td>";
                table += deposit.getPRUB();
                table += "</td>";

                table += "<td>";
                table += deposit.getPUSD();
                table += "</td>";
                table += "</tr>";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReadQueryDeposit.class.getName()).log(Level.SEVERE, null, ex);
        }

        table += "</table>";
        return table;
    }

}
