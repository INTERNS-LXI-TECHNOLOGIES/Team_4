<!DOCTYPE html>
<head>
	<title>ContactDelete</title>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="style_contacts.css">
</head>
<body>
<%@ page import="java.util.*,com.lxisoft.controller.*,com.lxisoft.model.Contact"%>
<div id="commom_top">
	<header>
	<img id="contact_pic" src="Images\contacts_3.png" alt="contact_icon">
	<h3 id="common_contactDetails">Contacts</h3>
	</header>
	</div>

<div id="individual_contacts">
<h2>
 <%   
   out.print("\t\t\t"+request.getParameter("first_name")+" ur account is removed");
   response.setHeader("Refresh", "3;url=Contacts");  
 %>
 	
 </h2>
 </div>
 </body>
 </html> 