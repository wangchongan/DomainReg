<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>过期域名查询宝 - 金米宝 - 玩转域名！我喜欢</title>
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
</style>
</head>

<body>

	 <s:include value="/common/tools_list.jsp"/>

	 <s:form action="del_query" namespace="/domain" method="get">
		 <table>
		 	<tr>
		 		<td colspan="2">过期域名查询宝</td>
		 	</tr>
		 	<tr>
		 		<td>包含关键字：</td>
		 		<td>
		 			<s:textfield name="pds.keywords" value="%{#request.pds.keywords}"/>
		 			在 
		 			<s:if test="#request.pds.keywordPlace != null">
			 			<s:radio list="#{0:'任意位置',1:'左边',2:'右边',3:'左边或右边'}" name="pds.keywordPlace" value="%{#request.pds.keywordPlace}" />
		 			</s:if>
		 			<s:else>
			 			<s:radio list="#{0:'任意位置',1:'左边',2:'右边',3:'左边或右边'}" name="pds.keywordPlace" value="0"  />
		 			</s:else>
		 		</td>
		 	</tr>
		 	<tr>
		 		<td>排除关键字：</td>
		 		<td>
		 			<s:textfield name="pds.excludeKeywords" value="%{#request.pds.excludeKeywords}"/>
		 		</td>
		 	</tr>
		 	<tr>
		 		<td>域名后缀：</td>
		 		<td> 
		 			<s:if test="#request.pds.domainTypeStr == null || #request.pds.domainTypeStr == ''">
		 				<s:checkboxlist list="#{0:'全部','com':'com','net':'net'}" name="pds.domainType" value="0" onclick="f_domainType(this)"></s:checkboxlist>
		 			</s:if>
		 			<s:else>
		 				<s:checkboxlist list="#{0:'全部','com':'com','net':'net'}" name="pds.domainType" onclick="f_domainType(this)"></s:checkboxlist>
		 			</s:else>
		 		</td>
		 	</tr>
		 	<tr>
		 		<td>长度：</td>
		 		<td>
		 		<s:select name="pds.lengthStart" list="#{0:'任意短',1:'1',2:'2',3:'3',4:'4',5:'5',6:'6',7:'7',8:'8',9:'9',10:'10',11:'11',12:'12',13:'13',14:'14',15:'15',16:'16',17:'17',18:'18',19:'19',20:'20',21:'21',22:'22',23:'23',24:'24',25:'25',26:'26',27:'27',28:'28',29:'29',30:'30'}" value="%{#request.pds.lengthStart}"></s:select>
		 		<s:select name="pds.lengthEnd" list="#{0:'任意长',1:'1',2:'2',3:'3',4:'4',5:'5',6:'6',7:'7',8:'8',9:'9',10:'10',11:'11',12:'12',13:'13',14:'14',15:'15',16:'16',17:'17',18:'18',19:'19',20:'20',21:'21',22:'22',23:'23',24:'24',25:'25',26:'26',27:'27',28:'28',29:'29',30:'30'}" value="%{#request.pds.lengthEnd}"></s:select>
			 	</td>
		 	</tr>
		 	<tr>
		 		<td>域名结构：</td>
		 		<td> 
		 		<s:if test="#request.pds.includeTypeStr == null || #request.pds.includeTypeStr == ''">
			 		<input type="checkbox" name="pds.includeType" value="0" checked="checked"/>字母
			 		<input type="checkbox" name="pds.includeType" value="1" checked="checked" onclick="f_includeTypeNum(this)"/>数字
		 		</s:if>
		 		<s:else>
			 		<input type="checkbox" name="pds.includeType" value="0"/>字母
			 		<input type="checkbox" name="pds.includeType" value="1" onclick="f_includeTypeNum(this)"/>数字
		 		</s:else>
		 	    </td>
		 	</tr>
		 	<tr>
		 		<td>中横线：</td>
		 		<td>
		 			<s:if test="#request.pds.line != null">
			 			<s:radio list="#{1:'可以含有',0:'不含有',2:'一定含有'}" name="pds.line" value="%{#request.pds.line}" onclick="f_line(this);"/>
		 			</s:if>
		 			<s:else>
			 			<s:radio list="#{1:'可以含有',0:'不含有',2:'一定含有'}" name="pds.line" value="1" onclick="f_line(this);"/>
		 			</s:else>
		 			<span id="lineCountSpan" style="display:none;">
			 	    	个数:
			 	    	<s:select list="#{0:'任意',1:'1',2:'2',3:'3',4:'4',5:'5',6:'6',7:'7',8:'8',9:'9',10:'10'}" value="%{#request.pds.lineCount}" name="pds.lineCount">
			 	    	</s:select>
		 			</span>
		 	    </td>
		 	</tr>
		 	<tr>
		 		<td>特殊结构：</td>
		 		<td>
		 			<s:checkboxlist list="#{0:''}" value="" name="pds.sepcType" id="sepcType" onclick="f_allPinyin(this);"></s:checkboxlist>
			 		<s:select list="#{0:'全部拼音',1:'单拼音',2:'双拼音',3:'三拼音'}" name="pds.pinyinType" value="%{#request.pds.pinyinType}"></s:select>
		 		</td>
		 	</tr>
		 	<tr>
		 		<td>删除日期：</td>
		 		<td>
		 		<input id="d4311" name="pds.deletedDateStart" value="<s:property value='#request.pds.deletedDateStart'/>" class="Wdate" type="text" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'d4312\')||$(\'#maxDateInput\').val()}',minDate:'%y-%M-{%d-5}' })"/> 
				- <input id="d4312" name="pds.deletedDateEnd"  value="<s:property value='#request.pds.deletedDateEnd'/>" class="Wdate" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')||$(\'#minDateInput\').val()}',maxDate:'%y-%M-{%d+4}'})"/>
		 		</td>
		 	</tr>
		 	<tr>
		 		<td>排序规则：</td>
		 		<td>
		 			<s:radio list="#{0:'长度',1:'字母',2:'删除时间',4:'先日期后长度',3:'先日期后字母',5:'先长度后字母'}" name="pds.rankByType" value="%{#request.pds.rankByType}"/>
		 		</td>
		 	</tr>
		 	<tr>
		 		<s:hidden id="maxDateInput" value="%{#request.maxDate}" ></s:hidden>
		 		<s:hidden id="minDateInput" value="%{#request.minDate}"></s:hidden>
		 		<s:hidden id="domainTypeStr" value="%{#request.pds.domainTypeStr}"></s:hidden>
		 		<s:hidden id="includeTypeStr" value="%{#request.pds.includeTypeStr}"></s:hidden>
		 		<s:hidden id="sepcTypeStr" value="%{#request.pds.sepcTypeStr}"></s:hidden>
		 		<s:hidden id="currPage" name="currPage" value="%{#request.domainDeletedPageControler.currPage}"></s:hidden>
		 		<td colspan="2"><s:submit value="搜索"/> </td>
		 	</tr>
		 </table>
	 </s:form>
	 
	 <table>
	 	<tr>
	 		<td colspan="10"><input type="button" value="一键查！" onclick="f_allCheck();"/></td>
	 	</tr>
	 	<tr>
	 		<td colspan="10"><s:include value="/common/page.jsp"></s:include></td>
	 	</tr>
	 	<tr>
	 		<td>序号</td>
	 		<td>域名</td>
	 		<td>后缀</td>
	 		<td>长度</td>
	 		<td>类型</td>
	 		<td>中文释义</td>
	 		<td>互动</td>
	 		<td>搜索引擎收录</td>
	 		<td>删除时间</td>
	 		<td>注册情况</td>
	 		<td>操作</td>
	 	</tr>
	 	<s:iterator value="#request.domainDeletedPageControler.resultList" id="obj" status="index">
			<tr>
				<td><s:property value="1 + #index.index + (#request.domainDeletedPageControler.pageSize * (#request.domainDeletedPageControler.currPage-1))"/></td>
				<td><s:property value="#obj.domainName"/></td>
				<td><s:property value="#obj.domainType"/> </td>
				<td><s:property value="#obj.allLength"/> </td>
				<td>
					<s:if test="#obj.isAllNum == 'YES'">
						纯数字
					</s:if>
					<s:if test="#obj.isAllPinyinOne == 'YES'">
						单拼
					</s:if>
					<s:if test="#obj.isAllPinyinTwo == 'YES'">
						双拼
					</s:if>
					<s:if test="#obj.isAllPinyinThree == 'YES'">
						三拼
					</s:if>
				</td>
				<td><span title="<s:property value="#obj.pinyinChinese"/>"><s:property value="#obj.allFirstChinese"/></span></td>
				<td>
				<a href="javascript:;">喜欢</a>
				</td>
				<td>
				<a href="http://www.baidu.com/s?wd=<s:property value='#obj.domain'/>" target="_blank">百度</a>  
				<a href="http://www.google.com.hk/search?q=<s:property value='#obj.domain'/>" target="_blank">Google</a>  
				<a href="http://www.sogou.com/web?query=<s:property value='#obj.domain'/>" target="_blank">搜狗</a>  
				<a href="http://cn.bing.com/search?q=<s:property value='#obj.domain'/>" target="_blank">必应</a>  
				<a href="http://one.cn.yahoo.com/s?p=<s:property value='#obj.domain'/>&pid=hp&v=web" target="_blank">雅虎</a>  
				<a href="http://www.soso.com/q?pid=s.idx&ch=s.idx&w=<s:property value='#obj.domain'/>" target="_blank">搜搜</a>  
				<a href="http://www.infomall.cn/cgi-bin/arcv/*/http://<s:property value='#obj.domain'/>/" target="_blank">历史</a>  
				</td>
				<td><s:property value="#obj.gmtPlanDeletedStr"/> </td>
				<td>
					<s:if test="#obj.isAlreadyDeleted == 'YES'">
						<span class="asyncCheckInfoClass" style="display:none;" id="asyncCheckInfoSpan_<s:property value='#obj.id'/>"><s:property value='#obj.id'/>,<s:property value='#obj.domain'/></span>
						<span id="asyncCheckSpan_<s:property value='#obj.id'/>"><a href="javascript:;" onclick="f_checkDomainReg(<s:property value='#obj.id'/>,'<s:property value='#obj.domain'/>')">查询</a></span>
						<a href="<%=path %>/whois.html?d=<s:property value='#obj.domain'/>" target="_blank">whois</a>
					</s:if>
				</td>
				<td>
					<s:if test="#obj.isAlreadyDeleted == 'NO'">
						<a href="javascript:;">预订</a>
						<a href="http://whoissoft.com/<s:property value='#obj.domain'/>" target="_blank">whois</a>
					</s:if>
				</td>
			</tr>
		</s:iterator>
		<tr>
	 		<td colspan="10"><s:include value="/common/page.jsp"></s:include></td>
	 	</tr>
	 </table>
</body>

<script type="text/javascript">
	$(document).ready(function(){
		
		//域名后缀
		var checkedDomainType = $("#domainTypeStr").val();
		$("input[name='pds.domainType']").each(function(i,obj){
			var objVal = $(obj).val();
			if (checkedDomainType.indexOf(objVal) != -1) {
				$(obj).attr("checked","checked");
			}
		});
		
		//基本结构
		var checkedIncludeType = $("#includeTypeStr").val();
		$("input[name='pds.includeType']").each(function(i,obj){
			var objVal = $(obj).val();
			if (checkedIncludeType.indexOf(objVal) != -1) {
				$(obj).attr("checked","checked");
			}
		});
		
		//处理特殊结构
		var checkedSepcType = $("#sepcTypeStr").val();
		$("input[name='pds.sepcType']").each(function(i,obj){
			var objVal = $(obj).val();
			if (checkedSepcType.indexOf(objVal) != -1) {
				$(obj).attr("checked","checked");
			}
		});
		
		//处理横线
		$("input[name='pds.line']").each(function(i,obj){
			var objVal = $(obj).val();
			if (objVal == 2 && f_isChecked(obj)) {
				$("#lineCountSpan").show();
			}
		});
	});
	
	//处理纯拼音
	function f_allPinyin(obj) {
		if (f_isChecked(obj)) {
			$("input[name='pds.includeType']").each(function(i,obj){
				var objVal = $(obj).val();
				if (objVal == 0) {
					$(obj).attr("checked","checked");
				}
				if (objVal == 1) {
					$(obj).removeAttr("checked");
				}
			});
			
			$("input[name='pds.line']").each(function(i,obj){
				var objVal = $(obj).val();
				if (objVal == 0) {
					$(obj).attr("checked","checked");
				}
			});
			//隐藏横线个数
			$("#lineCountSpan").hide();
		}
	}
	
	//处理横线
	function f_line(obj){
		var objVal = $(obj).val();
		if (objVal == 2 && f_isChecked(obj)) {
			$("#lineCountSpan").show();
			//移除纯拼音选项
			$("input[name='pds.sepcType']").each(function(i,myObj){
				if($(myObj).val() == 0 && f_isChecked(myObj)) {
					$(myObj).removeAttr("checked");
				}
			});
		} else {
			$("#lineCountSpan").hide();
		}
	}
	
	//选择后缀
	function f_domainType(obj) {
		var objVal = $(obj).val();
		//如果是选择了全部,则去掉其他的后缀
		if (objVal == 0 && f_isChecked(obj)) {
			$("input[name='pds.domainType']").each(function(i,obj){
				var objVal = $(obj).val();
				if (objVal != 0) {
					$(obj).removeAttr("checked");
				}
			});
		} else {
			$("input[name='pds.domainType']").each(function(i,obj){
				var objVal = $(obj).val();
				if (objVal == 0) {
					$(obj).removeAttr("checked");
				}
			});
		}
	}
	
	//域名结构如果选择了数字，则判断是否字母为空，如果为空，则取消纯拼音选项
	function f_includeTypeNum(obj) {
		if (f_isChecked(obj)) {
			$("input[name='pds.includeType']").each(function(i,newObj){
				var objVal = $(newObj).val();
				if (objVal == 0 && !f_isChecked(newObj)) {
					$("input[name='pds.sepcType']").each(function(i,myObj){
						if($(myObj).val() == 0 && f_isChecked(myObj)) {
							$(myObj).removeAttr("checked");
						}
					});
				}
			});
		}
	}
	
	
	function f_checkDomainReg(id,d) {
		$.getJSON(f_getRootPath() + "/domain/async_reg_check.action?d=" + d, function(data){
			 if (data == '-1') {
				 $("#asyncCheckSpan_" + id).html("<a href=\"javascript:;\" onclick=\"f_checkDomainReg(" + id + ",'" + d + "')\">请重试</a>");
			 }
			 if (data == '0') {
				 $("#asyncCheckSpan_" + id).html("<span style='color:green;'>尚未注册</span>");
			 }
			 if (data == '1') {
				 $("#asyncCheckSpan_" + id).html("已被注册");
			 }
		});
	}
	
	function f_allCheck() {
		$(".asyncCheckInfoClass").each(function(i,obj){
			
			var text = $("#asyncCheckSpan_" + $(obj).text().split(",")[0]).text();
		 
			if (text == "查询" || text == "请重试") {
				$.getJSON(f_getRootPath() + "/domain/async_reg_check.action?d=" + $(obj).text().split(",")[1], function(data){
					 var infos = $(obj).text().split(",");
					 var id = infos[0];
					 var d = infos[1];
					 if (data == '-1') {
						 $("#asyncCheckSpan_" + id).html("<a href=\"javascript:;\" onclick=\"f_checkDomainReg(" + id + ",'" + d + "')\">请重试</a>");
					 }
					 if (data == '0') {
						 $("#asyncCheckSpan_" + id).html("<span style='color:green;'>尚未注册</span>");
					 }
					 if (data == '1') {
						 $("#asyncCheckSpan_" + id).html("已被注册");
					 }
				});
			}
			
		});
	}
	
</script>

</html>
