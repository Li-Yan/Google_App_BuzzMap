<%@ page import="buzzmap.TweetSearchServlet" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="./tool.js"></script>
<title>BuzzMap</title>
<style type="text/css">
.main_title {
	font-family: "Goudy Old Style";
	font-size: 97px;
	color: #09F;
	font-weight: bolder;
}
.sub_title {
	font-family: "Goudy Old Style";
	font-size: large;
	color: #099;
	font-weight: bold;
}
.buzz_frame {
	height: 270px;
	width: 700px;
}
.normal_font {
	font-family: "Goudy Old Style";
	font-size: 16px;
	font-weight: bold;
}
.input_font {
	font-family: "Goudy Old Style";
	font-size: 16px;
	font-weight: normal;
	border-top-width: thin;
	border-right-width: thin;
	border-bottom-width: thin;
	border-left-width: thin;
	border-top-style: inset;
	border-right-style: inset;
	border-bottom-style: inset;
	border-left-style: inset;
	color: #777;
}
.normal_button {
	font-family: "Palatino Linotype", "Book Antiqua", Palatino, serif;
	font-size: 16px;
	color: #444;
	background-color: #CCC;
	border: thin outset #CCC;
}
body {
	background-image: url(images/back.jpg);
	background-repeat: repeat;
}
</style>
</head>

<body>
<div align="center">
<a class="main_title">BuzzMap</a><br /><br />

<!-- Form for search -->
<form id="search_form">
<img src="images/location.png" width="15" height="15" alt="location" />&nbsp;&nbsp;
<font class="normal_font">Location:</font>
<img src="images/transparent.png" width="17" height="10" alt="transperant" />
<input id="location_textField" name="location_textField" class="input_font" type="text" size="19"/><br /><br />

<img src="images/keyword.png" width="15" height="15" alt="keyword" />&nbsp;&nbsp;
<font class="normal_font">Category:</font>
<img src="images/transparent.png" width="17" height="10" alt="transperant" />
<input id="category_textField" name="category_textField" class="input_font" type="text" size="19"/><br /><br />

<input id="search_parameter" name="search_parameter" type="hidden" />

<script language="javascript">
function search_tweets() {
	if (document.getElementById("location_textField").value == "") {
		alert("Error: please input the location!");
		return;
	}
	else if (document.getElementById("category_textField").value == "") {
		alert("Error: please input the category!");
		return;
	}
	form = document.getElementById("search_form");
	form.action = "/tweetsearch";
	form.method = "get";
	form.submit();
}
</script>

<button id="search_button" class="normal_button" onclick="search_tweets()" >Search</button><br />
</form>
<!-- End: Form for search -->

<%TweetSearchServlet.buzzURL.clear();
for (int i = 0; i < TweetSearchServlet.Top_Buzz_Number; i++) { 
	if (request.getAttribute("buzzString" + i) != null) {
		String buzzString[] = request.getAttribute("buzzString" + i).toString().split(",");
%>
		<p class="sub_title"><%out.print("No." + (i + 1) + " " + buzzString[0] + " " + buzzString[1] 
				+ "(" + buzzString[2] + ")"); %></p>
<%
		String userString = "";
		for (int j = 0; j < TweetSearchServlet.User_Per_Buzz; j++) {
			if (request.getAttribute("userString" + i + "," + j) != null) {
				userString += "@;@" + request.getAttribute("userString" + i + "," + j).toString();
			}
		}
		TweetSearchServlet.buzzURL.add(userString.substring(3));
%>
		<script type="text/javascript">
		document.write("<iframe class='buzz_frame' src='buzz.jsp?n=" + "<%=i %>" + "' ></iframe>");
		</script>
<%
	}
}
%>
</div><br /><br />
</body>
</html>