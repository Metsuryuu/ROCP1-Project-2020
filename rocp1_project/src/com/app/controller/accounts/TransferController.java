package com.app.controller.accounts;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.accounts.TransferService;
import com.app.accounts.impl.TransferServiceImpl;
import com.app.exceptions.BusinessException;
import com.app.model.Account;


public class TransferController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransferController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if(session==null){	//check the session first.
			response.sendError(400, "Invalid Credentials.");
		}
		
		if((int) session.getAttribute("role") != 1){	//Check the security next.
			response.sendError(401, "The requested action is not permitted.");
		}
		
		response.setContentType("text/jsp");
		
		PrintWriter out = response.getWriter();
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("transfer");
		
		Account a1 = new Account();
		a1.setAccountId(Integer.parseInt(request.getParameter("accountId")));
		double amount = Integer.parseInt(request.getParameter("amount"));
		Account a2 = new Account();
		a2.setAccountId(Integer.parseInt(request.getParameter("accountId")));
		
		try{
			
			TransferService transfer = new TransferServiceImpl();
			transfer.transfer(a1, a2, amount);
			
			out.print("<h2>Transfer complete.</h2>");
			out.print("<h3>New balance is: "+a1.getBalance()+"</h3>");
			out.print("<a href='#' onclick='history.go(-1)'>Go Back</a>");
			
		}catch(BusinessException e){
			
			requestDispatcher = request.getRequestDispatcher("transfer.jsp");
			requestDispatcher.include(request, response);
			out.print("<center><span style='color:red;'>"+e.getMessage()+"</span></center>");
			
		}
		
	}

}
