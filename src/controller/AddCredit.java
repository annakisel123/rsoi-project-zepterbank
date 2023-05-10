package controller;

import dbHelpers.AddQuery;
import dbHelpers.credits.QueryCredit;
import dbHelpers.deposits.RQNewDeposit;
import dbHelpers.invoices.ReadQueryInvoices;

import java.io.IOException;
import java.io.PrintWriter;
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

public class AddCredit extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Pass execute on to doPost
        doPost(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        OC oc = new OC();

        oc.setClientId(Integer.parseInt(request.getParameter("clientID")));
        oc.setSumma(Float.parseFloat(request.getParameter("summa")));
        oc.setCrName(request.getParameter("crName"));
        oc.setCurName(request.getParameter("curName"));

        try {
            oc.setDateOfStart(request.getParameter("dateOfStart"));
        } catch (ParseException ex) {
            Logger.getLogger(AddDeposit.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            oc.setDateOfFinish(request.getParameter("dateOfFinish"));
        } catch (ParseException ex) {
            Logger.getLogger(AddDeposit.class.getName()).log(Level.SEVERE, null, ex);
        }
        oc.setPeriod(Integer.parseInt(request.getParameter("period")));
        oc.setPercent(Float.parseFloat(request.getParameter("percent")));
        oc.setAccumulation(Float.parseFloat(request.getParameter("accumulation")));


        QueryCredit qc = new QueryCredit();

        qc.doAdd(oc);


        ReadQueryInvoices rqi = new ReadQueryInvoices();

        rqi.doAddI(oc);


        String url = "/credits";
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

}
