
<%@page import="com.TravelRecommendation.user.Location"%>
<%@page import="com.TravelRecommendation.user.displaymap"%>

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
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<script type="text/javascript"
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAeVxw7rucFtQ7Lx_DEmcwSG-dtNZfejVs&callback=initMap"></script>
	<script type="text/javascript" src="js/map.js"></script>
	<title><%=Dbconn.ProjectName%></title>
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

			<script>
				$(function() {
					// Setup form validation on the #register-form element
					$("#search-form")
							.validate(
									{

										// Specify the validation rules
										rules : {

											skeyword : {
												required : true,
												minlength : 2
											}

										},

										// Specify the validation error messages
										messages : {
											skeyword : {
												required : "<font color=\"red\">&nbsp;&nbsp;&nbsp;Please enter your keyword</font>",
												minlength : "<font color=\"red\">&nbsp;Provide minimum 2 characters</font>"
											}

										},

										submitHandler : function(form) {
											form.submit();
										}
									});

				});
			</script>
</head>
<body>
	<div id="header">
		<div>
			<div id="logo">
				<div
					style="height: auto; align: center; font-size: 30px; color: rgb(255, 229, 204);"><%=Dbconn.ProjectName%></div>
			</div>
			<ul id="navigation">
					<li><a href="Homepage.jsp">Home</a></li>
				<li class="selected"><a href="Profile.jsp">Profile</a></li>
				<li class="selected"><a href="UserSearchPage.jsp">Search</a></li>
			 <li class="selected"><a href="UserHistory.jsp">History</a></li>
				<li class="selected"><a href="UserNotification.jsp">Notification</a></li> 
				
				<li><a href="logout.jsp">Logout</a></li>

				<%--<li><a href="#">Contact Us</a></li> --%>

			</ul>
		</div>
	</div>
	<div id="contents">
		<div class="body" id="about">
			<div>
				<div id="main">
					<div style="width: 900px">

						<div
							style="color: #2a2a2a; font-size: 25px; width: 100%; float: left">
							Home Page
							<div style="height: 1000px;">

								<div id="map-canvas" style="height: 70%; border-color: black;"></div>
								<div style="padding-right: 250px; padding-top: 200px;"></div>
							</div>

						</div>


						<div
							style="color: #0099cc; font-weight: bold; font-size: 25px; width: auto; float: right">
							<font face="French Script MT">Hi <%=session.getAttribute("UserLoginName")%></font>
						</div>

					</div>

					<div style="width: 900px;">



						<table border="1">
							<tr>
								<th>Sr.No.</th>
								<th>Location name</th>
								<th>Latitude</th>
								<th>Longitude</th>
								<th>Distance</th>
								<th>Seasons</th>
								<th>Positive</th>
								<th>Negative</th>
								<th>Review</th>
							</tr>
							<%
								int i = 1;
								String locationname = "";
								String lat = "";
								String lng = "";
								double distance = 0.0;
								double distanceTotal = 0.0;

								ResultSet rs = Dbconn.getResultFromSqlQuery("select distinct location,latitude,longitude from latlong1");
								while (rs.next()) {

									System.out.println("  " + rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3));

									ResultSet rs2=Dbconn.getResultFromSqlQuery("select * from travellogue where locationname='"+rs.getString(1)+"' and city='"+displaymap.city+"'");
									if (rs2.next()) {
										if (i != 1) {
											Location loc1 = new Location(locationname, Float.valueOf((String) lat),
													Float.valueOf((String) lng));
											Location loc2 = new Location(rs2.getString("locationname"),
													Float.valueOf((String) rs2.getString("lat")),
													Float.valueOf((String) rs2.getString("lng")));
											distance = loc1.distanceTo(loc2);
										}
										System.out.println("  " + rs2.getString("Seasons") + "  " + rs2.getString("locationname") + "  "
												+ rs2.getString("lat") + "  " + rs2.getString("lng"));
										System.out.println(locationname + "-kkkkkkkkkkkkkkkk-----" + rs2.getString("locationname") + "dis "
												+ distance);
										locationname = rs2.getString("locationname");
										lat = rs2.getString("lat");
										lng = rs2.getString("lng");
							%>
							<tr>
								<td><%=i%></td>
								<td><%=locationname%></td>
								<td><%=lat%></td>
								<td><%=lng%></td>
								<%
									distanceTotal += distance * 1.609344;
											String imagepath = "images/" + locationname + ".jpg";
								%>
								<td><%=distance * 1.609344%></td>
								<td><%=rs2.getString("Seasons")%></td>
								<td><%=rs2.getString("pos")%></td>
								<td><%=rs2.getString("neg")%></td>
								<td title="<%=rs2.getString("review")%>"><img
									src="<%=imagepath%>" style="width: 100px; height: 100px;"
									alt="" /></td>
							</tr>
							<%
								i++;
									}

								}
							%>





						</table>
						<br /> <br /> <br /> <br />
						<form action="saveHistory" method="post">
							<div align="left">
										<button class="search_btn">Save</button>
										
									</div>
							
							
						</form>
						Total Distance
						<%=distanceTotal%>

						<center>



							<!-- 					<div
							style="border: 1px solid #2a2a2a; height: auto; min-height: 400px; width: 585px; float: left;">

							<div
								style="padding-bottom: 20px; padding-top: 20px; padding-left: 50px;">
								
							</div>


							<div>
							

							</div>



						</div>
						<div
							style="width: 300px; float: right; padding-top: 80px; padding-left: 5px; min-height: 400px; border: 0px solid #2a2a2a;">
							<img src="./images/indexing.png"></img>
						</div> -->
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