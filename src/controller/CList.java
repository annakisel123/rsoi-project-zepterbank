package controller;

import dbHelpers.credits.QueryCredit;
import dbHelpers.deposits.ReadQueryDeposit;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CList extends HttpServlet {

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
        
        //Get the HTML table from ReadQuery object
        qc.doReadCredit();
        String table=qc.getHTMLtable();
        
        //Pass execution controll to index.jsp along with the table
        request.setAttribute("table", table);
        String url="/clist.jsp";
        
        RequestDispatcher dispatcher =request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
        
    }

}
