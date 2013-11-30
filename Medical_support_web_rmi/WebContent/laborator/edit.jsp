<%@page import="Model.LaboratorForm"%>
<%@page import="java.util.Vector"%>
<%@page import="Model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<s:include value="../head.jsp"></s:include>
</head>

<body>
	<div id="body-wrapper">
		<!-- Wrapper for the radial gradient background -->

		<%@include file="../sideBar.jsp"%>
		<%
			if(user.getRole().getId()!=1){
				String redirectURL = request.getContextPath()
						+ "/index";
				response.sendRedirect(redirectURL);
				return;
			}
		%>
		

		<div id="main-content">
			<!-- Main Content Section with everything -->
			<div class="clear"></div>
			<!-- End .clear -->
			<div class="content-box">
				<!-- Start Content Box -->
				<div class="content-box-header">
					<h3>Edit laborator form</h3>
					<ul class="content-box-tabs">
					</ul>
					<div class="clear"></div>
				</div>
				<%
					LaboratorForm lf = (LaboratorForm)request.getAttribute("laboratorFormBean");
					
				%>
				<div
					style="padding-left: 40px; padding-top: 20px; padding-bottom: 20px;">
					<form action="saveLaboratorForm" method="post">
						<table>
						
							<tr>
								<th>Laborator form ID</th>
								<td><%=lf.getId() %></td>
							</tr>
							
							<tr>
								<th>Patient ID</th>
								<td><input type = "text" name="laboratorFormBean.pantient.id" value = "<%=lf.getPantient().getId() %>" class="text-input medium-input"></input></td>
							</tr>
							<tr>
								<th>Time in hospital</th>
								<td><input type = "text" name="laboratorFormBean.count" value = "<%=lf.getCount() %>" class="text-input medium-input" ></input></td>
							</tr>
							<tr>
								<th>Result</th>
								<td><input type = "text" name="laboratorFormBean.result" value = "<%=lf.getResult() %>" class="text-input medium-input"></td>
							</tr>
							<tr>
								<th>View detail</th>
								<td><a
										href="<%=request.getContextPath()%>/laborator/detail?patientid=<%=lf.getPantient().getId()%>
										&count=<%=lf.getCount()%>">
											View
									</a></td>
							</tr>
							
							<tr>
								<td><input class="button" type="submit" value="Save"></input></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<div class="clear"></div>
			<s:include value="../footer.jsp"></s:include>

		</div>
		<!-- End #main-content -->

	</div>
</body>

</html>