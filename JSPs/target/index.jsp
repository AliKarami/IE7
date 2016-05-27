<%@include file="header.jsp"%>
<%@page import="brs.*"%>
<%@ page contentType="text/html; charset=UTF-8" %>
  	<% String msg=(String)request.getAttribute("Message"); %>
  	<% msg=(msg==null)?"":msg; %>
  	<% Database db = Database.getDB(); %>
    <div id="msg">
    	<%= msg %>
    </div>

     <%
        //allow access only if session exists
        int id = null;
        if(session.getAttribute("id") != null){
            user = (int) session.getAttribute("id");
            String userName = null;
            String sessionID = null;
            Cookie[] cookies = request.getCookies();
            if(cookies !=null){
                for(Cookie cookie : cookies){
        	        if(cookie.getName().equals("id")) userName = cookie.getValue();
        	        if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
            }
        }
        %>
        <h3>Hi <%=userName %>, Login successful. Your Session ID=<%=sessionID %></h3>
        <br>
        User=<%=user %>
        <br>

    <div id="frame">

    </div>

<%@include file="footer.jsp"%>

 