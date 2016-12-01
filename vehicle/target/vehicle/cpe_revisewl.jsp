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

<title>My JSP 'cpe_revisewl.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/metisMenu.js"></script>
<script type="text/javascript" src="js/dialog.js"></script>
<script type="text/javascript" src="js/dialog-plus.js"></script>
<!-- <script type="text/javascript" src="js/artDialog.iframeTools.js"></script> -->
<script type="text/javascript" src="js/jquery.event.ue.js"></script>
<script type="text/javascript" src="js/jquery.udraggable.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		showarea();
		zhylcon();
		wancon();
		$("#authenticationmode").change(function() {
			showarea();
		});
		$(".zhylstatus").change(function() {
			zhylcon();
		});
		$("#addressingType").change(function() {
			wancon();
		});
		/* $("#zhylserveraddr").blur(function() {
			var m = validateIP($(this).val());
			m = m || 'null';
			$("#result").text(m);
		}); */
		$("#wpapass").blur(function() {
			var m = validatePass($(this).val());
			m = m || 'null';
			if (m == 'null')
				$("#wpapassError").html("密码有误");
			else
				$("#wpapassError").html("");
		});
		$("#zhylport").blur(function() {
			var port = $("#zhylport").val();
			if (port > 65535 || port < 0)
				alert("端口号过超过范围");
			/* if (port < 0)
				alert("商品号超过范围"); */

		});
		/* $("#wpapass").blur(function() {
			var m = validatePass($(this).val());
			m = m || 'null';
			if (m == 'null')
				$("#wpapassError").html("密码有误");
			else
				$("#wpapassError").html("");
		}); */

		$("#confirm").click(function() {
			var flag = false;
			var pass = $("#wpapass").val();
			var passmsg = validatePass(pass);
			passmsg = passmsg || 'null';
			if (passmsg == 'null') {
				$("#wpapassError").html("密码有误");
				flag = true;
			} else
				$("#wpapassError").html("");
			var status = $("input[name='hbean.zhylstatus']:checked").val();
			if (status == "enable") {
				var ip = $("#zhylserveraddr").val();
				var ipmsg = validateIP(ip);
				ipmsg = ipmsg || 'null';
				if (ipmsg == 'null') {
					$("#serveraddrError").html("ip有误");
					flag = true;
				}
			}
			if (flag) {
				$("#confirmmsg").html("填写信息有误！");
				alert("填写信息有误！");
				return;
			}
			document.forms[0].action = 'hosts_reviseWl.do';
			document.forms[0].submit();

		});
		$("#confirmwan").click(function() {
			/* $.post("hosts_configWan.do", { */
			$.post("hosts_preconfigWan.do", {
				"hbean.id" : $("#hostsid").val(),
				"hbean.hwid" : $("#hostshwid").val(),
				"hbean.serialno" : $("#hostsserialno").val(),
				"hbean.addressingType" : $("#addressingType").val(),
				"hbean.ip" : $("#externalIPAddress").val(),
				"hbean.subnetMask" : $("#subnetMask").val(),
				"hbean.defaultGateway" : $("#defaultGateway").val(),
				"hbean.DNSServers" : $("#DNSServers").val()
			}, function() {

			});
		});

		$("#confirmzhyl").click(function() {
			/* $.post("hosts_configzhyl.do", { */
			$.post("hosts_preconfigzhyl.do", {
				"hbean.id" : $("#hostsid").val(),
				"hbean.hwid" : $("#hostshwid").val(),
				"hbean.serialno" : $("#hostsserialno").val(),
				"hbean.zhylstatus" : $("input[name='hbean.zhylstatus']:checked").val(),
				"hbean.zhylserveraddr" : $("#zhylserveraddr").val(),
				"hbean.zhylprotocol" : $("#zhylprotocol").val(),
				"hbean.zhylport" : $("#zhylport").val(),
			}, function() {

			});
		});

		$("#confirmwn").click(function() {
			/* $.post("hosts_configwireless.do", { */
			$.post("hosts_preconfigwireless.do", {
				"hbean.id" : $("#hostsid").val(),
				"hbean.hwid" : $("#hostshwid").val(),
				"hbean.serialno" : $("#hostsserialno").val(),
				"hbean.ssid" : $("#ssid").val(),
				"hbean.channel" : $("#channel").val(),
				"hbean.bandwidth" : $("#bandwidth").val(),
				"hbean.basicEncryptionmodes" : $("#authenticationmode").val(),
				"hbean.wepkey" : $("#wepkey").val(),
				"hbean.WPAEncryptionmodes" : $("input[name='hbean.WPAEncryptionmodes']:checked").val(),
				"hbean.wpapass" : $("#wpapass").val(),
			}, function() {

			});
		});

		$("#confirmLan").click(function() {
			/* $.post("hosts_configLan.do", { */
			$.post("hosts_preconfigLan.do", {
				"hbean.id" : $("#hostsid").val(),
				"hbean.hwid" : $("#hostshwid").val(),
				"hbean.serialno" : $("#hostsserialno").val(),
				"hbean.lanip" : $("#IPInterfaceIPAddress").val(),
				"hbean.lansubnetMask" : $("#IPInterfaceSubnetMask").val(),
			}, function() {

			});
		});

	});
	function validatePass(str) {
		var mode = $("#authenticationmode").val();
		if (mode == "wep")
			return !!str.match(/^[\@A-Za-z0-9\!\#\$\%\^\&\*\.\~]{5}$|^[\@A-Za-z0-9\!\#\$\%\^\&\*\.\~]{13}$/);
		else
			return !!str.match(/^[\@A-Za-z0-9\!\#\$\%\^\&\*\.\~]{7,62}$/);
	}
	function validateIP(str) {
		return !!str.match(/^((25[0-5]|2[0-4]\d|[01]?\d\d?)($|(?!\.$)\.)){4}$/);
	}
	function zhylcon() {
		var status = $("input[name='hbean.zhylstatus']:checked").val();
		if (status == "disable") {
			$("#zhylserveraddr").attr("readonly", "readonly");
			$("#zhylport").attr("readonly", "readonly");
			$("#zhylprotocol").attr("disabled", "disabled");
			$("#zhylserveraddr").off("blur");
			$("#serveraddrError").html('${ssidError }');
		}
		if (status == "enable") {
			$("#zhylserveraddr").removeAttr("readonly");
			$("#zhylport").removeAttr("readonly");
			$("#zhylprotocol").removeAttr("disabled");
			$("#zhylserveraddr").on("blur", function() {
				var m = validateIP($(this).val());
				m = m || 'null';
				if (m == 'null')
					$("#serveraddrError").html("ip有误");
				else
					$("#serveraddrError").html("");
				/* $("#serveraddrError").html(m); */
			});
		}
	}

	function wancon() {
		var addrtype = $("#addressingType").val();
		if (addrtype == "DHCP") {
			$("#externalIPAddress").attr("readonly", "readonly");
			$("#subnetMask").attr("readonly", "readonly");
			$("#defaultGateway").attr("readonly", "readonly");
			$("#DNSServers").attr("readonly", "readonly");
			$("#subnetMask,#defaultGateway,#externalIPAddress,#DNSServers").off("blur");
			$("#serveraddrError").html('${ssidError }');
		}
		if (addrtype == "Static") {
			$("#externalIPAddress").removeAttr("readonly");
			$("#subnetMask").removeAttr("readonly");
			$("#defaultGateway").removeAttr("readonly");
			$("#DNSServers").removeAttr("readonly");
			$("#subnetMask,#defaultGateway,#DNSServers,#externalIPAddress").on("blur", function() {
				var m = validateIP($(this).val());
				m = m || 'null';
				if (m == 'null')
					$("#serveraddrError").html($(this).attr("msg") + "格式有误"); 
				else
					$("#serveraddrError").html("");
				/* $("#serveraddrError").html(m); */
			});
		}
	}
	function showarea() {
		var v = $("#authenticationmode").val();
		if (v == "wep") {
			$("#weparea").show();
			$("#wpaarea").hide();
		}
		if (v == "wpapsk") {
			$("#weparea").hide();
			$("#wpaarea").show();
		}

	}
</script>

</head>

<body>

	<%-- <c:forEach items="${jd.data }" var="u" varStatus="aa"> --%>
	<h5>${hbean.serialno }无线参数设置</h5>

	<form action="hosts_reviseWl.do" method="post">
		<input type="hidden" name="hbean.id" value="${hbean.id }" id="hostsid" />
		<input type="hidden" name="hbean.hwid" value="${hbean.hwid }"
			id="hostshwid" /> <input type="hidden" name="hbean.serialno"
			value="${hbean.serialno }" id="hostsserialno" /> 网络名称:<input
			type="text" name="hbean.ssid" value="${hbean.ssid }" id="ssid" /> <br>
		信道:<select size="1" name="hbean.channel" id="channel">
			<option value="1"
				${(hbean.channel eq 1) ? "selected='selected'" : ""}>1</option>
			<option value="2"
				${(hbean.channel eq 2) ? "selected='selected'" : ""}>2</option>
			<option value="3"
				${(hbean.channel eq 3) ? "selected='selected'" : ""}>3</option>
			<option value="4"
				${(hbean.channel eq 4) ? "selected='selected'" : ""}>4</option>
			<option value="5"
				${(hbean.channel eq 5) ? "selected='selected'" : ""}>5</option>
			<option value="6"
				${(hbean.channel eq 6) ? "selected='selected'" : ""}>6</option>
			<option value="7"
				${(hbean.channel eq 7) ? "selected='selected'" : ""}>7</option>
			<option value="8"
				${(hbean.channel eq 8) ? "selected='selected'" : ""}>8</option>
			<option value="9"
				${(hbean.channel eq 9) ? "selected='selected'" : ""}>9</option>
			<option value="10"
				${(hbean.channel eq 10) ? "selected='selected'" : ""}>10</option>
			<option value="11"
				${(hbean.channel eq 11) ? "selected='selected'" : ""}>11</option>
		</select> <br> 频带带宽： <select size="1" name="hbean.bandwidth"
			id="bandwidth">
			<c:choose>
				<c:when test="${empty hbean.bandwidth }">
					<option value="20">20MHz</option>
					<option value="Mixed" selected='selected'>20/40MHz</option>
				</c:when>
				<c:otherwise>
					<option value="20"
						${(hbean.bandwidth eq "20") ? "selected='selected'" : ""}>20MHz</option>
					<option value="Mixed"
						${(hbean.bandwidth eq "Mixed") ? "selected='selected'" : ""}>20/40MHz</option>
				</c:otherwise>
			</c:choose>
		</select> <br>
		<!-- 安全模式 -->
		安全模式： <select size="1" name="hbean.basicEncryptionmodes"
			id="authenticationmode">
			<c:choose>
				<c:when test="${empty hbean.basicEncryptionmodes }">

					<option value="wep">WEP</option>
					<option selected='selected' value="wpapsk">WPA-PSK</option>

				</c:when>
				<c:otherwise>
					<option value="wep"
						${(hbean.basicEncryptionmodes eq "WEPEncryption") ? "selected='selected'" : ""}>WEP</option>
					<option
						${((hbean.basicEncryptionmodes eq "None") and (hbean.authenticationmode eq "PSKAuthentication")) ? "selected='selected'" : "" }
						value="wpapsk">WPA-PSK</option>
				</c:otherwise>
			</c:choose>
		</select>
		<div id="weparea">
			默认密钥：<select size="1" name="hbean.wepkey" id="wepkey">

				<c:choose>
					<c:when test="${empty hbean.wepkey }">
						<option value="key1" selected='selected'>Key1</option>
						<option value="key2">Key2</option>
						<option value="key3">Key3</option>
						<option value="key4">Key4</option>
					</c:when>
					<c:otherwise>
						<option value="key1"
							${(hbean.wepkey eq "key1") ? "selected='selected'" : ""}>Key1</option>
						<option value="key2"
							${(hbean.wepkey eq "key2") ? "selected='selected'" : ""}>Key2</option>
						<option value="key3"
							${(hbean.wepkey eq "key3") ? "selected='selected'" : ""}>Key3</option>
						<option value="key4"
							${(hbean.wepkey eq "key4") ? "selected='selected'" : ""}>Key4</option>
					</c:otherwise>
				</c:choose>
			</select><br>
			<!-- 密钥1：<input type="text" name="hbean.weppass1" value=""><br>
			密钥2：<input type="text" name="hbean.weppass2" value=""><br>
			密钥3：<input type="text" name="hbean.weppass3" value=""><br>
			密钥4：<input type="text" name="hbean.weppass4" value=""> -->
		</div>
		<div id="wpaarea">
			加密算法：
			<c:choose>
				<c:when test="${empty hbean.WPAEncryptionmodes }">
					<input type="radio" value="TKIPEncryption"
						name="hbean.WPAEncryptionmodes">TKIP <input type="radio"
						value="AESEncryption" name="hbean.WPAEncryptionmodes"
						checked="checked">AES
			<input type="radio" value="TKIPandAESEncryption"
						name="hbean.WPAEncryptionmodes">TKIP+AES<br>
				</c:when>
				<c:otherwise>
					<input type="radio" value="TKIPEncryption"
						${(hbean.WPAEncryptionmodes eq "TKIPEncryption") ? "checked='checked'" : "" }
						name="hbean.WPAEncryptionmodes">TKIP <input type="radio"
						value="AESEncryption" name="hbean.WPAEncryptionmodes"
						${(hbean.WPAEncryptionmodes eq "AESEncryption") ? "checked='checked'" : "" }>AES
			<input type="radio" value="TKIPandAESEncryption"
						name="hbean.WPAEncryptionmodes"
						${(hbean.WPAEncryptionmodes eq "TKIPandAESEncryption") ? "checked='checked'" : "" }>TKIP+AES<br>
				</c:otherwise>
			</c:choose>
		</div>
		密码：<input type="text" value="${hbean.wpapass }" name="hbean.wpapass"
			id="wpapass"><br> <br> 智慧养老<br>告警服务：
		<c:choose>
			<c:when test="${empty hbean.zhylstatus }">
				<input type="radio" value="enable" name="hbean.zhylstatus"
					class="zhylstatus">启用 <input type="radio" value="disable"
					name="hbean.zhylstatus" class="zhylstatus" checked="checked">关闭
				</c:when>
			<c:otherwise>
				<input ${(hbean.zhylstatus eq "enable") ? "checked='checked'" : ""}
					type="radio" value="enable" name="hbean.zhylstatus"
					class="zhylstatus">启用 <input type="radio" value="disable"
					name="hbean.zhylstatus"
					${(hbean.zhylstatus eq "disable") ? "checked='checked'" : ""}
					class="zhylstatus">关闭
			</c:otherwise>
		</c:choose>
		<br> 服务器地址： <input type="text" name="hbean.zhylserveraddr"
			value="${hbean.zhylserveraddr }" id="zhylserveraddr"><br>
		协议类型：<select size="1" name="hbean.zhylprotocol" id="zhylprotocol">
			<option value="TCP" selected='selected'>TCP</option>
			<option value="UDP">UDP</option>
		</select><br> 端口号： <input type="text" name="hbean.zhylport"
			value="${hbean.zhylport }" id="zhylport"> <br> <br>
		网络管理<br> WAN设置<br> WAN口连接类型：<select id="addressingType"
			name="hbean.addressingType">
			<option value="DHCP"
				${(hbean.addressingType eq 'DHCP')?"selected='selected'":""}>动态IP(DHCP)</option>
			<option value="Static"
				${(hbean.addressingType eq 'Static')?"selected='selected'":""}>静态IP</option>
		</select><br> IP地址：<input name="hbean.ip" type="text"
			id="externalIPAddress" value="${hbean.ip }" msg="wan IP地址"><br>
		子网掩码：<input type="text" name="hbean.subnetMask" id="subnetMask"
			value="${hbean.subnetMask}" msg="wan 子网掩码"><br> 默认网关：<input
			type="text" name="hbean.defaultGateway" id="defaultGateway"
			value="${hbean.defaultGateway}" msg="默认网关"><br> 主DNS服务器：<input
			type="text" name="hbean.DNSServers" id="DNSServers"
			value="${hbean.DNSServers}" msg="主DNS服务器"><br> <br>
		LAN设置<br> IP地址：<input name="hbean.lanip" type="text"
			id="IPInterfaceIPAddress" value="${hbean.lanip}"><br>
		子网掩码：<input type="text" name="hbean.lansubnetMask"
			id="IPInterfaceSubnetMask" value="${hbean.lansubnetMask}"><br>
		<br>
		<!-- <select id="IPInterfaceSubnetMask" name = "hbean.lansubnetMask" >
			<option value="255.0.0.0" >255.0.0.0</option>
			<option value="255.255.0.0" >255.255.0.0</option>
			<option value="255.255.255.0" selected='selected'>255.255.255.0</option>
		</select><br> -->

		<div id="result"></div>
		<div id="serveraddrError">${ssidError }</div>
		<div id="wpapassError">${ssidError }</div>
		<div id="confirmmsg"></div>
		<h1>${ssidError }</h1>

		<input type="button" value="无线设置" id="confirmwn"> <input
			type="button" value="智慧养老设置" id="confirmzhyl"> <input
			type="button" value="WAN设置" id="confirmwan"> <input
			type="button" value="LAN设置" id="confirmLan">
		<!-- <input
			type="button" value="提交" id="confirm"> -->
	</form>

	<a
		href="hosts_reqReboot.do?hwid=${hbean.hwid }&amp;sn=${hbean.serialno }">重启</a>
	<a
		href="hosts_reqFactoryReset.do?hwid=${hbean.hwid }&amp;sn=${hbean.serialno }">恢复出厂设置</a>
	<a
		href="hosts_requestUpload.do?hbean.hwid=${hbean.hwid }&amp;hbean.serialno=${hbean.serialno }">升级</a>
	<%-- 	</c:forEach> --%>
</body>
</html>
