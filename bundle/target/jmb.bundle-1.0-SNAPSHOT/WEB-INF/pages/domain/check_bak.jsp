<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>金米宝 - 玩转域名我喜欢！</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="金米宝">
<meta http-equiv="description" content="金米宝">

<script language="javascript" type="text/javascript" src="<%=path %>/javascript/common/common.js"></script>
<script language="javascript" type="text/javascript" src="<%=path %>/javascript/common/page.js"></script>
<script language="javascript" type="text/javascript" src="<%=path %>/javascript/common/jquery.min.js"></script>
<script language="javascript" type="text/javascript" src="<%=path %>/datepicker/WdatePicker.js"></script>

<style type="text/css">
	body{
		font-size: 13px;
	}
	
	#domainTextarea {
		width: 300px;
		height: 100px;
		font-size: 15px;
	}
</style>
</head>

<body>

	<s:include value="/common/tools_list.jsp"/>

	<s:form action="check" method="get" namespace="/" id="checkForm">
		 <s:textarea name="d" id="domainTextarea"></s:textarea>
		 <s:submit value="查询"/>
		 <input type="button" value="查询"/>
		 <input type="button" value="清空" onclick="javascript:$('#domainTextarea').val('');$('#domainTextarea').focus();"/>
	</s:form>
	
 
	<s:elseif test="#request.whoisBean.queryStatus == 'QUERY_ERROR'">
		查询超时，请稍后再重试。
	</s:elseif>
	<s:elseif test="#request.whoisBean.queryStatus == 'ILLEGAL_DOMAIN'">
		不合法的域名，请确认后再重试。
	</s:elseif>
	

	<s:if test="#request.whoisBeanList != null">
		<table id="queryResultTable">
			<tr>
				<td>域名</td>
				<td>注册状态</td>
			</tr>
			<s:iterator value="#request.whoisBeanList" id="obj" status="index">
				<tr>
					<td><s:property value="#obj.domain"/> </td>
					<td>
						<s:if test="#obj.queryStatus == 'QUERY_SUCCESS'">
							<s:if test="#obj.domainStatus == 'REG'">
								已被注册 
								<a href="<%=path %>/whois.html?d=<s:property value='#obj.domain'/>" target="_blank">详细</a>
								<a href="http://www.<s:property value='#obj.domain'/>" target="_blank">前往</a>
							</s:if>
							<s:elseif test="#obj.domainStatus == 'NOT_REG'">
								<span style='color:green;'>尚未注册</span>
							</s:elseif>
							<s:else>
								查询超时，请稍后再重试
							</s:else>
						</s:if>
						<s:if test="#obj.queryStatus == 'QUERY_ERROR'">
							 	查询失败，可能是不合法的域名后缀
						</s:if>
						<s:if test="#obj.queryStatus == 'ILLEGAL_DOMAIN'">
							 	不合法的域名
						</s:if>
						
					</td>
				</tr>
			</s:iterator>
		</table>
	</s:if>
	

</body>

<script type="text/javascript">

	$("#checkForm").submit(function(){
		$("#queryResultTable").remove();
	});
</script>

</html>
