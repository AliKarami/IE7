<%@include file="header.jsp"%>

	<% String msg=(String)request.getAttribute("Message"); %>
  	<% msg=(msg==null)?"":msg; %>
    <div id="msg"> <%= msg %> </div>

	<form id="buyform" method = "POST" action = "Buy">
	شنایه: <br><input name = "id" type=number> </br>
	شاخص: <br><input name = "instrument" type=text> </br>
	قیمت: <br><input name = "price" type=number> </br>
	مقدار: <br><input name = "quantity" type=number> </br>
	نوع: <br><input name = "type" type=text> </br>
	</br><input type=submit value="خرید">
	</form>
	<form id="sellform" method = "POST" action = "Sell">
	شناسه: <br><input name = "id" type=number> </br>
	شاخص: <br><input name = "instrument" type=text> </br>
	قیمت: <br><input name = "price" type=number> </br>
	مقدار: <br><input name = "quantity" type=number> </br>
	نوع: <br><input name = "type" type=text> </br>
	</br><input type=submit value="فروش">
	</form>

<%@include file="footer.jsp"%>