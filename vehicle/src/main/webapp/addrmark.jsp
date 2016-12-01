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
<base href="<%=basePath%>">

<title>My JSP 'addrmark.jsp' starting page</title>

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
	<%-- <h3>${errormsg }</h3> --%>
	<form method="post" action="hosts_addRmark.do">
		<c:forEach items="${jd.data }" var="u" varStatus="aa">
				<%-- <td style="display:none" class="hwid">${u.hwid }</td>
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
				<td class="remark">${u.remark }</td> --%>
				<input type="hidden" value="${u.hwid }" name="hbean.hwid">
				<input type="hidden" value="1" name="flag">
				mac:<input type="text" value="${u.serialno }" name="hbean.serialno" readonly="readonly"><br>
				备注：<textarea rows="2" cols="20" name="hbean.remark" >${u.remark }</textarea><br>
				<input type="submit" value="确定">
				
		</c:forEach>
	</form>
</body>
</html>
