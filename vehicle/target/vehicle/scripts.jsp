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
<title>My JSP 'scripts.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script language="JavaScript" src="js/jquery.js" type="text/JavaScript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#create").click(function() {
			/* document.forms[0].action = 'script_create.do';
			document.forms[0].submit(); */
			var name = $("#name").val();
			var description = $("#description").val();
			var scriipt = $("#scriipt").text();
			$.post("script_create.do", {
				"scripts.name" : name,
				"scripts.description" : description,
				"scripts.scriipt" : scriipt
			}, function() {
				//刷新另一个框架的页面用  parent.另一FrameID.location.reload();
				parent.Left.location.reload();
				//子窗口刷新父窗口
				/* parent.location.reload(); */
				location.href="script_getByName.do?scripts.name="+name;
			});
		});
		$("#save").click(function() {
			document.forms[0].action = 'script_save.do';
			document.forms[0].submit();
		});
		$("#delete").click(function() {
			document.forms[0].action = 'script_delete.do';
			document.forms[0].submit();
		});
	});
</script>
</head>

<body>
	<td width="100%">
		<dt>
			<span></span>
		</dt> <c:if test="${empty jd.data }">
			<h4>New script</h4>
		</c:if> <c:if test="${!empty jd.data }">
			<c:forEach var="u" items="${jd.data }">
				<h4>${u.name }</h4>
			</c:forEach>

		</c:if>
		<p>Here you can edit configuration scripts. Syntax is javascript.
			On Inform request the script named 'Default' is run. More ...</p>
		<form method="post" action="">
			<table>
				<tbody>
					<c:if test="${empty jd.data }">
						<tr>
							<td>Name:</td>
						</tr>
						<tr>
							<td><input name="scripts.name" type="text" value=""
								id="name"></td>
						</tr>
						<tr>
							<td>Description:</td>
						</tr>
						<tr>
							<td><input name="scripts.description" type="text" value=""
								id="description"></td>
						</tr>
						<tr>
							<td>Script:</td>
						</tr>
						<tr>
							<td><textarea name="scripts.scriipt" cols="100" rows="20"
									id="scriipt"></textarea></td>
						</tr>
					</c:if>
					<c:if test="${!empty jd.data }">
						<c:forEach var="u" items="${jd.data }">
							<tr>
								<td><input type="hidden" name="scripts.name"
									value="${u.name }"></td>
							</tr>
							<tr>
								<td>Description:</td>
							</tr>
							<tr>
								<td><input name="scripts.description" type="text"
									value="${u.description }"></td>
							</tr>
							<tr>
								<td>Script:</td>
							</tr>
							<tr>
								<td><textarea name="scripts.scriipt" cols="100" rows="20">${u.scriipt }</textarea></td>
							</tr>
						</c:forEach>
					</c:if>

				</tbody>
			</table>
			<c:if test="${empty jd.data }">
				<div style="display: block">
					<input value="Create" type="button" id="create">
				</div>
			</c:if>
			<c:if test="${!empty jd.data }">
				<div style="display: block">
					<input value="Save" type="button" id="save"> <input
						value="Delete" type="button" id="delete">
				</div>
			</c:if>

		</form>
	</td>
</body>
</html>
