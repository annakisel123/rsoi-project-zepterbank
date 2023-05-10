package controller;

import dbHelpers.AddQuery;
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
import model.OD;

public class AddDeposit extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Pass execute on to doPost
        doPost(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        OD od = new OD();
//        System.out.println(request.getParameter("clientID"));

        od.setSumma(Float.parseFloat(request.getParameter("summa")));
        od.setDepName(request.getParameter("depName"));
        od.setCurName(request.getParameter("curName"));

        try {
            od.setDateOfStart(request.getParameter("dateOfStart"));
        } catch (ParseException ex) {
            Logger.getLogger(AddDeposit.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            od.setDateOfFinish(request.getParameter("dateOfFinish"));
        } catch (ParseException ex) {
            Logger.getLogger(AddDeposit.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("проверка1" + od.getDateOfStart());
        System.out.println("проверка2" + od.getDateOfFinish());
        od.setPeriod(Integer.parseInt(request.getParameter("period")));
        od.setPercent(Float.parseFloat(request.getParameter("percent")));
        od.setAccumulation(Float.parseFloat(request.getParameter("accumulation")));
        od.setClientId(Integer.parseInt(request.getParameter("clientID")));

        RQNewDeposit aq = new RQNewDeposit();

        aq.doAdd(od);

        ReadQueryInvoices rqi = new ReadQueryInvoices();

        rqi.doAddI(od);

        String url = "/deposits";
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

}
