<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<a href="javascript:;" id="getRegBtn">注册</a> <a href="javascript:;" id="getLoginBtn">登录</a>
<hr/>
<a href="<%=path%>" >首页</a>
<a href="<%=path%>/for_del_query.action" >过期域名查询宝</a>
<a href="<%=path%>/whois.html" >域名Whois查询宝</a>
<a href="<%=path%>/domain/advance_check.html" >域名组合查询宝</a>
<a href="<%=path%>/check.html" >过期域名订阅宝</a>
<a href="<%=path%>/domain/del_monitor.html" >域名删除监视宝</a>
<a href="<%=path%>/check.html" >未注册短域名查询宝</a>
<a href="<%=path%>/check.html" >大批量域名查询宝</a>
<a href="<%=path%>/check.html" >i域名管理宝</a>
<a href="<%=path%>/check.html" >姓名域名查询宝</a>
<a href="<%=path%>/check.html" >地址域名查询宝</a>
<a href="<%=path%>/check.html" >域名晒换宝</a>
<a href="<%=path%>/check.html" >域名推荐宝</a>
<hr/>
<a href="<%=path%>/redirect.html?url=http%3a%2f%2ffanyi.baidu.com%2f" target="_blank">百度翻译</a>
<a href="<%=path%>/redirect.html?url=http%3a%2f%2ftranslate.google.cn%2f" target="_blank">谷歌翻译</a>
<!-- JiaThis Button BEGIN -->
<script type="text/javascript">var jiathis_config = {data_track_clickback:true};</script>
<script type="text/javascript" src="http://v2.jiathis.com/code/jiathis_r.js?btn=r5.gif&amp;uid=92225" charset="utf-8"></script>
<!-- JiaThis Button END -->

<div id="regMailInput" style="display: none;"> <a href="javascript:;" id="regEmailCloseBtn">关闭</a>
	<div id="regEmailMessage"></div>
	<div id="emailSubmitDiv">
		请输入注册邮箱：<s:textfield id="regEmailInput"></s:textfield>
		<input type="button" value="确定" id="regEmailSubmit">
	</div>
</div>

<script type="text/javascript" src="<%=path %>/javascript/common/jquery.blockui.js"></script>

<script type="text/javascript">

	$("#getRegBtn").click(function(){
		
		$("#emailSubmitDiv").show(); 
		
		jQuery.blockUI({ 
			message: $('#regMailInput'),
			css: {
				width:'300px',
				top: '25%',
				'-webkit-border-radius': '10px', 
		    	'-moz-border-radius': '10px',
		    	border: '9px solid #7F7F7F',
		    	cursor:     'auto' 
		    	
			},
			overlayCSS: { 
				backgroundColor: '#fff' 
			}
		}); 
	});
	
	//提交注册邮箱
	$("#regEmailSubmit").click(function(){
		var email = $("#regEmailInput").val();
		if ($.trim(email) == "") {
			$("#regEmailMessage").html("请先输入注册邮箱。");
			return;
		} else {
			$.getJSON(f_getRootPath() + "/user/reg_email_submit.action?email=" + email, function(data){
				if (data.errorFlag == 1) {
					$("#regEmailMessage").html(data.message);
					return;
				} 
				if (data.errorFlag == 0 && data.flag == 'SUCCESS') {
					$("#emailSubmitDiv").hide(); 
					$("#regEmailMessage").html("提交成功！请到您的邮箱" + email + "中查收邀请邮件并完成注册。");
					return;
				}
			});
		}
	});
	
	//关闭注册邮箱提交面板
	$("#regEmailCloseBtn").click(function(){
		jQuery.unblockUI();
		$("#regEmailMessage").html("");
	});
</script>
<hr/>