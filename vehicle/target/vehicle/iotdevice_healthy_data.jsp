<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'iotdevice_healthy_data.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<style type="text/css">
#healthydata_tabs .ui-widget-header {
	height: 34px;
}

#healthydata_tabs ul li.ui-tabs-active a {
	outline: 0 none;
}

#healthydata_tabs ul li {
	/* width: 150px; */
	font-family: STKaiti;
	font-size: 15px;
	font-weight: bold;
	height: 29px;
}

#WeighingScaleData,#SphygmomanometerData,#ChildrenTemperatureData,#HeightInstrumentData
	{
	height: 460px;
}
</style>
</head>

<body>
	<!-- <div
		style="width:1080px;overflow-y:auto;overflow-x:hidden;/* height:302px; */max-height:202px; margin-top: 20px;">
		<div id="WeighingScaleData"
			style="width:200px;overflow-y:auto;overflow-x:hidden;/* border:1px solid red; height:300px;*/max-height:200px;float:left;">
			<div>体脂秤</div>
			<table width="190px" cellspacing="0" cellpadding="0" border="1">
				<thead>
					<tr style="height: 22px;">
						<th style="width: 131px;"><div>时间</div></th>
						<th style="width: 131px;"><div>MAC</div></th>
						<th style="width: 50px;"><div>体重</div></th>
						<th><div>设备类型</div></th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
		<div id="SphygmomanometerData"
			style="width:320px;overflow-y:auto;overflow-x:hidden;/* border:1px solid green; height:300px;*/max-height:200px;float:left;margin-left: 12px;">
			<div>血压计</div>
			<table width="320px" cellspacing="0" cellpadding="0" border="1">
				<thead>
					<tr>
						<th style="width: 131px;"><div>时间</div></th>
						<th style="width: 131px;"><div>MAC</div></th>
						<th style="width: 100px;"><div>血压</div></th>
						<th style="width: 50px;"><div>脉搏</div></th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
		<div id="ChildrenTemperatureData"
			style="width:300px;overflow-y:auto;overflow-x:hidden;/* border:1px solid green; height:300px;*/min-height:147px;max-height:200px;float:left;margin-left: 12px;">
			<div>儿童体温带</div>
			<p id="ChildrenTemperatureData_flag" style="margin-bottom: 0px;"></p>
		</div>
		<div id="HeightInstrumentData"
			style="width:150px;overflow-y:auto;overflow-x:hidden;/* border:1px solid green; height:300px;*/min-height:147px;max-height:200px;float:left;margin-left: 12px;">
			<div>身高仪</div>
			<table width="150px" cellspacing="0" cellpadding="0" border="1">
				<thead>
					<tr>
						<th style="width: 131px;"><div>时间</div></th>
						<th style="width: 131px;"><div>MAC</div></th>
						<th style="width: 50px;"><div>身高</div></th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div> -->

	<div id="healthydata_tabs">
		<ul>
			<li><a href="#WeighingScaleData">体脂秤</a></li>
			<li><a href="#SphygmomanometerData">血压计</a></li>
			<li><a href="#ChildrenTemperatureData">儿童体温带</a></li>
			<li><a href="#HeightInstrumentData">身高仪</a></li>
		</ul>
		<div id="WeighingScaleData">
			<table width="400px" cellspacing="0" cellpadding="0" border="1">
				<thead>
					<tr>
						<th style="width: 231px;"><div>时间</div></th>
						<th style="width: 141px;"><div>MAC</div></th>
						<th style="width: 70px;"><div>体重</div></th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			<!-- <span style="margin-left: 100px;">时间</span><span>设备mac</span><span>体重</span>
			<p id="WeighingScaleData_flag" style="margin-bottom: 0px;"></p> -->
		</div>
		<div id="SphygmomanometerData">
			<table width="560px" cellspacing="0" cellpadding="0" border="1">
				<thead>
					<tr>
						<th style="width: 211px;"><div>时间</div></th>
						<th style="width: 141px;"><div>MAC</div></th>
						<th style="width: 120px;"><div>血压(高压/低压)</div></th>
						<th style="width: 60px;"><div>脉搏</div></th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			<!-- <p id="SphygmomanometerData_flag" style="margin-bottom: 0px;"></p> -->
		</div>
		<div id="ChildrenTemperatureData">
			<table width="400px" cellspacing="0" cellpadding="0" border="1">
				<thead>
					<tr>
						<th style="width: 231px;"><div>时间</div></th>
						<th style="width: 141px;"><div>MAC</div></th>
						<th style="width: 60px;"><div>体温</div></th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			<!-- <p id="ChildrenTemperatureData_flag" style="margin-bottom: 0px;"></p> -->
		</div>
		<div id="HeightInstrumentData">
			<table width="400px" cellspacing="0" cellpadding="0" border="1">
				<thead>
					<tr>
						<th style="width: 231px;"><div>时间</div></th>
						<th style="width: 141px;"><div>MAC</div></th>
						<th style="width: 60px;"><div>身高</div></th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			<!-- <p id="HeightInstrumentData_flag" style="margin-bottom: 0px;"></p> -->
		</div>
	</div>

</body>
</html>
