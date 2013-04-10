<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>激活成功 - 项目佬</title>
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
			<td>
				激活成功！欢迎您，<s:property value="#request.userDO.userNick"/>！请<a href="<%=path%>/member/login.action">登录</a>
			</td>
		</tr>
	</table>
</body>


</html>
