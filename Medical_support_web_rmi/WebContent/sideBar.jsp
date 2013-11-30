<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Vector"%>
<%@page import="Model.User"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div id="sidebar">

	<%
	
		User user = (User) session.getAttribute("user");
		String name = "";
		if (user == null) {
			String redirectURL = request.getContextPath()
					+ "/login.jsp";
			response.sendRedirect(redirectURL);
			return;
		} else {
			name = user.getUsername();
		}
	%>
 
	<div id="sidebar-wrapper">
		<!-- Sidebar with logo and menu -->

		<h1 id="sidebar-title">
			<a href="#">BookStore Admin</a>
		</h1>

		<!-- Logo (221px wide) -->
		<a href="#"><img id="logo" src="../resources/images/logo.png"
			alt="Simpla Admin logo" /></a>
		<!-- Sidebar Profile links -->
		<div id="profile-links">
			Hello, <a href="#" title="Edit your profile"><%=name%></a>, you have
			<a href="#messages" rel="modal" title="3 Messages">3 Messages</a><br />
			<br /> <a href="#" title="View the Site">View the Site</a> | <a
				href="<%=request.getContextPath()%>/logout">Logout</a>
		</div>

		<ul id="main-nav">
			<!-- Accordion Menu -->

			<li><a href="<%=request.getContextPath()%>/medicalSupport/index"
				class="nav-top-item no-submenu"> <!-- Add the class "no-submenu" to menu items with no sub menu -->
					Medical support
			</a></li>
			<%
				if(user.getRole().getId()==1){
			%>
			
		
			<li><a href="#" id="usermanager" class="nav-top-item"> User
					management </a>
				<ul>
					<li><a id="user"
						href="<%=request.getContextPath()%>/user/index.jsp">User</a></li>
					<li><a id="role"
						href="<%=request.getContextPath()%>/role/index.jsp">Role</a></li>
					<!-- Add class "current" to sub menu items also -->
				</ul>
			</li>
				<% 
				}
			%>

			<li><a href="#" id="laboratormanagement" class="nav-top-item"> Laborator Management </a>
				<ul>
					<li><a id="patient"
						href="<%=request.getContextPath()%>/patient/index">Patient</a></li>
					<li><a id="laborator"
						href="<%=request.getContextPath()%>/laborator/index">Laborator</a></li>
					<li><a id="medicine"
						href="<%=request.getContextPath()%>/medicine/index">Medicine</a></li>
						
					<!-- Add class "current" to sub menu items also -->
				</ul>
			</li>
		


		</ul>
		<!-- End #main-nav -->

	</div>
</div>
<script language="javascript">
	if (document.URL.toLowerCase().indexOf("/user") >= 0) {
		document.getElementById("user").setAttribute("class", "current");
		document.getElementById("usermanager").setAttribute("class",
				"nav-top-item current");
	}
	if (document.URL.toLowerCase().indexOf("/role") >= 0) {
		document.getElementById("role").setAttribute("class", "current");
		document.getElementById("usermanager").setAttribute("class",
				"nav-top-item current");
	}
	
	if (document.URL.toLowerCase().indexOf("/patient") >= 0) {
		document.getElementById("patient").setAttribute("class", "current");
		document.getElementById("laboratormanagement").setAttribute("class",
				"nav-top-item current");
	}
	
	if (document.URL.toLowerCase().indexOf("/laborator") >= 0) {
		document.getElementById("laborator").setAttribute("class", "current");
		document.getElementById("laboratormanagement").setAttribute("class",
				"nav-top-item current");
	}
	if (document.URL.toLowerCase().indexOf("/medicine") >= 0) {
		document.getElementById("medicine").setAttribute("class", "current");
		document.getElementById("laboratormanagement").setAttribute("class",
				"nav-top-item current");
	}
	

</script>
