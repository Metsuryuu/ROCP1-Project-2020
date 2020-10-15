package com.app.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.credentials.dao.CredentialsDAO;
import com.app.credentials.dao.impl.CredentialsDAOImpl;
import com.app.exceptions.BusinessException;
import com.app.login.service.LoginService;
import com.app.login.service.impl.LoginServiceImpl;
import com.app.model.Role;
import com.app.model.User;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();	//set the session at login.

		response.setContentType("text/html");
		
		User user = new User();
		
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		
		LoginService service = new LoginServiceImpl();
		
		RequestDispatcher requestDispatcher = null;
		PrintWriter out = response.getWriter();
		
		session.setAttribute("username", user.getUsername());
		
		try{
			if(service.isValidUser(user)){
				//success.
				Role role = new Role();
				
				CredentialsDAO cred = new CredentialsDAOImpl();
				cred.credentials(user, role);
				
				user.setRole(role);
				session.setAttribute("role", role.getRoleId());
				
				if(user.checkRole()==1){
					
					requestDispatcher = request.getRequestDispatcher("admin.jsp");	//pass to admin servlet.
					requestDispatcher.forward(request, response);
					
				}else if(user.checkRole()==2){
					
					requestDispatcher = request.getRequestDispatcher("employee");	//pass to employee servlet.
					requestDispatcher.forward(request, response);
					
				}else if(user.checkRole()==3){
					
					requestDispatcher = request.getRequestDispatcher("standard");	//pass to standard user servlet.
					requestDispatcher.forward(request, response);
					
				}else if(user.checkRole()==4){
					
					requestDispatcher = request.getRequestDispatcher("premium");	//pass to premium user servlet.
					requestDispatcher.forward(request, response);
					
				}else{
					throw new BusinessException("No access level assigned to this username:password combination.");
				}
				
			}
		}catch(BusinessException e){
			
			//failure
			requestDispatcher = request.getRequestDispatcher("login.html");
			requestDispatcher.include(request, response);
			out.print("<center><span style='color:red;'>"+e.getMessage()+"</span></center>");
			
		}			
//			LogoutController logout = new LogoutController();
//			logout.doGet(request, response);
//			out.print("<center><span style='color:red'>Logged out successfully</span></center");
		
	}

}
