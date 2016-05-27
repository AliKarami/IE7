<%@include file="header.jsp"%>
<%@page import="brs.*"%>
<%@ page contentType="text/html; charset=UTF-8" %>
  	<% String msg=(String)request.getAttribute("Message"); %>
  	<% msg=(msg==null)?"":msg; %>
    <div id="msg">
    	<%= msg %>
    </div>

     <br>

    <div id="frame">

    <%
    if (Database.getDB().LoggedInID == -1) {
    %>
    <%@include file="SignIn.jsp"%>
    <%
    }
    %>

    </div>

<%@include file="footer.jsp"%>

 