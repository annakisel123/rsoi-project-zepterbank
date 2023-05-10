package controller;

import dbHelpers.AddQuery;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Clients;

public class AddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Pass execute on to doPost
        doPost(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String fname = request.getParameter("fname");
        String birthdate = request.getParameter("birthdate");
        String spp = request.getParameter("spp");
        String npp = request.getParameter("npp");
        String ipp = request.getParameter("ipp");
        String whogive = request.getParameter("whogive");
        String whengive = request.getParameter("whengive");
        String cityborn = request.getParameter("cityborn");
        String city = request.getParameter("city");
        String address = request.getParameter("address");
        String mobilenum = request.getParameter("mobilenum");
        String homenum = request.getParameter("homenum");
        String email = request.getParameter("email");
        String job = request.getParameter("job");
        String post = request.getParameter("post");
        String citydoc = request.getParameter("citydoc");
        String marriage = request.getParameter("marriage");
        String nat = request.getParameter("nat");
        String invalid = request.getParameter("invalid");
        String old = request.getParameter("old");
        int money = Integer.parseInt(request.getParameter("money"));

        Clients client = new Clients();

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



        AddQuery aq = new AddQuery();


        //pass the client to addQuery to add to the database
        aq.doAdd(client);


        //pass execution control to the Read servlet
        String url = "/clients";
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

}
