package controller;

import dbHelpers.credits.QueryCredit;
import dbHelpers.deposits.ReadQueryDeposit;
import dbHelpers.invoices.ReadQueryInvoices;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CloseCredit extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


       //get id
       int DogID = Integer.parseInt(request.getParameter("DogID"));
       //create a DeleteQuery object
       
       ReadQueryInvoices qri = new ReadQueryInvoices();
       qri.doCloseCI(DogID);      
       
       
       QueryCredit qc = new QueryCredit();
       //use DeleteQuery to delete object
       qc.doDelete(DogID);
       

       
       //pass execution on to the REadServlet
       String url="/credits";
        
        RequestDispatcher dispatcher =request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

}
