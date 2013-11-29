<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript"
	src="resources/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
	src="resources/scripts/jquery.jeditable.js"></script>
	
<script type="text/javascript">

$(document).ready(function() {
    $('.edit').editable('http://www.example.com/save.php', {
        indicator : 'Saving...',
        tooltip   : 'Click to edit...'
    });
    $('.edit_area').editable('http://www.example.com/save.php', { 
        cancel    : 'Cancel',
        submit    : 'OK',
        indicator : '<img src="img/indicator.gif">',
        tooltip   : 'Click to edit...'
    });
});

</script>

</head>
<body>
<div class="edit" id="div_1">Dolor</div>
<div class="edit_area" id="div_2">Lorem ipsum dolor sit amet, consectetuer 
adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore 
magna aliquam erat volutpat.</div>
</body>
</html>