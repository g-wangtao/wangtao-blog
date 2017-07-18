<%@page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
+ path + "/";
%>
<!DOCTYPE html>
<html>

	<head>
		<base href="<%=basePath%>">
		<link href="static/styles/login/styles.css" rel="stylesheet" />
		<link href="static/styles/login/stars.css" rel="stylesheet" />
		<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" />
		<script src="static/scripts/plugins/jquery/3.1.1/jquery-3.1.1.min.js"></script>
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
									<img src="/blog/verifyCode/create" id="verifyImg" class="verifyImg" alt="看不清" onclick="switchVerifyImg()" />
								</div>
							</div>

							<div class="row" style="margin-top:80px;">
								<div style="display:none;" class="col-sm-6">
									<a href="register.html" class="btn btn-default btn-block">注册</a>
								</div>
								<div class="col-sm-12">
									<button type="submit" class="btn btn-success btn-block" onclick="loginHandler()">登录</button>
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