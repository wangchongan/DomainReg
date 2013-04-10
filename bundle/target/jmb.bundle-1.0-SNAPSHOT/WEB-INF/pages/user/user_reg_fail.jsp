<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>注册成功 - 项目佬</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="项目佬">
<meta http-equiv="description" content="项目佬">
</head>

<body>
	<table>
	 	<tr>
			<td colspan="2">
				<s:if test="#request.actionResult.errorFlag == 1">
					<font style="color: red;"><s:property value="#request.actionResult.errorStr"/></font>
				</s:if>
			</td>
		</tr>
		<tr>
			<td><a href="<%=path%>/member/reg.action">我要重新注册</a></td>
		</tr>
	</table>
 	
</body>


</html>
