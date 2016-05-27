<%@page import="brs.*"%>
<%
Database.getDB().logOutUser();

request.setAttribute("Message","Logged out Successfully");
request.getRequestDispatcher("index.jsp").forward(request,response);
%>