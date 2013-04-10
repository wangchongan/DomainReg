<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>域名删除监视面板#<s:property value="%{#request.monitorNO}"/># - 金米宝 - 玩转域名！我喜欢</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="金米宝">
<meta http-equiv="description" content="金米宝">

<script language="javascript" type="text/javascript" src="<%=path %>/javascript/common/common.js" defer="defer"></script>
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
	监控批次号：<s:property value="%{#request.monitorNO}"/>
	<table>
		<tr>
			<td>监视号</td>
			<td>域名</td>
			<td>当前状态</td>
			<td>监视时间</td>
			<td>删除时间</td>
			<td>新注册时间</td>
			<td>操作</td>
		</tr>
	</table>
	<div id="musicDiv">
		<embed id="music" src="" width=0 height=0 autostart=false loop=true hidden=true></embed>
	</div>
	<input type="button" id="startBtn" value="开始">
	<input type="button" id="stopAlarmBtn" value="停止">
</body>

<script type="text/javascript">


	 $(document).ready(function(){
		 
		 $("#startBtn").click(function(){
			 $("#music").attr("src",f_getRootPath() + "/music/jiang_nan.mp3");
			 document.getElementById("music").play();
		 });
		 
		 $("#stopAlarmBtn").click(function(){
			 document.getElementById("music").stop();
		 });
		 
	 });

	 
</script>

</html>
