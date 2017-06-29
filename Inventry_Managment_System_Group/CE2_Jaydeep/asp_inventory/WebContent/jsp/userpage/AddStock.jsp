<%@page import="com.inventory.model.StockCategory"%>
<%@page import="java.util.List"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
	<div class="row">
		<div class="col-md-12">
			<h1 class="page-head-line">Add Item</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="panel panel-default">
<!-- 				<div class="panel-heading">REGISTERs</div> -->
				<div class="panel-body">
					<form:form name="addcatform" id="addcatform" method="post" modelAttribute="mainForm" action="saveItem.htm">
					<div class="form-group">
							<label for="name">Select Category Type</label>
							<select class="form-control" name="catid">
								<option>-- Select --</option>
								<% List<StockCategory> list = (List<StockCategory>) request.getAttribute("cats");
								for(StockCategory s : list){
								%>
									<option value="<%=s.getCat_id()%>"><%=s.getCat_name() %></option>
								<%} %>
							</select> 
						</div>
						
					<div class="form-group">
							<label for="name">Item Name</label> <input
								type="text" class="form-control" id="name" name="itemname"
								placeholder="Enter Item name" required="required" />
						</div>
						
						<input type="submit" class="btn btn-default"/>
						<hr />
					</form:form>
				</div>
			</div>
		</div>
</div>
