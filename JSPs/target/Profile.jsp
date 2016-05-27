<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@page import="brs.*"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@include file="header.jsp"%>


<% Database db = Database.getDB();%>
    <% String id = (String)request.getAttribute("id"); %>
      	<% id=(id==null)?Integer.toString(db.LoggedInID):id; %>


     <%Vector<String> profile = db.get_profile(Integer.parseInt(id)); %>


    	<h3> User Profile </h3> <br/>
    	<table>
        <tr>
            <td>
             Name
            </td>
            <td>
             Family
            </td>
            <td>
             Fund
            </td>
            <td>
             Role
            </td>
        </tr>
    	<tr>
            <td>
             <%= profile.get(0) %>
            </td>
    		<td>
    		 <%= profile.get(1) %>
    		</td>
            <td>
             <%= profile.get(2) %>
            </td>
            <td>
             <%= profile.get(3) %>
            </td>

    	</tr>
    	</table>
<%@include file="footer.jsp"%>