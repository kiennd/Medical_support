<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<tr>
	<td colspan="6">
		<%
			if(user.getRole().getId()==1){
		%>
		<div class="bulk-actions align-left">
			<a class="button"
				onclick="document.getElementById('form1').submit();">Delete
				selected item</a>
		</div>
		<%
			}
		%>
		<%
			int pg = (Integer)request.getAttribute("page");
			int totalPage = (Integer)request.getAttribute("totalPage");
			
		%>
		<div class="pagination">
			<%if(pg>1){%>			
				<a href="index?page=1" title="First Page">&laquo; First</a>
				<a href="index?page=<%=pg-1%>" class="number"
					title="1"><%=pg-1%></a>
			<%} %>
			
			<a href="index?page=<%=pg%>" class="number current" title="1"><%=pg%></a>
			
			<%if(pg<totalPage){%>				
				<a href="index?page=<%=pg+1%>" class="number"
					title="1"><%=pg+1%></a>
				<a href="index?page=<%=totalPage%>"
					title="Last Page">Last &raquo;</a>
			<%} %>
		</div> <!-- End .pagination -->
		<div class="clear"></div>
	</td>
</tr>