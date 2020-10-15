package com.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SuccessfulLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SuccessfulLoginController() {
        super();
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<h2>Welcome "+request.getParameter("username")+"</h2>");
		out.print("<h3>You have logged in successfully at "+new Date()+"</h3>");
		out.print("<a href='/rocp1_project'>Click Here to Logout</a>");
	}

}
