package com.TravelRecommendation.user;

import javax.servlet.http.HttpServlet;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.TravelRecommendation.Dbconn;



public class saveHistory extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			
		
		HttpSession session=request.getSession(true);
		String skeyword=(String) session.getAttribute("skeyword");
		String UserLoginName=(String)session.getAttribute("UserLoginName");
		String poi=(String)session.getAttribute("poi");
		int i=1;
		ResultSet rsAlready=Dbconn.getResultFromSqlQuery("select * from userhistory where user='"+session.getAttribute("UserLoginName")+"' and keyword='"+skeyword+"' and poi='"+poi+"'");
		if(!rsAlready.next())
		{
			
		
		ResultSet rs=Dbconn.getResultFromSqlQuery("select * from latlong1");
		while(rs.next())
		{
			Dbconn.insertUpdateFromSqlQuery("insert into userhistory values('"+UserLoginName+"','"+skeyword+"','"+poi+"','"+rs.getString(1)+"','"+rs.getString(2)+"','"+rs.getString(3)+"','"+i+"')");
			i++;
		}
		}
		RequestDispatcher rd = request.getRequestDispatcher("/Homepage.jsp");
		rd.include(request, response);	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
