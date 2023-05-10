/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dbHelpers.DeleteQuery;
import dbHelpers.ReadRecord;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Clients;
import model.OD;

public class NewDeposit extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        OD od = new OD();
        od.setClientId(Integer.parseInt(request.getParameter("ClientID")));
        od.setSumma(Float.parseFloat(request.getParameter("summa")));
        od.setCurId(Integer.parseInt(request.getParameter("curID")));
        od.setDepId(Integer.parseInt(request.getParameter("depID")));
        try {
            od.setDateOfStart(request.getParameter("dateOfStart"));
        } catch (ParseException ex) {
            Logger.getLogger(NewDeposit.class.getName()).log(Level.SEVERE, null, ex);
        }
        od.setPercent(od.getDepId(),od.getCurId());
        od.setPeriod(Integer.parseInt(request.getParameter("period")));
        od.setAccumulation((int) od.getPeriod(), od.getPercent(), od.getSumma());
        try {
            od.setDateOfFinish(od.getDateOfStart(), (int) od.getPeriod());
        } catch (ParseException ex) {
            Logger.getLogger(NewDeposit.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(od.getDateOfStart());
        System.out.println(od.getDateOfFinish());
        

        int ClientID = Integer.parseInt(request.getParameter("ClientID"));
        ReadRecord rr = new ReadRecord(ClientID);
        rr.doRead();
        Clients client = rr.getClient();

        request.setAttribute("client", client);
        request.setAttribute("od", od);

        String url = "/open.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}
