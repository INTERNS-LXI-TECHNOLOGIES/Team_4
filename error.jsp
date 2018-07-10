<%@ page isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<title>Details</title>
<link rel="stylesheet" type="text/css" href="css\bootstrap.css">
<link rel="stylesheet" type="text/css" href="css\mystyle.css">
<meta charset="UTF-8">
</head>
<body>
	<div class="jumbotron top">
		<img class="img-fluid contacts_icon" src="Images\contacts_2.png"
			alt="Contacts_icon">
		<h2>Contacts</h2>
	</div>
	<div>
		<p class="text-info error-message">
			Sorry..something went wrong...(<%=exception%>)!
		</p>
		<%
			response.setHeader("Refresh", "3;url=getAll");
		%>;
	</div>
</body>
</html>