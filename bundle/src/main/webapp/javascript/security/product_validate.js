
$().ready(function(){
	
	$("#productValidateForm").validate({
		rules: {
			"userDTO.emailAuthCode": {
				required: true,
				maxlength: 5,
				minlength:5
			} 
		},
		messages: {
			"userDTO.emailAuthCode": {
				required: "请输入身份验证码",
				maxlength: "身份验证应为5个字符",
				minlength: "身份验证应为5个字符"
			} 
		}
	});
	
	$("#productValidateForm").submit(function(){
		var value = $("#validateTypeSelect").val();
		if (value == 0) {
			var result = true;
			$("input[name='miBaoDTO.daAns']").each(function(i,obj){
				var val = $(obj).val();
				if (val == undefined || $.trim(val) == "") {
					$("#miBaoMsgTD").html("<font color='red'>请输入密保答案" + (i+1) + "</font>");
					result = false;
				}
			});
			return result;
		} else {
			return true;
		}
	});
	
	$("#validateTypeSelect").change(function(){
		var value = $("#validateTypeSelect").val();
		//密保
		if (value == 0) {
			$("#mail").hide();
			$("#problem").show();
			$("#validateType").val(0);
		} 
		//邮箱
		if (value == 1) {
			$("#problem").hide();
			$("#mail").show();
			$("#validateType").val(1);
		}
	});
});