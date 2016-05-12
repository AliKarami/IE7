<%@page import="java.util.*"%>
<%@page import="brs.*"%>

<%@include file="header.jsp"%>

    <% PriorityQueue<Request> qu=(PriorityQueue<Request>)request.getAttribute("Queue"); %>
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
    	for (Request e : qu) { 
    	%>
    	<tr>
            <td>
             <%= e.cstmr.id %>
            </td>
    		<td>
    		 <%= e.cstmr.name %>
    		</td>
            <td>
             <%= e.cstmr.family %>
            </td>
            <td>
             <%= e.type %>
            </td>
            <td>
             <%= e.quantity %>
            </td>
            <td>
             <%= e.price %>
            </td>
    	</tr>
    	<%
    	}
    	%>
    	</table>
<%@include file="footer.jsp"%>