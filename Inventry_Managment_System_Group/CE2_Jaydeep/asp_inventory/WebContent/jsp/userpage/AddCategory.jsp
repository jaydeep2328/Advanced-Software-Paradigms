<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<div class="row">
		<div class="col-md-12">
			<h1 class="page-head-line">Add Category</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="panel panel-default">
				<div class="panel-heading">REGISTER</div>
				<div class="panel-body">
					<form:form name="addcatform" id="addcatform" method="post" modelAttribute="mainForm" action="saveAddCat.htm">
					<div class="form-group">
							<label for="name">Category Name</label> <input
								type="text" class="form-control" id="name" name="catname"
								placeholder="Enter category name" required="required" />
						</div>
						
						<input type="submit" class="btn btn-default"/>
						<hr />
					</form:form>
				</div>
			</div>
		</div>
</div>
