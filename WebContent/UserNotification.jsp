
<%@page import="com.TravelRecommendation.Dbconn"%>
<%@ page language="java" import="java.util.*" import="java.io.*"%>

<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%@ page language="java" import="java.io.*"%>
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="java.lang.*"%>
<%@ page language="java" import="java.net.*"%>

<%@ page import="java.util.*,java.text.*,java.text.SimpleDateFormat"%>
<%@ page import="java.io.*"%>
<%@ page import="java.sql.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8">
	<title><%=Dbconn.ProjectName %></title>
	<link rel="stylesheet" href="css/style.css" type="text/css">
		<link rel="stylesheet" href="css/components.css" type="text/css">
			<script src="//code.jquery.com/jquery-1.4.1.js"></script>
			<script
				src="//ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
			<link rel="stylesheet"
				href="http://code.jquery.com/ui/1.10.3/themes/redmond/jquery-ui.css" />
			<script type='text/javascript' src="js/jquery.autocomplete.js"></script>
			<link rel="stylesheet" type="text/css"
				href="js/jquery.autocomplete.css" />


</head>
<body>
	<div id="header">
		<div>
			<div id="logo">
				<div
					style="height: auto; align: center; font-size: 30px; color: rgb(255, 229, 204);"><%=Dbconn.ProjectName %></div>
			</div>
			<ul id="navigation">
				<li><a href="Homepage.jsp">Home</a></li>
				<li class="selected"><a href="Profile.jsp">Profile</a></li>
				<li class="selected"><a href="UserSearchPage.jsp">Search</a></li>
			 
				<li class="selected"><a href="UserNotification.jsp">Notification</a></li> 
				
				<li><a href="logout.jsp">Logout</a></li>
				<!-- <li><a href="#">Contact Us</a></li> -->

			</ul>
		</div>
	</div>
	<div id="contents">
		<div class="body" id="about">
			<div>
				<div id="main">
					<div style="width: 900px">
						<div
							style="color: #2a2a2a; font-size: 25px; width: 500px; float: left">Admin Homepage</div>


						<div
							style="color: #0099cc; font-weight: bold; font-size: 25px; width: auto; float: right">
							<font face="French Script MT">Hi <%=session.getAttribute("UserLoginName")%></font>
						</div>
					</div>

					<div style="width: 900px;">
						<div
							style="border: 1px solid #2a2a2a; height: auto; min-height: 400px; width: 585px; float: left;">

							<div
								style="padding-bottom: 20px; padding-top: 20px; padding-left: 50px;">
								
							<table border="1">
									<tr>
										<th style="padding-left: 10px; padding-right: 10px;">UserName</th>
										<th style="padding-left: 10px; padding-right: 10px;">Search Keyword</th>
										<th style="padding-left: 10px; padding-right: 10px;">POI</th>
										<th style="padding-left: 10px; padding-right: 10px;">Date</th>
										<th style="padding-left: 10px; padding-right: 10px;">Status</th>
									</tr>
									<%
										ResultSet rs = Dbconn
												.getResultFromSqlQuery("select * from adminnotification where user='"+session.getAttribute("UserLoginName")+"'");
										while (rs.next()) {
									%>
									<tr>
										<td style="padding-left: 10px;"><%=rs.getString(1)%></td>
										<td style="padding-left: 10px;"><%=rs.getString(2)%></td>
										<td style="padding-left: 10px;"><%=rs.getString(3)%></td>
										<td style="padding-left: 10px;"><%=rs.getString(4)%></td>
										<%
										if(rs.getString("status").equals("1"))
										{
											%>
											<td style="padding-left: 10px;">Done</td>
											<%
										}
										else
										{
											%>
											<td style="padding-left: 10px;">Not Done</td>
											<%
										}
										%>
									</tr>
									<%
										}
									%>
								</table>

								
								
								
							</div>


							<div>
							


							</div>



						</div>
						<div
							style="width: 300px; float: right; padding-top: 80px; padding-left: 5px; min-height: 400px; border: 0px solid #2a2a2a;">
							<img src="./images/indexing.png"></img>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="footer">
		<div class="footer1_rev_3x1">
			<div style="padding-left: 400px;">Copyright &#169; 2013 All
				rights reserved.</div>
		</div>
	</div>
</body>
</html>