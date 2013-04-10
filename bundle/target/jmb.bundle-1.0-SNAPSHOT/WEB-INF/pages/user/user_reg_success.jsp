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

 	恭喜您！注册成功！<br/>
 	会员名：<s:property value="#session.sessionUser.userNick"/>，数字帐号：<s:property value="#session.sessionUser.userId"/> <br/>
 	请到您的邮箱
 	<s:if test="#request.emailLoginUrl != null">
 		<a href="<s:property value="#request.emailLoginUrl"/>"  target="_blank">(<s:property value="#session.sessionUser.email"/>)</a>
 	</s:if>
 	<s:else>
 		<s:property value="#session.sessionUser.email"/>
 	</s:else>
 	查收帐号激活邮件。如果看不到邮件，请查看是否在垃圾邮件中。
 	<input type="button" value="立即到邮箱激活帐号"/> 收不到？<a href="#">再发一次</a>
</body>


</html>
