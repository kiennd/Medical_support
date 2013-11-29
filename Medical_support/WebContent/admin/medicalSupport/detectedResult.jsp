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
			<p>
			<%
				String disease = (String)request.getAttribute("disease");
				Vector<Laborator> abNormals =  (Vector<Laborator>)request.getAttribute("abNormals");
			%>
			<table>
				<tr>
					<th>Disease name</th>
					<th><%=disease%></th>
				</tr>
			</table>
			<p>
				<b>Abnormal values</b>
			</p>
			
			<table>
				<tr>
					<th>Name</th>
					<th>Value</th>
					<th>Normal value</th>
				</tr>
				
				<%
				for(int i=0;i< abNormals.size();i++){
					Laborator la = abNormals.elementAt(i);
				%>
				
				<tr>
					<th><%=la.getName() %></th>
					<th><%=la.getResult() %></th>
					<th><%=la.getNormalValue() %></th>
				</tr>
				
				<% 
				}
				%>
				
				
			</table>

			<p>
			<div class="content-box">
				<!-- Start Content Box -->
				<div class="content-box-header">
					<h3>Content box</h3>
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
									<th><input class="check-all" type="checkbox" /></th>
									<th>#</th>
									<th>Patient id</th>
									<th>Time in hospital</th>
									<th>Patient name</th>
									<th>Result</th>
									<th>Modification</th>
								</tr>
							</thead>
							<tbody>
								<%
									Vector<LaboratorForm> laboratorForms = (Vector<LaboratorForm>) request
												.getAttribute("nearLaboratorForms");
										for (LaboratorForm laboratorForm : laboratorForms) {
								%>
								<tr>
									<td><input type="checkbox" name="slelect"
										value=<s:property value = "id"/>></td>
									<td><%=laboratorForm.getId()%></td>
									<td><a
										href="<%=request.getContextPath()%>/admin/laborator/detail?patientid=<%=laboratorForm.getPantient().getId()%>
										&count=<%=laboratorForm.getCount()%>">
											<%=laboratorForm.getPantient().getId()%>
									</a></td>
									<td><%=laboratorForm.getCount()%></td>
									<td><%=laboratorForm.getPantient().getName()%></td>
									<td><%=laboratorForm.getResult()%></td>
									<td>
										<!-- Icons --> <a
										href="<%=request.getContextPath()%>/admin/laborator/edit?patientid=<%=laboratorForm.getPantient().getId()%>&count=<%=laboratorForm.getCount() %>"
										title="Edit"><img
											src="../resources/images/icons/pencil.png" alt="Edit" /></a> <a
										href="<%=request.getContextPath()%>/admin/laborator/delete?id=<%=laboratorForm.getId()%>"
										title="Delete"><img
											src="../resources/images/icons/cross.png" alt="Delete" /></a>
									</td>
								</tr>

								<%
									}
								%>
							</tbody>
					
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