<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@page import="brs.*"%>

<%@include file="header.jsp"%>

    <% ResultSet qu=(ResultSet)request.getAttribute("Queue"); %>
    <% ResultSet user=(ResultSet)request.getAttribute("User"); %>
    <% String ins=(String)request.getAttribute("instrument"); %>
    <% String sb=(String)request.getAttribute("SB"); %>

    	<h3> Queue Detail <%= ins %> <%= sb %> </h3> <br/>
    	<table>
        <tr>
            <td>
             ID
            </td>
            <td>
             Name
            </td>
            <td>
             Family
            </td>
            <td>
             Type
            </td>
            <td>
             Quantity
            </td>
            <td>
             Price
            </td>
        </tr>
      <%
    	while (qu.next()) {
    	%>
    	<tr>
            <td>
             <%= qu.getString("cstmr_id") %>
            </td>
    		<td>
    		 <%= user.getString("name") %>
    		</td>
            <td>
             <%= user.getString("family") %>
            </td>
            <td>
             <%= qu.getString("type") %>
            </td>
            <td>
             <%= qu.getString("quantity") %>
            </td>
            <td>
             <%= qu.getString("price") %>
            </td>
    	</tr>
    	<%
    	}
    	%>
    	</table>
<%@include file="footer.jsp"%>