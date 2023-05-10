package controller;

import dbHelpers.credits.QueryCredit;
import dbHelpers.deposits.ReadQueryDeposit;
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


public class CListdoc extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }


    @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Create a ReadQuery helper object
        QueryCredit qc = new QueryCredit();
        

        qc.doReadOC();
        String table="";
        try {
            table = qc.getHTMLtableOC();
        } catch (ParseException ex) {
            Logger.getLogger(CListdoc.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Pass execution controll to index.jsp along with the table
        request.setAttribute("table", table);
        String url="/clistdoc.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
        
    }

}
