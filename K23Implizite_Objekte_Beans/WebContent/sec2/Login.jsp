<form method="post" action="<%=response.encodeURL("j_security_check")%>">
<h1>LogIn</h1>
	<table border="0">
		<tr>
			<th align="right">Username:</th>
			<td align="left"><input type="text" name="j_username"></td>
		</tr>
		<tr>
			<th align="right">Password:</th>
			<td align="left"><input type="password" name="j_password"></td>
		</tr>
		<tr>
			<td align="right"><input type="submit" value="Login"></td>
			<td align="left"><input type="reset" value="Reset"></td>
		</tr>
	</table>
</form>
<a href="http://localhost:8081/K23Implizite_Objekte_Beans/Nr4.jsp">Back to Messages</a>