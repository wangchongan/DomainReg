<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>域名批量查询宝  - 金米宝 - 玩转域名！我喜欢 - 致力于玩转域名更容易！</title>
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
	 <h2>金米宝</h2>
	 <div style="">
	 	<div style="">
	 		<s:textarea name="d" id="domainTextarea"></s:textarea>
	 		<a href="javascript:;" id="openPadBtn">打开随写板</a>
	 		<a href="javascript:;" id="closePadBtn" style="display: none;">收起随写板</a>
	 	</div>
	 	<div id="padDiv" style="display: none;">随写板：<s:textarea name="pad" id="padTextarea"></s:textarea></div>
	 	<div style="">
		 <input type="checkbox" value="com" checked="checked" name="domainType">.com
		 <input type="checkbox" value="cn"  checked="checked" name="domainType">.cn
		 <input type="checkbox" value="net" name="domainType">.net
		 <input type="checkbox" value="com.cn" name="domainType">.com.cn
		 <input type="checkbox" value="org" name="domainType">.org
		 <input type="checkbox" value="mobi" name="domainType">.mobi
		 <input type="checkbox" value="co" name="domainType">.co
		 <input type="checkbox" value="net.cn" name="domainType">.net.cn
		 <input type="checkbox" value="so" name="domainType">.so
		 <input type="checkbox" value="gov.cn" name="domainType">.gov.cn
		 <input type="checkbox" value="org.cn" name="domainType">.org.cn
		 <input type="checkbox" value="tel" name="domainType">.tel
		 <input type="checkbox" value="tv" name="domainType">.tv
		 <input type="checkbox" value="biz" name="domainType">.biz
		 <input type="checkbox" value="cc" name="domainType">.cc
		 <input type="checkbox" value="hk" name="domainType">.hk
		 <input type="checkbox" value="name" name="domainType">.name
		 <input type="checkbox" value="info" name="domainType">.info
		 <input type="checkbox" value="me" name="domainType">.me
		 <input type="checkbox" value="asia" name="domainType">.asia
	 	</div>
	 </div>
	 <div>
		 <input type="button" value="查询(F8)" id="queryBtn"/>
		 <input type="button" value="清空" onclick="javascript:$('#domainTextarea').val('');$('#domainTextarea').focus();"/>
	 </div>
 	 <div id="queryResult">
 	 </div>
 	 
 	 
</body>

<script type="text/javascript">


	 $(document).ready(function(){
		$("#queryBtn").click(function(){
			var domainText = $("#domainTextarea").val();
			if ($.trim(domainText) != "") {
			 	var queryHtml = "<table id='queryResultTable'><tr><td>域名</td><td>注册状态</td></tr>";
				var domainArray = domainText.split("\n");
				for (i=0; i < domainArray.length; i++) {
					var domain = $.trim(domainArray[i]);
					
					if (domain != "") {
						
						//如果不含有.，则自动查询.com域名
						if (domain.indexOf(".") == -1 && domain.indexOf(" ") == -1) {
							var hasType = false;
							$("input[name='domainType']").each(function(j,obj){
								if (f_isChecked(obj)) {
									var newDomain = domain + "." + $(obj).val();
									var h = "<tr><td><span>" + newDomain + "</span></td><td><span class=\"domainClass\" id=\"" + newDomain + "\">查询中</span></td></tr>"; 
									queryHtml = queryHtml + h;
									hasType = true;
								}
							});
							if (!hasType) {
								domain = domain + ".com";
							}else {
								continue
							}
						}
						
						//如果没有含有.并且中间含有空格，则自动把空格换成.
						if (domain.indexOf(" ") != -1) {
							var domains = domain.split(" ");
							domain = $.trim(domains[0]) + "." +  $.trim(domains[1]);
						}
						 
						var h = "<tr><td><span>" + domain + "</span></td><td><span class=\"domainClass\" id=\"" + domain + "\">查询中</span></td></tr>"; 
						queryHtml = queryHtml + h;
					}
				}
				queryHtml = queryHtml + "</table>";
				$("#queryResult").html(queryHtml);
			}
			
			$(".domainClass").each(function(i,obj){
					$.getJSON(f_getRootPath() + "/domain/async_reg_check.action?d=" + $(obj).attr("id"), function(data){
						var d = $(obj).attr("id");
						 if (data == '-1') {
							 $(obj).html("查询超时");
						 }
						 if (data == '-2') {
							 $(obj).html("不合法的域名");
						 }
						 if (data == '0') {
							 $(obj).html("<span style='color:green;'>尚未注册</span>");
						 }
						 if (data == '1') {
							 var toolsInfo = "<a href=\"" + f_getRootPath() + "/whois.html?d=" + d + "\" target=\"_blank\">详细</a>";
							 toolsInfo = toolsInfo + " <a href=\"http://www." + d + "\" target=\"_blank\">前往</a>";
							 $(obj).html("已被注册 " + toolsInfo);
						 }
					});
				
			});
		});
		
		$("#openPadBtn").click(function(){
			$("#padDiv").show();
			$("#openPadBtn").hide();
			$("#closePadBtn").show();
		});
		
		$("#closePadBtn").click(function(){
			$("#padDiv").hide();
			$("#openPadBtn").show();
			$("#closePadBtn").hide();
		});
		
		 
	 });
	 
	 function hotkey(){ 
		 var a=window.event.keyCode;
		 //F8键实现快捷查询
		 if(a==119){
			 $("#queryBtn").click();
		 } 
	 } 
	 
	 document.onkeydown = hotkey; //当onkeydown事件发生时调用hotkey函数 


	 
</script>

</html>
