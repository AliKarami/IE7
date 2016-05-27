<%@include file="header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>

  	<% String msg=(String)request.getAttribute("Message"); %>
  	<% msg=(msg==null)?"":msg; %>
    <div id="msg"> <%= msg %> </div>

    <form id="signform" method = "POST" action = "SymbolAdding">

	نام نماد: <br><input name = "name" type=text> </br>

	</br><input type=submit value="اضافه کردن">
	</form>

<%@include file="footer.jsp"%>