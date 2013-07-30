<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.List,
				 java.util.Iterator,
				 com.intuit.ds.qb.QBEmployee,
				 users.nluthra.*"%>
<H1>Employees</H1>
<form>
	<table width=33%>
		<%
			List<QBEmployee> employees = EmployeeFactory.getEmployees();
			if (employees != null && employees.size() > 0) {
				for (QBEmployee employee : employees) {
		%>
		<tr>
			<td width=20%><button name="deleteEmployee"
					value="<%=employee.getId().getValue()%>" disabled>Delete</button></td>
			<td wodth=80%><%=employee.getName()%></td>
		</tr>
		<%
			}
			}
		%>
		<tr>
			<td><button type="submit">Add Employee</button></td>
			<td><input type="text" name="employeeName" /></td>
		</tr>
	</table>
</form>
