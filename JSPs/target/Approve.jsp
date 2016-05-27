<%@page import="java.util.*"%>
<%@page import="brs.*"%>

<%@include file="header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>
 <div id="main">
    <% String msg=(String)request.getAttribute("Message"); %>
    <% msg=(msg==null)?"":msg; %>
    <div id="msg"> <%= msg %> </div>

    <%
    Database db = Database.getDB();
    Vector<Integer[]> DepReqs = db.getDepReqs();
    %>

      <h3> Requests </h3> <br/>
      <table>
      <%
      for (int i =0 ; i<DepReqs.size(); i++) {
      %>
      <form action="requests" method="GET">
      <tr>
        <td>
        user  <%= DepReqs.get(i)[0] %>
        </td>
        <td>
        amount <%= DepReqs.get(i)[1] %>
        </td>
        <td>
          <input type="hidden" name="myI" value="<%= i %>">
          <input type="hidden" name="cstmrid_" value="<%= DepReqs.get(i)[0] %>">
          <input type="hidden" name="amount_" value="<%= DepReqs.get(i)[1] %>">
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