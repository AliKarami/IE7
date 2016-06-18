<%@page import="brs.*"%>
<% Database db = Database.getDB(); %>
<!DOCTYPE html>
<html lang="en">
<%@ page contentType="text/html; charset=UTF-8" %>
<head>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" type="text/css" href="style.css"/>
    <title> بازار بورس </title>
</head>
<body>
 <div id="top">

  <div id="logo">
    <img src="poker.jpg" alt="poker-face" id="strike" class="bottom">
    <img src="fanniLogo.jpg" alt="fanni-logo" id="logoimg" class="top">
  </div>
  
  <p>-بازار بورس مهندسی اینترنت-</p>

 </div>
 

 <div id="menu">

  <a href="index.jsp"> خانه </a> <hr>
  <% if(db.LoggedInID == -1){ %>
  <a href="SignUp.jsp">  عضویت </a> <hr>
  <% } %>
  <% if(db.LoggedInID != -1){ %>
  <a href="BuySell.jsp"> خرید و فروش </a> <hr>
  <a href="DepositWithdraw.jsp"> مدیریت اعتبار </a> <hr>
  <a href="Status.jsp"> وضعیت بازار </a> <hr>
  <% } %>
  <% if(db.LoggedInID == 1){ %>
  <a href="admin/requests">تایید درخواست اعتبار</a> <hr>
  <a href="Backup">تهیه پشتیبان</a> <hr>
  <% } %>
  <% if(db.LoggedInID != -1){ %>
  <a href="Logout.jsp"> خروج </a> <hr>
  <% } %>


 </div>
 <div id="main">