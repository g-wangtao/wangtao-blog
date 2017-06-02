/**
 * 博主登录
 * @returns
 */
function loginHandler() {
	var userName = $('#userName').val().trim();
	var password = $('#password').val().trim();
	var message = check(userName,password);
	if(message != null) {
		messageDiv(message);
		switchVerifyImg();
		return;
	}
	
}

/**
 * 验证方法
 * @param logName 登录名
 * @param logPwd 登录密码
 * @param verifyCode验证码
 * @returns message 验证信息字符串
 */
function check(userName,password) {
	var message = null;
	if (userName == "" || userName == null || userName == undefined) {
		message = "账号不能为空!";
	}
	if (password == '' || password == null || password == undefined) {
		if (message != null) {
			message = message + " 密码不能为空！";
		} else {
			message = "密码不能为空！";
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

