<%
session.invalidate();
if(request.getParameter("id") != null && request.getParameter("id").length() != 0){
	response.sendRedirect("../OldMessage.jsp?id="+request.getParameter("id"));
} else {
	response.sendRedirect("../Nr4.jsp");	
}

%>