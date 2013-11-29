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

<%
	Patient currentPatient = (Patient) request
			.getAttribute("currentPatient");
%>
<script type="text/javascript">

$(document).ready(function() {
    $('.editable_tf').editable('http://localhost:8080/Medical_support/admin/laborator/saveLaborator', { 
    	indicator : "<img src='../resources/images/indicator.gif'>",
		id:'laboratorName',
		name:'laboratorValue',
		 submitdata : function(value, settings) {
		       return {patientid: "<%=currentPatient.getId()%>",
		    			count:"<%=request.getAttribute("count")%>"   
		       };
		 },
        select : true,
        submit : 'Save',
        cancel : 'Cancel',
        tooltip : 'Click to edit'
    });
});

</script>
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
			<p>
			<table>
				<tr>
					<th>Patient ID</th>
					<th><%=currentPatient.getId()%></th>
				</tr>
				<tr>
					<th>Patient Name</th>
					<th><%=currentPatient.getName()%></th>
				</tr>
				<tr>
					<th>Born year</th>
					<th><%=currentPatient.getBornYear()%></th>
				</tr>
				<tr>
					<th>Born year</th>
					<%
						if (currentPatient.getSex() == 1) {
					%>
					<th>Nam</th>
					<%
						} else {
					%>
					<th>Nữ</th>
					<%
						}
					%>
				</tr>

			</table>

			<p>
			<div class="content-box">
				<!-- Start Content Box -->
				<div class="content-box-header">
					<h3>Laborator Table - Click to value to edit</h3>
					<a href="<%=request.getContextPath()%>/admin/laboratorForm/new"
						class="button" style="margin-left: 70%; margin-top: 5px">New
						User</a>
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
					<s:form action="delete" id="form1">
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
									<th class = "editable_tf" id="<%=laborator.getName()%>"><%=laborator.getResult()%></th>
								</tr>
								<%
									}
								%>
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