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
import model.OC;
import model.OD;

public class NewCredit extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        OC oc = new OC();
        oc.setClientId(Integer.parseInt(request.getParameter("ClientID")));
        oc.setSumma(Float.parseFloat(request.getParameter("summa")));
        oc.setCurId(Integer.parseInt(request.getParameter("curID")));
        oc.setCrId(Integer.parseInt(request.getParameter("crID")));
        try {
            oc.setDateOfStart(request.getParameter("dateOfStart"));
        } catch (ParseException ex) {
            Logger.getLogger(NewCredit.class.getName()).log(Level.SEVERE, null, ex);
        }
        oc.setPercent(oc.getCrId(),oc.getCurId());
        oc.setPeriod(Integer.parseInt(request.getParameter("period")));
        oc.setAccumulation((int) oc.getPeriod(), oc.getPercent(), oc.getSumma());
        try {
            oc.setDateOfFinish(oc.getDateOfStart(), (int) oc.getPeriod());
        } catch (ParseException ex) {
            Logger.getLogger(NewCredit.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(oc.getDateOfStart());
        System.out.println(oc.getDateOfFinish());
        

        int ClientID = Integer.parseInt(request.getParameter("ClientID"));
        ReadRecord rr = new ReadRecord(ClientID);
        rr.doRead();
        Clients client = rr.getClient();

        request.setAttribute("client", client);
        request.setAttribute("oc", oc);

        String url = "/copen.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}
