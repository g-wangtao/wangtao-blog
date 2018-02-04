<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title></title>
		<jsp:include page="../common/com_head.jsp"></jsp:include>
		<!-- <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" /> -->
		<style>
			.bor {
				border-bottom: 1px solid #EEE;
				border-top: 1px solid #EEE;
				border-right: 1px solid #EEE;
			}
			
			.field {
				margin: 10px 0 20px;
				border: none;
				border-top: 1px solid #e2e2e2;
			}
			
			.leg {
				width: 250px;
				border: none;
				text-align: left;
				margin-left: 20px;
				padding-left: 20px;
			}
			hr{
				margin-top: 20px;
				margin-bottom: 0px;
			}
		</style>
	</head>

	<body>
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-7">
					<fieldset class="field">
						<legend class="leg">欢迎登录后台管理系统</legend>
					</fieldset>
					<div>
						<blockquote class="bor">
							<p style="color: #0f88eb;font-size: 18px;">
								开发这个基础系统的初衷:是想帮助解决,后台基础管理系统<span style="color: orangered;">定制化开发</span>的问题. <br>
							</p>
						</blockquote>
						<blockquote class="bor">
							<p style="color: red;font-size: 18px;">
								目前系统版本尚处于RC阶段，仅供学习参考，请勿用于商业项目！<br> 系统还在RC阶段,离正式发布还有一段时间,我也会根据框架的调整作出相应调整.
							</p>
						</blockquote>
						<blockquote class="bor">
							<p style="color: red;font-size: 18px;">
								下载地址:
								<a href="https://github.com/g-wangtao/wangtao-blog.git" target="_blank">https://github.com/g-wangtao/wangtao-blog.git</a>
								<br> 目前系统还处于RC版本，系统功能可能不稳定,仅供学习参考，请勿用于商业项目！
							</p>
						</blockquote>
					</div>
				</div>
				<div class="col-md-3">
					<fieldset class="field">
						<legend class="leg" style="width: 120px;">系统信息</legend>
						<div>
							<hr>
							<p><strong>服务器操作系统:</strong><span class="float-right">Linux</span></p>
							<hr>
							<p><strong>框架内核版本:</strong><span class="float-right">5.1.0RC3</span></p>
							<hr>
							<p><strong>JAVA版本:</strong><span class="float-right">1.8</span></p>
							<hr>
							<p><strong>运行环境:</strong><span class="float-right">Tomcat8</span></p>
							<hr>
							<p><strong>MYSQL版本:</strong><span class="float-right">5.7.18-cdb20170530-log</span></p>
							<hr>
							<p><strong>上传限制:</strong><span class="float-right">2M</span></p>
						</div>
					</fieldset>
				</div>
			</div>
			<div class="row">
				<div class="col-md-7">
					<fieldset class="field">
						<legend class="leg" style="width: 140px;">今日浏览量</legend>
					</fieldset>
				</div>
				<div class="col-md-3">
					<fieldset class="field">
						<legend class="leg" style="width: 120px;">硬件信息</legend>
					</fieldset>
				</div>
			</div>
		</div>
	</body>
</html>