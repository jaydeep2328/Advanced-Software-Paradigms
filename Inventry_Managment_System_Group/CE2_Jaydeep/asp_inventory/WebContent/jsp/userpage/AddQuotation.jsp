<%@page import="com.inventory.model.Item"%>
<%@page import="com.inventory.model.StockCategory"%>
<%@page import="java.util.List"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<script>
var srno = 1;
function addrow(){
	var hiddenval = $("#itemname").val()+"#"+$("#itemquant").val()+"#"+$("#ppi").val()+"#"+$("#total").val();
	var row = "<tr><td>"+srno+"<input type='hidden' value="+hiddenval+" name='master.data'/></td><td>"+$("#itemname option:selected").text()+"</td><td>"+$("#itemquant").val()+"</td></tr>";
	document.getElementById("addrowtable").innerHTML = document.getElementById("addrowtable").innerHTML + row;
	srno++;
	$("#gtotal").val($("#total").val() + $("#gtotal").val());
}

function gettotal(){
	var a  = $("#itemquant").val();
	$("#total").val($("#itemquant").val() * $("#ppi").val());
}
</script>
	<div class="row">
		<div class="col-md-12">
			<h1 class="page-head-line">Add Stock</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
<!-- 				<div class="panel-heading">REGISTERs</div> -->
				<div class="panel-body">
					<form:form name="addcatform" id="addcatform" method="post" modelAttribute="mainForm" action="saveQuotation.htm">
					<input type="hidden" name="grandtotal" id="gtotal" value="0"/>
					<div class="row">
						<div class="col-md-12">
							<div class="col-md-4">
							<label for="name">Quotation Number :</label> 
							<input type="text" class="form-control" id="quotenum" name="quotenum"
							placeholder="Enter Quotation Number" required="required" />
							</div>
							
							<div class="col-md-4">
							<label for="name">Quotation Date :</label> 
							<input type="date" class="form-control" id="quotedate" name="quotedate"
							placeholder="Enter Quotation Date" required="required" />
							</div>
							
							<div class="col-md-4">
							<label for="name">Select Item :</label>
							<select class="form-control" name="catid" id="itemname">
								<option>-- Select --</option>
								<% List<Item> list = (List<Item>) request.getAttribute("items");
								for(Item s : list){
								%>
									<option value="<%=s.getItem_id()%>"><%=s.getItem_name()%></option>
								<%} %>
							</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="col-md-4">
							<label for="name">Item Quantity</label> <input
								type="text" class="form-control" id="itemquant" name="itemquant"
								placeholder="Enter Quantity" required="required" />
							</div>
							
							<div class="col-md-4">
							<label for="ppi">Price Per Item</label> <input
								type="text" class="form-control" id="ppi" name="ppi"
								placeholder="Enter price per item" required="required" onchange="gettotal();"/>
							</div>
							
							<div class="col-md-4">
							<label for="name">Total</label> <input
								type="text" class="form-control" id="total" name="total"
								placeholder="total" readonly="readonly" required="required" />
							</div>
						</div>
					</div>
					<br/>
					
					<div class="row">
					<div class="col-md-12">
						<div class="col-md-4"></div>
						<div class="col-md-4"><input type="button" class="btn btn-default" value="Add" onclick="addrow();"/></div>
						<div class="col-md-4"></div>
					</div>
					</div>
					<div class="row">
					<div class="col-md-12">
                      <!--    Striped Rows Table  -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Added items
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>Sr #</th>
                                            <th>Item Name</th>
                                            <th>Quantity</th>
                                        </tr>
                                    </thead>
                                    <tbody id="addrowtable">
                                        
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <!--  End  Striped Rows Table  -->
                </div>
                <br/>
                <div align="center">
                	<input type="submit" value="Save Quotation" class="btn btn-default"/>
                </div>
					</div>
					</form:form>
				</div>
			</div>
		</div>
</div>
