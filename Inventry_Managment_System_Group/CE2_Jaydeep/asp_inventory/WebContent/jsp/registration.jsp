<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="container">
	<div class="row">
		<div class="col-md-12">
			<h1 class="page-head-line">User Registration Form</h1>
		</div>
	</div>
	<div class="row">
	<div class="col-md-3"></div>
		<div class="col-md-6">
			<div class="panel panel-default">
				<div class="panel-heading">REGISTER</div>
				<div class="panel-body">
					<form:form name="register" id="register" method="post" modelAttribute="mainForm" action="saveRegistration.htm">
					<div class="form-group">
							<label for="name">Name</label> <input
								type="text" class="form-control" id="name" name="registration.name"
								placeholder="Enter your name" required="required" />
						</div>
						
						<div class="form-group">
							<label for="phoneno">Contact Number</label> <input
								type="number" class="form-control" id="phoneno"
								placeholder="Enter contact #" required="required" name="registration.phoneno"/>
						</div>
						
						<div class="form-group">
							<label for="exampleInputEmail1">Email address</label> <input
								type="email" class="form-control" id="exampleInputEmail1"
								placeholder="Enter email" required="required" name="registration.emailid"/>
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Password</label> <input
								type="password" class="form-control" id="exampleInputPassword1"
								placeholder="Password" required="required" name="registration.password"/>
						</div>
						
						<input type="submit" class="btn btn-default"/>
						<hr />
					</form:form>
				</div>
			</div>
		</div>
<div class="col-md-3"></div>
	</div>
</div>