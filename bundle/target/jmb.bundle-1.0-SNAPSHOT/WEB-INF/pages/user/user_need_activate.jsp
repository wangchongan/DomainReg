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

	欢迎您，<s:property value="#session.sessionUser.userNick"/>！
	您的帐号尚未激活，请到您的邮箱
 	<s:if test="#session.sessionUser.emailUrl != null">
 		<a href="<s:property value="#session.sessionUser.emailUrl"/>"  target="_blank">(<s:property value="#session.sessionUser.email"/>)</a>
 	</s:if>
 	<s:else>
 		<s:property value="#session.sessionUser.email"/>
 	</s:else>
 	查收帐号激活邮件。如果看不到邮件，请查看是否在垃圾邮件中。<br/>
 	您可以选择<a href="#">换个邮箱</a>，或者<a href="#">再发一次激活邮件</a>
</body>


</html>
