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
<title>My JSP 'cpe_foundmany.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>

	<h3>${jd.total } CPE found for serial:</h3>
	<form method="post">
		<table width="700" cellspacing="0" cellpadding="0" border="0">
			<colgroup span="6"></colgroup>
			<thead>
				<tr>
					<th><div>OUI</div></th>
					<th><div>Manufacturer</div></th>
					<th><div>Model</div></th>
					<th><div>Currentsoftware</div></th>
					<th><div>Hardware</div></th>
					<th><div>Serial number</div></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${jd.data }" var="u" varStatus="aa">
					<tr onmouseout="this.style.backgroundColor='#FFFFFF'"
						onmouseover="this.style.backgroundColor='#F1F1F1'"
						style="background-color: rgb(255, 255, 255);">
						<td>${u.model.oui }</td>
						<td>${u.model.manufacturer }</td>
						<td>${u.model.displayname }</td>
						<td>${u.currentsoftware }</td>
						<td>${u.hardware }</td>
						<td><a  href="hosts_findItems.do?snIn=${u.serialno }">${u.serialno }</a></td>
					</tr>
				</c:forEach>


			</tbody>

		</table>
	</form>
</body>
</html>
