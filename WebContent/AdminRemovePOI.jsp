<?xml version="1.0" encoding="iso-8859-1" ?>

<%@page import="com.TravelRecommendation.Dbconn"%>
<%@ page language="java" import="java.util.*" import="java.io.*"%>

<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.io.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.text.SimpleDateFormat"%>
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

			<script>
  $(function() {
    // Setup form validation on the #register-form element
    $("#login-form").validate({
    
        // Specify the validation rules
        rules: {
          
        	
           
            logemail: {
                required: true,
                email: true
            },
            logpassword: {
                required: true,
                minlength: 6
            }
            
        },
        
        // Specify the validation error messages
        messages: {
            logpassword: {
                required: "<font color=\"red\">&nbsp;&nbsp;&nbsp;Please provide a password</font>",
                minlength: "<font color=\"red\">&nbsp;&nbsp;&nbsp;Weak password</font>"
            },
            
            logemail:{
            	 required: "<font color=\"red\">&nbsp;&nbsp;&nbsp;Please enter your email address</font>",
            	 email:  "<font color=\"red\">&nbsp;&nbsp;&nbsp;Please enter a valid email address</font>"
            	
            }
        },
        
        submitHandler: function(form) {
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
					style="height: auto; align: center; font-size: 30px; color: rgb(255, 229, 204);"><%=Dbconn.ProjectName %></div>
			</div>
			<ul id="navigation">
				<li  class="selected"><a href="AdminHomePage.jsp">Home</a></li>

				
				
				 <li><a href="AdminSearchPage.jsp">Search</a></li>
				<li><a href="AdminAddPOI.jsp">Add POI</a></li>
				<li><a href="AdminRemovePOI.jsp">Remove POI</a></li>
				<li><a href="#">Notification</a></li> 
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
						<div style="color: #2a2a2a; font-size: 25px; width: auto;">User
							Login</div>
					</div>
					<div style="width: 900px;">
						<div
							style="border: 1px solid #2a2a2a; height: auto; min-height: 400px; width: 585px; float: left;">

							<%

%>

							<div
								style="padding-bottom: 20px; padding-top: 20px; padding-left: 100px;">

								

								
								<form name="SearchForm" action="RemovePoi" method="post">


									
										<%
											ResultSet rsPoi=Dbconn.getResultFromSqlQuery("select * from poi_user");
											while(rsPoi.next())
											{
												%>
											<input type="checkbox" name="Checkbox" value="<%=rsPoi.getString(1) %>"/><b><%=rsPoi.getString(1) %></b><br/>	
												<%
											}
											
											%>
											
											

										
									<div align="left">
										<button class="search_btn">Remove POI</button>
										
									</div>
								</form>
							</div>
						</div>
						<div
							style="width: 300px; float: right; padding-top: 80px; padding-left: 5px; min-height: 400px; border: 0px solid #2a2a2a;">
							<img src="./images/login.jpg"></img>
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