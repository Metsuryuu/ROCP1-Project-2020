<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transfer</title>
</head>
<body>
	<div align="center">
	<h2>Please fill out to transfer</h2>
	<form action="accounts/transfer" method="post">
		<table border="1px">
			<tr>
			<td><input type="text" name="accountId" placeholder="Enter Account ID"/></td>
			</tr>
			
			<tr>
			<td><input type="text" name="amount" placeholder="Enter amount to transfer"/></td>
			</tr>
			
			<tr>
			<td><input type="text" name="amount" placeholder="Enter recipient Account ID"/></td>
			</tr>
			
			<tr>
			<td style="text-align:center"><input type="submit" value="Send Request"/></td>
			</tr>
			
		</table>
	</form>
	
	<a href='#' onclick='history.go(-1)'>Return</a>
	
	</div>

</body>
</html>