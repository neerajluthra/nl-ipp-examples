<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.List,
				 java.util.Iterator,
				 com.intuit.ds.qb.QBCustomer,
				 users.nluthra.*"%>
<H1>Customers</H1>
<%
	if (request.getParameter("customerName") != null) {
		CustomerFactory.addCustomer(request
				.getParameter("customerName"));
	}
	if (request.getParameter("deleteCustomer") != null) {
		CustomerFactory.deleteCustomerById(request
				.getParameter("deleteCustomer"));
	}
%>
<form>
	<table width=33%>
		<%
			List<QBCustomer> customers = CustomerFactory.getCustomers();
			if (customers != null && customers.size() > 0) {
				for (QBCustomer customer : customers) {
		%>
		<tr>
			<td width=20%><button name="deleteCustomer"
					value="<%=customer.getId().getValue()%>">Delete</button></td>
			<td wodth=80%><%=customer.getName()%></td>
		</tr>
		<%
			}
			}
		%>
		<tr>
			<td><button type="submit">Add Customer</button></td>
			<td><input type="text" name="customerName" /></td>
		</tr>
	</table>
</form>
