<%@page import="javax.sound.midi.SysexMessage"%>
<%@page import="buzzmap.TweetSearchServlet"%>
<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="./tool.js"></script>
<title>Buzz</title>
<style type="text/css">
.normal_font {
	font-family: "Goudy Old Style";
	font-size: 16px;
	font-weight: bold;
}
.text_font {
	font-family: "Goudy Old Style";
	font-size: 11px;
	color: #555;
}
.page_layout {
	width: 650px;
}
body {
	background-image: url(images/iframe_back.jpg);
}
</style>
</head>

<body>
<div class="page_layout">
<%int n = Integer.parseInt(request.getParameter("n"));
String users[] = TweetSearchServlet.buzzURL.get(n).split("@;@");
for (int i = 0; i < users.length; i++) {
	String user[] = users[i].split("@,@");
%>
	<script type="text/javascript">
	document.write("<img src='" + "<%=user[2] %>" + "' width='37' height='37' /><br />");
	document.write("<a class='normal_font'>" + unescape("<%=user[0] %>") + ":&nbsp;</a>");
	document.write("<a class='text_font'>" + unescape("<%=user[1] %>") + "</a><br /><br />");
	</script>
<%}%>

</div>

</body>
</html>