$().ready(function(){
	//表单校验
	$("#realNameAuthForm").validate({
		rules: {
			"authDTO.realName": {
				realNameRequired : true,
				realName: true
			},
			"authDTO.certType": {
				required: true 
			}, 
			"authDTO.certNo": {
				required: true, 
				rangelength: [4,50]
			}, 
			"authDTO.phone": {
				phone: true
			}, 
			"authDTO.companyName": {
				requiredIfChecked: "authDTO.accountType,1",
				companyName: true 
			} 
		},
		messages: {
			"authDTO.companyName": {
				requiredIfChecked: "请输入公司全称" 
			} 
		}
	});
	
	
});

function f_accountType(val){
	if (val == 0) {
		$("#companyNameTR").hide();
	} else {
		$("#companyNameTR").show();
	}
}