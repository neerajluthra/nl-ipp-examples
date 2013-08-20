<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.intuit.utils.WebUtils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:ipp="">
<head>
	<title>Application Home Page</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	
	<link type="text/css" rel="stylesheet" href="css/master.css" />
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/default/easyui.css">

	<script type="text/javascript" src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>

	<script type="text/javascript" src="<%=WebUtils.APPCENTER_URL%>/Content/IA/intuit.ipp.anywhere.js"></script>
	
	<script>intuit.ipp.anywhere.setup({
    		menuProxy: '<%=WebUtils.APP_URL%>/bluedot.htm',
    		grantUrl: '<%=WebUtils.APP_URL%>/requesttoken.htm'
	});
	</script>

</head>
<body >
<div id="wrapper">
	
    <div id="header">
    	<div class="logo"><h1><a href="#">Neeraj Luthra's Test Application</a></h1></div>
    	<div class="topNavigation">
        	<ul>
            	<li><a href="javascript:void(0)" onclick="return intuit.ipp.anywhere.logout(function () { window.location.href = '<%=WebUtils.APP_URL%>/logout.htm'; });"><b>Sign out</b></a></li>
            </ul>
        </div>
    </div>
    <div id="body">
    	<div class="pageHeader">
        	<div class="pageTitle">
            	<h2>Access your Quick Books Account</h2>
            </div>
            <div class="primaryNavigation">
            	<ul>
                	<li class="active"><a href="home.htm">Home</a></li>
                	<li><a href="settings.htm">Settings</a></li>
                </ul>
            </div>
        </div>
        <div class="container">
        	<%--
        	<div class="welcome">
            	<h3>Welcome, 
            		<span class="username">
            		<% if(session.getAttribute("displayUserName") != null) {
            			out.println(session.getAttribute("displayUserName"));
            			}
            			else 
            				if(session.getAttribute("firstName") != null && session.getAttribute("lastName") != null){
            				out.println(session.getAttribute("firstName").toString() + " " + session.getAttribute("lastName").toString());
            			}%>
            		</span>
            	</h3>
                <p><a href="http://www.intuit.com/">Intuit: Going Beyond Innovation</a> As the world evolves, so do we. Yet we remain driven by our passion for inventing solutions to solve important problems, perfecting those solutions and delighting our customers.</p>
            </div>
            	 --%>
            <div class="blocks">
        	<%--
            	<div class="block latestNews">
                	<h3>Latest News</h3>
                    <ul>
                    	<li>
                        	<img src="images/raywang_136.jpg" />
                            <p><a href="http://www.forbes.com/sites/raywang/2012/02/09/how-intuit-uses-cloud-computing/"> How Intuit Uses Cloud Computing </a> DBrad Smith became Intuit's president and chief executive officer in January ....</p>
                        </li>
                    	<li>
                        	<img src="images/news.png" />
							<p><a href="http://www.pcmag.com/article2/0,2817,2379132,00.asp/"> TurboTax Premier Online (2011) </a> As much as personal tax preparation software and Web sites have evolved over the last ....</p>
                        </li>
                    	<li>
                        	<img src="images/intuit-logo.jpg" />
                            <p><a href="http://money.cnn.com/magazines/fortune/best-companies/2012/snapshots/19.html">100 Best Companies to Work For</a> The TurboTax maker uses its culture to spark innovation, with "idea jams," formal ....</p>
                        </li>
                    	<li>
                        	<img src="images/brad_smith_lg1-142x150.jpg" />
                            <p><a href="http://news.investors.com/article/596820/201201051641/intuit-anticipated-social-media-impact.htm">Intuit CEO: We Foresaw The Social Media Revolution</a> Social media's rocking the financial software industry just ....</p>
                        </li>
                    </ul>
                    <a href="http://about.intuit.com/about_intuit/press_coverage/" class="readMore">Show More</a>
                </div>
                        	 --%>
                
					<%
					String connectionStatus = (String) session
							.getAttribute("connectionStatus");
					if (connectionStatus != null
							&& connectionStatus.equalsIgnoreCase("authorized")) {
					%>
					<%--
						<p> Welcome QuickBooks user! To get started, use following links.</p>
						<br />
						<p>To see the applications available to the user, select the Intuit "blue dot" menu.</p>
						<br />
						<p>The OAuth flow has been completed. Application now has an authorized access token, it can access QuickBooks data.</p>
						<br />
						 --%>
						 <h3>
					<ul>
						<li><b><a href="home.htm" style="color:#0000FF;">Home</a></b></li>
						<li>&nbsp;</li>
						<li><b><a href="vendors.htm" style="color:#0000FF;">Get All Vendors with non-zero Open Balance</a></b></li>
						<li>&nbsp;</li>
						<li><b><a href="customers.htm" style="color:#0000FF;">Get All Customers with Payments Due</a></b></li>
					</ul>
					</h3>

					<ipp:blueDot></ipp:blueDot>
					<%
						} else {
					%>
					<%--
					<p>Do you use QuickBooks? Enable QuickBooks integration to seamlessly connect your accounting software with your work in AditiApp.</p>
					<br />
					 --%>
					<ul>
						<li><b><a href="settings.htm" style="color:#660033;">Connect Now ....</a></b></li>
					</ul>						
					<%
						session.setAttribute("flowType", "connect_button");
						}
					%>
                <%--
                <div class="block map">
                	<h3>Map the User</h3>
                    <div class="googleMap" id="googleMap" ></div>
                </div>
                 --%>
            </div>
        </div>
        <div class="container">
			<c:if test="${not empty customerList}">
	        	<%@include file="customers.jsp" %>
			</c:if>
			<c:if test="${not empty vendorList}">
				<%@include file="vendors.jsp" %>
			</c:if>
        </div>
    </div>
    <div id="footer"><div class="cont">
        <div class="copyright">Copyright &copy2012 Company, Inc.</div></div>
    </div>
</div>
</body>
</html>
