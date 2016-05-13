<%@include file="header.jsp"%>
  	<% String msg=(String)request.getAttribute("Message"); %>
  	<% msg=(msg==null)?"":msg; %>
  	
    <div id="msg">
    	<%= msg %>
    </div>

    <div id="frame">

    </div>

<%@include file="footer.jsp"%>

 