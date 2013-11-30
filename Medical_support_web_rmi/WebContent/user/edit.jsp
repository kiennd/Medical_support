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
<%
	if (request.getAttribute("roles") == null) {
		String redirectURL = "index";
		response.sendRedirect(redirectURL);
	}
%>

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
					<h3>Content box</h3>
					<ul class="content-box-tabs">
					</ul>
					<div class="clear"></div>
				</div>
				<div
					style="padding-left: 40px; padding-top: 20px; padding-bottom: 20px;">
					<form action="save" method="post">
						<s:hidden name="userBean.id"></s:hidden>
						<p>
							<s:textfield name="userBean.username" key="Username"
								cssClass="text-input medium-input"></s:textfield>
						</p>
						<p>
							<s:textfield name="userBean.fullname" key="Full name"
								cssClass="text-input medium-input"></s:textfield>
						</p>
						<p>
							<s:textfield name="userBean.phonenumber" key="Phone number"
								cssClass="text-input medium-input"></s:textfield>
						</p>
						<p>
							<select name="userBean.role.id">
								<s:iterator value="roles">
									<s:if test="%{id == userBean.role.id}">
										<option selected="selected" value="${userBean.role.id}"><s:property
												value="userBean.role.role" /></option>
									</s:if>
									<s:else>
										<option value="${id}"><s:property value="role" /></option>
									</s:else>
								</s:iterator>

							</select>
						</p>

						<p>
							<input class="button" type="submit" value="Submit"></input>
						</p>




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