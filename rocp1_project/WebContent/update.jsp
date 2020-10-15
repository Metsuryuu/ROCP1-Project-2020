<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update User</title>
</head>
<body>
	<div align="center">
	<form action="admin" method="put">
		<table border="1px">
			
			<label for="Search">What would you like to update? </label>
			
			<select name="params" id="params" form="paramform">
				<option value="Username">Username</option>
				<option value="ID">ID</option>
				<option value="fname">First name</option>
				<option value="lname">Last name</option>
				<option value="email">Email</option>
				<option value="password">Password</option>
				<option value="role">Role</option>
			</select>	
			
			<tr>
			<td><input type="text" name="update"/></td>
			</tr>
				
			<tr>
			<td style="text-align:center"><input type="submit" value="Update"></td>
			</tr>
				
		</table>
	</form>
	</div>

</body>
</html>