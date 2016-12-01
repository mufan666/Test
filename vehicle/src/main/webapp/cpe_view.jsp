<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<style type="text/css">
td {
	border: solid #add9c0;
	border-width: 0px 1px 1px 0px;
	padding: 10px 0px;
	text-align: center;
}

th {
	border: solid #add9c0;
	border-width: 0px 1px 1px 0px;
	padding: 10px 0px;
}

table {
	border: solid #add9c0;
	border-width: 1px 0px 0px 1px;
}
</style>
<title>My JSP 'cpe_view.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!-- <link rel="stylesheet" href="../css/artdialog.css" type="text/css"/> -->
<link rel="stylesheet" href="css/ui-dialog.css" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>


<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/metisMenu.js"></script>
<script type="text/javascript" src="js/dialog.js"></script>
<script type="text/javascript" src="js/dialog-plus.js"></script>
<!-- <script type="text/javascript" src="js/artDialog.iframeTools.js"></script> -->
<script type="text/javascript" src="js/jquery.event.ue.js"></script>
<script type="text/javascript" src="js/jquery.udraggable.js"></script>
<!-- <script type="text/javascript" src="js/dialog-plus.js"></script> -->

<script type="text/javascript">
	$(document).ready(function() {
		changebackgroundout();
		changebackgroundover();
		startRequest();
		setInterval("startRequest()", 5000);

		$(".remark").dblclick(function() {
			var curr = $(this);
			var hwid = $(this).parent().children(".hwid").text();
			var cpeid = $(this).parent().children(".cpeid").text();
			var serialno = $(this).parent().children(".serialno").children("a").text();
			var d = dialog({
				fixed : true,
				title : '修改备注',
				content : '<input autofocus id="auto" value="' + curr.text() + '"/>',
				ok : function() {
					var a = $("#auto").val();
					$.post("cpe_addRmarkByJson.do", {
						"hbean.hwid" : hwid,
						"hbean.serialno" : serialno,
						"hbean.remark" : a
					}, function(msg) {
						msg = eval('(' + msg + ')');
						if (msg.errormsg == 'error')
							alert("失败");
						else {
							$("[hid='" + cpeid + "']").text(a);
						}
					});
				},
				follow: this
			});
			d.show();
		});
		/* $("#test").click(function() {
			var d = dialog({
				title : 'message',
				content : '<input autofocus />'
			});
			d.showModal();
		}); */
	});
	function startRequest() {
		$.post("cpe_valOnLine.do", {}, function(msg) {
			msg = eval('(' + msg + ')'); //转为json对象
			var data = msg.data;
			$("#cpeinfo").empty();
			for (var i = 0, j = data.length; i < j; i++) {
				var u = data[i];
				var tr = $("<tr></tr>");
				var td0 = $("<td></td>").text(u.id);
				var td1 = $("<td></td>").text(u.model.oui);
				var td2 = $("<td></td>").text(u.model.manufacturer);
				var td3 = $("<td></td>").text(u.model.displayname);
				var td4 = $("<td></td>").text(u.currentsoftware);
				var td5 = $("<td></td>").text(u.hardware);
				var td6 = $("<td></td>").append("<a href='hosts_findItemByID.do?id=" + u.id + "'>" + u.serialno + "</a>");
				var td7 = $("<td></td>");
				var td8 = $("<td></td>").text(u.hwid);
				var td9 = $("<td></td>").text(u.remark);
				td8.css("display", "none");
				td8.attr("class", "hwid");
				td0.attr("class", "cpeid");
				td6.attr("class", "serialno");
				td9.attr("class", "remark");
				td9.attr("hid", u.id);
				if (u.onLine == 0)
					td7.text("初始化");
				if (u.onLine == 1)
					td7.text("在线");
				if (u.onLine == 2) {
					td7.text("掉线");
					td7.css("color", "red");
				}
				tr.append(td0, td1, td2, td3, td4, td5, td6, td7, td8, td9);
				tr.on("mouseout", function() {
					$(this).css("background-color", "#FFFFFF");
				});
				tr.on("mouseover", function() {
					$(this).css("background-color", "#F1F1F1");
				});
				td9.on("dblclick", updatedesByartdialog);
				$("#cpeinfo").append(tr);
			}
		});
	}
	function changebackgroundout() {
		$("tr").mouseout(function() {
			$(this).css("background-color", "#FFFFFF");
		});
	}
	function changebackgroundover() {
		$("tr").mouseover(function() {
			$(this).css("background-color", "#F1F1F1");
		});
	}
	function updatedesByartdialog() {
		var curr = $(this);
		var hwid = $(this).parent().children(".hwid").text();
		var cpeid = $(this).parent().children(".cpeid").text();
		var serialno = $(this).parent().children(".serialno").children("a").text();
		var d = dialog({
			fixed : true,
			title : '修改备注',
			content : '<input autofocus id="auto" value="' + curr.text() + '"/>',
			ok : function() {
				var a = $("#auto").val();
				$.post("cpe_addRmarkByJson.do", {
					"hbean.hwid" : hwid,
					"hbean.serialno" : serialno,
					"hbean.remark" : a
				}, function(msg) {
					msg = eval('(' + msg + ')');
					if (msg.errormsg == 'error')
						alert("失败");
					else {
						$("[hid='" + cpeid + "']").text(a);
					}
				});
			},
			follow: this
		});
		d.show();
	}
	function updatedes() {
		var hwid = $(this).parent().children(".hwid").text();
		var serialno = $(this).parent().children(".serialno").children("a").text();
		location.href = "hosts_addRmark.do?hbean.serialno=" + serialno + "&hbean.hwid=" + parseInt(hwid);
		/* var d = dialog({
			title : 'message',
			content : '<input autofocus />'
		});
		d.showModal(); */
	}
</script>

</head>

<body>
	<form method="post">
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
	</form>
</body>
</html>
