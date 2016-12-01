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

<title>My JSP 'deviceprofile_new.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script language="JavaScript" src="js/jquery.js" type="text/JavaScript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$.post("df_getScriptNames.do", {

		}, function(msg) {
			 msg = eval('(' + msg + ')'); //转为json对象
            var data = msg.data;
            var res = $("#res").val();
            $('#scriptname').append('<option value=""></option>');
            for (var i = 0, j = data.length; i < j; i++)
            	if(typeof (res) != "undefined"){
	            	if(data[i] == res)
	            		$('#scriptname').append('<option selected="selected" value="'+data[i]+'">'+data[i]+'</option>');
	            	else
	                	$('#scriptname').append('<option value="'+data[i]+'">'+data[i]+'</option>');
                }else{
                	if(data[i] == 'Default')
	            		$('#scriptname').append('<option selected="selected" value="'+data[i]+'">'+data[i]+'</option>');
	            	else
	                	$('#scriptname').append('<option value="'+data[i]+'">'+data[i]+'</option>');
                }
        
		});
		$("#create").click(function() {
			/* document.forms[0].action = 'script_create.do';
			document.forms[0].submit(); */
			var name = $("#name").val();
			var informinterval = $("#informinterval").val();
			var dayskeepstats = $("#dayskeepstats").val();
			var saveParamValuesInterval = $("#saveParamValuesInterval").val();
			var saveParamValuesOnBoot = $("#saveParamValuesOnBoot").val();
			var saveParamValuesOnChange = $("#saveParamValuesOnChange").val();
			var scriptname = $("#scriptname").val();
			$.post("deviceprofile_create.do", {
				"dp.name" : name,
				"dp.informinterval" : informinterval,
				"dp.dayskeepstats" : dayskeepstats,
				"dp.saveParamValuesInterval" : saveParamValuesInterval,
				"dp.saveParamValuesOnBoot" : saveParamValuesOnBoot,
				"dp.saveParamValuesOnChange" : saveParamValuesOnChange,
				"dp.scriptname" : scriptname,
			}, function() {
				parent.Left.location.reload();
				location.href="deviceprofile_load.do?dp.name="+name;
			});
		});
		$("#save").click(function() {
			document.forms[0].action = 'deviceprofile_save.do';
			document.forms[0].submit();
		});
		$("#delete").click(function() {
			document.forms[0].action = 'deviceprofile_delete.do';
			document.forms[0].submit();
		});
	});
</script>
</head>

<body>
	<c:if test="${empty jd.data }">
		<td width="100%"><dl style="display: none; null"
				class="rich-messages" id="j_id51">
				<dt>
					<span></span>
				</dt>
			</dl>
			<h4>New device profile</h4>
			<form action="" method="post">
				<table>
					<tbody>
						<tr>
							<td>Name:</td>
							<td><input type="text" name="dp.name" id="name"></td>
						</tr>
					</tbody>
				</table>
				<fieldset>
					<table>
						<tbody>
							<tr>
								<td>periodicInformInterval:</td>
								<td><input type="text" name="dp.informinterval"
									id="informinterval"></td>
							</tr>
							<tr>
								<td>Days to keep statistics (days). 0 - don't keep:</td>
								<td><input type="text" name="dp.dayskeepstats"
									id="dayskeepstats"></td>
							</tr>
							<tr>
								<td>Save parameter values once in this interval (in hours).
									0 - don't save:</td>
								<td><input type="text" name="dp.saveParamValuesInterval"
									id="saveParamValuesInterval"></td>
							</tr>
						</tbody>
					</table>

				</fieldset>
				<fieldset>
					<table class="dr-pnl-b rich-panel-body">
						<tbody>
							<tr>
								<td>Save parameter values on events BOOT<input
									type="checkbox" name="dp.saveParamValuesOnBoot"
									id="saveParamValuesOnBoot"></td>
								<td>VALUECHANGE<input type="checkbox"
									name="dp.saveParamValuesOnChange" id="saveParamValuesOnChange"></td>
							</tr>
						</tbody>
					</table>

				</fieldset>
				<fieldset>
					<table class="dr-pnl-b rich-panel-body">
						<tbody>
							<tr>
								<td>Configuration script to be run:</td>
								<td><select size="1" name="dp.scriptname" id="scriptname">
								</select></td>
							</tr>
						</tbody>
					</table>

				</fieldset>
				<input type="button" value="Create" id="create">
			</form></td>
	</c:if>

	<c:if test="${!empty jd.data }">
		<c:forEach var="u" items="${jd.data }">
			<td width="100%"><dl style="display: none; null">
					<dt>
						<span></span>
					</dt>
				</dl>
				<h4>${u.name }</h4> <input type="hidden" name="dp.name" id="name">

				<form action="" method="post">

					<fieldset>
						<table>
							<tbody>
								<tr>
									<td>periodicInformInterval:</td>
									<td><input type="text" name="dp.informinterval"
										value="${u.informinterval }" id="informinterval"></td>
								</tr>
								<tr>
									<td>Days to keep statistics (days). 0 - don't keep:</td>
									<td><input type="text" name="dp.dayskeepstats"
										value="${u.dayskeepstats}" id="dayskeepstats"></td>
								</tr>
								<tr>
									<td>Save parameter values once in this interval (in
										hours). 0 - don't save:</td>
									<td><input type="text" name="dp.saveParamValuesInterval"
										id="saveParamValuesInterval" value="${u.saveParamValuesInterval}"></td>
								</tr>
							</tbody>
						</table>

					</fieldset>
					<fieldset>
					
						<table class="dr-pnl-b rich-panel-body">
							<tbody>
								<tr>
									<td>Save parameter values on events BOOT 
									<c:choose>
    									<c:when test="${u.saveParamValuesOnBoot}">
									<input type="checkbox" name="dp.saveParamValuesOnBoot" checked="checked" id="saveParamValuesOnBoot">
									</c:when>
									<c:otherwise>
									<input type="checkbox" name="dp.saveParamValuesOnBoot"  id="saveParamValuesOnBoot">
									</c:otherwise>
									</c:choose>
									</td>
									<td>VALUECHANGE 
									<c:choose>
    									<c:when test="${u.saveParamValuesOnChange}">
									<input type="checkbox" name="dp.saveParamValuesOnChange" checked="checked" id="saveParamValuesOnChange">
									</c:when>
									<c:otherwise>
									<input type="checkbox" name="dp.saveParamValuesOnChange"  id="saveParamValuesOnChange">
									</c:otherwise>
									</c:choose>
									</td>
								</tr>
							</tbody>
						</table>

					</fieldset>
					<fieldset>
					<input id="res" type="hidden" value="${u.scriptname }">
						<table class="dr-pnl-b rich-panel-body">
							<tbody>
							
								<tr>
									<td>Configuration script to be run:</td>
									
									<td><select size="1" name="dp.scriptname" id="scriptname">
									</select></td>
								</tr>
							</tbody>
						</table>

					</fieldset>
					<div style="display: block">
						<input value="Save" type="button" id="save"> <input
							value="Delete" type="button" id="delete">
					</div>
				</form></td>
		</c:forEach>
	</c:if>

</body>
</html>
