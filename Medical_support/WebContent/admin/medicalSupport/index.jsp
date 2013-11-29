<%@page import="java.util.*"%>
<%@page import="Model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<s:include value="../head.jsp"></s:include>
<%-- <%
	if (request.getAttribute("laboratorForms") == null) {
		String redirectURL = "index";
		response.sendRedirect(redirectURL);
	}
%> --%>

</head>


<body>
	<div id="body-wrapper">
		<!-- Wrapper for the radial gradient background -->
		<%@include file="../sideBar.jsp"%>
		<div id="main-content">
			<!-- Main Content Section with everything -->
			<%@include file="../searchForm.jsp"%>
			<div class="clear"></div>
			<!-- End .clear -->
			<div class="content-box">
				<!-- Start Content Box -->
				<div class="content-box-header">
					<h3>Medical support system</h3>
				</div>

				<div class="tab-content">
					<!-- This is the target div. id must match the href of this div's tab -->
					<s:if test="hasActionMessages()">
						<div class="notification information png_bg">
							<a href="#" class="close"><img
								src="../resources/images/icons/cross_grey_small.png"
								title="Close this notification" alt="close" /></a>
							<div>
								<s:actionmessage />
							</div>
						</div>
					</s:if>
					<s:form action="detect" id="form1">
						<table>
							<thead>
								<tr>
									<th width="50%">Laborator Name</th>
									<th>Value</th>
								</tr>
							</thead>
							<tbody>
								<%
									Vector<Laborator> laborators = (Vector<Laborator>) request
												.getAttribute("laborators");
										for (Laborator laborator : laborators) {
								%>
								<tr>
									
									<th><%=laborator.getName()%></th>
									<th class = "editable_tf">
										<input type = "hidden" name="laboratorName" value = "<%=laborator.getName()%>">
										<input type = "text" name="laboratorValue">
									</th>
								</tr>
								<%
									}
								%>
								
								<tr>
									
									<th><input type = "submit" value = "submit"></th>
									
								</tr>
							</tbody>
							<%-- <tfoot>
								<%@include file = "../tableFooter.jsp" %>
							</tfoot> --%>
						</table>
					</s:form>
				</div>
				<!-- End .content-box-content -->
			</div>
			<!-- End .content-box -->
			<s:include value="../footer.jsp"></s:include>
		</div>
		<!-- End #main-content -->
	</div>
</body>

</html>