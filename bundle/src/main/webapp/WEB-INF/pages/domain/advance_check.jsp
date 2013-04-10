<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>域名组合查询宝 - 金米宝 - 玩转域名！我喜欢</title>
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
	
	单词组合方式：<s:radio list="#{0:'按位置组合',1:'全排列组合'}" name="acs.checkType" value="%{#request.acs.checkType}" onclick="f_checkType(this);"/>
	
	<div id="placeTypeDiv">
		<div id="placeListDiv">
			<div>域名第<span class="placeFlagClass">1</span>位：<s:textfield value="" name="word" cssClass="placeTextClass"/>中文含义：<s:textfield value="" name="wordChinese" cssClass="placeTextClass"/><a href="javascript:;" onclick="f_delPlace(this);">删除</a></div>
			<div>域名第<span class="placeFlagClass">2</span>位：<s:textfield value="" name="word" cssClass="placeTextClass"/>中文含义：<s:textfield value="" name="wordChinese" cssClass="placeTextClass"/><a href="javascript:;" onclick="f_delPlace(this);">删除</a></div>
		</div>
		<a href="javascript:;" id="addNewPlaceBtn">新增位</a>
	</div>
	
	<div id="allWordsTypeDiv" style="display: none;">
		*请输入需要全组合的单词：<br/>
		<s:textfield  name="acs.allWords" id="allWords"/>(最多8个，以空格分开)<br/>
		请输入单词对应中文：<br/>
		<s:textfield  name="acs.allWordsChinese" id="allWordsChinese"/>(对应单词的顺序，以空格分开)
		<br/>
		*组合后单词个数：<s:select id="countStart" name="acs.countStart" list="#{2:'2',3:'3',4:'4',5:'5',6:'6'}" value="%{#request.acs.countStart}"></s:select>
		至 <s:select id="countEnd" name="acs.countEnd" list="#{2:'2',3:'3',4:'4',5:'5',6:'6'}" value="%{#request.acs.countEnd}"></s:select>
	</div>
	
	  *组合后域名长度：<s:select id="lengthStart" name="acs.lengthStart" list="#{2:'2',3:'3',4:'4',5:'5',6:'6',7:'7',8:'8',9:'9',10:'10',11:'11',12:'12',13:'13',14:'14',15:'15',16:'16',17:'17',18:'18',19:'19',20:'20'}" value="%{#request.acs.lengthStart}"></s:select>
	  至 <s:select id="lengthEnd" name="acs.lengthEnd" list="#{2:'2',3:'3',4:'4',5:'5',6:'6',7:'7',8:'8',9:'9',10:'10',11:'11',12:'12',13:'13',14:'14',15:'15',16:'16',17:'17',18:'18',19:'19',20:'20'}" value="%{#request.acs.lengthEnd}"></s:select>
	 <br/>
	 
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
	 
	<br/>
	<input type="button" value="试算" id="checkBtn">
	<input type="button" value="一键查！" id="allCheckBtn">
	<input type="button" value="清空">
	
	<div>
		<div id="message"></div>
		<div id="domain">
		</div>
	</div>
	
</body>

<script type="text/javascript">

	 var placeCount = 2;
	 
	 var placeMinCount = 2;
	 
	 var placeMaxCount = 5;
	 
	 //域名可查总个数
	 var domainCount = 0;
	 
	 //是否需要试算，1-需要，0-不需要
	 var needTry = 1;
	 
	 $(document).ready(function(){
		 
		 $("#addNewPlaceBtn").click(function(){
			 if (placeCount >= placeMaxCount) {
				 alert("亲！最大可设置" + placeMaxCount + "位哦。");
				 return;
			 }
			 var html = "<div>域名第<span class='placeFlagClass'></span>位：<input value='' name='word' class='placeTextClass'>中文含义：<input value='' name='wordChinese' class='placeTextClass'><a href='javascript:;' onclick='f_delPlace(this);'>删除</a></div>";
			 $("#placeListDiv").append(html);
			 placeCount++;
			 f_rePlaceShow();
		 });
		 
		 //查询
		 $("#checkBtn").click(function(){
			 
			 //初始化
			 $("#message").html("");
			 $("#domain").html("");
			 domainCount = 0;
			 
			 //已试算过，无需试算
			 needTry = 0;
			 
			 //组合类型
			 var checkType = f_getCheckedRadioValue("acs.checkType");
			 //位数单词
			 var wordStr = "";
			 $("input[name='word']").each(function(i,obj){
				 var word = $(obj).val();
				 wordStr = wordStr + word + "|";
			 });
			 
			 //位数单词的中文含义
			 var wordChineseStr = "";
			 $("input[name='wordChinese']").each(function(i,obj){
				 var wordChinese = $(obj).val();
				 wordChineseStr = wordChineseStr + wordChinese + "|";
			 });
			 
			 //全组合单词
			 var allWords = "";
			 if ($.trim($("#allWords").val()) != "") {
				 var allWordsArray = $("#allWords").val().split(" ");
				 for (var i = 0; i < allWordsArray.length; i++) {
					 if ($.trim(allWordsArray[i]) != "") {
					 	allWords = allWords + allWordsArray[i] + "|";
					 }
				 }
			 }
			 
			 //全组合单词对应中文意思
			 var allWordsChinese = "";
			 if ($.trim($("#allWordsChinese").val()) != "") {
				 var allWordsChineseArray = $("#allWordsChinese").val().split(" ");
				 for (var i = 0; i < allWordsChineseArray.length; i++) {
					 if ($.trim(allWordsChineseArray[i]) != "") {
						 allWordsChinese = allWordsChinese + allWordsChineseArray[i] + "|";
					 }
				 }
			 }
			 
			 var lengthStart = $("#lengthStart").val();
			 var lengthEnd = $("#lengthEnd").val();
			 var countStart = $("#countStart").val();
			 var countEnd = $("#countEnd").val();
			 
			 //要查询的域名类型
			 var domainType = "";
			 $("input[name='domainType']").each(function(i,obj){
				 if(f_isChecked(obj)){
					 domainType = domainType + $(obj).val() + "|";
				 }
			 });
			 
			 $.getJSON(f_getRootPath() + "/domain/async_advance_check.html?acs.checkType=" + checkType 
					 + "&acs.wordStr=" + wordStr 
					 + "&acs.wordChineseStr=" + wordChineseStr 
					 + "&acs.placeCount=" + placeCount 
					 + "&acs.allWords=" + allWords
					 + "&acs.allWordsChinese=" + allWordsChinese
					 + "&acs.lengthStart=" + lengthStart
					 + "&acs.lengthEnd=" + lengthEnd
					 + "&acs.countStart=" + countStart
					 + "&acs.countEnd=" + countEnd
					 + "&acs.domainType=" + domainType, function(data){
					 if (data.errorFlag) {
						 $("#message").html(data.message);
						 return;
					 }
					 
					var htmlStart = "<table><tr><td>序号</td><td>域名</td><td>中文含义</td><td>域名长度</td><td>单词个数</td><td>操作</td></tr>";
					var htmlEnd = "<table></table>";
					
					 var trStr = "";
					 var count = 1;
					 $(data.domainList).each(function(i,obj){
						 trStr = trStr 
						 + "<tr><td>" + (count++) + "</td>" 
						 + "<td><span id='domainSpan_" + obj.randStr + "' class='domainStrClass'>" + obj.domain + "</span></td>" 
						 + "<td>" + obj.domainChinese + "</td>" 
						 + "<td>" + obj.domainLength + "</td>" 
						 + "<td>" + obj.wordLength + "</td>" 
						 + "<td><span class=\"domainClass\" id=\"checkSpan_" + obj.randStr + "\"><a href='javascript:;' onclick=\"f_checkReg('" + obj.randStr + "','" + obj.domain + "')\">查询</a></span></td>" 
						 + "</tr>";
					 });
					 
					 domainCount = data.domainCount;
					 var message = "共组合得到" + data.domainCount + "个域名。" ;
					 $("#message").html(message);
					 var htmlStr = htmlStart + trStr + htmlEnd;
					 $("#domain").html(htmlStr);
			 });
			 
		 });
		 
		 
		 //一键查询所有
		 $("#allCheckBtn").click(function(){
			 
			 if (needTry == 1) {
				 alert("亲！新的一次查询请先[试算]哦。");
				 return;
			 }
			 
			 //下次查询前需要试算
			 needTry = 1;
			 
			 if (domainCount <= 0) {
				 alert("亲！当前没有可查询的域名哦。");
				 return;
			 }
			 
			 
			 
			 $(".domainStrClass").each(function(i,obj){

				 $.getJSON(f_getRootPath() + "/domain/async_reg_check.action?d=" + $(obj).text(), function(data){
						
					    var domain = $(obj).text();
						var spanId = $(obj).attr("id");
						var randStr = spanId.split("_")[1];
						
					    
						if (data == '-1') {
							$("#checkSpan_" + randStr).html("<a href=\"javascript:;\" onclick=\"f_checkReg('" + randStr + "','" + domain + "')\">超时请重试</a>");
						}
						if (data == '-2') {
							 $("#checkSpan_" + randStr).html("不合法的域名");
						}
						if (data == '0') {
							$("#checkSpan_" + randStr).html("<span style='color:green;'>尚未注册</span>");
						}
						if (data == '1') {
							 var toolsInfo = "<a href=\"" + f_getRootPath() + "/whois.html?d=" + domain + "\" target=\"_blank\">详细</a>";
							 toolsInfo = toolsInfo + " <a href=\"http://www." + domain + "\" target=\"_blank\">前往</a>";
							$("#checkSpan_" + randStr).html("已被注册 " + toolsInfo);
						}
				});
			 });
		 });
		 
	 });
	 
	 function f_delPlace(obj){
		 if (placeCount <= placeMinCount) {
			 alert("亲！最小需要" + placeMinCount + "位哦。");
			 return;
		 }
		 $(obj).parent().remove();
		 placeCount--;
		 f_rePlaceShow();
	 }
	 
	 function f_rePlaceShow() {
		 var i = 1;
		 $(".placeFlagClass").each(function(i,obj){
			 $(obj).text(++i);
		 });
	 }
	 
	 //组合方式切换
	 function f_checkType(obj) {
		 var checkType = f_getCheckedRadioValue("acs.checkType");
		 if (checkType == 0) {
			 $("#placeTypeDiv").show();
			 $("#allWordsTypeDiv").hide();
		 }
		 if (checkType == 1) {
			 $("#placeTypeDiv").hide();
			 $("#allWordsTypeDiv").show();
		 }
		 f_clean();
	 }
	 
	 //清理
	 function f_clean() {
		 
		 $("#message").html("");
		 $("#domain").html("");
	 }
	 
	 function f_checkReg(r, d) {
			$.getJSON(f_getRootPath() + "/domain/async_reg_check.action?d=" + d, function(data){
				 if (data == '-1') {
					 $("#checkSpan_" + r).html("<a href=\"javascript:;\" onclick=\"f_checkReg('" + r + "','" + d + "')\">超时请重试</a>");
				 }
				 if (data == '-2') {
					 $("#checkSpan_" + r).html("不合法的域名");
				 }
				 if (data == '0') {
					 $("#checkSpan_" + r).html("<span style='color:green;'>尚未注册</span>");
				 }
				 if (data == '1') {
					 $("#checkSpan_" + r).html("已被注册");
				 }
			});
	 }
	 
</script>

</html>
