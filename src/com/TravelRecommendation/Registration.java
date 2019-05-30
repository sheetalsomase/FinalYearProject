package com.TravelRecommendation;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Registration extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("Registration Servlet");
		try {
			HttpSession session=request.getSession();
			
			
			
			//String t7=req.getParameter("agree");

			
			
			int id=0;
			Dbconn.insertUpdateFromSqlQuery("insert into user_details values('"+request.getParameter("firstname")+"','"+request.getParameter("lastname")+"','"+request.getParameter("email")+"','"+request.getParameter("number")+"','"+request.getParameter("firstname")+"','"+request.getParameter("password")+"')");
			System.out.println("Done");

			PrintWriter out1 = null;
			response.setContentType("text/html;charset=UTF-8");
			out1 = response.getWriter();
			out1.println("<html><script>alert('Register Sucessfully');</script><body>");
			out1.println("");
			out1.println("</body></html>");
		/*	RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			rd.include(request, response);*/
			
		
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.include(request, response); 
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
