<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@page import="brs.*"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@include file="header.jsp"%>

    <% String name =(String)request.getAttribute("name"); %>
    <% String family =(String)request.getAttribute("family"); %>
    <% String fund =(String)request.getAttribute("fund"); %>
    <% String role =(String)request.getAttribute("role"); %>

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
             <%= name %>
            </td>
    		<td>
    		 <%= family %>
    		</td>
            <td>
             <%= fund %>
            </td>
            <td>
             <%= role %>
            </td>

    	</tr>
    	</table>
<%@include file="footer.jsp"%>