    <script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      function drawChart(rows) {
        var data = google.visualization.arrayToDataTable(rows);

        var options = {
          title: 'Due Payments',
          hAxis: {format:'$###,###,###.00'}, // Money format
        };

        var chart = new google.visualization.BarChart(document.getElementById('barchart-customers'));
        chart.draw(data, options);
      }
    var customers = [
		["Customer", "Amount Due"],
		<c:forEach items="${customerList}" var="customer">
			["${customer.name}", ${customer.openBalance.amount}],
		</c:forEach>  
	];


      google.setOnLoadCallback(function() {
    	  $(function() {
    	    drawChart(customers);
    	  });
    	});
      

    </script>
				<div class="smallHeading">
					<h2>Customers with payments due</h2>
				</div>
				    <div id="barchart-customers" style="width: 900px; height: 900px;"></div>
				<div>
					<table id="tt" class="easyui-datagrid" style="width:700px;height:auto;" rownumbers="true" singleSelect="true">
						<thead>
							<tr>
								<th field="custmoerName" width="200"><b>Customer Name</b></th>
								<th field="custmoerAddress" width="500"><b>Customer Address</b></th>
							</tr>                          
						</thead>                           
						<tbody>                            
							<c:forEach items="${customerList}" var="customer">
							<tr>    
								<td>${customer.name}</td>            
								<c:forEach var="address" items="${customer.address}">
									<c:set var="completeAddress" value="${address.line1} ${address.city} ${address.country} ${address.postalCode}"/>
								</c:forEach>
								<td>${completeAddress}</td>
								<c:set var="completeAddress" value=""/>
							</tr> 
							</c:forEach>
						</tbody>                           
					</table>
				</div>
