<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="#request.pager != null && #request.pager.pageCount > 1">
	<s:if test="#request.pager.currPage == 1">
		首页
	</s:if>
	<s:else>
		<a href="javascript:;" onclick="f_setPage(1);">首页</a>
	</s:else>
	<s:if test="#request.pager.currPage == 1">
		上一页
	</s:if>
	<s:else>
		<a href="javascript:;" onclick="f_setPage(<s:property value="#request.pager.prePage"/>);">上一页</a>
	</s:else> 
	<s:if test="#request.pager.currPage == #request.pager.pageCount">
		下一页
	</s:if>
	<s:else>
		<a href="javascript:;" onclick="f_setPage(<s:property value="#request.pager.nextPage"/>);">下一页</a>
	</s:else>
	<s:if test="#request.pager.currPage == #request.pager.pageCount">
		尾页
	</s:if>
	<s:else>
		<a href="javascript:;" onclick="f_setPage(<s:property value="#request.pager.pageCount"/>);">尾页</a>
	</s:else>
 跳转至第<input type="text" name="jumpPage" style="width:40px;"/>页<input type="button" value="确定"/>  |第<s:property value="#request.pager.currPage"/>页/共<s:property value="#request.pager.pageCount"/>页|共<s:property value="#request.pager.totalLine"/>条记录|每页<s:property value="#request.pager.pageSize"/>条
</s:if>
 
  