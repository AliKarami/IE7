<%@include file="header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>
  
  	<% String msg=(String)request.getAttribute("Message"); %>
  	<% msg=(msg==null)?"":msg; %>
    <div id="msg"> <%= msg %> </div>

    <form id="signform" method = "POST" action = "SignUp">
	شناسه: <br><input name = "id" type=number> </br>
	نام: <br><input name = "name" type=text> </br>
	نام خانوادگی: <br><input name = "family" type=text> </br>
	</br><input type=submit value="عضویت">
	</form>

<%@include file="footer.jsp"%>