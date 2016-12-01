<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
  <body>
  <div style="width:80;hight:30;cursor:pointer;color:red" id="iotdeviceadd">确定</div>
    <table>
    	<tr style="display:none"><th width="80"></th><th></th></tr>
    	<tr><td>MAC:</td><td><input type="text" name = "device" id="devicename"></td></tr>
    	<tr><td>设备类型</td><td><select id="devicebox"></select></td></tr>
    	<tr><td>所在大楼</td><td><select id="buildbox"></select></td></tr>
    	<tr><td>所在楼层</td><td><select id="floorbox"></select></td></tr>
    </table>
  </body>
</html>
