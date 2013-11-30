<%@page import="java.util.Vector"%>
<%@page import="Model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<s:include value="../head.jsp"></s:include>
<%
	if (request.getAttribute("roles") == null) {
		String redirectURL = "new";
		response.sendRedirect(redirectURL);
	}
%>

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
					<h3>Content box</h3>
					<ul class="content-box-tabs">
					</ul>
					<div class="clear"></div>
				</div>
				<div
					style="padding-left: 40px; padding-top: 20px; padding-bottom: 20px;">

					<s:if test="hasActionErrors()">
						<div class="input-notification error png_bg">
							<div>
								<s:actionerror />
							</div>
						</div>
					</s:if>
					<form action="new" method="post">
						<s:hidden name="userBean.id" value="0"></s:hidden>
						<p>
							<s:textfield name="userBean.username" key="Username"
								cssClass="text-input medium-input"></s:textfield>
						</p>
						<p>
							<s:password name="userBean.password" key="password"
								cssClass="text-input medium-input"></s:password>
						</p>

						<p>
							<s:password name="confirmPassword" key="Confirm password"
								cssClass="text-input medium-input"></s:password>
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
										<option value="${id}">${role}</option>
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
			<!-- End #footer -->

		</div>
		<!-- End #main-content -->

	</div>
</body>

</html>