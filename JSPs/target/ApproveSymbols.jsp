<%@page import="java.util.*"%>
<%@page import="brs.*"%>

<%@include file="header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>
 <div id="main">
    <% String msg=(String)request.getAttribute("Message"); %>
    <% msg=(msg==null)?"":msg; %>
    <div id="msg"> <%= msg %> </div>

    <%
    Vector<String> symbs = db.getSymbs("FALSE");
    %>

      <h3> Requests </h3> <br/>
      <table>
      <%
      for (int i =0 ; i<symbs.size(); i++) {
      %>
      <form action="ApproveSymbols" method="GET">
      <tr>
        <td>
        symbol:  <%= symbs.get(i) %>
        </td>
        <td>
          <input type="hidden" name="name" value="<%= symbs.get(i) %>">
          <input type="submit" name="action" value="Approve">
          <input type="submit" name="action" value="Decline">
        </td>
      </tr>
         </form>
      <%
      }
      %>

      </table>
<%@include file="footer.jsp"%>