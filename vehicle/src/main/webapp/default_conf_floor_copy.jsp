<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<style type="text/css">
#shortcutBar {
	height: 30px;
}

#shortcutBar ul {
	position: relative;
	/* left: 50%; */
	float: left;
}

#shortcutBar li {
	position: relative;
	/* right: 50%; */
	display: inline;
	float: left;
	margin-left: 10px;
	line-height: 30px;
}

.barbutton {
	line-height: 25px;
	text-align: center;
	background: #00ff00;
	cursor: pointer;
	border-radius: 3px;
	height: 25px;
	-moz-user-select: none;
	-khtml-user-select: none;
	user-select: none;
	-khtml-user-select: none;
}
 .barbutton:hover {
	background: #35b033;
	color:white;
} 
.currButton{
	background: #35b033;
	color:white;
}
</style>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/metisMenu.css">
<link rel="stylesheet" href="css/head.css">
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
<link rel="stylesheet" href="css/ui-dialog.css" type="text/css" />

<title>My JSP 'default_conf_floor.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/metisMenu.js"></script>
<script type="text/javascript" src="js/dialog.js"></script>
<script type="text/javascript" src="js/dialog-plus.js"></script>
<!-- <script type="text/javascript" src="js/artDialog.iframeTools.js"></script> -->
<script type="text/javascript" src="js/jquery.event.ue.js"></script>
<script type="text/javascript" src="js/jquery.udraggable.js"></script>


<script type="text/javascript">
	$(document).ready(function() {

		unitid = GetQueryString("unitid");
		floorid = GetQueryString("floorid");
		buildingid = GetQueryString("buildingid");
		$("#menu>li>ul>li").removeClass("activeli");
		$("#menu>li").removeClass("active");
		$("#menu>li").removeClass("activefirstli");
		$("[href='default_conf_floor.jsp']").parent().parent().parent().addClass("activefirstli");
		$("[href='default_conf_floor.jsp']").parent().parent().parent().addClass("active");
		$("[href='default_conf_floor.jsp']").parent().addClass("activeli");
		$('#menu').metisMenu();

		$("#menu>li>ul>li").click(function() {
			$("#menu>li>ul>li").removeClass("activeli");
			$(this).addClass("activeli");
		});

		$("#menu>li").click(function() {
			$("#menu>li").removeClass("activefirstli");
			$(this).addClass("activefirstli");
		});
		
		if(unitid == null || typeof (unitid) == undefined || unitid == '')
			unitid=1;
		if(floorid == null || typeof (floorid) == undefined || floorid == '')
			floorid=1;
		if(buildingid == null || typeof (buildingid) == undefined || buildingid == '')
			buildingid=1;

		getBuild();
		getFloor();
		getHosts();

		$("#buildbox").change(function() {
			var v = $("#buildbox").find("option:selected");
			buildingid = v.val();
			$("#floorbox").children().hide();
			$("#floorbox").find($("#floorbox [buildingid=" + buildingid + "]")).show();
			var f = $("#floorbox").find("option:selected");
			f.removeAttr("selected");
			var firstFloor = $("#floorbox").find($("#floorbox [buildingid=" + buildingid + "]:first"));

			if (typeof (firstFloor.attr("picturePath")) == "undefined")
				return;
			firstFloor.attr("selected", "selected");
			/* $("#picture").css({
				"width" : firstFloor.attr("floorwidth"),
				"height" : firstFloor.attr("floorheight"),
				"position" : "relative",
				"background" : "url(" + firstFloor.attr("picturePath") + ") 0 0 no-repeat"
			}); */
			changePicture(firstFloor.attr("floorwidth"), firstFloor.attr("floorheight"), firstFloor.attr("picturePath"));
			$("#picture").empty();
			$("#trhead").siblings().remove();
			buildingid = v.val();
			floorid = firstFloor.val();

			getHosts();
		});
		$("#floorbox").change(function() {
			var f = $("#floorbox").find("option:selected");
			/* $("#picture").css({
				"width" : f.attr("floorwidth"),
				"height" : f.attr("floorheight"),
				"position" : "relative",
				"background" : "url(" + f.attr("picturePath") + ") 0 0 no-repeat"
			}); */
			changePicture(f.attr("floorwidth"), f.attr("floorheight"), f.attr("picturePath"));
			$("#picture").attr("floorid", f.val());
			$("#picture").attr("buildingid", f.attr("buildingid"));
			floorid = f.val();
			$("#picture").empty();
			$("#trhead").siblings().remove();
			getHosts();
		});

		$("#picture").mousemove(function(e) {
			var offset = $(this).offset();
			var relativeX = (e.pageX - offset.left);
			var relativeY = (e.pageY - offset.top);
			$("#mousex").val(relativeX);
			$("#mousey").val(relativeY);
		}).mouseout(function() {
			$("#mousex").val("");
			$("#mousey").val("");
		});
		$("#monitorbutton").click(function() {
			/* $(".barbutton").css({
				"background" : "#00ff00",
				"color" : "black"
			});
			$("#monitorbutton").css({
				"background" : "#35b033",
				"color" : "white"
			}); */
			$(".barbutton").removeClass("currButton");
			$("#monitorbutton").addClass("currButton");
			
			getHosts();
			start();

		});
		$("#conbutton").click(function() {
			/* $(".barbutton").css({
				"background" : "#00ff00",
				"color" : "black"
			});
			$("#conbutton").css({
				"background" : "#35b033",
				"color" : "white"
			}); */
			$(".barbutton").removeClass("currButton");
			$("#conbutton").addClass("currButton");
			clear();
			
			getHosts(1);
			
		});
		$("#editbutton").click(function() {
			/* $(".barbutton").css({
				"background" : "#00ff00",
				"color" : "black"
			});
			$("#editbutton").css({
				"background" : "#35b033",
				"color" : "white"
			}); */
			$(".barbutton").removeClass("currButton");
			$("#editbutton").addClass("currButton");
			clear();
			/* $.getScript("js/jquery.udraggable.js");  */
			getHosts(2);
		});

		start();
	});
	var unitid;
	var floorid;
	var buildingid;
	var oldText;

	function edit(td, field) {
		td.off("dblclick");
		oldText = td.text();
		td.empty();
		var inputtag = $("<input></input>");
		inputtag.width(td.width());
		inputtag.height(td.height());
		inputtag.val(oldText);

		td.append(inputtag);
		inputtag.focus();
		inputtag.select();
		inputtag.on("blur", function() {
			saveVal(td, field);
		});
	}
	function saveVal(td, field) {
		var currText = td.find("input").val();
		td.empty();
		td.text(currText);
		td.on("dblclick", function() {
			edit(td, field);
		});
		var hostsid = td.parent().find("input[name^='chkSon']").val();
		if (oldText != currText) {
			var arr = {
				'hbean.id' : hostsid
			};
			if (field == 'x') {
				arr['hbean.x'] = currText;
			} else {
				arr['hbean.y'] = currText;
			}
			$.post("cpe_updateField.do", arr, function(msg) {
			});
			if (field == 'x') {
				$("#host" + hostsid).css("left", currText);
			} else {
				$("#host" + hostsid).css("top", currText);
			}

		}

	}

	function getBuild() {
		$.post("build_getByUnitId.do", {
			'building.unitId' : unitid
		}, function(msg) {
			msg = eval('(' + msg + ')');
			var builds = msg.data;
			for (var i = 0; i < builds.length; i++) {
				var build = builds[i];
				var option = $("<option></option>").text(build.buildingName).val(build.id);
				option.attr("buildwidth", build.width);
				option.attr("buildheight", build.height);
				option.attr("picturePath", build.picturePath);
				$("#buildbox").append(option);
			}
		});
	}
	function getHosts() {
		getHosts(0);
	}

	function getHosts(/* boolean */flag) {
		$.post("cpe_getByFloor.do", {
			'hbean.floorId' : floorid
		}, function(msg) {
			msg = eval('(' + msg + ')');
			var hosts = msg.data;
			$("#picture").empty();
			$("#trhead").siblings().remove();
			for (var i = 0; i < hosts.length; i++) {
				var host = hosts[i];
				var hostdiv = $("<div></div>");
				hostdiv.attr("id", "host" + host.id);
				hostdiv.attr("hostsid", host.id);
				hostdiv.addClass("sub-drag");
				hostdiv.css({
					"position" : "absolute",
					"width" : "20px",
					"height" : "20px",
					"background" : "#00ffff",
					"left" : host.x,
					"top" : host.y,
					"cursor" : "pointer",
					"-moz-user-select" : "none",
					"-khtml-user-select" : "none",
					"user-select" : "none",
					"border-radius" : "10px"
				});
				if (host.onLine == 2) {
					hostdiv.css("background", "red");
				}
				$("#picture").append(hostdiv);
				var trtag = $("<tr></tr>");
				trtag.attr("trid", host.id);
				trtag.append($("<td></td>").append("<INPUT name='chkSon' type='checkbox' value='"+host.id+"'/>"));
				trtag.append($("<td></td>").text(i + 1));
				trtag.append($("<td></td>").text(host.serialno));
				var xtd = $("<td></td>").text(host.x);
				xtd.addClass("x");
				xtd.on("dblclick", function() {
					edit($(this), "x");
				});
				xtd.css({
					width : 14,
					height : 19
				});
				var ytd = $("<td></td>").text(host.y);
				ytd.addClass("y");

				ytd.on("dblclick", function() {
					edit($(this), "y");
				});
				trtag.append(xtd, ytd);

				$("#hostsdiv").append(trtag);
			}
			if (flag == 1){
				$(".sub-drag").on("click", editH);
			}
			if (flag == 2) {
				$('.sub-drag').udraggable({
					containment : 'parent',
					stop : function(e, ui) {
						var pos = ui.position;
						var tab_tr = $("[trid='" + $(this).attr("hostsid") + "']");
						tab_tr.find(".x").text(pos.left);
						tab_tr.find(".y").text(pos.top);
						var arr = {
							'hbean.id' : $(this).attr("hostsid")
						};
						arr['hbean.x'] = pos.left;
						arr['hbean.y'] = pos.top;
						$.post("cpe_updateField.do", arr, function(msg) {
						});
					}
				});
			}
		});
	}

	function getFloor() {
		$.post("floor_getFloorByUnit.do", {
			'building.unitId' : unitid
		}, function(msg) {
			msg = eval('(' + msg + ')');
			var floors = msg.data;
			for (var i = 0; i < floors.length; i++) {
				var floor = floors[i];
				var option = $("<option></option>").text(floor.floorName).val(floor.id);
				option.attr("floorwidth", floor.width);
				option.attr("floorheight", floor.height);
				option.attr("picturePath", floor.picturePath);
				option.attr("buildingid", floor.buildingId);
				if (floor.id == floorid) {
					option.attr("selected", "selected");
					/* $("#picture").css({
						"width" : floor.width,
						"height" : floor.height,
						"position" : "relative",
						"background" : "url(" + floor.picturePath + ") 0 0 no-repeat"
					}); */
					changePicture(floor.width, floor.height, floor.picturePath);
					$("#picture").attr("floorid", floor.id);
					$("#picture").attr("buildingid", floor.buildingId);

				}
				$("#floorbox").append(option);
				$("#floorbox").children().hide();
				$("#floorbox").find($("#floorbox [buildingid=" + buildingid + "]")).show();

			}
		});
	}
	var editH = function editHosts() {
		var d = dialog({
			id : 'host1',
			align : 'top',
			fixed : true,
			title : '修改配置',
			height : 500,
			width : 400,
			lock : false,
			left : 30

		});
		jQuery.ajax({
			url : 'hosts_findItemByID.do?id=' + $(this).attr("hostsid"),
			success : function(data) {
				d.content(data);
			}
		});
		d.showModal();
	};
	function GetQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	}
	function changePicture(width, height, path) {
		$("#picture").css({
			"width" : width,
			"height" : height,
			"position" : "relative",
			"background" : "url(" + path + ") 0 0 no-repeat"
		});

	}
	var refreshid = "";
	function start() {
		refreshid = setInterval(getResult, 2000);
	}

	function clear() {
		clearInterval(refreshid);
	}
	function getResult() {
		getHosts();
	}
</script>
</head>

<body>
	<%@include file="/head.jsp"%>
	<div id="left" style="float:left;width:200px;">
		<%-- <%@include file="/menu.jsp"%> --%>
		<%@include file="/menuli.jsp"%>
	</div>
	<div id="right"
		style="padding-left:210px;/*; border-left:1px solid #00F */">
		<div style="width:700px;float:left">
			<div id="shortcutBar">
				<ul>
					<li><div>
							<select id="buildbox"></select>
						</div></li>
					<li><div>
							<select id="floorbox"></select>
						</div></li>
					<li><div style="width:40px/* ;background:#35b033;color:white */;"
							class="barbutton currButton" id="monitorbutton">监控</div></li>
					<li><div style="width:40px;" class="barbutton" id="editbutton">编辑</div></li>
					<li><div style="width:40px;" class="barbutton" id="conbutton">配置</div></li>
				</ul>
			</div>
			<div id="picture"></div>
		</div>
		<div style="float:right;/* background:red; */width:400px">
			<table width="400" border="1" id="hostsdiv">
				<tr id="trhead">
					<td width="50"><INPUT name="chkAll" id="chkAll" title="全选"
						type="checkbox" />全选</td>
					<td width="50">序号</td>
					<td align="center" width="100">AP</td>
					<td width="50">x</td>
					<td width="50">y</td>
				</tr>
			</table>
			x:<input id="mousex" />y:<input id="mousey" />
		</div>
	</div>
</body>
</html>
