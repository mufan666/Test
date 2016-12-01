<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/metisMenu.css">
<link rel="stylesheet" href="css/head.css">
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
<title>My JSP 'default_conf_building.jsp' starting page</title>

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
	var building;
	$(document).ready(function() {
		/* alert(GetQueryString("building")); */
		building = GetQueryString("building");
		$("#menu>li>ul>li").removeClass("activeli");
		$("#menu>li").removeClass("active");
		$("#menu>li").removeClass("activefirstli");
		$("[href='default_conf_building.jsp']").parent().parent().parent().addClass("activefirstli");
		$("[href='default_conf_building.jsp']").parent().parent().parent().addClass("active");
		$("[href='default_conf_building.jsp']").parent().addClass("activeli");
		$('#menu').metisMenu();

		$("#menu>li>ul>li").click(function() {
			$("#menu>li>ul>li").removeClass("activeli");
			$(this).addClass("activeli");
		});

		$("#menu>li").click(function() {
			$("#menu>li").removeClass("activefirstli");
			$(this).addClass("activefirstli");
		});

		getbuild();
		getHosts();
		$("#buildbox").change(function() {
			var v = $("#buildbox").find("option:selected");
			$("#picture").css({
				"width" : v.attr("buildwidth"),
				"height" : v.attr("buildheight"),
				"position" : "relative",
				"background" : "url(" + v.attr("picturePath") + ") 0 0 no-repeat"
			});
			$("#picture").attr("buildingid", v.val());
			$("#picture").attr("unitid", v.attr("unitid"));
			building = v.val();
			$("#picture").empty();
			getfloorbybuild();
			getHosts();
		});
	});
	function GetQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	}
	function getbuild() {

		$.post("build_getByUnitId.do", {
			'building.unitId' : '1'
		}, function(msg) {
			genBuild(msg);
		});
	}
	function getfloorbybuild() {

		$.post("floor_getFloorByBuildingId.do", {
			'floor.buildingId' : building
		}, function(msg) {
			msg = eval('(' + msg + ')');
			var floors = msg.data;
			for (var i = 0; i < floors.length; i++) {
				var floor = floors[i];
				var floordiv = $("<div></div>").text(floor.floorName);
				floordiv.addClass("floor");
				floordiv.css({
					"color" : "white",
					"position" : "absolute",
					"width" : "60px",
					"height" : "30px",
					"background" : "#00ffff",
					"left" : floor.x,
					"top" : floor.y,
					"text-align" : "center",
					"line-height" : "30px",
					"cursor" : "pointer",
					"-moz-user-select" : "none",
					"-khtml-user-select" : "none",
					"user-select" : "none",
					"border-radius" : "12px"
				});
				floordiv.attr("floor", floor.id);
				floordiv.on("click", function() {
					/* floorload(floor); */
					location.href = "default_conf_floor.jsp?floorid=" + $(this).attr("floor") + "&unitid=" + $("#picture").attr("unitid") + "&buildingid="
							+ $("#picture").attr("buildingid");
				});
				$("#picture").append(floordiv);
			}
		});
	}

	function genBuild(msg) {
		msg = eval('(' + msg + ')');
		var builds = msg.data;
		for (var i = 0; i < builds.length; i++) {
			var build = builds[i];
			var option = $("<option></option>").text(build.buildingName).val(build.id);
			option.attr("buildwidth", build.width);
			option.attr("buildheight", build.height);
			option.attr("picturePath", build.picturePath);
			option.attr("unitid", build.unitId);
			if (build.id == building) {
				option.attr("selected", "selected");
				var widthattr = build.width;
				var heigthattr = build.height;
				if (build.width > 700) {
					var percentage = 700 / build.width;
					widthattr = 700;
					heigthattr = Math.ceil(build.height * percentage);
				}
				$("#picture").css({
					"width" : widthattr,
					"height" : heigthattr,
					/* "width":"100%","height":"100%", */
					"position" : "relative",
					"background" : "url(" + build.picturePath + ") 0 0 no-repeat"
				});
				$("#picture").attr("buildingid", build.id);
				$("#picture").attr("unitid", build.unitId);

			}
			$("#buildbox").append(option);
		}

		if (building == null || typeof (building) == undefined || building == '') {
			build = builds[0];
			building = build.id;
			$("#picture").css({
				"width" : build.width,
				"height" : build.height,
				"position" : "relative",
				"background" : "url(" + build.picturePath + ") 0 0 no-repeat"
			});
			$("#picture").attr("buildingid", build.id);
			$("#picture").attr("unitid", build.unitId);
		}
		getfloorbybuild();
	}
	function getHosts() {
		if(building == null || typeof (building) == undefined || building == '')
			building=1;
		
		$.post("cpe_getByBuild.do", {
			'hbean.buildingId' : building
		}, function(msg) {
			msg = eval('(' + msg + ')');
			var hosts = msg.data;
			$("#trhead").siblings().remove();
			for (var i = 0; i < hosts.length; i++) {

				var host = hosts[i];
				var trtag = $("<tr></tr>");
				trtag.attr("trid", host.id);
				trtag.append($("<td></td>").append("<INPUT name='chkSon' type='checkbox' value='"+host.id+"'/>"));
				trtag.append($("<td></td>").text(i + 1));
				trtag.append($("<td></td>").text(host.serialno));
				
				/* if(host.floor.floorName == null || typeof (host.floor.floorName) == undefined){
					alert(1);
				} */
				var xtd;
				if(host.floor == null){
					xtd = $("<td></td>").text("");
				}else if(host.floor.floorName == null){
					xtd = $("<td></td>").text("");
				}else{
					xtd = $("<td></td>").text(host.floor.floorName);
				}
				xtd.addClass("floor");
				xtd.on("dblclick", function() {
					edit($(this), "floor");
				});
				trtag.append(xtd);
				$("#hostsdiv").append(trtag);
			}
		});
	}
	function edit(td, field) {
		td.off("dblclick");
		oldText = td.text();
		td.empty();
		/* var inputtag = $("<input></input>");
		inputtag.width(td.width());
		inputtag.height(td.height());
		inputtag.val(oldText); */
		var selecttag = $("<select></select>");
		var flooroption = $("#picture").find(".floor");
		flooroption.each(function() {
			var textVal = $(this).text();
			var floorVal = $(this).attr("floor");
			var optiontag = $("<option></option>").val(floorVal).text(textVal);
			if (oldText == textVal)
				optiontag.attr("selected", "selected");
			selecttag.append(optiontag);
		});
		td.append(selecttag);
		selecttag.on("blur", function() {
			saveVal(td, field);
		});
		/*inputtag.focus();
		inputtag.select();
		inputtag.on("blur", function() {
			saveVal(td, field);
		}); */
	}
	function saveVal(td, field) {
		var currText = td.find("select").find("option:selected");
		td.empty();
		td.text(currText.text());
		td.on("dblclick", function() {
			edit(td, field);
		});
		var hostsid = td.parent().find("input[name^='chkSon']").val();
		/* alert(hostsid+":"+currText.val()+":"+currText.text()); */
		if (oldText != currText) {
			var arr = {
				'hbean.id' : hostsid
			};
			arr['hbean.floorId'] = currText.val();
			$.post("cpe_updateField.do", arr, function(msg) {
			});
		} 

	}
	function isNull(val){
		if(val == null || typeof (val) == undefined){
			return "";
		}
	}
	function JsonIsNull(value) {
        var type;
        if (value == null) { // 等同于 value === undefined || value === null
            return true;
        }
        type = Object.prototype.toString.call(value).slice(8, -1);
        switch (type) {
            case 'String':
                return !!$.trim(value);
            case 'Array':
                return !value.length;
            case 'Object':
                return $.isEmptyObject(value); // 普通对象使用 for...in 判断，有 key 即为 false
            default:
                return false; // 其他对象均视作非空
        }
    };
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
			<div>
				<select id="buildbox"></select>
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
					<td width="100">楼层</td>
				</tr>
			</table>
		</div>
	</div>

</body>
</html>
