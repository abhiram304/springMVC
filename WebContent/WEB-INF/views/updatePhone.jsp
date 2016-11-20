<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>UpdatePhone || ${titleMessage}</title>
<link rel='stylesheet' href='/stylesheets/style.css' />
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
</head>

<style>
#jumbo2{
	
}
</style>
<body><div class="container-full">
	<div class="container-fluid" id="signupbox">
		<div class="row">
		<div class="col-md-3"></div>
			<div class="col-md-6">
			
					<form action="/CMPE275LAB2/user" method="put">
					<div class="form-group" style="width: 100%;">
						<label for="phoneId">Id: </label> <input type="Text"
							class="form-control" name="phoneId" value='${phone.phoneId}'  placeholder="phoneId" readonly>
					</div>
					<div class="form-group" style="width: 100%;">
						<label for="number">Phone Number</label> <input type="Text"
							class="form-control" name="number" value='${phone.number}'  placeholder="Phone Number">
					</div>
					<div class="form-group" style="width: 100%;">
						<label for="description">Description</label> <input type="Text"
							class="form-control" name="description" value='${phone.description}' placeholder="Description">
					</div>
					<div class="form-group" style="width: 100%;">
						<label for="street">Street</label> <input type="text" value='${phone.address.street}'
							class="form-control" name="street" placeholder="Street">
					</div>
					<div class="form-group" style="width: 100%;">
						<label for="City">City</label> <input type="text" value='${phone.address.city}'
							class="form-control" name="city" placeholder="City">
					</div>
					<div class="form-group" style="width: 100%;">
						<label for="State">State</label> <input type="text"
							class="form-control" name="state" value='${phone.address.state}' placeholder="State">
					</div>
					<div class="form-group" style="width: 100%;">
						<label for="Zip">Zip</label> <input type="text"
							class="form-control" name="zip" value='${phone.address.zip}'  placeholder="Zip">
					</div>
					<div><h4>Assigned users </h4></div>
					<details>
					<div>
					<h4>List of users</h4>
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
					</div>
					</details>
					
					
					<div align="center"><button type="submit" class="btn btn-default">Update</button></div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>
