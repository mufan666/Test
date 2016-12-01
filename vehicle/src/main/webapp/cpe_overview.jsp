<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<title>My JSP 'cpe_overview.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script language="JavaScript" src="js/jquery.js" type="text/JavaScript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#connection").click(function() {

			var hwid = $("#hwid").val();
			var sn = $("#serialno").text();

			$.post("hosts_connectionRequest.do", {
				hwid : hwid,
				sn : sn
			}, function() {
				$("#connection").attr("disabled", "true");
			});
		});
		$("#reboot").click(function() {

			var hwid = $("#hwid").val();
			var sn = $("#serialno").text();

			$.post("hosts_requestReboot.do", {
				hwid : hwid,
				sn : sn
			}, function() {
				$("#reboot").attr("disabled", "true");
			});
		});
		$("#factoryReset").click(function() {

			var hwid = $("#hwid").val();
			var sn = $("#serialno").text();

			$.post("hosts_factoryReset.do", {
				hwid : hwid,
				sn : sn
			}, function() {
				$("#factoryReset").attr("disabled", "true");
			
			});
		});
		$("#factoryReset2").click(function() {

			var hwid = $("#hwid").val();
			var sn = $("#serialno").text();

			$.post("hosts_requestFactoryReset.do", {
				hwid : hwid,
				sn : sn
			}, function() {
				$("#factoryReset2").attr("disabled", "true");
			});
		});
	});
</script>

</head>

<body>
	<form action="" method="post">

		<table class="dr-pnl-b rich-panel-body" id="cpedetails:panel">
			<c:forEach items="${jd.data }" var="u" varStatus="aa">
				<input type="hidden" id="hwid" value="${u.hwid }">
				<tbody>
					<tr>
						<td><div id="cpedetails:j_id81" class="dr-pnl rich-panel ">
								<div id="cpedetails:j_id81_header"
									class="dr-pnl-h rich-panel-header ">Generic informations</div>
								<div id="cpedetails:j_id81_body"
									class="dr-pnl-b rich-panel-body ">
									<table cellspacing="0" cellpadding="0" border="0"
										style="text-align:center" id="cpedetails:j_id83"
										class="dr-table rich-table ">
										<colgroup span="6"></colgroup>
										<thead class="dr-table-thead">
											<tr class="dr-table-subheader rich-table-subheader ">
												<th id="cpedetails:j_id83:j_id84header" scope="col"
													class="dr-table-subheadercell rich-table-subheadercell  "><div
														id="cpedetails:j_id83:j_id84header:sortDiv">Serial
														number</div></th>
												<th id="cpedetails:j_id83:j_id87header" scope="col"
													class="dr-table-subheadercell rich-table-subheadercell  "><div
														id="cpedetails:j_id83:j_id87header:sortDiv">Vendor</div></th>
												<th id="cpedetails:j_id83:j_id90header" scope="col"
													class="dr-table-subheadercell rich-table-subheadercell  "><div
														id="cpedetails:j_id83:j_id90header:sortDiv">OUI</div></th>
												<th id="cpedetails:j_id83:j_id93header" scope="col"
													class="dr-table-subheadercell rich-table-subheadercell  "><div
														id="cpedetails:j_id83:j_id93header:sortDiv">Model</div></th>
												<th id="cpedetails:j_id83:j_id96header" scope="col"
													class="dr-table-subheadercell rich-table-subheadercell  "><div
														id="cpedetails:j_id83:j_id96header:sortDiv">Hardware</div></th>
												<th id="cpedetails:j_id83:j_id99header" scope="col"
													class="dr-table-subheadercell rich-table-subheadercell  "><div
														id="cpedetails:j_id83:j_id99header:sortDiv">Customer
														ID</div></th>
											</tr>
										</thead>
										<tbody id="cpedetails:j_id83:tb">
											<tr class="dr-table-firstrow rich-table-firstrow ">
												<td id="serialno" class="dr-table-cell rich-table-cell ">${u.serialno }</td>
												<td id="cpedetails:j_id83:0:j_id87"
													class="dr-table-cell rich-table-cell ">${u.model.manufacturer }</td>
												<td id="cpedetails:j_id83:0:j_id90"
													class="dr-table-cell rich-table-cell ">${u.model.oui}
												</td>
												<td id="cpedetails:j_id83:0:j_id93"
													class="dr-table-cell rich-table-cell ">${u.model.displayname }</td>
												<td id="cpedetails:j_id83:0:j_id96"
													class="dr-table-cell rich-table-cell ">${u.model.version }</td>
												<td id="cpedetails:j_id83:0:j_id99"
													class="dr-table-cell rich-table-cell ">${u.customerid }</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div></td>
					</tr>
					<tr>
						<td><div id="cpedetails:cr_panel" class="dr-pnl rich-panel "
								xmlns="http://www.w3.org/1999/xhtml">
								<div id="cpedetails:cr_panel_header"
									class="dr-pnl-h rich-panel-header ">
									<label> CPE operations</label>
								</div>
								<div id="cpedetails:cr_panel_body"
									class="dr-pnl-b rich-panel-body ">
									<label class="rich-label"> Connection request URL: </label>${u.url }
									<div id="cpedetails:pb" style="null">
										<input type="button" value="Connection Request"
											name="cpedetails:j_id105" id="connection" class="rich-button"><span
											style="display: none;"></span>
									</div>
									<label> Last Conreq: </label><span id="cpedetails:crStatus">${u.lastcrres }</span>
									<br> <label> Last Inform: </label>${u.lastcontact }<br>
									<!-- <a target="new" title="CPE page" href="http://192.168.199.138">Go
									to CPE web UI</a> -->
									<br> <input type="button" class="rich-button"
										value="Reboot" name="cpedetails:j_id115" id="reboot">(this
									will mark CPE for reboot and try to request connection.)
									<br> <input type="button" class="rich-button"
										value="factoryReset" name="cpedetails:j_id115" id="factoryReset">
										<br> <input type="button" class="rich-button"
										value="factoryReset2" name="cpedetails:j_id115" id="factoryReset2">
								</div>
							</div></td>
					</tr>
					<tr>
						<td><div id="cpedetails:j_id117" class="dr-pnl rich-panel ">
								<div id="cpedetails:j_id117_header"
									class="dr-pnl-h rich-panel-header ">
									<label> Configuration</label>
								</div>
								<div id="cpedetails:j_id117_body"
									class="dr-pnl-b rich-panel-body ">
									<table cellspacing="0" cellpadding="0" border="0"
										style="text-align:right" id="cpedetails:j_id119"
										class="dr-table rich-table ">
										<colgroup span="6"></colgroup>
										<thead class="dr-table-thead">
											<tr class="dr-table-subheader rich-table-subheader ">
												<th id="cpedetails:j_id119:j_id120header" scope="col"
													class="dr-table-subheadercell rich-table-subheadercell  "><div
														id="cpedetails:j_id119:j_id120header:sortDiv">Config
														name</div></th>
												<th id="cpedetails:j_id119:j_id123header" scope="col"
													class="dr-table-subheadercell rich-table-subheadercell  "><div
														id="cpedetails:j_id119:j_id123header:sortDiv">Config
														version</div></th>
												<th id="cpedetails:j_id119:j_id126header" scope="col"
													class="dr-table-subheadercell rich-table-subheadercell  "><div
														id="cpedetails:j_id119:j_id126header:sortDiv">Config
														update at</div></th>
												<th id="cpedetails:j_id119:j_id129header" scope="col"
													class="dr-table-subheadercell rich-table-subheadercell  "><div
														id="cpedetails:j_id119:j_id129header:sortDiv">Upgrade
														Result</div></th>
											</tr>
										</thead>
										<tbody id="cpedetails:j_id119:tb">
											<tr class="dr-table-firstrow rich-table-firstrow ">
												<td id="cpedetails:j_id119:0:j_id120"
													class="dr-table-cell rich-table-cell ">${u.configname }</td>
												<td id="cpedetails:j_id119:0:j_id123"
													class="dr-table-cell rich-table-cell ">${u.cfgversion }</td>
												<td id="cpedetails:j_id119:0:j_id126"
													class="dr-table-cell rich-table-cell ">${u.cfgupdtime }</td>
												<td id="cpedetails:j_id119:0:j_id129"
													class="dr-table-cell rich-table-cell ">${u.cfgupdres}</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div></td>
					</tr>
					<tr>
						<td><div id="cpedetails:j_id132" class="dr-pnl rich-panel ">
								<div id="cpedetails:j_id132_header"
									class="dr-pnl-h rich-panel-header ">
									<label> Software</label>
								</div>
								<div id="cpedetails:j_id132_body"
									class="dr-pnl-b rich-panel-body ">
									<table cellspacing="0" cellpadding="0" border="0"
										style="text-align:right" id="cpedetails:j_id134"
										class="dr-table rich-table ">
										<colgroup span="6"></colgroup>
										<thead class="dr-table-thead">
											<tr class="dr-table-subheader rich-table-subheader ">
												<th id="cpedetails:j_id134:j_id135header" scope="col"
													class="dr-table-subheadercell rich-table-subheadercell  "><div
														id="cpedetails:j_id134:j_id135header:sortDiv">SW
														version</div></th>
												<th id="cpedetails:j_id134:j_id138header" scope="col"
													class="dr-table-subheadercell rich-table-subheadercell  "><div
														id="cpedetails:j_id134:j_id138header:sortDiv">SW
														update at</div></th>
												<th id="cpedetails:j_id134:j_id141header" scope="col"
													class="dr-table-subheadercell rich-table-subheadercell  "><div
														id="cpedetails:j_id134:j_id141header:sortDiv">Upgrade
														Result</div></th>
											</tr>
										</thead>
										<tbody id="cpedetails:j_id134:tb">
											<tr class="dr-table-firstrow rich-table-firstrow ">
												<td id="cpedetails:j_id134:0:j_id135"
													class="dr-table-cell rich-table-cell ">${u.currentsoftware }</td>
												<td id="cpedetails:j_id134:0:j_id138"
													class="dr-table-cell rich-table-cell ">${u.sfwupdtime }</td>
												<td id="cpedetails:j_id134:0:j_id141"
													class="dr-table-cell rich-table-cell ">${u.sfwupdres }</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div></td>
					</tr>
				</tbody>
			</c:forEach>
		</table>


	</form>
	<form enctype="application/x-www-form-urlencoded">
		<input type="submit" class="rich-button" value="Remove" name="remove"
			id="remove">
	</form>
</body>
</html>
