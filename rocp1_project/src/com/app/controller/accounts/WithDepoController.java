package com.app.controller.accounts;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.accounts.DepositService;
import com.app.accounts.WithdrawService;
import com.app.accounts.impl.DepositServiceImpl;
import com.app.accounts.impl.WithdrawServiceImpl;
import com.app.exceptions.BusinessException;
import com.app.model.Account;


public class WithDepoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public WithDepoController() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	HttpSession session = request.getSession(false);
		
		if(session==null){	//check the session first.
			response.sendError(400, "Invalid Credentials.");
		}
		
		if((int) session.getAttribute("role") != 1){	//Check the security next.
			response.sendError(401, "The requested action is not permitted.");
		}
		
		response.setContentType("text/html");
		
		response.sendRedirect(request.getContextPath()+"/withdraw.jsp");
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		HttpSession session = request.getSession(false);
		
		if(session==null){	//check the session first.
			response.sendError(400, "Invalid Credentials.");
		}
		
		if((int) session.getAttribute("role") != 1){	//Check the security next.
			response.sendError(401, "The requested action is not permitted.");
		}
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("withdraw");
		
		Account account = new Account();
		account.setAccountId(Integer.parseInt(request.getParameter("accountId")));
		double amount = Integer.parseInt(request.getParameter("amount"));
		
		
		
		try {
			
			switch(request.getParameter("type")){
				case "Withdraw":
					
					WithdrawService with = new WithdrawServiceImpl();
					with.withdraw(account, amount);
					
					out.print("<h2>Withdraw complete.</h2>");
					out.print("<h3>New balance is: "+account.getBalance()+"</h3>");
					out.print("<a href='#' onclick='history.go(-1)'>Go Back</a>");
					
					break;
				case "Deposit":
					DepositService depo = new DepositServiceImpl();
					depo.deposit(account, amount);
					
					out.print("<h2>Deposit complete.</h2>");
					out.print("<h3>New balance is: "+account.getBalance()+"</h3>");
					out.print("<a href='#' onclick='history.go(-1)'>Go Back</a>");
					
					break;
				case "Transfer":
					
					requestDispatcher = request.getRequestDispatcher("/transfer.jsp");	//setting the backslash resets the URL.
					requestDispatcher.forward(request, response);
			}
			
		} catch (BusinessException e) {
			
			requestDispatcher = request.getRequestDispatcher("/withdraw.jsp");
			requestDispatcher.include(request, response);
			//response.sendRedirect(request.getContextPath()+"/withdraw.jsp");
			out.print("<center><span style='color:red;'>"+e.getMessage()+"</span></center>");
			
		}
		
	}

}
