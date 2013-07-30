<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!-- 
Simple JSP file to test server setup and configuration. 
-->

<%@ page
	import="java.util.List,
				 java.util.Iterator,
				 com.intuit.ds.qb.QBCustomer,
				 users.nluthra.*"%>

<HTML>
<HEAD>
<TITLE>NL Test App</TITLE>
</HEAD>
<BODY>
<a href="Hello.jsp">Home</a>
<jsp:include page="customer.jsp"></jsp:include>
<jsp:include page="employee.jsp"></jsp:include>
</BODY>
</HTML>