package com.TravelRecommendation.admin;

import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.TravelRecommendation.Dbconn;

public class addLatLng extends HttpServlet {
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) {
		try {
		ResultSet rs=Dbconn.getResultFromSqlQuery("select * from travellogue where City='"+request.getParameter("skeyword")+"' and lat='1'");
		int i=1;
		while(rs.next())
		{
			String lat="lat"+i;
			String lng="lng"+i;
			Dbconn.insertUpdateFromSqlQuery("update travellogue set lat='"+request.getParameter(lat)+"', lng='"+request.getParameter(lng)+"' where locationname='"+rs.getString("locationname")+"' ");
			i++;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/AdminHomePage.jsp");
		rd.include(request, response);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
