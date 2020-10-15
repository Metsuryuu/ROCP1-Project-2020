package com.app.controller.accounts;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.accounts.AccountSearch;
import com.app.accounts.impl.AccountSearchImpl;
import com.app.exceptions.BusinessException;
import com.app.model.Account;
import com.app.model.AccountStatus;
import com.app.model.User;

/**
 * Servlet implementation class AccountSearchController
 */
public class AccountSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountSearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if(session==null){	//check the session first.
			response.sendError(400, "Invalid Credentials.");
		}
		
		if((int) session.getAttribute("role") != 1){	//Check the security next.
			response.sendError(401, "The requested action is not permitted.");
		}
		
		response.sendRedirect(request.getContextPath()+"/accounts.html");
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/accounts.html");
		
		if(request.getParameter("search")!=null){
			
			String account = request.getParameter("account");
			
			try {
				
				requestDispatcher.include(request, response);
				
				switch(account){
					case "User":
						
						AccountSearch search = new AccountSearchImpl();
						User user = new User();
						Account au = new Account();
						
						user.setUserId(Integer.parseInt(request.getParameter("search")));
						search.byUser(au, user);
						
						out.print("<h2>Account information: </h2>");
						out.print("<h3>"+au.toString()+"</h3>");
						out.print("<a href='#' onclick='history.go(-1)'>Go Back</a>");
						
						break;
					case "ID":
						
						AccountSearch searchi = new AccountSearchImpl();
						Account ai = new Account();
						
						ai.setAccountId(Integer.parseInt(request.getParameter("search")));
						searchi.byId(ai);
						
											
						out.print("<h2>Account information: </h2>");
						out.print("<h3>"+ai.toString()+"</h3>");
						out.print("<a href='#' onclick='history.go(-1)'>Go Back</a>");
						
						break;
					case "Status":
						
						AccountSearch searchs = new AccountSearchImpl();
						Account as = new Account();
						AccountStatus stat = new AccountStatus();
						
						stat.setStatusId(Integer.parseInt(request.getParameter("search")));
						as.setStatus(stat);
						searchs.byStatus(as);
						
											
						out.print("<h2>Account information: </h2>");
						out.print("<h3>"+as.toString()+"</h3>");
						out.print("<a href='#' onclick='history.go(-1)'>Go Back</a>");
						break;
					case "all":
						AccountSearch searcha = new AccountSearchImpl();
						List<Account> aList = new ArrayList<>();
	
						searcha.byAll(aList);
						
											
						out.print("<h2>Account information: </h2>");
						
						for(Account a : aList){
							out.print("<h3>"+a.toString()+"</h3>");
						}
						
						out.print("<a href='#' onclick='history.go(-1)'>Go Back</a>");
						
						break;
				}
				
			} catch (BusinessException e) {
				
				requestDispatcher = request.getRequestDispatcher("/accounts.html");
				requestDispatcher.include(request, response);
				//response.sendRedirect(request.getContextPath()+"/withdraw.jsp");
				out.print("<center><span style='color:red;'>"+e.getMessage()+"</span></center>");
				
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
