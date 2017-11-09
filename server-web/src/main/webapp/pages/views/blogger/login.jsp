<%@page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
+ path + "/";
%>
<!DOCTYPE html>
<html>
	<head>
		<base href="${contentPath}">
		<jsp:include page="../common/com_head.jsp"></jsp:include>
		<link href="static/styles/login/styles.css" rel="stylesheet" />
		<link href="static/styles/login/stars.css" rel="stylesheet" />
		<script src="static/scripts/login.js"></script>
		<title>BLOG登陆</title>
		<style>
			.verifyImg {
				cursor: pointer;
				margin-left: 20px;
				margin-top: 2.5px;
				width: 80%
			}
			
			.message {
				z-index: 2000;
				position: fixed;
				left: 44.6%;
				top: 20px;
			}
			
			P {
				font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
				font-size: .5em;
			}
			
			.full-content-center {
				width: 100%;
				padding: 5px 0px;
				max-width: 500px;
				margin: 6% auto;
				text-align: center;
			}
			
			.full-content-center h1 {
				font-size: 150px;
				font-family: "Open Sans";
				line-height: 150px;
				font-weight: 700;
				color: #252932;
			}
			
			.not-logged-avatar {
				width: 100px;
				margin: 0px auto;
				display: block;
				margin-bottom: 20px;
				text-align: center;
				box-shadow: 1px 1px 3px rgba(0, 0, 0, 0.1);
			}
			
			.btn-default {
				background-color: #ABB7B7;
				border-color: #ABB7B7;
				color: #fff;
			}
			
			.btn-default:hover,
			.btn-default:focus,
			.btn-default:active,
			.btn-default.active,
			.open .dropdown-toggle.btn-default {
				background-color: #98A3A3;
				border-color: #98A3A3;
				color: #fff;
			}
			
			.login-wrap {
				margin: 20px 10%;
				text-align: left;
				background: rgba(250, 250, 2250, 0.1);
				padding: 20px 20px;
				color: #fff;
			}
			
			.login-wrap a {
				color: #fff;
			}
			
			.login-wrap i {
				margin-right: 5px;
			}
			
			.login-wrap .checkbox {
				margin-left: 0;
				padding-left: 0;
			}
			
			.login-wrap .btn-block {
				margin: 5px 0;
			}
			
			.login-wrap .login-input {
				position: relative;
			}
			
			.login-wrap .login-input .text-input {
				padding-left: 30px;
			}
			
			.login-wrap .login-input i.overlay {
				position: absolute;
				left: 10px;
				top: 10px;
				color: #aaa;
			}
		</style>
	</head>

	<body>
		<div class="prod-fallback-edoc" style="position: fixed;top: 0;left: 0;right: 0;bottom: 0;">
			<div id="stars"></div>
			<div id="stars2"></div>

			<!-- container -->
			<div class="container">
				<div class="full-content-center">
					<p class="text-center">
						<a href="#"><img src="static/images/logo/logo2.png" alt="Logo" style="height: 36px;"></a>
					</p>
					<div class="login-wrap animated flipInX">
						<div class="login-block">
							<img src="static/images/logo/default-user.png" class="img-circle not-logged-avatar">
							<!--  <form role="form" method="post"> -->
							<div class="form-group login-input">
								<i class="glyphicon glyphicon-user overlay"></i>
								<input type="text" name="userName" class="form-control text-input" placeholder="用户名" id="userName" value="">
							</div>
							<div class="form-group login-input">
								<i class="glyphicon glyphicon-eye-close overlay"></i>
								<input type="password" name="password" class="form-control text-input" placeholder="******" id="password">
							</div>

							<div class="form-group login-input">
								<div class="pull-left" style="width:60%;">
									<i class="glyphicon glyphicon-picture overlay"></i>
									<input type="text" name="verifyCode" class="form-control text-input" placeholder="验证码" id="verifyCode">
								</div>
								<div class="pull-left">
									<img src="verifyCode/create" id="verifyImg" class="verifyImg" alt="看不清" onclick="switchVerifyImg()" />
								</div>
							</div>

							<div class="row" style="margin-top:80px;">
								<div style="display:none;" class="col-sm-6">
									<a href="register.html" class="btn btn-default btn-block">注册</a>
								</div>
								<div class="col-sm-12">
									<button type="submit" class="btn btn-success btn-block" onclick="loginHandler()">登录</button>
									<el-popover ref="popover5" v-model="QRcodeApk" trigger="manual" placement="right" width="160">
                                		<img id="imgpshr123" src="data:image/png;base64,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" style="width: 99%;opacity: 0;">
                            		</el-popover>
								</div>
							</div>
							<div style="text-align:center;padding-top: 30px">
								<P>Copyright © 2017, bwangtao All Rights Reserved.</P>
								<P>沪ICP备16050559号</P>
							</div>
							<!--  </form> -->
						</div>
					</div>

				</div>
			</div>
			<div style="width:300px;position:fixed;bottom:0px;right:0px;">
				<audio autoplay="autoplay" loop="loop">
					<source src="static/music/login/yongyou.mp3" type="audio/mpeg" />
					<p>Your browser does not support the audio element.</p>
				</audio>
			</div>
		</div>
		<script>
			// 随机背景
			function rd(begin, end) {
				return Math.floor(Math.random() * (end - begin)) + begin;;
			}
			$(".prod-fallback-edoc").css("background-image", "url('static/images/login/bg/" + rd(1, 10) + ".jpg')");
		</script>
	</body>

</html>