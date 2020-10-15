package com.app.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutController
 */
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		response.sendRedirect("/logout.jsp");
		
		String yes = new String();
		String no = new String();
		yes = request.getParameter("yes");
		no = request.getParameter("no");
		
		if(yes == "yes"){
			if(session!=null){
			session.removeAttribute("username");
			session.removeAttribute("role");
			response.sendRedirect("login.html");
		}else if(no == "no"){
			PrintWriter out = response.getWriter();
			out.print("<a href='#' onclick='history.go(-1)'>Go Back</a>");
		}
		
		
		}
		
	}

}
