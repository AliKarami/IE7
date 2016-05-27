<%@include file="header.jsp"%>
<%@page import="brs.*"%>
<%@ page contentType="text/html; charset=UTF-8" %>
  	<% String msg=(String)request.getAttribute("Message"); %>
  	<% msg=(msg==null)?"":msg; %>
  	<% Database db = Database.getDB(); %>
    <div id="msg">
    	<%= msg %>
    </div>

    <div id="frame">

    </div>

<%@include file="footer.jsp"%>

 