<%@page import="java.util.*"%>
<%@page import="brs.*"%>
<% Database db = Database.getDB(); %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../style.css"/>
    <title> بازار بورس </title>
</head>
<body>
 <div id="top">

  <div id="logo">
    <img src="../poker.jpg" alt="poker-face" id="strike" class="bottom">
    <img src="../fanniLogo.jpg" alt="fanni-logo" id="logoimg" class="top">
  </div>

  <p>-بازار بورس مهندسی اینترنت-</p>

 </div>


<div id="menu">

  <a href="../index.jsp"> خانه </a> <hr>
  <% if(db.LoggedInID == -1){ %>
  <a href="../SignUp.jsp">  عضویت </a> <hr>
  <% } %>
  <% if(db.LoggedInID != -1){ %>
  <a href="../BuySell.jsp"> خرید و فروش </a> <hr>
  <% } %>
  <% if(db.LoggedInRole.equals("NO") || db.LoggedInRole.equals("AD")){ %>
  <a href="../DepositWithdraw.jsp"> مدیریت اعتبار </a> <hr>
  <% } %>
   <% if(db.LoggedInRole.equals("BK") || db.LoggedInRole.equals("AD")){ %>
   <a href="requests">تایید درخواست اعتبار</a> <hr>
   <% } %>
  <% if(db.LoggedInID != -1){ %>
  <a href="../Status.jsp"> وضعیت بازار </a> <hr>
  <a href="../Profile.jsp"> مشاهده پروفایل</a> <hr>
  <% } %>
  <% if(db.LoggedInRole.equals("AD")){ %>
  <a href="../OtherProfile.jsp">   مشاهده پروفایل دیگر کاربران</a> <hr>
  <a href="../Backup">تهیه پشتیبان</a> <hr>
  <a href="../ApproveSymbols.jsp">تایید درخواست اضافه کردن نماد جدید</a> <hr>
  <% } %>
  <% if(db.LoggedInRole.equals("BS") || db.LoggedInRole.equals("AD")){ %>
  <a href="../SymbolAdding.jsp">اضافه کردن نماد جدید</a> <hr>
  <% } %>
  <% if(db.LoggedInID != -1){ %>
  <a href="../Logout.jsp"> خروج </a> <hr>
  <% } %>


 </div>


 <div id="main">
    <% String msg=(String)request.getAttribute("Message"); %>
    <% msg=(msg==null)?"":msg; %>
    <div id="msg"> <%= msg %> </div>

    <%

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