package com.app.users.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.exceptions.BusinessException;
import com.app.model.Role;
import com.app.model.User;
import com.app.users.register.RegisterService;
import com.app.users.register.impl.RegisterServiceImpl;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	HttpSession session = request.getSession(false);
		
		if(session==null){	//check the session first.
			response.sendError(400, "Invalid Credentials.");
		}
		
		if((int) session.getAttribute("role") != 1){	//Check the security next.
			response.sendError(401, "The requested action is not permitted.");
		}
		
		response.setContentType("text/html");
		
		/*System.out.println("Before redirect");
		response.sendRedirect(request.getContextPath()+"/registration.html");
		System.out.println("After redirect");*/
		
		PrintWriter out = response.getWriter();

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/registration.html");
		
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("lastName"));
		user.setEmail(request.getParameter("email"));
		Role role = new Role();
		role.setRole(request.getParameter("role"));
		

		try{
			
			RegisterService register = new RegisterServiceImpl();
			boolean flag = register.registerUser(user,role); 
			
			if(flag){
				user.setRole(role);
				
				response.setStatus(201);
				out.print("<h2>The following user has been registered: "+user.toString()+"</h2>");
			}else{
				response.sendError(400, "Username or email are invalid.");
			}
			
		}catch(BusinessException e){
			
			requestDispatcher = request.getRequestDispatcher("/registration.html");
			requestDispatcher.include(request, response);
			out.print("<center><span style='color:red;'>"+e.getMessage()+"</span></center>");		
		}
    }
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	/*System.out.println("In post");
		HttpSession session = request.getSession(false);
		
		if(session==null){	//check the session first.
			response.sendError(400, "Invalid Credentials.");
		}
		
		if((int) session.getAttribute("role") != 1){	//Check the security next.
			response.sendError(401, "The requested action is not permitted.");
		}
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		System.out.println("Before dispatcher");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/registration.html");
		System.out.println("After dispatcher.");
		
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("lastName"));
		user.setEmail(request.getParameter("email"));
		Role role = new Role();
		role.setRole(request.getParameter("role"));
		
		System.out.println("Before try");
		try{
			
			RegisterService register = new RegisterServiceImpl();
			boolean flag = register.registerUser(user,role); 
			
			if(flag){
				response.setStatus(201);
				out.print("<h2>The following user has been registered: "+user.toString()+"</h3>");
			}else{
				response.sendError(400, "Username or email are invalid.");
			}
			
		}catch(BusinessException e){
			
			requestDispatcher = request.getRequestDispatcher("/registration.html");
			requestDispatcher.include(request, response);
			out.print("<center><span style='color:red;'>"+e.getMessage()+"</span></center>");		}*/
	}

}
