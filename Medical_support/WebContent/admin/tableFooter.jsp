<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<tr>
	<td colspan="6">
		<div class="bulk-actions align-left">
			<a class="button"
				onclick="document.getElementById('form1').submit();">Delete
				selected item</a>
		</div>
		<div class="pagination">
			<s:if test="%{page>1}">
				<a href="index?page=1" title="First Page">&laquo; First</a>
				<a href="index?page=<s:property value="%{page-1}" />" class="number"
					title="1"><s:property value="%{page-1}" /></a>
			</s:if>
			<a href="index?page=<s:property value="%{page}" />"
				class="number current" title="1"><s:property value="%{page}" /></a>
			<s:if test="%{page<totalPage}">
				<a href="index?page=<s:property value="%{page+1}" />" class="number"
					title="1"><s:property value="%{page+1}" /></a>
				<a href="index?page=<s:property value="%{totalPage}" />"
					title="Last Page">Last &raquo;</a>
			</s:if>
		</div> <!-- End .pagination -->
		<div class="clear"></div>
	</td>
</tr>