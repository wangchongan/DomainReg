$().ready(function() {
	$("#userLoginForm").validate({
		rules: {
			"userDTO.loginName": {
				required: true,
				maxlength: 50,
				loginName: true
			},
			"userDTO.password": {
				password: true
			},
			"userDTO.authCode": {
				authCode: true
			}
		},
		messages: {
			"userDTO.loginName": {
				required: "请输入登录帐号",
				maxlength: "最大50个字符",
				loginName: "登录帐号应为会员名、数字帐号或注册邮箱"
			}
		}
	});
});