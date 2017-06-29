<%@page import="com.inventory.model.Item"%>
<%@page import="com.inventory.model.StockCategory"%>
<%@page import="java.util.List"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script>

function buy(id, quant, sold){
	alert(id);
	alert(quant);
	alert(sold);
	document.getElementById("item_id").value = id;
	document.getElementById("instock").value = quant;
	document.getElementById("sold").value = sold;
	document.getElementById("ibought").value = document.getElementById(id+"#number").value;
	alert("here");
	document.buyform.action = "userBuy.htm";
	document.buyform.submit();
}

</script>
	<div class="row">
		<div class="col-md-12">
			<h1 class="page-head-line">Search Stock</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
				<div class="panel-heading">SEARCH / BUY</div>
				<div class="panel-body">
					<form:form name="searchform" id="searchform" method="post" modelAttribute="mainForm" action="search.htm">
					<div class="col-md-12">
					<div class="col-md-4"></div>
					<div class="col-md-4">
					<div class="form-group">
							<label for="name">Category Name</label>
							<select class="form-control" name="catid">
								<option value="0">-- Select --</option>
								<% List<StockCategory> list = (List<StockCategory>) request.getAttribute("cats");
								for(StockCategory s : list){
								%>
									<option value="<%=s.getCat_id()%>"><%=s.getCat_name() %></option>
								<%} %>
							</select> 
					</div>
					<div class="form-group">
							<label for="name">Item Name</label> <input
								type="text" class="form-control" id="itemname" name="itemname"
								placeholder="Enter Item name" />
					</div>
					</div>
					<div class="col-md-4"></div>
					</div>
					<div class="col-md-12" align="center">
						<input type="submit" value="Search" class="btn btn-default"/>
					</div>
					<hr />
					</form:form>
				</div>
			</div>
		</div>
</div>

<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
				<div class="panel-heading">Search Result</div>
				<div class="panel-body">
					<form:form name="buyform" id="buyform" method="post" modelAttribute="mainForm">
					<input type="hidden" id="item_id" name="itemid"/>
					<input type="hidden" id="instock" name="instock"/>
					<input type="hidden" id="ibought" name="ibought"/>
					<input type="hidden" id="sold" name="sold"/>
					<div class="col-md-12">
					<% if(null != request.getAttribute("items")){ %>
					<table class="table table-striped">
						<thead>
							<tr>
								<th> Sr #</th>
								<th> Item Name</th>
								<th> Quantity Available </th>
								<th> Enter Quantity you want to buy </th>
								<th> Action </th>
							</tr>
						</thead>
						<tbody>
						<%List<Item> items = (List<Item>) request.getAttribute("items"); 
						int c = 1;	for(Item i : items){%>
							<tr>
								<td><%=c %></td>
								<td><%=i.getItem_name() %></td>
								<td><%=i.getInstock() %></td>
								<td><input type="number" id="<%=i.getItem_id()%>#number"/></td>
								<td><input type="button" value="Buy" onclick="buy('<%=i.getItem_id()%>',<%=i.getInstock() %>,<%=i.getSold()%>);"/></td>
							</tr>	
						<%	c++;}
						%>
						</tbody>
					</table>
					<%}else{ %>
						<h3 style="color: red;">No results found according to your Search criteria !!!</h3>
					<%} %>
					</div>
					<hr />
					</form:form>
				</div>
			</div>
		</div>
</div>
