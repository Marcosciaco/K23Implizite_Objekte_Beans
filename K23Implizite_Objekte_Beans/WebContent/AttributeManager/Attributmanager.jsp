<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Attribut-Manager</title>
</head>
<body>

	<h1>Attributmanager</h1>
	
	<h6>Geben Sie den Attributnamen, den Wert des Attributs und den Scope an in welchem das Attribut abgelegt werden soll:</h6>
	

	<jsp:useBean id="bean" class="AttributBean" scope="session"></jsp:useBean>
	<h3>Name</h3>
	<form method="post" action="NeuesBean.jsp"></form>
	<table>
		<tr>
			<td>Name: </td>
			<td>
				<input type="text" size ="30" name="name" value="<jsp:getProperty name="bean" property="name"/>">
			</td>
			<td>
				<span style="color:red">
				</span>
			</td>
		</tr>
	</table>
</body>
</html>