package com.TravelRecommendation.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.TravelRecommendation.Dbconn;




public class displaymap extends HttpServlet {

	public static String city="";
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//UserPOI.path("", "");
		try{
			HttpSession session=request.getSession(true);
			
		String skeyword = request.getParameter("skeyword");
		city=skeyword;
		String poi = request.getParameter("poi");
		System.out.println("---skeyword-----"+request.getParameter("skeyword"));
		System.out.println("---poi-----"+request.getParameter("poi"));
		Dbconn.insertUpdateFromSqlQuery("insert into userlog values('"+session.getAttribute("UserLoginName")+"','"+skeyword+"','"+poi+"')");
		
		ResultSet rsAlready=Dbconn.getResultFromSqlQuery("select * from userhistory where user='"+session.getAttribute("UserLoginName")+"' and keyword='"+skeyword+"' and poi='"+poi+"'");
		if(!rsAlready.next())
		{
			
		
		
		ResultSet rs=Dbconn.getResultFromSqlQuery("select DISTINCT locationname,city,poi,lat,lng from travellogue where  poi='"+poi+"' and City like '%"+skeyword+"%'");
		if(rs.next())
		{
			session.setAttribute("skeyword", skeyword);
			session.setAttribute("poi", poi);
			
			TSPNearestNeighbour.TSPProblem(skeyword, poi);
			
			int length=TSPNearestNeighbour.ar.size();
			int count=TSPNearestNeighbour.count;
			
			//UserPOI.path(skeyword, poi);
			RequestDispatcher rd = request.getRequestDispatcher("/map.jsp");
			rd.include(request, response);
		}
		else
		{
			SimpleDateFormat sd = new SimpleDateFormat(
		            "yyyy/MM/dd");
		        Date date = new Date();
		        sd.format(date);
			
			
			
			Dbconn.insertUpdateFromSqlQuery("insert into adminnotification values('"+session.getAttribute("UserLoginName")+"','"+skeyword+"','"+poi+"','"+sd.format(date)+"','0')");
			
			
			PrintWriter out1 = null;
			response.setContentType("text/html;charset=UTF-8");
			out1 = response.getWriter();
			out1.println("<html><script>alert('Data for the mentioned "+skeyword+" & "+poi+" is not available');</script><body>");
			out1.println("");
			out1.println("</body></html>");
			RequestDispatcher rd = request.getRequestDispatcher("/Homepage.jsp");
			rd.include(request, response);
		}
		}
		else
		{
			Dbconn.insertUpdateFromSqlQuery("truncate latlong1");
			ResultSet rs=Dbconn.getResultFromSqlQuery("select * from userhistory where user='"+session.getAttribute("UserLoginName")+"' and keyword='"+skeyword+"' and poi='"+poi+"'  order by order1 asc");
	         while(rs.next())
	         {
	        	 Dbconn.insertUpdateFromSqlQuery("insert into latlong1 values('"+rs.getString(4)+"','"+rs.getString(5)+"','"+rs.getString(6)+"')");
	         }
	         session.setAttribute("skeyword", skeyword);
	         session.setAttribute("poi", poi);
			RequestDispatcher rd = request.getRequestDispatcher("/map.jsp");
			rd.include(request, response);
		}
		
		
		}catch(Exception e)
		{
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/Homepage.jsp");
			rd.include(request, response);
		}
		
		
	//	UserPOI.user_poi(poi, session.getAttribute("UserLoginName").toString());
		
	}
	
}
