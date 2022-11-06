<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
	<title>List of Customer</title>
	<link type="text/css" rel="stylesheet"
			href="${pageContext.request.contextPath}/resources/css/style.css"/>
			
	<!-- ${pageContext.request.contextPath} = give proper name of app and from there go to location -->
</head>
<body>
	<div id="wrapper"><!-- adding style -->
		<div id ="header">
		 <h2> CRM-customer Relationship Manager</h2>
		</div>
	</div>
	
	<div id="container">
		<div id ="content">
		
		<!-- put a button: Add Customer -->
		<input type="button" value="Add Customer"
				onclick="window.location.href='showFormForAdd'; return false;"
				class="add-button" />
				
		<!--  add a search box -->
            <form:form action="search" method="GET">
                Search customer: <input type="text" name="theSearchName" />
                
                <input type="submit" value="Search" class="add-button" />
            </form:form>		
		
		<!-- add out HTML table here -->
		
		<table>
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Action</th>
			</tr>
			<!-- looping and printing out values -->
			

			<c:forEach var="tempCustomer" items="${customers}">
			
			<!-- construct an update link-->
			<c:url var="updateLink" value="/customer/showFormForUpdate">
			<c:param name="customerId" value="${tempCustomer.id}" />
			</c:url>
			
			<!-- construct an delete link-->
			<c:url var="deleteLink" value="/customer/delete">
			<c:param name="customerId" value="${tempCustomer.id}" />
			</c:url>
			
				<tr>
					<td> ${tempCustomer.firstName}</td>
					<td> ${tempCustomer.lastName}</td>
					<td> ${tempCustomer.email}</td>
					<td>
						<!-- to display the update link -->
					   <a href="${updateLink}">Update</a>
					   |
					   <a href="${deleteLink}"
					   onclick="if (!(confirm('Are you sure you want to delete it ??'))) return false">Delete</a>
					   <!-- pop up for confirmation before deleting -->
					 </td>
				</tr>
			</c:forEach>		
		</table>
		
		</div>
	</div>
</body>
</html>