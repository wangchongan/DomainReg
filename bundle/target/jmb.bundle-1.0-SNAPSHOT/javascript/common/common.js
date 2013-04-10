	
//验证框架开关，如果设置为false，则关闭在提交前对数据进行校验
var validateToggle = true;

//刷新验证码
function f_changeCode(path) {
    $('#kaptcha').attr('src', path +'/kaptcha.jpg?' + Math.floor(Math.random()*100) ).fadeIn();  
    $("#authCode").val("").focus();
}

$().ready(function(){
	$("#authCode").val("");
});

/**
 * 校验字符数目
 * 一个汉字算两个字符
 * @param str
 * @param maxLength
 * @param minLength
 * @returns {Boolean}
 */
function f_wordsLength(str, maxLength, minLength){
	var r = /[^\x00-\xff]/g;
	if(minLength == null){
		minLength = 0;
	}
	if((str.replace(r,"mm").length >= minLength) && (str.replace(r,"mm").length <= maxLength)){
		return true;
	}else{
		return false;
	}
}

/**
 * 登录时候会员昵称校验
 * @param value
 * @returns
 */
function f_isUserNick_login(value) {
	var exp = new RegExp(regExp_userNick);
    var check = exp.test(value); 
    if (check && value.indexOf("__") == -1) {
    	return f_wordsLength(value, 20, 4);
    }
    return false;
}

/**
 * 注册时候会员昵称校验
 * @param value
 * @returns
 */
function f_isUserNick_reg(value) {
	var exp = new RegExp(regExp_userNick);
    var check = exp.test(value);
    if (check && value.indexOf("__") == -1) {
    	return f_wordsLength(value, 20, 5);
    }
    return false;
}

function f_isEmail(value) {
	return  /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i.test(value);
}

/**
 * 获取网站根路径
 * @returns
 */
function f_getRootPath(){
	var strFullPath=window.document.location.href;
	var strPath=window.document.location.pathname;
	var pos=strFullPath.indexOf(strPath);
	var prePath=strFullPath.substring(0,pos);
	var postPath=strPath.substring(0,strPath.substr(1).indexOf('/')+1);
	return(prePath+postPath);
}

/**
 * 判断多选框是否选中
 * 
 * @param obj
 * @returns {Boolean}
 */
function f_isChecked(obj) {
	if($(obj).attr("checked") == "checked"){
		return true
	} else {
		return false;
	}
}

String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {  
    if (!RegExp.prototype.isPrototypeOf(reallyDo)) {  
        return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);  
    } else {  
        return this.replace(reallyDo, replaceWith);  
    }  
}

//获取被选中的radioBox的值
function f_getCheckedRadioValue(radioName)  
{  
  var obj = document.getElementsByName(radioName);
  for(i = 0; i < obj.length; i++)
  {  
	  if (obj[i].checked == true) {
	  	return obj[i].value;
	  }
  }
  return null;
}