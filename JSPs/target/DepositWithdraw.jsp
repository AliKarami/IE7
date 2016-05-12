<%@include file="header.jsp"%>

  	<% String msg=(String)request.getAttribute("Message"); %>
  	<% msg=(msg==null)?"":msg; %>
    <div id="msg"> <%= msg %> </div>

	<form id="depositform" method = "POST" action = "Deposit">
	شناسه: <br><input name = "id" type=number> </br>
	مقدار: <br><input name = "amount" type=number> </br>
	</br><input type=submit value="افزایش اعتبار">
	</form>
	<form id="withdrawform" method = "POST" action = "Withdraw">
	شناسه: <br><input name = "id" type=number> </br>
	مقدار: <br><input name = "amount" type=number> </br>
	</br><input type=submit value="کاهش اعتبار">
	</form>

<%@include file="footer.jsp"%>