<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="项目佬">
<meta http-equiv="description" content="项目佬">
<title>免费注册会员 - 项目佬</title>

<script language="javascript" type="text/javascript" src="<%=path %>/javascript/common/jquery.min.js?r=<%=System.currentTimeMillis()%>"  charset="utf-8"></script>
<script language="javascript" type="text/javascript" src="<%=path %>/javascript/common/jquery.validate.min.js?r=<%=System.currentTimeMillis()%>" charset="utf-8"></script>
<script language="javascript" type="text/javascript" src="<%=path %>/javascript/common/metadata.min.js?r=<%=System.currentTimeMillis()%>" charset="utf-8"></script>
<script language="javascript" type="text/javascript" src="<%=path %>/javascript/common/common.js?r=<%=System.currentTimeMillis()%>" charset="utf-8"></script>
<script language="javascript" type="text/javascript" src="<%=path %>/javascript/user/reg.js?r=<%=System.currentTimeMillis()%>" charset="utf-8"></script>

<script language="javascript" type="text/javascript" src="<%=path %>/javascript/common/jquery.validate.extend.js?r=<%=System.currentTimeMillis()%>"></script>

</head>
<body>
	<s:form id="regForm" name="regForm" action="new_reg" namespace="/member" >
		<table>
			<tr>
				<td colspan="2">
					<s:if test="#request.actionResult.errorFlag == 1">
						<font style="color: red;"><s:property value="#request.actionResult.errorStr"/></font>
					</s:if>
				</td>
			</tr>
			<tr>
				<td>会员名：</td>
				<td><s:textfield name="userDTO.user.userNick" id="userNick"></s:textfield></td>
			</tr>
			<tr>
				<td>邮箱地址：</td>
				<td><s:textfield name="userDTO.user.email" id="email"></s:textfield></td>
			</tr>
			<tr>
				<td>登录密码：</td>
				<td><s:password name="userDTO.user.password" id="userPassword"></s:password></td>
			</tr>
			<tr>
				<td>确认密码：</td>
				<td><s:password name="userDTO.rePassword" ></s:password></td>
			</tr>
			<tr>
				<td>验证码：</td>
				<td><s:textfield name="userDTO.authCode" id="authCode"></s:textfield><br/><img src="<%=path%>/kaptcha.jpg" onclick="f_changeCode('<%=path%>')" id="kaptcha"/><a href="javascript:void;" onclick="f_changeCode('<%=path%>')">看不清?换一张</a></td>
			</tr>
			<tr>
				<td colspan="2"><s:submit value="同意以下协议并注册"></s:submit></td>
			</tr>
			<tr>
				<td colspan="2"><a href="<%=path%>/doc/fu_wu_xie_yi.htm" target="_blank">《项目佬服务协议》</a></td>
			</tr>
		</table>
	</s:form>
</body>
</html>

