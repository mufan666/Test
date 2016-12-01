<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<!-- <script type="text/javascript">
$(document).ready(function() {
	alert($("#table").find("thead :checkbox").attr("id"));
	$("#table").find("tbody>tr").each(function(){
		alert($(this).find("td:first :checkbox").attr("name"));
	});
	
});
</script> -->
</head>
<body>
	<div id="title">
		<div>
			<ul id="toolbar">
				<!-- <li><div id="iotdevi_add">添加</div></li>
			<li><div id="iotdevi_upd">修改</div></li>
			<li><div id="iotdevi_del">删除</div></li>
			<li><div id="iotdevi_bat">批量修改</div></li> -->
				<li id="addiotdevice">添加</li>
				<li>修改</li>
				<li id="batchdelete">删除</li>
				<li>批量修改</li>
			</ul>
		</div>
		<div>
			<table border="1" id="table">
				<thead>
					<tr>
						<th width="50"><INPUT name="chkAll" id="chkAll" title="全选"
							type="checkbox" />全选</th>
						<th width="80">操作</th>
						<th width="80">deviceMAC</th>
						<th width="100">设备名称</th>
						<th width="80">绑定设备</th>
						<th width="80">使用人</th>
						<th width="80">所在大楼</th>
						<th width="80">所在楼层</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${jd.data }" var="u" varStatus="aa">
						<tr ss="1">
							<td><INPUT class='chkSon' type='checkbox'
								value=${u.deviceId } /></td>
							<td><label class="opbutton device_update">修改</label><label
								class="opbutton device_delete">删除</label></td>
							<td class="cpeid">${u.deviceMAC }</td>
							<td>${u.deviceType.deviceName }</td>
							<td>${u.hosts.serialno }</td>
							<td>1</td>
							<td>${u.building.buildingName }</td>
							<td>${u.floor.floorName }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
