package com.TravelRecommendation.admin;

import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.TravelRecommendation.Dbconn;



public class addpoi extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) {
		
		try{
			ResultSet rs=Dbconn.getResultFromSqlQuery("select * from poi_user where poi='"+request.getParameter("poi")+"'");
			if(rs.next())
			{
				
				
				PrintWriter out1 = null;
				response.setContentType("text/html;charset=UTF-8");
				out1 = response.getWriter();
				out1.println("<html><script>alert('POI Already Exists');</script><body>");
				out1.println("");
				out1.println("</body></html>");
				RequestDispatcher rd = request.getRequestDispatcher("/AdminHomePage.jsp");
				rd.include(request, response);
			}else
			{
				Dbconn.insertUpdateFromSqlQuery("insert into poi_user values('"+request.getParameter("poi")+"')");
				
				PrintWriter out1 = null;
				response.setContentType("text/html;charset=UTF-8");
				out1 = response.getWriter();
				out1.println("<html><script>alert('POI Added Successfully');</script><body>");
				out1.println("");
				out1.println("</body></html>");
				RequestDispatcher rd = request.getRequestDispatcher("/AdminHomePage.jsp");
				rd.include(request, response);
			}
			
			
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
	}
}
