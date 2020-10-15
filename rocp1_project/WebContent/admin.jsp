<%@ page import = "java.io.*,java.util.*" %>

<%
   // Get session creation time.
   Date createTime = new Date(session.getCreationTime());
   
   // Get last access time of this Webpage.
   Date lastAccessTime = new Date(session.getLastAccessedTime());

	String title = new String();
	Integer visitCount = new Integer(0);
   	String visitCountKey = new String("visitCount");
	String username = (String)session.getAttribute("username");
	String userIDKey = new String();
	String userID = new String();

   // Check if this is new comer on your Webpage.
   if (session.isNew() ){
      title = "Please Login";
      session.setAttribute(userIDKey, userID);
      session.setAttribute(visitCountKey,  visitCount);
   } 
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Login Page</title>
</head>
<body>
	<div align="center">
	<h2>Welcome <%out.print(username);%></h2>
	
	<form action="admin" method="get"> <!-- For the doGet function -->
		<table border="1px">
			
			<h3>Search</h3>	
					
			<label for="Search">Search by </label>
			
			<select name="users" id="users" form="userform">
				<option value="Username">Username</option>
				<option value="ID">ID</option>
				<option value="all">Get all users</option>
			</select>
			
			<tr>
			<td><input type="text" name="search" placeholder="Enter filter"/></td>
			</tr>
				
			<tr>
			<td style="text-align:center"><input type="submit" value="Search"></td>
			</tr>
				
		</table>
	</form>
	
	<form action="admin" method="put">
		<table border="1px">
			
			<h3>Update User</h3>	
			
			<tr>
			<td><input type="text" name="updateUser" placeholder="Enter username"/></td>
			</tr>
				
			<tr>
			<td style="text-align:center"><input type="submit" value="Update"></td>
			</tr>
				
		</table>
	</form>
	
	<h3>Withdraw/Deposit/Transfer</h3>
	<a href='/rocp1_project/accounts/withdraw'>Go!</a>
	
	<h3>Find Accounts</h3>
	<a href='/rocp1_project/accounts'>Begin Search</a>
	
	<h3>Register New User</h3>
	<a href='/rocp1_project/register'>Go!</a>
	<!--<a href='/rocp1_project/registration.html'>Register now</a> -->
	
	<a href='/rocp1_project/logout'>Logout</a>
	</div>

</body>
</html>