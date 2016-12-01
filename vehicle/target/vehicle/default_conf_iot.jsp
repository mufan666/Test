<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

</head>

<body>
	<div>
		<div
			style="width:498px;float:left;/* overflow-y:auto;overflow-x:hidden;height:150px; */">
			<table width="470" cellspacing="0" cellpadding="0" border="1">
				<thead>
					<tr>
						<th><div>ID</div></th>
						<th style="width: 90px;"><div>操作</div></th>
						<th style="width: 131px;"><div>MAC地址</div></th>
						<th style="width: 141px;"><div>IP</div></th>
						<th style="width: 75px;"><div>PORT</div></th>
					</tr>
				</thead>
				<tbody id="ap_info">
				</tbody>
			</table>
		</div>
		<div id="find_ap"
			style="width:478px;float:right;/* overflow-y:auto;overflow-x:hidden;height:150px; */">
			<!-- <div style="border:1px solid gray;width:200px;">
				<table width="200" cellspacing="0" cellpadding="0" border="0">
					<thead>
						<tr>
							<th><div>发现新AP</div></th>
						</tr>
					</thead>
					<tbody id="new_device">
						<tr>
							<td>aaaaaaaa</td>
						</tr>
						<tr>
							<td><div
									style="width:50px;float:right;height:20px;cursor:pointer;">加入</div></td>
						</tr>
					</tbody>
				</table>
			</div> -->
		</div>
	</div>
	<div
		style="width:1080px;overflow-y:auto;overflow-x:hidden;/* height:302px; */max-height:202px; margin-top: 20px;">
		<div
			style="width:200px;overflow-y:auto;overflow-x:hidden;/* border:1px solid red; height:300px;*/max-height:200px;float:left;">
			<div>设备</div>
			<table width="190px" cellspacing="0" cellpadding="0" border="1">
				<thead>
					<tr style="height: 22px;">
						<th style="width: 131px;"><div>MAC</div></th>
						<th style="width: 50px;"><div>RSSI</div></th>
						<!-- <th><div>设备类型</div></th> -->
					</tr>
				</thead>
				<tbody id="device_info">
				</tbody>
			</table>
		</div>
		<div
			style="width:320px;overflow-y:auto;overflow-x:hidden;/* border:1px solid green; height:300px;*/max-height:200px;float:left;margin-left: 12px;">
			<div
				style="width:320px;overflow-y:auto;overflow-x:hidden;/* border:1px solid red; height:300px;*/max-height:200px;float:left;">
				<div>绑定手环</div>
				<table width="320px" cellspacing="0" cellpadding="0" border="1">
					<thead>
						<tr>
							<th style="width: 131px;"><div>MAC</div></th>
							<th style="width: 50px;"><div>在线</div></th>
							<th style="width: 131px;"><div>操作</div></th>
						</tr>
					</thead>
					<tbody id="band_xiaomi_info">
					<tr>
						<td style="width: 131px;" class="mac"></th>
						<td style="width: 50px;" class="onLine"></th>
						<td style="width: 131px;"class="oper"></th>
					</tr>
					</tbody>
				</table>
			</div>
			<div>手环</div>
			<table width="320px" cellspacing="0" cellpadding="0" border="1">
				<thead>
					<tr>
						<th style="width: 131px;"><div>MAC</div></th>
						<th style="width: 50px;"><div>RSSI</div></th>
						<th style="width: 131px;"><div>操作</div></th>
					</tr>
				</thead>
				<tbody id="xiaomi_info">
				</tbody>
			</table>
		</div>
		<div id="mi_info_oper"
			style="width:280px;overflow-y:auto;overflow-x:hidden;/* border:1px solid green; height:300px;*/min-height:147px;max-height:200px;float:left;margin-left: 12px;">
			<!-- <div>手环</div>
			<div>
				<table width="320px" cellspacing="0" cellpadding="0" border="1">
					<tr>
						<td>年龄：</td>
						<td><input type="text" id="mi_age"></td>
					</tr>
					<tr>
						<td>身高：</td>
						<td><input type="text" id="mi_height">厘米</td>
					</tr>
					<tr>
						<td>体重：</td>
						<td><input type="text" id="mi_weight">公斤</td>
					</tr>
					<tr>
						<td>性别：</td>
						<td>男:<input type="radio" name="sex" value="1"  checked="checked">女:<input type="radio" name="sex" value="0"></td>
					</tr>
				</table>
			</div>
			<button>提交</button><button>关闭</button> -->
		</div>
		<div id="mi_info_message"
			style="width:250px;overflow-y:auto;overflow-x:hidden;/* border:1px solid green; height:300px;*/min-height:147px;max-height:200px;float:left">
		</div>
	</div>
	<div id="iot_device_message_last"
		style="width:1000px;overflow-y:auto;overflow-x:hidden;height:207px; margin-top: 20px;">
		<div id="iot_device_message_last1"
			style="width:490px;overflow-y:auto;overflow-x:hidden; border:1px solid red; height:205px;float:left;">
			<p id="iot_device_message_last1_flag" style="margin-bottom: 0px;"></p>
		</div>
	</div>
</body>
</html>
