<%@page import="com.inventory.model.Item"%>
<%@page import="java.util.List"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script> -->
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script>
<% List<Item> list = (List<Item>) request.getAttribute("items");%>
$(document).ready(function () {
var colors = Highcharts.getOptions().colors,
    categories = [<%for(Item i : list){%>'<%= i.getItem_name()%>',<%}%>],
    data = [
    	<% int c = 0;for(Item i : list){ int t = i.getExpired()+i.getInstock()+i.getPurchased()+i.getSold();%>
    	{
        y: <%=t%>,
        color: colors[<%=c%>],
        drilldown: {
            name: 'MSIE versions',
            categories: ['Purchased', 'Instock', 'Sold', 'Expired'],
            data: [<%=i.getPurchased()%>, <%=i.getInstock()%>, <%=i.getSold()%>, <%=i.getExpired()%>],
            color: colors[<%=c%>]
        }
    },<%c++;}%>],
    browserData = [],
    versionsData = [],
    i,
    j,
    dataLen = data.length,
    drillDataLen,
    brightness;


// Build the data arrays
for (i = 0; i < dataLen; i += 1) {

    // add browser data
    browserData.push({
        name: categories[i],
        y: data[i].y,
        color: data[i].color
    });

    // add version data
    drillDataLen = data[i].drilldown.data.length;
    for (j = 0; j < drillDataLen; j += 1) {
        brightness = 0.2 - (j / drillDataLen) / 5;
        versionsData.push({
            name: data[i].drilldown.categories[j],
            y: data[i].drilldown.data[j],
            color: Highcharts.Color(data[i].color).brighten(brightness).get()
        });
    }
}

// Create the chart
Highcharts.chart('container', {
    chart: {
        type: 'pie'
    },
    title: {
        text: 'Item Report'
    },
    yAxis: {
        title: {
            text: 'Total percent market share'
        }
    },
    plotOptions: {
        pie: {
            shadow: false,
            center: ['50%', '50%']
        }
    },
    tooltip: {
        valueSuffix: '%'
    },
    series: [{
        name: 'Items',
        data: browserData,
        size: '60%',
        dataLabels: {
            formatter: function () {
                return this.y > 5 ? this.point.name : null;
            },
            color: '#ffffff',
            distance: -30
        }
    }, {
        name: 'Stock',
        data: versionsData,
        size: '80%',
        innerSize: '60%',
        dataLabels: {
            formatter: function () {
                // display only if larger than 1
                return this.y > 1 ? '<b>' + this.point.name + ':</b> ' + this.y + '%' : null;
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
							<th> Item Name </th>
							<th> Purchased </th>
							<th> Instock </th>
							<th> Sold </th>
							<th> Expired </th>
						</tr>
						</thead>
						<tbody>
						<%for(Item s : list){ %>
						<tr>
							<td><%=s.getItem_name()%></td>
							<td><%=s.getPurchased()%></td>
							<td><%=s.getInstock()%></td>
							<td><%=s.getSold()%></td>
							<td><%=s.getExpired()%></td>
						</tr>
						<%} %>
						</tbody>
					</table>
				</div>
			</div>
		</div>
</div>
