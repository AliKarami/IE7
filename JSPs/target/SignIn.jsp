<%@include file="header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>

  	<% String msg=(String)request.getAttribute("Message"); %>
  	<% msg=(msg==null)?"":msg; %>
    <div id="msg"> <%= msg %> </div>


    <form id="signform" method = "POST" action = "SignIn">
	شناسه: <br><input name = "id" type=number> </br>

	</br><input type=submit value="ورود">
	</form>

<%@include file="footer.jsp"%>