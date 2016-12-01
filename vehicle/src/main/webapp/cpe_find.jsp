<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Find CPE</title>
<meta http-equiv="Content-Type" content="text/xhtml; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var sss = $('#serialno').val();
		$("#search").click(function() {
			$.post("cpe_findItemsJson.do", {
				snIn : sss
			}, function(result) {
				var msg = eval("(" + result + ")");
				var statusCode = msg.statusCode;
				alert(statusCode);
				//window.location.href = "test.jsp";
				$("#result").html(statusCode);
			});
		});
		/* function callbak(result) {
			var msg = eval("(" + result + ")");
			var statusCode = msg.statusCode;
			alert(statusCode);
			//window.location.href = "test.jsp";
			$("#result").html(statusCode);
		} */
	});

	function submit() {
		// 阻止表单默认提交

		var serialno = $('#serialno').val();

		var data = JSON.stringify({
			snIn : serialno
		});

		$.ajax({

			url : "cpe_findItemsJson.do",
			type : 'Post',
			data : {
				snIn : "021018010001"
			},
			cache : false,
			dataType : 'json',
			success : function(result) {
				var msg = eval("(" + result + ")");
				var statusCode = msg.statusCode;
				alert(statusCode);
				//window.location.href = "test.jsp";
				$("#result").html(statusCode);
			}
		});
	}
</script>

</head>

<body>
	<!-- <form action="cpe_findItems.do" method="post"> -->
	Serial No:
	<input id="serialno" type="text" name="snIn" />
	<input type="submit" value="Search"
		style="margin-top: 0;margin-bottom: 0" onclick="submit()" />
	<input type="button" value="查找" style="margin-top: 0;margin-bottom: 0"
		id="search" />
	<!-- </form> -->
	<form action="hosts_findItems.do" method="post">
		Serial No: <input id="serialno" type="text" name="snIn" /> <input
			type="submit" value="Search" style="margin-top: 0;margin-bottom: 0" />
	</form>
	<div id="result"></div>

</body>
</html>
