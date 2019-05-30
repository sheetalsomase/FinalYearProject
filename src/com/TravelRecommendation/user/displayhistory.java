package com.TravelRecommendation.user;

import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.TravelRecommendation.Dbconn;


public class displayhistory extends HttpServlet {


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) {
		try{
		HttpSession session=request.getSession(true);
		
		String locationandPOI=request.getParameter("u");
		System.out.println("-----"+locationandPOI);
		String locationAndPOI[]=locationandPOI.split("@@");
		Dbconn.insertUpdateFromSqlQuery("truncate latlong1");
		System.out.println(locationAndPOI[0]);
		System.out.println(locationAndPOI[1]);
		
		 ResultSet rs=Dbconn.getResultFromSqlQuery("select * from userhistory where user='"+session.getAttribute("UserLoginName")+"' and keyword='"+locationAndPOI[1]+"' and poi='"+locationAndPOI[0]+"'  order by order1 asc");
         while(rs.next())
         {
        	 Dbconn.insertUpdateFromSqlQuery("insert into latlong1 values('"+rs.getString(4)+"','"+rs.getString(5)+"','"+rs.getString(6)+"')");
         }
         displaymap.city=locationAndPOI[1];
         session.setAttribute("skeyword", locationAndPOI[1]);
         session.setAttribute("poi", locationAndPOI[0]);
         RequestDispatcher rd = request.getRequestDispatcher("/map.jsp");
		rd.include(request, response);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	

}
