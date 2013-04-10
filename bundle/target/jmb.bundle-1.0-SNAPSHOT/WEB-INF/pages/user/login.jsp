<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
<title>会员登录 - 项目佬</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="项目佬">
<meta http-equiv="description" content="项目佬">

<script language="javascript" type="text/javascript" src="<%=path %>/javascript/common/jquery.min.js"></script>
<script language="javascript" type="text/javascript" src="<%=path %>/javascript/common/metadata.min.js" ></script>
<script language="javascript" type="text/javascript" src="<%=path %>/javascript/common/jquery.validate.min.js" ></script>
<script language="javascript" type="text/javascript" src="<%=path %>/javascript/common/jquery.validate.extend.js" ></script>
<script language="javascript" type="text/javascript" src="<%=path %>/javascript/common/common.js" ></script>
<script language="javascript" type="text/javascript" src="<%=path %>/javascript/user/login.js"></script>
</head>
<body>
	<s:form name="userLoginForm" id="userLoginForm" action="user_login" namespace="/member" method="post">
		<table>
			<tr>
				<td colspan="2">
					<s:if test="#request.actionResult.errorFlag == 1">
						<font style="color: red;"><s:property value="#request.actionResult.errorStr"/></font>
					</s:if>
				</td>
			</tr>
			<tr>
				<td>帐号：</td>
				<td><s:textfield name="userDTO.loginName"></s:textfield></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><s:password name="userDTO.password"></s:password></td>
			</tr>
			<tr>
				<td>验证码：</td>
				<td><s:textfield name="userDTO.authCode" id="authCode"></s:textfield><img src="<%=path%>/kaptcha.jpg" id="kaptcha" onclick="f_changeCode('<%=path%>')"/><a href="javascript:;" onclick="f_changeCode('<%=path%>')">看不清?换一张</a></td>
			</tr>
			<tr>
				<td colspan="2"><s:submit value="登录"></s:submit></td>
			</tr>
		</table>
		<s:hidden name= "userDTO.fromUrl" value="%{#request.fromUrl}"></s:hidden>
	</s:form>
</body>


</html>
