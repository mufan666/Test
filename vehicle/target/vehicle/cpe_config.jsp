<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'cpe_config.jsp' starting page</title>

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
	<table >
		<tbody>
			<tr>
				<td><div>
						<div>Connection request</div>
						<div>
							Username:<input type="text" value="" name="hosts.conrequser"
								id="conrequser">Password:<input type="text" value=""
								name="hosts.conreqpass" id="conreqpass">
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div>
						<div>ACS credentials on CPE</div>
						<div>
							Authentication type:<select size="1" name="hosts.authtype"
								id="authtype">
								<option selected="selected" value="0">None</option>
								<option value="1">Basic</option>
								<option value="2">MD5</option>
							</select> Username:<input type="text" value="" name="hosts.username"
								id="username"> Password:<input type="text" value=""
								name="hosts.password" id="password">
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div id="cpedetails:j_id105" class="dr-pnl rich-panel ">
						<div id="cpedetails:j_id105_header"
							class="dr-pnl-h rich-panel-header ">Profile and Config
							settings</div>
						<div>
							Profile:<select size="1" name="hosts.profileName"
								id="profileName">
								<option selected="selected" value="Default">Default</option>
								<option value="Reset">Reset</option>
								<option value="reboot">reboot</option>
							</select>Config name:<select size="1" name="hosts.configname"
								id="configname">
								<option value="None">None</option>
							</select>
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div id="cpedetails:j_id116" class="dr-pnl rich-panel ">
						<div id="cpedetails:j_id116_header"
							class="dr-pnl-h rich-panel-header ">Customer features</div>
						<div id="cpedetails:j_id116_body"
							class="dr-pnl-b rich-panel-body ">
							Customer ID:<input type="text" value="" name="hosts.customerid"
								id="customerid">
						</div>
					</div></td>
			</tr>
			<tr>
				<td><div id="cpedetails:j_id121" class="dr-pnl rich-panel ">
						<div id="cpedetails:j_id121_header"
							class="dr-pnl-h rich-panel-header ">Properties</div>
						<div id="cpedetails:j_id121_body"
							class="dr-pnl-b rich-panel-body ">
							<table>
								<tbody>
									<tr>
										<td>Hint: properties can be used in scripts and in
											configurations as # {property_name}</td>
									</tr>
									<tr>
										<td><textarea rows="20" cols="100" name="hosts.props"
												id="props"></textarea></td>
									</tr>
								</tbody>
							</table>
							<input type="submit" value="Save" id="save">
						</div>
					</div></td>
			</tr>
		</tbody>
	</table>
</body>
</html>
