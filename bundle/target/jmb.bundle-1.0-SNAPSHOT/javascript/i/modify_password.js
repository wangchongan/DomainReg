$().ready(function(){
	
	$("#modifyPasswordForm").validate({
		rules: {
			"userDTO.password": {
				required: true,
				rangelength: [6,16],
				notEqualTo: "#oldPassword"
			}, 
			"userDTO.rePassword": {
				required: true,
				rangelength: [6,16],
				equalTo: "#userPassword"
			} 
		},
		messages: {
			"userDTO.password": {
				required: "请输入新密码"
			},
			"userDTO.rePassword": {
				required: "请确认新密码",
				equalTo: "新密码前后输入不一致"
			}
		}
	});
});