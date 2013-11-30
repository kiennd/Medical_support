<%@page import="Model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<title>Bookstore Admin | Sign In</title>

<!--                       CSS                       -->

<!-- Reset Stylesheet -->
<link rel="stylesheet" href="resources/css/reset.css" type="text/css"
	media="screen" />

<!-- Main Stylesheet -->
<link rel="stylesheet" href="resources/css/style.css" type="text/css"
	media="screen" />

<!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
<link rel="stylesheet" href="resources/css/invalid.css" type="text/css"
	media="screen" />

<!-- Colour Schemes
	  
		Default colour scheme is green. Uncomment prefered stylesheet to use it.
		
		<link rel="stylesheet" href="resources/css/blue.css" type="text/css" media="screen" />
		
		<link rel="stylesheet" href="resources/css/red.css" type="text/css" media="screen" />  
	 
		-->

<!-- Internet Explorer Fixes Stylesheet -->

<!--[if lte IE 7]>
			<link rel="stylesheet" href="resources/css/ie.css" type="text/css" media="screen" />
		<![endif]-->

<!--            Javascripts                       -->

<!-- jQuery -->
<script type="text/javascript"
	src="resources/scripts/jquery-1.3.2.min.js"></script>

<!-- jQuery Configuration -->
<script type="text/javascript"
	src="resources/scripts/simpla.jquery.configuration.js"></script>

<!-- Facebox jQuery Plugin -->
<script type="text/javascript" src="resources/scripts/facebox.js"></script>

<!-- jQuery WYSIWYG Plugin -->
<script type="text/javascript" src="resources/scripts/jquery.wysiwyg.js"></script>

<!-- Internet Explorer .png-fix -->

<!--[if IE 6]>
			<script type="text/javascript" src="resources/scripts/DD_belatedPNG_0.0.7a.js"></script>
			<script type="text/javascript">
				DD_belatedPNG.fix('.png_bg, img, li');
			</script>
		<![endif]-->

<%
	if (session.getAttribute("user") != null) {
		String redirectURL = "index.jsp";
		response.sendRedirect(redirectURL);
	}
%>

</head>

<body id="login">
	<div id="login-wrapper" class="png_bg">
		<div id="login-top">

			<h1>Bookstore Admin</h1>
			<!-- Logo (221px width) -->
			<img id="logo" src="resources/images/logo.png"
				alt="Simpla Admin logo" />
		</div>
		<!-- End #logn-top -->

		<div id="login-content">

			<form action="login" method="post">
				<s:if test="hasActionErrors()">
					<div class="notification information png_bg">
						<div>
							<s:actionerror />
						</div>
					</div>
				</s:if>
				<p>
					<s:textfield name="userBean.username" key="Username"
						cssClass="text-input"></s:textfield>
				</p>
				<div class="clear"></div>
				<p>
					<s:password name="userBean.password" key="Password"
						cssClass="text-input"></s:password>

				</p>
				<div class="clear"></div>
				<p id="remember-password">
					<input type="checkbox" />Remember me
				</p>
				<div class="clear"></div>
				<p>
					<input class="button" type="submit" value="Sign In" />
				</p>

			</form>
		</div>
		<!-- End #login-content -->

	</div>
	<!-- End #login-wrapper -->

</body>

</html>
