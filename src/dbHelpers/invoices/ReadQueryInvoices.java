package dbHelpers.invoices;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Invoices;
import model.OC;
import model.OD;

public class ReadQueryInvoices {

    private Connection conn;
    private ResultSet results;
    private ResultSet results2;

    public ReadQueryInvoices() {

        try {

            Properties props = new Properties();
            InputStream instr = getClass().getResourceAsStream("dbConn.properties");
            props.load(instr);
            String driver = props.getProperty("driver.name");
            String url = props.getProperty("server.name");
            String username = props.getProperty("user.name");
            String password = props.getProperty("user.password");

            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(url, username, password);
        } catch (IOException ex) {
            Logger.getLogger(ReadQueryInvoices.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReadQueryInvoices.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }

    }

    public void doReadI() {

        try {
            String query = "Select * from invoices";
            PreparedStatement ps = conn.prepareStatement(query);
            this.results = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ReadQueryInvoices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void doAddI(OD od) {

        try {

            od.setSumma(od.getSumma(), od.getCurName());
            String q1 = "SELECT  dogId from opendep order by dogId DESC limit 1";
            PreparedStatement ps = conn.prepareStatement(q1);
            this.results = ps.executeQuery();
            this.results.next();
            od.setDogId(this.results.getInt("dogId"));

            q1 = "SELECT  debit, credit from invoices where kod =\"1010\"";
            ps = conn.prepareStatement(q1);
            this.results = ps.executeQuery();
            this.results.next();
            float debit, credit;
            debit = this.results.getFloat("debit");
            credit = this.results.getFloat("credit");

            q1 = "Update invoices SET debit = ?, credit = ? WHERE kod=\"1010\"";
            ps = conn.prepareStatement(q1);
            ps.setFloat(1, debit + od.getSumma());
            ps.setFloat(2, credit + od.getSumma());
            ps.executeUpdate();

            String query = "INSERT INTO invoices (name, number, type, kod, debit, credit, saldo, clientId, dogId) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            ps = conn.prepareStatement(query);

            ps.setString(1, "Тек. счет клиента");
            // ps.setFloat(2, od.getSumma());
            ps.setString(3, "П");
            ps.setString(4, "3014");
            ps.setFloat(5, od.getSumma());
            ps.setFloat(6, od.getSumma());
            ps.setFloat(7, 0);
            ps.setInt(8, od.getClientId());
            ps.setFloat(9, od.getDogId());

            String num = "3014";
            String addNUL = Integer.toString(od.getClientId());
            while (addNUL.length() < 4) {
                addNUL = "0" + addNUL;
            }
            num += addNUL;

            addNUL = Integer.toString(od.getDogId());
            while (addNUL.length() < 4) {
                addNUL = "0" + addNUL;
            }
            num += addNUL;
            num += "1";

            ps.setString(2, num);
            ps.executeUpdate();

            q1 = "SELECT credit from invoices where kod =\"7327\"";
            ps = conn.prepareStatement(q1);
            this.results = ps.executeQuery();
            this.results.next();
            credit = this.results.getFloat("credit");

            q1 = "Update invoices SET credit = ? WHERE kod=\"7327\"";
            ps = conn.prepareStatement(q1);
            ps.setFloat(1, credit + od.getSumma());
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ReadQueryInvoices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public void doAddI(OC oc) {

        try {

            float debit=0;
            float credit=0;
            System.out.println(oc.getSumma());
            oc.setSumma(oc.getSumma(), oc.getCurName());
            System.out.println(oc.getSumma());
            String q1 = "SELECT  dogId from givecr order by dogId DESC limit 1";
            PreparedStatement ps = conn.prepareStatement(q1);
            this.results = ps.executeQuery();
            this.results.next();
            oc.setDogId(this.results.getInt("dogId"));

            q1 = "SELECT debit, credit from invoices where kod =\"7327\"";
            ps = conn.prepareStatement(q1);
            this.results = ps.executeQuery();
            this.results.next();
            debit = this.results.getFloat("debit");
            credit = this.results.getFloat("credit");

            q1 = "Update invoices SET debit = ?, saldo = ? WHERE kod=\"7327\"";
            ps = conn.prepareStatement(q1);
            ps.setFloat(1, debit + oc.getSumma());
            ps.setFloat(2, credit - debit - oc.getSumma());
            ps.executeUpdate();

            String query = "INSERT INTO invoices (name, number, type, kod, debit, credit, saldo, clientID, dogID) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            ps = conn.prepareStatement(query);

            ps.setString(1, "Кредитный счет клиента");
            // ps.setFloat(2, od.getSumma());
            ps.setString(3, "А");
            ps.setString(4, "2400");
            ps.setFloat(5, oc.getSumma());
            ps.setFloat(6, oc.getSumma());
            ps.setFloat(7, 0);
            ps.setInt(8, oc.getClientId());
            ps.setFloat(9, oc.getDogId());

            String num = "2400";
            String addNUL = Integer.toString(oc.getClientId());
            while (addNUL.length() < 4) {
                addNUL = "0" + addNUL;
            }
            num += addNUL;

            addNUL = Integer.toString(oc.getDogId());
            while (addNUL.length() < 4) {
                addNUL = "0" + addNUL;
            }
            num += addNUL;
            num += "3";

            ps.setString(2, num);
            ps.executeUpdate();

            q1 = "SELECT  debit, credit from invoices where kod =\"1010\"";
            ps = conn.prepareStatement(q1);
            this.results = ps.executeQuery();
            this.results.next();
            debit = this.results.getFloat("debit");
            credit = this.results.getFloat("credit");

            q1 = "Update invoices SET debit = ?, credit = ? WHERE kod=\"1010\"";
            ps = conn.prepareStatement(q1);
            ps.setFloat(1, debit + oc.getSumma());
            ps.setFloat(2, credit + oc.getSumma());
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ReadQueryInvoices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public void doCloseI(int dogID) {

        try {
            String q1 = "Select * from opendep WHERE dogId = ?";
            PreparedStatement ps = conn.prepareStatement(q1);
            ps.setInt(1, dogID);
            this.results = ps.executeQuery();
            this.results.next();
            OD od = new OD();
            float debit = 0;
            float credit = 0;
            od.setDogId(dogID);
            System.out.println(dogID);
            System.out.println(od.getDogId());

            od.setSumma(this.results.getFloat("summa"));
            od.setCurName(this.results.getString("summa"));
            od.setSumma(od.getSumma(), od.getCurName());
            od.setAccumulation(this.results.getFloat("accumulation"));
            od.setAccumulation(od.getAccumulation(), od.getCurName());
            od.setClientId(this.results.getInt("clientId"));

            q1 = "SELECT debit from invoices where kod =\"7327\"";
            ps = conn.prepareStatement(q1);
            this.results = ps.executeQuery();
            this.results.next();
            debit = this.results.getFloat("debit");

            q1 = "Update invoices SET debit = ? WHERE kod=\"7327\"";
            ps = conn.prepareStatement(q1);
            ps.setFloat(1, debit + od.getAccumulation());
            ps.executeUpdate();

            String query = "INSERT INTO invoices (name, number, type, kod, debit, credit, saldo, clientID, dogID) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, "Процентный счет");
            ps.setString(3, "П");
            ps.setString(4, "1273");
            ps.setFloat(5, od.getAccumulation());
            ps.setFloat(6, od.getAccumulation());
            ps.setFloat(7, 0);
            ps.setInt(8, od.getClientId());
            ps.setFloat(9, od.getDogId());
            String num = "1273";
            String num2 = "3014";
            String addNUL = Integer.toString(od.getClientId());
            while (addNUL.length() < 4) {
                addNUL = "0" + addNUL;
            }
            num += addNUL;
            num2 += addNUL;
            addNUL = Integer.toString(od.getDogId());
            while (addNUL.length() < 4) {
                addNUL = "0" + addNUL;
            }
            num += addNUL;
            num2 += addNUL;
            num += "2";
            num2 += "1";
            ps.setString(2, num);
            ps.executeUpdate();

            q1 = "SELECT  debit, credit from invoices where kod =\"1010\"";
            ps = conn.prepareStatement(q1);
            this.results = ps.executeQuery();
            this.results.next();
            debit = this.results.getFloat("debit");
            credit = this.results.getFloat("credit");

            String  q2 = "Update invoices SET debit = ?, credit = ? WHERE kod=\"1010\"";
            PreparedStatement ps1 = conn.prepareStatement(q2);
            ps1.setFloat(1, debit + od.getAccumulation());
            ps1.setFloat(2, credit + od.getAccumulation());
            ps1.executeUpdate();

            q1 = "SELECT debit from invoices where kod =\"7327\"";
            ps = conn.prepareStatement(q1);
            this.results = ps.executeQuery();
            this.results.next();
            debit = this.results.getFloat("debit");

            q1 = "Update invoices SET debit = ? WHERE kod=\"7327\"";
            ps = conn.prepareStatement(q1);
            ps.setFloat(1, debit + od.getSumma());
            ps.executeUpdate();

            q1 = "SELECT  debit, credit from invoices where number = ?";
            ps = conn.prepareStatement(q1);
            ps.setString(1, num2);
            System.out.println(num2);
            this.results = ps.executeQuery();
            this.results.next();
            debit = this.results.getFloat("debit");
            credit = this.results.getFloat("credit");
            System.out.println(debit);
            System.out.println(od.getSumma());

            String q3 = "Update invoices SET debit = ?, credit = ? WHERE number= ?";
            ps = conn.prepareStatement(q3);
            ps.setFloat(1, debit + od.getSumma());
            ps.setFloat(2, credit + od.getSumma());
            ps.setString(3, num2);
            ps.executeUpdate();

            q1 = "SELECT  debit, credit from invoices where kod =\"1010\"";
            ps = conn.prepareStatement(q1);
            this.results = ps.executeQuery();
            this.results.next();
            debit = this.results.getFloat("debit");
            credit = this.results.getFloat("credit");

            String q4 = "Update invoices SET debit = ?, credit = ? WHERE kod=\"1010\"";
            ps1 = conn.prepareStatement(q4);
            ps1.setFloat(1, debit + od.getSumma());
            ps1.setFloat(2, credit + od.getSumma());
            ps1.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ReadQueryInvoices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void doCloseCI(int dogID) {

        try {
            String q1 = "Select * from givecr WHERE dogId = ?";
            PreparedStatement ps = conn.prepareStatement(q1);
            ps.setInt(1, dogID);
            this.results = ps.executeQuery();
            this.results.next();
            OC oc = new OC();
            float debit = 0;
            float credit = 0;
            oc.setDogId(dogID);

            oc.setSumma(this.results.getFloat("summa"));
            System.out.println(oc.getSumma());
            oc.setCurName(this.results.getString("curName"));
            oc.setSumma(oc.getSumma(), oc.getCurName());
            System.out.println(oc.getSumma());
            oc.setAccumulation(this.results.getFloat("accumulation"));
            oc.setAccumulation(oc.getAccumulation(), oc.getCurName());
            oc.setClientId(this.results.getInt("clientId"));

            String query = "INSERT INTO invoices (name, number, type, kod, debit, credit, saldo, clientID, dogID) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, "Процентный счет кредита");
            ps.setString(3, "П");
            ps.setString(4, "1673");
            ps.setFloat(5, oc.getAccumulation());
            ps.setFloat(6, oc.getAccumulation());
            ps.setFloat(7, 0);
            ps.setInt(8, oc.getClientId());
            ps.setFloat(9, oc.getDogId());
            String num = "1673";
            String num2 = "2400";
            String addNUL = Integer.toString(oc.getClientId());
            while (addNUL.length() < 4) {
                addNUL = "0" + addNUL;
            }
            num += addNUL;
            num2 += addNUL;
            addNUL = Integer.toString(oc.getDogId());
            while (addNUL.length() < 4) {
                addNUL = "0" + addNUL;
            }
            num += addNUL;
            num2 += addNUL;
            num += "4";
            num2 += "3";
            ps.setString(2, num);
            ps.executeUpdate();

            q1 = "SELECT debit, credit from invoices where kod =\"7327\"";
            ps = conn.prepareStatement(q1);
            this.results = ps.executeQuery();
            this.results.next();
            debit = this.results.getFloat("debit");
            credit = this.results.getFloat("credit");

            String q5 = "Update invoices SET credit = ?, saldo=  ? WHERE kod=\"7327\"";
            ps = conn.prepareStatement(q5);
            ps.setFloat(1, credit + oc.getAccumulation());
            ps.setFloat(2, credit + oc.getAccumulation() - debit);
            ps.executeUpdate();


            q1 = "SELECT  debit, credit from invoices where kod =\"1010\"";
            ps = conn.prepareStatement(q1);
            this.results = ps.executeQuery();
            this.results.next();
            debit = this.results.getFloat("debit");
            credit = this.results.getFloat("credit");

            String q6 = "Update invoices SET debit = ?, credit = ? WHERE kod=\"1010\"";
            ps = conn.prepareStatement(q6);
            ps.setFloat(1, debit + oc.getAccumulation());
            ps.setFloat(2, credit + oc.getAccumulation());
            ps.executeUpdate();

            q1 = "SELECT debit, credit from invoices where kod =\"7327\"";
            ps = conn.prepareStatement(q1);
            this.results = ps.executeQuery();
            this.results.next();
            debit = this.results.getFloat("debit");
            credit = this.results.getFloat("credit");

           String q7 = "Update invoices SET credit = ?, saldo = ? WHERE kod=\"7327\"";
            ps = conn.prepareStatement(q7);
            ps.setFloat(1, credit + oc.getSumma());
            ps.setFloat(2, credit + oc.getSumma()- debit);
            ps.executeUpdate();

            q1 = "SELECT  debit, credit from invoices where number = ?";
            ps = conn.prepareStatement(q1);
            ps.setString(1, num2);
            System.out.println(num2);
            this.results = ps.executeQuery();
            this.results.next();
            debit = this.results.getFloat("debit");
            credit = this.results.getFloat("credit");


           String q9 = "Update invoices SET debit = ?, credit = ? WHERE number= ?";
            ps = conn.prepareStatement(q9);
            ps.setFloat(1, debit + oc.getSumma());
            ps.setFloat(2, credit + oc.getSumma());
            ps.setString(3, num2);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ReadQueryInvoices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }



    public String getHTMLtableI() throws ParseException {

        String table = "";
        try {

            while (this.results.next()) {

                Invoices iv = new Invoices();

                iv.setIvID(this.results.getInt("ivID"));
                iv.setName(this.results.getString("name"));
                iv.setNumber(this.results.getString("number"));
                iv.setType(this.results.getString("type"));
                iv.setKod(this.results.getString("kod"));
                iv.setDebit(this.results.getInt("debit"));
                iv.setCredit(this.results.getInt("credit"));
                iv.setClientID(this.results.getInt("clientID"));
                iv.setDogID(this.results.getInt("dogID"));

                table += "<tr>";

                table += "<td>";
                String addNUL = Integer.toString(iv.getIvID());
                while (addNUL.length() < 3) {
                    addNUL = "0" + addNUL;
                }
                table += addNUL;
                table += "</td>";

                table += "<td>";
                table += iv.getName();
                table += "</td>";

                table += "<td>";
                table += iv.getNumber();
                table += "</td>";

                table += "<td>";
                table += iv.getType();
                table += "</td>";

                table += "<td>";
                table += iv.getKod();
                table += "</td>";

                table += "<td>";
                table += iv.getDebit();
                table += "</td>";

                table += "<td>";
                table += iv.getCredit();
                table += "</td>";

                table += "<td>";
                table += iv.getCredit() - iv.getDebit();
                table += "</td>";

                table += "<td>";
                table += iv.getClientID();
                table += "</td>";

                table += "<td>";
                table += iv.getDogID();
                table += "</td>";

                table += "</tr>";

            }

        } catch (SQLException ex) {
            Logger.getLogger(ReadQueryInvoices.class.getName()).log(Level.SEVERE, null, ex);
        }
        table += "</table>";
        return table;
    }

}
