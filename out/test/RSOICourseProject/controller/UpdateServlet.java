/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dbHelpers.UpdateQuery;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Clients;

public class UpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


      
        int ClientID = Integer.parseInt(request.getParameter("clientID"));
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String fname = request.getParameter("fname");
        String birthdate = request.getParameter("birthdate");
        String spp = request.getParameter("spp");
        String npp = request.getParameter("npp");
        String ipp = request.getParameter("ipp");
        String whogive = request.getParameter("whogive");
        String whengive = request.getParameter("whengive");
        String city = request.getParameter("city");
        String cityborn = request.getParameter("cityborn");
        String address = request.getParameter("address");
        String mobilenum = request.getParameter("mobilenum");
        String homenum = request.getParameter("homenum");
        String email = request.getParameter("email");
        String job = request.getParameter("job");
        String post = request.getParameter("post");
        String marriage = request.getParameter("marriage");
        String citydoc = request.getParameter("citydoc");
        String nat = request.getParameter("nat");
        String invalid = request.getParameter("invalid");
        String old = request.getParameter("old");
        int money = Integer.parseInt(request.getParameter("money"));
        System.out.println(request.getParameter("old"));
   

        Clients client = new Clients();
        
        client.setId(ClientID);
        client.setName(name);
        client.setSurname(surname);
        client.setFname(fname);
        client.setBirthdate(birthdate);
        client.setSpp(spp);
        client.setNpp(npp);
        client.setIpp(ipp);
        client.setWhogive(whogive);
        client.setWhengive(whengive);
        client.setCity(city);
        client.setCityborn(cityborn);
        client.setAddress(address);
        client.setMobilenum(mobilenum);
        client.setHomenum(homenum);
        client.setEmail(email);
        client.setJob(job);
        client.setPost(post);
        client.setMarriage(marriage);
        client.setCitydoc(citydoc);
        client.setNat(nat);
        client.setInvalid(invalid);
        client.setOld(old);
        client.setMoney(money);
       
        UpdateQuery uq = new UpdateQuery();
        uq.doUpdate(client);
        String url = "/clients";
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

}
