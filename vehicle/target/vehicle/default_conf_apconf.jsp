<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<!-- <link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/metisMenu.css">
<link rel="stylesheet" href="css/head.css">
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
<title>My JSP 'default_conf_apconf.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/metisMenu.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#menu>li>ul>li").removeClass("activeli");
	$("#menu>li").removeClass("active");
	$("#menu>li").removeClass("activefirstli");
	$("[href='default_conf_apconf.jsp']").parent().parent().parent().addClass("activefirstli");
	$("[href='default_conf_apconf.jsp']").parent().parent().parent().addClass("active");
	$("[href='default_conf_apconf.jsp']").parent().addClass("activeli");
	$('#menu').metisMenu();

	$("#menu>li>ul>li").click(function() {
		$("#menu>li>ul>li").removeClass("activeli");
		$(this).addClass("activeli");
	});

	$("#menu>li").click(function() {
		$("#menu>li").removeClass("activefirstli");
		$(this).addClass("activefirstli");
	});
});
</script> -->
</head>

<body>
	<%-- <%@include file="/head.jsp"%>
	<div id="left" style="float:left;width:200px;">
		<%@include file="/menu.jsp"%>
		<%@include file="/menuli.jsp"%>
	</div>
	<div id="right"
		style="padding-left:210px;/*; border-left:1px solid #00F */">
		<%@include file="/cpe_view.jsp"%>
	</div> --%>
	<table width="700" cellspacing="0" cellpadding="0" border="0">
			<colgroup span="6"></colgroup>
			<thead>
				<tr>
					<th><div>ID</div></th>
					<th><div>OUI</div></th>
					<th><div>供应商</div></th>
					<th><div>Model</div></th>
					<th><div>当前版本</div></th>
					<th><div>硬件版本</div></th>
					<th><div>MAC地址</div></th>
					<th><div>状态</div></th>
					<th><div>备注</div></th>
				</tr>
			</thead>
			<tbody id="cpeinfo">
				<!-- <div id="cpeinfo"> -->
				<c:forEach items="${jd.data }" var="u" varStatus="aa">
					<tr>
						<!-- onmouseout="this.style.backgroundColor='#FFFFFF'"
						onmouseover="this.style.backgroundColor='#F1F1F1'"
						style="background-color: rgb(255, 255, 255);" -->
						<td style="display:none" class="hwid">${u.hwid }</td>
						<td class="cpeid">${u.id }</td>
						<td>${u.model.oui }</td>
						<td>${u.model.manufacturer }</td>
						<td>${u.model.displayname }</td>
						<td>${u.currentsoftware }</td>
						<td>${u.hardware }</td>
						<td class="serialno"><a
							href="hosts_findItemByID.do?id=${u.id }">${u.serialno }</a></td>
						<td><c:choose>
								<c:when test="${u.onLine eq 0 }">初始化</c:when>
								<c:when test="${u.onLine eq 1 }">在线</c:when>
								<c:when test="${u.onLine eq 2 }">
									<div style="color:red;">掉线</div>
								</c:when>
							</c:choose></td>
						<td class="remark" hid=${u.id }>${u.remark }</td>
					</tr>
				</c:forEach>

				<!-- </div> -->
			</tbody>

		</table>
</body>
</html>
