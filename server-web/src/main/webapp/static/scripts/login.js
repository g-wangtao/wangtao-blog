$(function(){
	$("#userName").keydown(function (event){
		if (event && event.keyCode == 13) {
	        $("#password").focus();
	    }       
	});
	$("#password").keydown(function (event){
		if(event && event.keyCode == 13) {
			$("#verifyCode").focus();
		}
	});
	$("#verifyCode").keydown(function (event){
	    if (event && event.keyCode == 13) {
	    	loginHandler();
	    }
	});
})        
/**
 * 博主登录
 * @returns
 */
function loginHandler() {
	var userName = $('#userName').val().trim();
	var password = $('#password').val().trim();
	var verifyCode = $('#verifyCode').val().trim();
	var message = check(userName,password,verifyCode);
	if(message != null) {
		messageDiv(message);
		return;
	}
	var data = {'userName':userName,'password':password,'verifyCode':verifyCode};
	$.ajax({
		type: 'POST',
		url: 'blogger/login',
		processData: true,
		data: data,
		success: function(callData) {
			var json = eval('(' + callData + ')');
			if(json) {
				if(json.isSuccess){
					window.location.herf = "";
				}else {
					switchVerifyImg()
					messageDiv(json.message);
				}
			}
		},
		error: function() {
			messageDiv('登陆失败，请稍后再试！');
		}
	});
}

/**
 * 验证方法
 * @param logName 登录名
 * @param logPwd 登录密码
 * @param verifyCode验证码
 * @returns message 验证信息字符串
 */
function check(userName,password,verifyCode) {
	var message = null;
	if (userName == "" || userName == null || userName == undefined) {
		message = " 账号不能为空!";
		$("#userName").focus();
	}
	if (password == '' || password == null || password == undefined) {
		if (message != null) {
			message = message + " 密码不能为空！";
		} else {
			message = "密码不能为空！";
			$("#password").focus();
		}
	}
	if(verifyCode == "" || verifyCode == null || verifyCode == undefined) {
		if(message != null) {
			message = message + "验证码不能为空！";
		} else {
			message = "验证码不能为空！";
			$("#verifyCode").focus();
		}
	}
	return message;
};

/**
 * 消息弹窗DIV
 * @param messageStr
 * @returns
 */
function messageDiv(messageStr) {
	$('body').append('<div id="message" class="alert alert-warning message">' + messageStr + '</div>');
	function remove(){
		$('#message').remove();
	};
	setTimeout(remove,1500);
	return;
}

/**
 * 切换图片验证码
 * @returns
 */
function switchVerifyImg() {
    $("#verifyImg").attr("src","/blog/verifyCode/create?timestamp="+new Date().getTime());
}

