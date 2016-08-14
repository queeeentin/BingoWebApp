<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">



<head>
	<title>User Profile</title>
	<link rel="stylesheet" href="script01.css" />
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
	<script type="text/javascript" src="javascript.js"></script>
	<script type="text/javascript" src="home.js"></script>
	
</head>

<body>
	<% 
	 	String person =  "";
		String user  = request.getParameter("user");
		if (user != null){
			person = user;
		}else {
			person = "user is null";
		}
		
	%>
	
	<h1> Welcome To the Bingo Game, <%out.println(person); %> ! </h1> 
	<form action="gotolocation.cgi" class="centered">
		<select name="GameSelection" id="BinGo">
			<option selected >Select a Bingo Game Type</option>
			<option value="Bingo.html">5x5 BingGo Game</option>
			<option value="BG4.html">4x4 BingGo Game</option>
			<option value="BG3.html">3x3 BingGo Game</option>
		</select>


		<noscript> 		
		<input type="submit" Value="Let's go!!"/>
		//whatever wrap inside the noscript tag will only display when the JS is not on
		</noscript>

	</form>

<a href="Bingo.html"> Go to 5x5 Bingo Game!!</a>


	
</body>

</html>