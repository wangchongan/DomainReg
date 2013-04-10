<%@page language="java"  pageEncoding="UTF-8"%>
<%@page import="com.jinmibao.common.util.whois.WhoisBean"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>
	<s:if test="#request.whoisBean == null">
		 域名Whois查询宝 - 金米宝 - 玩转域名！我喜欢
	</s:if>
	<s:else>
		<s:property value="%{#request.whoisBean.domain}"/> - 域名Whois查询宝 - 金米宝 - 玩转域名我喜欢！
	</s:else>
</title>
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
	
	.whoisMessageClass {
     	width:99%;
     	height:99%;
     	border-top-style: none;
     	border-right-style: none;
     	border-left-style: none;
     	border-bottom-style: none;
	}
</style>
</head>

<body>

	<s:include value="/common/tools_list.jsp"/>

	<s:form action="whois" method="get" namespace="/" id="whoisForm">
		域名：<s:textfield name="d"/>.<s:select list="#{'com':'com','cn':'cn','net':'net','com.cn':'com.cn','org':'org','mobi':'mobi','co':'co','net.cn':'net.cn','so':'so','gov.cn':'gov.cn','org.cn':'org.cn','tel':'tel','tv':'tv','biz':'biz','cc':'cc','hk':'hk','name':'name','info':'info','me':'me','asia':'asia'}" name="t" value="%{#request.t}"></s:select>
		<s:submit value="查询(F8)"/>
	</s:form>
	
 
	<s:if test="#request.whoisBean.queryStatus == 'QUERY_ERROR'">
		亲！很抱歉！查询失败，请稍后再试。
		金米宝建议：<a href="http://whois.www.net.cn/whois/domain/<s:property value="%{#request.whoisBean.domain}"/>" target="_blank">打开其他网站该域名Whois信息</a>
	</s:if>
	<s:elseif test="#request.whoisBean.queryStatus == 'ILLEGAL_DOMAIN'">
		亲！这不是合法的域名，请确认后重试哦。
	</s:elseif>
	
	<s:if test="#request.whoisBean.queryStatus == 'QUERY_SUCCESS'">
		<span id="title" style="">
			<br/><br/><s:property value="%{#request.whoisBean.domain}"/> 域名WHOIS查询结果:
			<br/><br/>域名注册情况：
			<s:if test="#request.whoisBean.domainStatus == 'REG'">
				已被注册 <a href="http://www.<s:property value="%{#request.whoisBean.domain}"/>" target="_blank">前往</a>
				<br/>域名状态：<s:property value="%{#request.whoisBean.whoisStatus}"/>
				<br/>注册商：<s:property value="%{#request.whoisBean.registrar}"/>
				<br/>DNS服务器：<s:property value="%{#request.whoisBean.nameServer}"/>
				<br/>注册时间：<s:property value="%{#request.whoisBean.creationDateStr}"/>
				<br/>到期时间：<s:property value="%{#request.whoisBean.expirationDateStr}"/>
			</s:if>
			<s:elseif test="#request.whoisBean.domainStatus == 'NOT_REG'">
				<span style='color:green;'>尚未注册</span>
			</s:elseif>
			<s:elseif test="#request.whoisBean.domainStatus == 'FORBID'">
				可能被注册局保留、正在审核或限制注册，暂无whois信息
			</s:elseif>
			<s:else>
				查询失败
			</s:else>
		</span>
		<s:if test="#request.whoisBean.domainStatus == 'REG'">
			<br/>全部信息：<br/> 
			<%
				String whoisMessage = ((WhoisBean)request.getAttribute("whoisBean")).getWhoisMessage();
				String[] whoisMessageArray = whoisMessage.split("\n");
				for (String message : whoisMessageArray) {
				    out.println(message+"<br/>");
				}
			%>
		</s:if>
	</s:if>
</body>

<script type="text/javascript">

	 
	
	function hotkey(){ 
		 var a=window.event.keyCode;
		 //F8键实现快捷查询
		 if(a==119){
			 $("#whoisForm").submit();
		 } 
	 } 
	 document.onkeydown = hotkey; //当onkeydown事件发生时调用hotkey函数 
	 
	 
</script>

</html>
