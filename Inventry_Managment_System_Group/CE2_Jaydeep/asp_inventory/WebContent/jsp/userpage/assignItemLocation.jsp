<%@page import="com.inventory.model.StockCategory"%>
<%@page import="java.util.List"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
	<div class="row">
		<div class="col-md-12">
			<h1 class="page-head-line">Assign Location</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="panel panel-default">
<!-- 				<div class="panel-heading">REGISTERs</div> -->
				<div class="panel-body">
					<form:form name="addcatform" id="addcatform" method="post" modelAttribute="mainForm" action="saveLocation.htm">
					<div class="form-group">
							<label for="name">Select Category Type</label>
							<select class="form-control" name="catid" id="cats">
								<option>-- Select --</option>
								<% List<StockCategory> list = (List<StockCategory>) request.getAttribute("list");
								for(StockCategory s : list){
								%>
									<option value="<%=s.getCat_id()%>"><%=s.getCat_name() %></option>
								<%} %>
							</select> 
					</div>
					
					<div class="form-group">
							<label for="name">Select Item</label>
							<select class="form-control" name="location.item_id" id="items">
								<option>-- Select --</option>
							</select> 
					</div>
					
					<div class="form-group">
							<label for="name">Total Item Purchased</label>
							<input type="text" class="form-control" id="totali" name="totali"
								placeholder="total Item" readonly="readonly" />
					</div>
					
					<div class="form-group">
							<label for="name">Enter number of Items you want to place it in display</label>
							<input type="text" class="form-control" id="itemquants" name="location.item_quant"
								placeholder="Section name" required="required" />
					</div>
						
					<div class="form-group">
							<label for="name">Name Of the Section:</label> <input
								type="text" class="form-control" id="name" name="location.section_name"
								placeholder="Section name" required="required" />
						</div>
						
					<div class="form-group">
							<label for="name">Number of section in which item is placed:</label> <input
								type="text" class="form-control" id="name" name="location.section_number"
								placeholder="Section number" required="required" />
						</div>
						
						<input type="submit" class="btn btn-default"/>
						<hr />
					</form:form>
				</div>
			</div>
		</div>
</div>

<script>

$("#cats").change(function(){
	$.ajax({
		url : "getItemsByCategory.htm?id="+this.value,
		method : "POST",
		success : function(result){
			document.getElementById("items").innerHTML = result;
		}
	});
});

$("#items").change(function(){
	$.ajax({
		url : "getItemsPurchased.htm?id="+this.value,
		method : "POST",
		success : function(result){
			$("#totali").val(result);
		}
	});
});

</script>
