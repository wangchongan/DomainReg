$().ready(function(){
	
	//表单校验
	$("#realNameAuthForm").validate({
		rules: {
			"authDTO.orgCode": {
				rangelength:[4,30]
			}, 
			"authDTO.regNo": {
				required: true ,
				fixLength:15
			}, 
			"authDTO.businessOn": {
				maxlength:150
			}, 
			"authDTO.regCapital": {
				maxlength:20
			}, 
			"authDTO.businessOutDate": {
				requiredIfNotChecked: "authDTO.longTermFlag,YES",
				maxlength:15
			}, 
			"authDTO.address": {
				required: true ,
				maxlength:100
			}, 
			"authDTO.phone": {
				required: true ,
				phone:true
			}, 
			"authDTO.fax": {
				fax:true
			}, 
			"userDTO.authCode": {
				authCode: true  
			},
			"authDTO.prov": {
				required: true  
			}
		},
		messages: {
		}  
	});
	
	var longTermFlag = "";
	$("#longTermFlag").click(function(){
		if(f_isChecked($("#longTermFlag"))){
			//把前面填写的值先保存起来备用
			longTermFlag = $("#businessOutDate").val();
			$("#businessOutDate").val("");
			$("#businessOutDate").attr("readonly","true");
		} else {
			$("#businessOutDate").removeAttr("readonly");
			$("#businessOutDate").val(longTermFlag);
			$("#businessOutDate").focus();
		}
	});
	
	//上一步
	$("#lastStepBtn").click(function(){
		//关闭校验开关
		validateToggle = false;
		$("#realNameAuthForm").attr("action","for_auth.action");
		$("#realNameAuthForm").submit();
	});
	
});

 