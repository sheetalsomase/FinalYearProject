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
	<title><%=Dbconn.ProjectName %> </title>
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
	 
	  jQuery.validator.addMethod("accept", function(value, element, param) {
  		  return value.match(new RegExp("." + param + "$"));
  		});
	
    // Setup form validation on the #register-form element
    $("#register-form").validate({
    	
    	
    	
    
        // Specify the validation rules
        rules: {
          
        	
        	
            firstname: {
            	
            	accept: "[a-zA-Z]+",
                required: true,
                minlength: 3
               
              
            },
            lastname: {
            	accept: "[a-zA-Z]+",
                required: true,
                minlength: 3
            },
           
            email: {
                required: true,
                email: true
            },
            password: {
                required: true,
                minlength: 6
            },
            password_confirm : {
            	required: true,
                minlength: 6,
                equalTo : "#password"
            },
            agree: "required",
            url: "required",
            number: {
                required: true,
                number: true,
                minlength: 10
            }
        },
        
        // Specify the validation error messages
        messages: {
            
        		firstname:{
         		
        		accept:"<font color=\"red\">&nbsp;&nbsp;&nbsp;Please Enter Characters Only</font>",
            	required: "<font color=\"red\">&nbsp;&nbsp;&nbsp;Please enter your first name</font>",
            	 minlength: "<font color=\"red\">&nbsp;&nbsp;&nbsp;Provide minimum 3 characters</font>"
            		    		        
        	},
            lastname:{
            	accept:"<font color=\"red\">&nbsp;&nbsp;&nbsp;Please Enter Characters Only</font>",
               	required: "<font color=\"red\">&nbsp;&nbsp;&nbsp;Please enter your last name</font>",
            	 minlength: "<font color=\"red\">&nbsp;&nbsp;&nbsp;Provide minimum 3 characters</font>"
            },
          
            password: {
                required: "<font color=\"red\">&nbsp;&nbsp;&nbsp;Please provide a password</font>",
                minlength: "<font color=\"red\">&nbsp;&nbsp;&nbsp;Weak password</font>"
                	     },
            password_confirm: {
                required: "<font color=\"red\">&nbsp;&nbsp;&nbsp;Please provide a confirm password</font>",
                minlength: "<font color=\"red\">&nbsp;&nbsp;&nbsp;Weak password</font>",
                equalTo: "<font color=\"red\">&nbsp;&nbsp;&nbsp;Mismatch password</font>"
               
            },
            email:{
            	 required: "<font color=\"red\">&nbsp;&nbsp;&nbsp;Please enter your email address</font>",
            	 email:  "<font color=\"red\">&nbsp;&nbsp;&nbsp;Please enter a valid email address</font>"
            	
            },
            agree: "<font color=\"red\">&nbsp;&nbsp;&nbsp;Please accept our policy</font>",
            url: "<font color=\"red\">&nbsp;&nbsp;&nbsp;Please enter your url</font>",
            number: {
                required: "<font color=\"red\">&nbsp;&nbsp;&nbsp;Please enter mobile number</font>",
                minlength: "<font color=\"red\">&nbsp;&nbsp;&nbsp;enter mobile number only</font>",
                number: "<font color=\"red\">&nbsp;&nbsp;&nbsp;Accept only numbers</font>"
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
				<li class="selected"><a href="index.jsp">Home</a></li>
				<li><a href="register.jsp">Register</a></li>

				<li><a href="login.jsp">Login</a></li>
				

			<%--<li><a href="contactus.jsp">Contact Us</a></li> --%>
				

			</ul>
		</div>
	</div>
	<div id="contents">
		<div class="body" id="about">
			<div>
				<div id="main">
					<div style="width: 900px">
						<div style="color: #2a2a2a; font-size: 25px; width: auto;">Create
							an New Account</div>
					</div>
					<div style="width: 900px;">
						<div
							style="border: 1px solid #2a2a2a; height: auto; min-height: 400px; width: 585px; float: left;">
							<div
								style="padding-bottom: 20px; padding-top: 20px; padding-left: 100px;">

								<%
				String insta=request.getParameter("regsta");
				if (request.getParameter("regsta") != null)
				{
					%>
								<div
									style="padding-left: 50px; padding-top: 20px; padding-bottom: 20px; color: green; font-size: 25px">Successfully
									Register</div>
								<%
				}
						
				%>
								<%
				String instaf=request.getParameter("regstaf");
				if (request.getParameter("regstaf") != null)
				{
					%>
								<div
									style="padding-left: 20px; padding-top: 20px; padding-bottom: 20px; color: red; font-size: 20px">Already
									Register Please give different email-id</div>
								<%
				}
						
				%>

								<form name="second" class="snform" id="register-form" 
									action="Registration" method="post">




									<label class="label req">First Name</label><br /> 
									<input
										type="text" class="stext bgu" id="firstname" name="firstname"
										value="" placeholder="First Name" /><br /> <label
										class="req label">Last Name</label><br /> <input type="text"
										class="stext bgu" id="lastname" name="lastname" value=""
										placeholder="Last Name" /><br /> <label class="req label">Email
										ID</label><br /> <input type="text" class="stext bgm" id="email"
										name="email" value="" placeholder="E-Mail ID" /><br /> <label
										class="req label">Password</label><br /> <input
										type="password" class="stext bgp" id="password"
										name="password" value="" placeholder="Password" /><br /> <label
										class="req label">Confirm Password</label><br /> <input
										type="password" class="stext bgp" id="password_confirm"
										name="password_confirm" value=""
										placeholder="Confirm Password" /><br /> <label
										class="req label">Contact Number</label><br /> <input
										type="text" class="stext" id="number" name="number" value=""
										maxlength="10" placeholder="9999999999" /><br />
									<div align="left">
										<label> <input type="checkbox" checked="checked"
											id="agree" name="agree" value="agree" /> I Agree to the All
											Terms of Service and Privacy Policy.</label>
											
											
											

									</div>

									<br />
									<div align="left">
										<button class="search_btn">Register</button>
									</div>
								</form>
							</div>

						</div>
						<div
							style="width: 300px; float: right; padding-left: 5px; padding-top: 80px; min-height: 400px; border: 0px solid #2a2a2a;">
							<img src="./images/index.jpg"></img>
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