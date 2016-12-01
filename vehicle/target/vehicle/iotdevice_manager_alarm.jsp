<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

</head>

<body>
	<div>
		<table border="1" id="table">
			<thead>
				<tr>
					<!-- <th width="50"><INPUT name="chkAll" id="chkAll" title="全选"
						type="checkbox" />全选</th>
					<th width="80">操作</th> -->
					<th width="80">deviceMAC</th>
					<th width="100">设备名称</th>
					<th width="80">绑定设备</th>
					<th width="80">使用人</th>
					<th width="80">所在大楼</th>
					<th width="80">所在楼层</th>
				</tr>
			</thead>
			<tbody>
				<%-- <c:forEach items="${jd.data }" var="u" varStatus="aa">
					<tr ss="1">
						<td><INPUT class='chkSon' type='checkbox'
							value=${u.deviceId } /></td>
						<td><label class="opbutton device_update">修改</label><label class="opbutton device_delete">删除</label></td>
						<td class="cpeid">${u.deviceMAC }</td>
						<td>${u.deviceType.deviceName }</td>
						<td>${u.hosts.serialno }</td>
						<td>1</td>
						<td>${u.building.buildingName }</td>
						<td>${u.floor.floorName }</td>
					</tr>
				</c:forEach> --%>
			</tbody>
		</table>
	</div>
</body>
</html>
