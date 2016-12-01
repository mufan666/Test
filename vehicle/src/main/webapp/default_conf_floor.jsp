<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
</head>

<body>
	<div style="width:770px;float:left">
		<div id="shortcutBar">
			<ul>
				<li><div>
						<select id="buildbox"></select>
					</div></li>
				<li><div>
						<select id="floorbox"></select>
					</div></li>
				<li><div
						style="width:40px/* ;background:#35b033;color:white */;"
						class="barbutton currButton" id="monitorbutton">监控</div></li>
				<li><div style="width:40px;" class="barbutton" id="editbutton">编辑</div></li>
				<li><div style="width:40px;" class="barbutton" id="conbutton">配置</div></li>
			</ul>
		</div>
		<div id="picture"></div>
		<div style="width:600px;heigth:40px;position: relative;">
			<!-- <div
				style="position: absolute; width: 15px; height: 15px; background: green none repeat scroll 0% 0%; left: 30px; top: 12px; cursor: pointer; -moz-user-select: none; border-radius: 10px;"></div>
			<div
				style="position: absolute; width: 15px; height: 15px; background: yellow none repeat scroll 0% 0%; left: 110px; top: 12px; cursor: pointer; -moz-user-select: none; border-radius: 10px;"></div>
			<div
				style="position: absolute; width: 15px; height: 15px; background: red none repeat scroll 0% 0%; left: 176px; top: 12px; cursor: pointer; -moz-user-select: none; border-radius: 10px;"></div>
			<div
				style="position: absolute; width: 15px; height: 15px; background: gray none repeat scroll 0% 0%; left: 194px; top: 12px; cursor: pointer; -moz-user-select: none; border-radius: 10px;"></div>
			<div
				style="position: absolute; width: 20px; height: 27px; background: url(images/flag1.png) 0 0 no-repeat; left: 270px; top: 5px;
	background-position: 0px -20px;"></div>
			<ul id="toolbar" style="padding-left: 0px;"> -->
			<label><input name="device" class="device_label" type="checkbox" value="ap" />AP </label> <label><input
				name="device" class="device_label" type="checkbox" fun="getDeviceByTypeAndFloor" value="3" />插座</label> <label><input
				name="device" class="device_label" type="checkbox" fun="getDeviceByTypeAndFloor" value="4" />报警设备 </label>
			</ul>
		</div>
	</div>
	<div style="float:right;/* background:red; */width:300px">
		<table width="300" border="1" id="hostsdiv">
			<tr id="trhead">
				<td width="50"><INPUT name="chkAll" id="chkAll" title="全选"
					type="checkbox" />全选</td>
				<td width="45">序号</td>
				<td align="center" width="50">AP</td>
				<td width="50">x</td>
				<td width="50">y</td>
			</tr>
		</table>
		x:<input id="mousex" style="width:50px" />y:<input id="mousey"
			style="width:50px" />
	</div>
</body>
</html>
