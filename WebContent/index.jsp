
<%@page import="com.TravelRecommendation.Dbconn"%>
<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8">
	
	<title><%=Dbconn.ProjectName %></title> 
	
	<link rel="stylesheet" href="css/style.css" type="text/css">
		<link rel="stylesheet" href="css/components.css" type="text/css">
</head>
<body>
	<div id="header">
		<div>
			<div id="logo">
			
			<div
					style="height: auto; align: center; font-size: 30px; color: rgb(255, 229, 204);"><%=Dbconn.ProjectName %> </div>
			</div>
			<ul id="navigation">
				<li class="selected"><a href="index.jsp">Home</a></li>
				<li><a href="register.jsp">Register</a></li>
				<li><a href="login.jsp">Login</a></li> 
				
				<%-- <li><a href="contactus.jsp">Contact Us</a></li>--%>
			</ul>
		</div>
	</div>
	<div id="contents">
		<div class="body" id="about">
			<div>
				<div id="main">
					<div style="width: 900px">
						<div style="color: #2a2a2a; font-size: 25px; width: auto;">Home</div>
					</div>
					<div style="width: 900px;">
					
					<img src="images/main.jpg" style="width: 100%;" alt="" />

					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="footer">
		<div class="footer1_rev_3x1">
			<div style="padding-left: 400px;">Copyright &#169;  All rights reserved.</div>
		</div>
	</div>
</body>
</html>