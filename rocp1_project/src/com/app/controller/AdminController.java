package com.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.app.exceptions.BusinessException;
import com.app.model.Role;
import com.app.model.User;
import com.app.search.SearchAll;
import com.app.search.SearchById;
import com.app.search.SearchByUsername;
import com.app.search.impl.SearchAllImpl;
import com.app.search.impl.SearchByIdImpl;
import com.app.search.impl.SearchByUsernameImpl;
import com.app.update.UserUpdate;
import com.app.update.impl.UserUpdateImpl;


public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if(session==null){	//check the session first.
			response.sendError(400, "Invalid Credentials.");
		}
		
		if((int) session.getAttribute("role") != 1){	//Check the security next.
			response.sendError(401, "The requested action is not permitted.");
		}
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin.jsp");
		
		if(request.getParameter("search")!=null){
			String type = request.getParameter("users");
			
			try{
				requestDispatcher.include(request, response);
				
				switch(type){
					case "Username":
						
						User u = new User();
						u.setUsername(request.getParameter("search"));
						
						SearchByUsername uName = new SearchByUsernameImpl();
						
						if(uName.userByUsername(u)){	//the summaryservlet in session_ex has helpful tips.
							
							out.print("<h2>User data is below</h2>");
							out.print("<h3>"+u.toString()+"</h3>");
							out.print("<h3>Session Id : "+session.getId()+"</h3>");
							out.print("<h3>Session Creation Time : "+new Date(session.getCreationTime())+"</h3>");
							
						}else{
							throw new BusinessException("No users found with this username.");
						}
						
						break;
					case "ID":
						
						User uid = new User();
						uid.setUserId(Integer.parseInt(request.getParameter("search")));
						
						SearchById uId = new SearchByIdImpl();

						if(uId.userById(uid)){	
							
							out.print("<h2>User data is below</h2>");
							out.print("<h3>"+uid.toString()+"</h3>");
							out.print("<h3>Session Id : "+session.getId()+"</h3>");
							out.print("<h3>Session Creation Time : "+new Date(session.getCreationTime())+"</h3>");
							
						}else{
							throw new BusinessException("No users found with this ID.");
						}
						
						break;
					case "all":
						
						List<User> ulist = new ArrayList<>();
						
						request.getParameter("search");	//doesn't need to be stored anywhere.
						
						SearchAll uAll = new SearchAllImpl();
						
						uAll.allUsers(ulist);	
							
						out.print("<h2>User data is below</h2>");
							
						for(User ul:ulist){	//print each user in the list.
							out.print("<h3>"+ul.toString()+"</h3>");
						}
							
						out.print("<h3>Session Id : "+session.getId()+"</h3>");
						out.print("<h3>Session Creation Time : "+new Date(session.getCreationTime())+"</h3>");
							
						

						break;
					default:
						throw new BusinessException("Please select a choice.");
				}
				
			}catch(BusinessException e){
				
				requestDispatcher = request.getRequestDispatcher("admin.jsp");
				requestDispatcher.include(request, response);
				out.print("<center><span style='color:red;'>"+e.getMessage()+"</span></center>");
				
			}
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if(session==null){
			response.sendError(400, "Invalid Credentials.");
		}
		
		if((int) session.getAttribute("role") != 1){
			response.sendError(401, "The requested action is not permitted.");
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if(session==null){
			response.sendError(400, "Invalid Credentials.");
		}
		
		if((int) session.getAttribute("role") != 1){
			response.sendError(401, "The requested action is not permitted.");
		}
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin.jsp");
		
		if(request.getParameter("updateUser") != null){
			
			User uu = new User();
			uu.setUsername(request.getParameter("updateUser"));
			//direct to update.jsp and then work from there. After directing, call the DAOs as needed for their functions.
			try{
				requestDispatcher = request.getRequestDispatcher("/update.jsp");
				requestDispatcher.forward(request, response);
				
				String change;
				String username = uu.getUsername();
				UserUpdate up = new UserUpdateImpl();
				
				switch(request.getParameter("params")){
					case "Username":
						uu.setUsername(request.getParameter("update"));
						change = "username";
						up.userUpdate(uu, username, change);
						break;
					case "fname":
						uu.setFirstName(request.getParameter("update"));
						change = "firstName";
						up.userUpdate(uu, username, change);
						break;	
					case "lname":
						uu.setLastName(request.getParameter("update"));
						change = "lastName";
						up.userUpdate(uu, username, change);
						break;
					case "email":
						uu.setEmail(request.getParameter("update"));
						change = "email";
						up.userUpdate(uu, username, change);
						break;
					case "password":
						uu.setPassword(request.getParameter("update"));
						change = "password";
						up.userUpdate(uu, username, change);
						break;
					case "role":
						Role r = new Role();
						r.setRole(request.getParameter("update"));
						uu.setRole(r);
						change = "role";
						up.userUpdate(uu, username, change);
						break;
					default:
						throw new BusinessException("Please select a choice.");
				}
			}catch(BusinessException e){
				
				requestDispatcher = request.getRequestDispatcher("admin.jsp");
				requestDispatcher.include(request, response);
				out.print("<center><span style='color:red;'>"+e.getMessage()+"</span></center>");
				
			}			
		}
	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}