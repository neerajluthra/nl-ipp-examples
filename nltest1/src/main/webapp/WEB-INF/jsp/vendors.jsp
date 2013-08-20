    <script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      function drawChart(rows) {
        var data = google.visualization.arrayToDataTable(rows);

        var options = {
          title: 'Vendors with non-zero Open Balance'
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart-vendors'));
        chart.draw(data, options);
      }
    var vendors = [
		['Vendor', 'Open Balance'],
		<c:forEach items="${vendorList}" var="vendor">
			['${vendor.name}', ${vendor.openBalance.amount}],
		</c:forEach>  
	];


      google.setOnLoadCallback(function() {
    	  $(function() {
    	    drawChart(vendors);
    	  });
    	});
      

    </script>
        	
				<div class="smallHeading">
					<h2>Listing all Vendors</h2>
				</div>
				    <div id="piechart-vendors" style="width: 900px; height: 500px;"></div>
				<div>
					<table id="tt2" class="easyui-datagrid" style="width:400px;height:auto;" rownumbers="true" singleSelect="true">
						<thead>
							<tr>
								<th field="vendorName" width="300"><b>Vendor Name</b></th>
								<th field="jobStatus" width="100"><b>Open Balance</b></th>
							</tr>                          
						</thead>                           
						<tbody>   
							<c:forEach items="${vendorList}" var="vendor">
							<tr>    
								<td>${vendor.name}</td>            
								<td>${vendor.openBalance.amount}</td>
							</tr> 
							</c:forEach>
						</tbody>                           
					</table>
				</div>
