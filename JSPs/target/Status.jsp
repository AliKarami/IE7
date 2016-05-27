<%@page import="java.util.*"%>
<%@page import="brs.*"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@include file="header.jsp"%>

    <%
    Database db = Database.getDB();
    Vector<String> symbs = db.getSymbs("TRUE");
    %>

    	<h3> List of Symbols </h3> <br/>
    	<table>
      <%
    	for (int i = 0; i<symbs.size() ; i++) { 
    	%>
    	<tr>
    		<td>
    		 <%= symbs.get(i) %>
    		</td>
    		<td>
    		<form action="Status" method="GET">
          <input type="submit" name="action" value="Sellers">
          <input type="submit" name="action" value="Buyers">
          <input type="hidden" name="instrument" value="<%= symbs.get(i) %>">
    		</td>
    	</tr>
    	<%
    	}
    	%>
      </form>
    	</table>
<%@include file="footer.jsp"%>