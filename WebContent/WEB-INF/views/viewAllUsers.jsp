<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>All Users || ${titleMessage}</title>

</head>
<body>
	<div class="container-fluid">
		<div class="jumbotron" id="jumbo2">
			
			<h2 id="title">All Users in the system</h2>
			<h4 id="title2"></h4>
		</div>
		<div class="container-full">
	<div class="container-fluid" id="signupbox">
		<div class="row">
		<div class="col-md-3"></div>
			<div class="col-md-6">
			<h1></h1>
			<%-- <p>${userList}</p> --%>
			<table class="table">
    <thead>
      <tr>
        <th>Firstname</th>
        <th>Lastname</th>
        <th>Email</th>
        <th>Street</th>
        <th>City</th>
        <th>State</th>
        <th>Zip</th>
        <th>Phones</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${userList}" var="a">
      <tr>
        <td>${a.firstName}</td>
        <td>${a.lastName}</td>
        <td>${a.title}</td>
        <td>${a.address.street}</td>
        <td>${a.address.city}</td>
        <td>${a.address.state}</td>
		<td>${a.address.zip}</td>        
      	<td>${a.phones}</td>
      </tr>
      </c:forEach>
    </tbody>
  </table>
			
			</div></div></div>
				</div>
		</div>
</body>
</html>
