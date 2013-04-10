var emailOK = false;
var userNickOK = false;
var needCheckEmail = true;
var needCheckUserNick = true;

$().ready(function() {
	
	$("#userNick").val("");
	$("#email").val("");
	
	$("#regForm").validate({
		rules: {
			"userDTO.user.userNick": {
				required: true,
				userNick: true
			},
			"userDTO.user.password": {
				password: true 
			},
			"userDTO.rePassword": {
				password: true,
				equalTo: "#userPassword"
			},
			"userDTO.user.email": {
				required: true,
				email: true
			},
			"userDTO.authCode": {
				authCode: true 
			} 
		},
		messages: {
			"userDTO.user.userNick": {
				userNick: "会员名5-20个字符，一个汉字为2个字符，允许汉字、字母、数字以及下划线"
			},
			"userDTO.rePassword": {
				equalTo: "前后密码不一致"
			},
			"userDTO.user.email": {
				maxlength: "邮箱最长30个字符",
				email: "邮箱地址不合法"
			} 
		}
	});
	


	//失去焦点时候对用户昵称进行AJAX校验
	$("#userNick").blur(function(){
		//校验只有是合法登录名才会校验
		if (f_isUserNick_reg($("#userNick").val()) && needCheckUserNick) {
			$.getJSON(f_getRootPath() + "/member/check_reg.action?type=USER_NICK&userNick=" + $("#userNick").val(), function(data){
				var errorFlag = data.errorFlag;
				if (errorFlag == 1) {
					$("#userNickCheckSpan").remove();
					$("#userNick").after("<span id='userNickCheckSpan'>该会员名已存在，请换一个注册。</span>");
					userNickOK = false;
				} else {
					userNickOK = true;
				}
				needCheckUserNick = false;
			});
		} else {
			userNickOK = false;
		}
	});
	
	$("#userNick").keydown(function(){
		needCheckUserNick = true;
		$("#userNickCheckSpan").remove();
	});
	
	//失去焦点时候对用户昵称进行AJAX校验
	$("#email").blur(function(){
		//校验只有是合法登录名才会校验
		if (f_isEmail($("#email").val()) && needCheckEmail) {
			$.getJSON(f_getRootPath() + "/member/check_reg.action?type=USER_EMAIL&email=" + $("#email").val(), function(data){
				var errorFlag = data.errorFlag;
				if (errorFlag == 1) {
					$("#emailCheckSpan").remove();
					$("#email").after("<span id='emailCheckSpan'>该邮箱地址已注册过，请换一个注册。</span>");
					emailOK = false;
				} else {
					emailOK = true;
				}
				needCheckEmail = false;
			});
		} else {
			emailOK = false;
		}
	});
	
	$("#email").keydown(function(){
		needCheckEmail = true;
		$("#emailCheckSpan").remove();
	});
	
	$("#regForm").submit( function () {
		  if(!userNickOK) {
			  $("#userNick").focus();
			  return false;
		  }
		  if(!emailOK) {
			  $("#email").focus();
			  return false;
		  }
		  return true;
	});
});