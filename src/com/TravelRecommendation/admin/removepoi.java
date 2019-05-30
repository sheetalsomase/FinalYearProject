package com.TravelRecommendation.admin;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.TravelRecommendation.Dbconn;



public class removepoi extends HttpServlet {

	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) {
		
		try{
			
			HttpSession session=request.getSession(false);  
			String[] Checkbox = request.getParameterValues("Checkbox");
			
			for (String s : Checkbox) {
				//Checked[i] = s;
				System.out.println("CheckBox Values" + s);
				Dbconn.insertUpdateFromSqlQuery("delete from poi_user where poi='"+s+"'");
			}
			
			
			
			PrintWriter out1 = null;
			response.setContentType("text/html;charset=UTF-8");
			out1 = response.getWriter();
			out1.println("<html><script>alert('POI Removed Successfully');</script><body>");
			out1.println("");
			out1.println("</body></html>");
			RequestDispatcher rd = request.getRequestDispatcher("/AdminHomePage.jsp");
			rd.include(request, response);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
	}
	
}
