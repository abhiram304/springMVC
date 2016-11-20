<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Create New User || ${titleMessage} </title>
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
	<div class="container-fluid" >
		<div class="row">
		<div class="col-md-3"></div>
			<div class="col-md-6">
			
					<form action="/CMPE275LAB2/user" method="post">
					<div class="form-group" style="width: 100%;">
						<label for="FisrtName">First Name</label> <input type="Text"
							class="form-control" name="firstName"  placeholder="First Name">
					</div>
					<div class="form-group" style="width: 100%;">
						<label for="LastName">Last Name</label> <input type="Text"
							class="form-control" name="lastName" placeholder="Last Name">
					</div>
					<div class="form-group" style="width: 100%;">
						<label for="title">Title</label> <input type="text"
							class="form-control" name="title" placeholder="title">
					</div>
					<div class="form-group" style="width: 100%;">
						<label for="street">Street</label> <input type="text" 
							class="form-control" name="street" placeholder="Street">
					</div>
					<div class="form-group" style="width: 100%;">
						<label for="City">City</label> <input type="text"
							class="form-control" name="city" placeholder="City">
					</div>
					<div class="form-group" style="width: 100%;">
						<label for="State">State</label> <input type="text"
							class="form-control" name="state" placeholder="State">
					</div>
					<div class="form-group" style="width: 100%;">
						<label for="Zip">Zip</label> <input type="text"
							class="form-control" name="zip"  placeholder="Zip">
					</div>
					<div align="center"><button type="submit" class="btn btn-default">Create</button></div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>
