<%@include file="header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>
  	<% String msg=(String)request.getAttribute("Message"); %>
  	<% msg=(msg==null)?"":msg; %>
    <div id="msg"> <%= msg %> </div>

	<form id="depositform" method = "POST" action = "Deposit">
	مقدار: <br><input name = "amount" type=number> </br>
	</br><input type=submit value="افزایش اعتبار">
	</form>
	<form id="withdrawform" method = "POST" action = "Withdraw">
	مقدار: <br><input name = "amount" type=number> </br>
	</br><input type=submit value="کاهش اعتبار">
	</form>

<%@include file="footer.jsp"%>