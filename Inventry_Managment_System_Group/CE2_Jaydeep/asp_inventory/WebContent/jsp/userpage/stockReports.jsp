<%@page import="com.inventory.model.StockCategory"%>
<%@page import="java.util.List"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script> -->
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script>
$(document).ready(function () {

    // Build the chart
    Highcharts.chart('container', {
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: 'Stock Category Wise Report'
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false
                },
                showInLegend: true
            }
        },
        series: [{
            name: 'Categories',
            colorByPoint: true,
            data: [
            	<% List<StockCategory> list = (List<StockCategory>) request.getAttribute("cats");
            		for(StockCategory s : list){
            	%>
            	{
                name: '<%=s.getCat_name()%>',
                y: <%=s.getCount()%>,
                id : <%=s.getCat_id()%>
            },<%}%>
            
           ],
           point :{
        	   events:{
        		   click: function(event){
        			   alert("clicked" + this.id);
        			   document.report.action = "loadItemReport.htm?id="+this.id;
        			   document.report.submit();
        		   }
        	   }
           }
        }]
    });
});
</script>
	<div class="row">
		<div class="col-md-12">
			<h1 class="page-head-line">Stock Report</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="panel panel-default">
				<div class="panel-heading">Stock Report</div>
				<div class="panel-body">
					<form:form name="report" id="report" method="post" modelAttribute="mainForm" action="saveAddCat.htm">
					<div id="container">
							
						</div>
						<hr />
					</form:form>
				</div>
			</div>
		</div>
		
		<div class="col-md-6">
			<div class="panel panel-default">
				<div class="panel-heading">Stock Table</div>
				<div class="panel-body">
					<table class="table table-striped">
					<thead>
						<tr>
							<th> Category Name </th>
							<th> Number of Items </th>
						</tr>
						</thead>
						<tbody>
						<%for(StockCategory s : list){ %>
						<tr>
							<td><%=s.getCat_name() %></td>
							<td><%=s.getCount() %></td>
						</tr>
						<%} %>
						</tbody>
					</table>
				</div>
			</div>
		</div>
</div>
