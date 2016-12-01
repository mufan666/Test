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
	background: #00ffff;
	cursor: pointer;
	border-radius: 3px;
	height: 25px;
	-moz-user-select: none;
	-khtml-user-select: none;
	user-select: none;
	-khtml-user-select: none;
}
</style>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/metisMenu.css" rel="stylesheet">
<link href="css/head.css" rel="stylesheet">
<link href="css/jquery-ui.css" type="text/css" rel="stylesheet">
<link type="text/css" href="css/ui-dialog.css" rel="stylesheet">

<title>My JSP 'default_conf_floor.jsp' starting page</title>

<meta content="no-cache" http-equiv="pragma">
<meta content="no-cache" http-equiv="cache-control">
<meta content="0" http-equiv="expires">
<meta content="keyword1,keyword2,keyword3" http-equiv="keywords">
<meta content="This is my page" http-equiv="description">
<!-- <script src="js/jquery.js" type="text/javascript"></script>
<script src="js/jquery-ui.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script src="js/metisMenu.js" type="text/javascript"></script>
<script src="js/dialog.js" type="text/javascript"></script>
<script src="js/dialog-plus.js" type="text/javascript"></script>
<script src="js/jquery.event.ue.js" type="text/javascript"></script>
<script src="js/jquery.udraggable.js" type="text/javascript"></script>-->


</head>

<body>

	<div class="div_head">
		<!-- 绿线 -->
		<div id="greenline"></div>
		<div>
			<div class="div_head_li">
				<ul>
					<li>平台导航助手：</li>
					<li><a href="#">无线网线导航</a></li>
					<li>|</li>
					<li><a>安全管理导航</a></li>
					<li>|</li>
					<li><a>AP配置导航</a></li>
				</ul>
				<div id="testtitle">测试版</div>
			</div>


		</div>
		<!-- 灰线 -->
		<div id="grayline"></div>
		<div style="height:120px;" id="fourfloor">
			<div style="float: left; width:600px;height:60px;">
				<div
					style="margin-left:30px;margin-top:10px;font-family:FangSong;font-size:30px;font-weight:bold;">
					<font color="#6B92BD">ASWE</font> <font color="#86B859">无线AP远程管理平台</font>
				</div>
			</div>
			<div style="float: right; width:600px;">
				<div style="margin-right:10px;margin-top:-5px;" id="rightbar">
					<!-- style="margin-left:245px;" -->
					<ul>
						<li>当前用户：詹姆斯</li>
						<li><a>管理员信息</a></li>
						<li>|</li>
						<li><a>退出登录</a></li>
					</ul>
				</div>
				<div style="text-align:right;margin-top:10px;margin-right:10px;">
					<img style="margin-right:10px;" src="images/search.png"><input
						width="50" type="text" placeholder="站内搜索">
				</div>
				<!-- <div><img src="img/ss.png"><input type="text" width="50"></div> -->
			</div>
		</div>
	</div>
	<div style="float:left;width:200px;" id="left">


		<div class="clearfix">
			<aside class="sidebar"> <nav class="sidebar-nav">
			<ul id="menu" class="metismenu">
				<li class=""><a aria-expanded="false" href="#">系统监控<span
						class="fa arrow"></span></a>
					<ul aria-expanded="false" class="collapse">
						<li class=""><a href="default.jsp">网络概况</a></li>
						<li><a href="#">区域地图(Map)</a></li>
						<li><a href="#">AP工作状况</a></li>
						<li><a href="#">IOT工作状况</a></li>
						<li><a href="#">数据报文</a></li>
						<li><a href="#">无线覆盖</a></li>
						<li><a href="#">接入用户密度</a></li>
						<li><a href="#">接入流量管理</a></li>
						<li><a href="#">工作日志（Log）</a></li>
						<li><a href="#">报告</a></li>
					</ul></li>
				<li class="activefirstli active"><a aria-expanded="false"
					href="hosts_findAll.do">配置管理<span class="glyphicon arrow"></span></a>
					<ul aria-expanded="true" class="collapse in">
						<li><a href="default_conf_building.jsp">大楼管理</a></li>
						<li class="activeli"><a href="default_conf_floor.jsp">楼层管理</a></li>
						<li><a href="#">item 1.3</a></li>
					</ul></li>
				<li><a aria-expanded="false" href="#">IOT数据分析<span
						class="glyphicon arrow"></span></a>
					<ul aria-expanded="false" class="collapse">
						<li><a href="#">item 2.1</a></li>
						<li><a href="#">item 2.2</a></li>
						<li><a href="#">item 2.3</a></li>
						<li><a href="#">item 2.4</a></li>
					</ul></li>
				<li><a aria-expanded="false" href="#">应用程序<span
						class="glyphicon arrow"></span></a></li>
				<li><a aria-expanded="false" href="#">系统数据输出<span
						class="glyphicon arrow"></span></a></li>
				<li><a aria-expanded="false" href="#">帮助<span
						class="glyphicon arrow"></span></a></li>
			</ul>
			</nav> </aside>
		</div>
	</div>
	<div style="padding-left:210px;/*; border-left:1px solid #00F */"
		id="right">
		<div style="width:700px;float:left">
			<div id="shortcutBar">
				<ul>
					<li><div>
							<select id="buildbox"><option value="1"
									buildheight="548" picturepath="images/building/building1.jpg">一号楼</option>
								<option value="2" buildheight="306"
									picturepath="images/building/asd1.png">二号楼</option></select>
						</div></li>
					<li><div>
							<select id="floorbox"><option value="1"
									floorheight="480" picturepath="images/floor/aac.jpg"
									buildingid="1" selected="selected" style="display: block;">一楼</option>
								<option value="2" floorheight="341"
									picturepath="images/floor/aad.jpg" buildingid="1"
									style="display: block;">二楼</option>
								<option value="3" floorheight="586"
									picturepath="images/floor/aas.jpg" buildingid="1"
									style="display: block;">三楼</option></select>
						</div></li>
					<li><div id="monitorbutton" class="barbutton"
							style="width:40px;">监控</div></li>
					<li><div id="editbutton" class="barbutton" style="width:40px;">编辑</div></li>
					<li><div id="conbutton" class="barbutton" style="width:40px;">配置</div></li>
					<!-- <li><div style="float:left;width:40px;height:20px">编辑</div></li> -->
				</ul>
			</div>
			<!-- <div> 
				<select id="buildbox"></select><select id="floorbox"></select>
				<div style="float:left;width:40px;height:20px">编辑</div>
			</div> -->
			<div id="picture"
				style="height: 480px; position: relative; background: transparent url(&quot;images/floor/aac.jpg&quot;) no-repeat scroll 0px 0px;"
				floorid="1" buildingid="1">
				<div id="host1" hostsid="1" class="sub-drag"
					style="position: absolute; width: 20px; height: 20px; background: red none repeat scroll 0% 0%; left: 0px; top: 0px; cursor: pointer; -moz-user-select: none; border-radius: 10px;"></div>
				<div id="host4" hostsid="4" class="sub-drag"
					style="position: absolute; width: 20px; height: 20px; background: red none repeat scroll 0% 0%; left: 0px; top: 0px; cursor: pointer; -moz-user-select: none; border-radius: 10px;"></div>
				<div id="host5" hostsid="5" class="sub-drag"
					style="position: absolute; width: 20px; height: 20px; background: red none repeat scroll 0% 0%; left: 100px; top: 210px; cursor: pointer; -moz-user-select: none; border-radius: 10px;"></div>
				<div id="host6" hostsid="6" class="sub-drag"
					style="position: absolute; width: 20px; height: 20px; background: rgb(0, 255, 255) none repeat scroll 0% 0%; left: 240px; top: 150px; cursor: pointer; -moz-user-select: none; border-radius: 10px;"></div>
			</div>
		</div>
		<div style="float:right;/* background:red; */width:400px">
			<table width="400" border="1" id="hostsdiv">
				<tbody>
					<tr id="trhead">
						<td width="50"><input type="checkbox" title="全选" id="chkAll"
							name="chkAll">全选</td>
						<td width="50">序号</td>
						<td width="100" align="center">AP</td>
						<td width="50">x</td>
						<td width="50">y</td>
					</tr>
					<tr hostsid="1">
						<td><input type="checkbox" value="1" name="chkSon"></td>
						<td>1</td>
						<td>4CE676D9A2E2</td>
						<td class="x" style="width: 14px; height: 19px;">0</td>
						<td class="y">0</td>
					</tr>
					<tr hostsid="4">
						<td><input type="checkbox" value="4" name="chkSon"></td>
						<td>2</td>
						<td>4CE676D9A2E2</td>
						<td class="x" style="width: 14px; height: 19px;">0</td>
						<td class="y">0</td>
					</tr>
					<tr hostsid="5">
						<td><input type="checkbox" value="5" name="chkSon"></td>
						<td>3</td>
						<td>4CE676D9A2DC</td>
						<td class="x" style="width: 14px; height: 19px;">100</td>
						<td class="y">210</td>
					</tr>
					<tr hostsid="6">
						<td><input type="checkbox" value="6" name="chkSon"></td>
						<td>4</td>
						<td>00904C084050</td>
						<td class="x" style="width: 14px; height: 19px;">240</td>
						<td class="y">150</td>
					</tr>
				</tbody>
			</table>
			x:<input id="mousex">y:<input id="mousey">
		</div>
	</div>
	<script src="js/sea.js"></script> 
	<script>
		seajs.config({
			alias : {
				"jquery" : "jquery.js"
			}
		});
	</script>

	<script>
		seajs.use([ 'jquery', 'dialog' ], function($, dialog) {
			window.dialog = dialog;
			/* $('#test').on('click', function() {
				alert('click');
			}); */

			var d = dialog({
				title : '消息',
				content : '风吹起的青色衣衫，夕阳里的温暖容颜，你比以前更加美丽，像盛开的花<br>——许巍《难忘的一天》',
				okValue : '确 定'
			});

			var elem = document.getElementById('test-content');

			$('#test2').on('click', function() {
				d.content(elem).show();
			});

			$('#test3').on('click', function() {
				d.close();
			});

			$('#test4').on('click', function() {
				d.show();
			});

			$('#test5').on('click', function() {
				d.close().remove();
			});

			d.addEventListener('remove', function() {
				$('#test2, #test3, #test4, #test5').off('click').on('click', function() {
					alert('对话框已经销毁');
				});
			});

		});
	</script>

</body>
</html>
