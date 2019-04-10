<form method="post" action="<%=response.encodeURL("j_security_check")%>">
<h1>SignIn</h1>
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
			<td align="left"><input type="reset"></td>
		</tr>
	</table>
</form>