<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<style>
@font-face {
	 font-family: "rei";
    url("https://i.alipayobjects.com/common/fonts/rei.svg?20150616#rei") format("svg"); /* iOS 4.1- */
		src: url("font/font_1441789796_8667505.eot?#iefix") format("embedded-opentype"),
		 url("font/font_1441789796_8667505.woff") format("woff"),
		 url("font/font_1441789796_8667505.ttf") format("truetype"),
		 url("font/font_1441789796_8667505.svg#iconauth") format("svg");
		 
		 /*
		 "https://i.alipayobjects.com/common/fonts/rei.svg?20150616#rei") format("svg");
		 src: url("https://at.alicdn.com/t/font_1441789796_8667505.eot?#iefix") format("embedded-opentype"),
		 url("https://at.alicdn.com/t/font_1441789796_8667505.woff") format("woff"), 
		 url("https://at.alicdn.com/t/font_1441789796_8667505.ttf") format("truetype"), 
		 url("https://at.alicdn.com/t/font_1441789796_8667505.svg#iconauth") format("svg");
		 */
}

/* .iconauth {
    font-family:"rei";
    font-style: normal;
    font-weight: normal;
    cursor: default;
    -webkit-font-smoothing: antialiased;
} */
.iconauth {
	font-family:"rei";
    color: #fff;
    
    font-size: 24px;
    font-style: normal;
    -webkit-font-smoothing: antialiased;
}

html,body,div,span,p {
	margin: 0;
	padding: 0;
	border: 0;
	font-size: 100%;
	font: inherit;
	vertical-align: baseline;
}

body {
	line-height: 1;
}

body {
	font: 13px/20px 'Lucida Grande', Tahoma, Verdana, sans-serif;
	color: #404040;
	/*background: #e6e6e6;*/
	/* background: #ECECEC; */
}

.container {
	margin: 0px auto;
	width: 650px;
	height: 368px;
	/*background:url(img/world.jpg);*/
	/* background: url(images/world31.jpg) no-repeat; */
	background: url(images/loginbg.png) no-repeat;
}

#comtext {
	position: relative;
	width: 240px;
	left: 100px;
	/* top: -60px; */
	top: -190px;
}

#comtext p {
	margin-top: 6px;
}

/*#firsttext{
	letter-spacing:12px;
}*/
#firsttext,#secondtext {
	font-family: LiSu;
	/*color:#7fc042;*/
	color: #7c7c7c;
	font-size: 35px;
	font-weight: bold;
}

#secondtext {
	color: #000;
}

#thirdtext {
	color: #67A77B;
	/* 	color: #35B034; */
	/* 	color: #7fc042; */
	font-size: 10px;
	font-weight: bold;
}

.login {
	position: relative;
	/*margin: 0 auto;
  padding: 200px 200px ;*/
	width: 175px;
	left: 420px;
	top: 40px;
	/* 	top: 100px; */
}

.login p {
	margin: 10px 0 0;
}

/* .login p:first-child {
	margin-top: 0;
} */

.login div {
	margin: 10px 0 0;
	width: 168px; height: 32px;
	
}

.login div:first-child {
	margin-top: 0;
}

.login p.submit {
	text-align: right;
	margin-right: 30px;
}

input {
	font-family: 'Lucida Grande', Tahoma, Verdana, sans-serif;
	font-size: 14px;
}

.login input[type=text],.login input[type=password] {
	width: 120px;
}

input[type=text],input[type=password] {
	margin: 5px;
	margin-left:0px;
	padding: 0 10px;
	width: 66px; /*长度*/
	height: 34px;
	color: #404040;
	/*color: #C3C3C3;*/
	background: white;
	border: 1px solid;
	border-color: #c4c4c4 #d1d1d1 #d4d4d4;
	ime-mode: disabled;
}

input[type=submit] {
	padding: 0 18px;
	height: 29px;
	font-size: 12px;
	font-weight: bold;
	color: #527881;
	text-shadow: 0 1px #e3f1f1;
	background: #cde5ef;
	border: 1px solid;
	border-color: #b4ccce #b3c0c8 #9eb9c2;
	border-radius: 16px;
	outline: 0;
	-webkit-box-sizing: content-box;
	-moz-box-sizing: content-box;
	box-sizing: content-box;
	background-image: -webkit-linear-gradient(top, #edf5f8, #cde5ef);
	background-image: -moz-linear-gradient(top, #edf5f8, #cde5ef);
	background-image: -o-linear-gradient(top, #edf5f8, #cde5ef);
	background-image: linear-gradient(to bottom, #edf5f8, #cde5ef);
	-webkit-box-shadow: inset 0 1px white, 0 1px 2px rgba(0, 0, 0, 0.15);
	box-shadow: inset 0 1px white, 0 1px 2px rgba(0, 0, 0, 0.15);
}

input[type=submit]:active {
	background: #cde5ef;
	border-color: #9eb9c2 #b3c0c8 #b4ccce;
	-webkit-box-shadow: inset 0 0 3px rgba(0, 0, 0, 0.2);
	box-shadow: inset 0 0 3px rgba(0, 0, 0, 0.2);
}

.lt-ie9 input[type=text],.lt-ie9 input[type=password] {
	line-height: 34px;
}

.login-help {
	margin-top: -1px;
	margin-left: -30px;
	font-size: 11px;
	color: #35B033;
	text-align: center;
	/*text-shadow: 0 1px #2a85a1;*/
	font-size: 12px;
}

#title {
	/*margin: 100px auto;*/
	margin-top: 150px;
	margin-left: auto;
	margin-right: auto;
	width: 650px;
	/* height: 80px; */
	height: 60px;
	background: #e6e6e6;
}

#title>div:first-child {
	float: left;
	text-align: center;
	line-height: 60px;
	width: 200px;
	font-size: 30px;
	font-weight: bold;
	color: #7c7c7c;
}

#title>div:last-child {
	float: right;
	width: 350px;
	font-size: 10px;
	color: #7c7c7c;
	/* font-weight: bold; */
}

#title ul {
	position: relative;
	padding-left: 80px;
	/* left: 50%; */
	/* float: left; 
	text-align: right;*/
}

#title li {
	position: relative;
	/* right: 50%; */
	display: inline;
	/* float: left; */
	margin-left: 5px;
	line-height: 60px;
}
.ui-label .ui-icon {
    background-color: #aaabab;
    background-repeat: no-repeat;
    display: block;
    height: 32px;
    line-height: 32px;
    overflow: hidden;
    text-align: center;
    width: 32px;
}

.ui-label {
    border-bottom: 1px solid #a7a7a7;
    border-left: 1px solid #a7a7a7;
    border-top: 1px solid #a7a7a7;
}
.ui-icon:hover {
    /* border-bottom: 1px solid #6d6d6d;
    border-left: 1px solid #6d6d6d;
    border-top: 1px solid #6d6d6d; */
    background-color: #6d6d6d;
}
.ui-label {
     cursor: pointer; 
    display: block;
    float: left;
    height: 32px;
    width: 32px;
    margin-top:5px;
}

</style>
<title>login</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
	<%--<script type="text/javascript" src="js/jquery.js"></script>
	<script type="jscript">
		$(document).ready(function() {
			$("#submitBtn").submit(function () {
				var username = $("#loginpass").val();
				var userpass = $("#loginpass").val();
				alert(username+' '+userpass);
			});
		});
	</script>--%>
</head>

<body>
	<div id="title">
		<div><!-- ASWE --></div>
		<div>
			<ul>
				<li>Cloud App</li>
				<li>|</li>
				<li>WIFI/BT Network</li>
				<li>|</li>
				<li>IOT Net</li>
			</ul>
		</div>

	</div>

	<div class="container">
		<div class="login">

			<form action="login.do" method="post">
				<div id="message">${errormsg }</div>
				<div>
					<label id="J-label-user" class="ui-label"
						> <span
						class="ui-icon ui-icon-userDEF"> <i class="iconauth">&#xe600;</i>
					</span>
					</label> <input type="text" name="loginname" id="loginname"
						value="${loginname }" id="loginname" placeholder="User Name"
						autocomplete="off" onpropertychange="change()">
				</div>
				<div>
					<label id="J-label-user" class="ui-label"
						seed="authcenter-switch-account"> <span
						class="ui-icon ui-icon-userDEF"> <i class="iconauth">&#xe603;</i>
					</span>
					</label> <input type="password" name="loginpass" id="loginpass"
						value="${loginpass }" placeholder="Passowrd" onpropertychange="change()">
				</div>

				<p class="submit">
					<input type="submit" name="commit" value="Login" id="submitBtn">
				</p>
			</form>
			<div class="login-help">
				<p></p>
			</div>
		</div>


		<div id="comtext">
			<p id="firsttext"><!-- 岸欣科技 -->  </p>
			<p id="secondtext">Cloud Manage</p>
			<p id="thirdtext">WIFI&nbsp;|&nbsp;SWITCHING&nbsp;|&nbsp;SECURITY&nbsp;|&nbsp;IOT</p>
		</div>


	</div>
	<script type="text/javascript">
		document.getElementById("loginname").focus();
		function change() {
			document.getElementById("message").appendChild(document.createTextNode("${errormsg}"));
		}
	</script>
</body>
</html>
