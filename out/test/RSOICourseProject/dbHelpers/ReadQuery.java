package dbHelpers;

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
import model.Clients;


public class ReadQuery {

    private Connection conn;
    private ResultSet results;

    public ReadQuery() {

        Properties props = new Properties();
        InputStream instr = getClass().getResourceAsStream("dbConn.properties");
        try {
            props.load(instr);
        } catch (IOException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            instr.close();
        } catch (IOException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        String driver = props.getProperty("driver.name");
        String url = props.getProperty("server.name");
        String username = props.getProperty("user.name");
        String password = props.getProperty("user.password");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void doRead() {

        try {
            String query = "Select * from client";
            PreparedStatement ps = conn.prepareStatement(query);
            this.results = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getHTMLtable() {
        String table = "";
        try {
            while (this.results.next()) {

                Clients client = new Clients();
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

                table += "<tr>";

                table += "<td>";
                String addNUL = Integer.toString(client.getId());
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
                table += client.getFname();
                table += "</td>";

                table += "<td>";
                table += client.getBirthdate();
                table += "</td>";

                table += "<td>";
                table += client.getSpp();
                table += "</td>";

                table += "<td>";
                table += client.getNpp();
                table += "</td>";
                
                table += "<td>";
                table += client.getIpp();
                table += "</td>";

                table += "<td>";
                table += client.getWhogive();
                table += "</td>";

                table += "<td>";
                table += client.getWhengive();
                table += "</td>";

                table += "<td>";
                table += client.getCityborn();
                table += "</td>";

                table += "<td>";
                table += client.getCity();
                table += "</td>";

                table += "<td>";
                table += client.getAddress();
                table += "</td>";

                table += "<td>";
                table += client.getMobilenum();
                table += "</td>";

                table += "<td>";
                table += client.getHomenum();
                table += "</td>";

                table += "<td>";
                table += client.getEmail();
                table += "</td>";

                table += "<td>";
                table += client.getJob();
                table += "</td>";

                table += "<td>";
                table += client.getPost();
                table += "</td>";

                table += "<td>";
                table += client.getCitydoc();
                table += "</td>";

                table += "<td>";
                table += client.getMarriage();
                table += "</td>";

                table += "<td>";
                table += client.getNat();
                table += "</td>";

                table += "<td>";
                table += client.getInvalid();
                table += "</td>";

                table += "<td>";
                table += client.getOld();
                table += "</td>";

                table += "<td>";
                table += client.getMoney();
                table += "</td>";

                table += "<td>";
                table += "<input type=\"button\" name=\"button\" value=\"Удалить\" onclick=\"return confirmDelete(" + client.getId() + ")\">";
                table += "</td>";

                table += "<td>";
                table += "<a href=update?ClientID=" + client.getId() + "> Изменить </a>";
                table += "</td>";
                table += "</tr>";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        table += "</table>";
        return table;
    }

}
