function changeMenu(firstMenu, secondMenu) {
    $("#menu>li>ul>li").removeClass("activeli");
    $("#menu>li>ul").removeClass("in");
    $("#menu>li>ul").attr("aria-expanded", "false");
    $("#menu>li>a").attr("aria-expanded", "false");
    $("#menu>li").removeClass("active");
    $("#menu>li").removeClass("activefirstli");
    $("#" + firstMenu).addClass("active");
    $("#" + firstMenu).addClass("activefirstli");
    $("#" + firstMenu + " ul").attr("aria-expanded", "true");
    $("#" + firstMenu + ">a").attr("aria-expanded", "true");
    $("#" + firstMenu + " ul").addClass("in");
    $("#" + firstMenu + " ul").attr("style", "");
    $("#" + secondMenu).addClass("activeli");
}

/*处理刷新菜单显示问题*/
function loadByModel(model) {
    if (null != model) {
        var ss = $("[model='" + model + "']");
        var target = ss.attr("target");
        var model = ss.attr("model");
        $("#menu>li>ul>li").removeClass("activeli");
        $("#menu>li>ul").removeClass("in");
        $("#menu>li").removeClass("active");
        $("#menu>li").removeClass("activefirstli");
        ss.parent().parent().parent().addClass("activefirstli");
        ss.parent().parent().parent().addClass("active");
        ss.parent().addClass("activeli");
        ss.parent().parent().addClass("in");
        $("#right").load(target, function () {
            doCallback(eval(model), [model]);
        });
    } else {
        $("#right").load("default_main.html", function () {
            doCallback(networkoverview, ['networkoverview']);
        });
    }
}
/* 车队管理 */
function busmanager() {
    var subGruopId = 1;
    var flag = true;
    var funFlag = 0;
    $.post("subgroup/getAllGroup.do", {}, function (msg) {
        var data = msg.jsonResult.data;
        for (var i = 0; i < data.length; i++) {
            var group = data[i];
            var option = $("<option></option>").text(group.name).val(group.id);
            /*option.attr("floorwidth", floor.width);
             option.attr("floorheight", floor.height);
             option.attr("picturePath", floor.picturePath);
             option.attr("buildingid", floor.buildingId);*/
            if (group.id == subGruopId) {
                option.attr("selected", "selected");
                var buses = group.buses;
                getBuses(group.id);
                refreshBus(group.id);
            }
            $("#groupbox").append(option);
        }
    });

    $("#groupbox").change(function () {
        var f = $("#groupbox").find("option:selected");
        clearIntervalRefresh();
        subGruopId = f.val();
        getBuses(subGruopId, funFlag);
        if (flag)
            refreshBus(subGruopId);
    });
    $("#monitorbutton").click(function () {
        $(".barbutton").removeClass("currButton");
        $("#monitorbutton").addClass("currButton");
        clearIntervalRefresh();
        flag = true;
        funFlag = 0;
        getBuses(subGruopId, funFlag);
        refreshBus(subGruopId);
    });
    $("#conbutton").click(function () {
        $(".barbutton").removeClass("currButton");
        $("#conbutton").addClass("currButton");
        clearIntervalRefresh();
        flag = false;
        funFlag = 2;
        getBuses(subGruopId, funFlag);

    });
    $("#editbutton").click(function () {
        $(".barbutton").removeClass("currButton");
        $("#editbutton").addClass("currButton");
        clearIntervalRefresh();
        flag = false;
        funFlag = 1;
        getBuses(subGruopId, funFlag);
    });
}
function refreshBus(id) {
    /*alert(id);*/

    intervalRefresh = setInterval(/*getBuses(id)*/function () {
        getBuses(id, 0);
    }, 2000);
}
function getBuses(id, funFlag) {
    $.post("subgroup/getSubGroup.do", {jsonPara: JSON.stringify({id: id})}, function (msg) {
        var buses = msg.jsonResult.data[0].buses;
        $("#picture").empty();
        for (var j = 0; j < buses.length; j++) {
            var bus = buses[j];
            var heightRuler = parseInt(j / 5);
            var indexRuler = j - (heightRuler * 5);
            var $bus = $("<div></div>").css({
                "position": "absolute",
                "width": "150px",
                "height": "60px",
                "left": 40 * indexRuler + indexRuler * 150,
                "top": 30 * heightRuler + heightRuler * 60,
                "background": "url(images/bus/bus_view_black.jpg) 0 0 no-repeat"
            }).attr({"busid": bus.id, "funFlag": funFlag});
            if (bus.hosts.devicestatus) {
                $bus.css({"background": "url(images/bus/bus_view_color.jpg) 0 0 no-repeat"});
                var $deviceDiv = $("<div></div>").attr({
                    rtmp: 'rtmp://192.168.199.151:1935/live/test.stream',
                }).addClass("IPCamera");
                $deviceDiv.css({
                    "position": "absolute",
                    "width": '64px',
                    "height": '64px',
                    "cursor": "pointer",
                    "background": "url(images/device/webcam.png) 0 0 no-repeat",
                    /*"background" : "red",*/
                    "left": 40 * indexRuler + indexRuler * 150 + 100,
                    "top": 30 * heightRuler + heightRuler * 60,
                    'z-index': 23
                })/*;
                 $(".IPCamera")*/.on("click", function () {
                    var $this = $(this);
                    $("#ipvideo").remove();
                    $("#suspensionmenu,#suspensiondraggable").hide();
                    var $ipvideo = $("<div></div>").attr("id", "ipvideo").css({
                        /*"position" : "absolute",
                         'z-index' : '999',*/
                    });
                    ;
                    var $ipvideotitle = $("<div></div>");
                    var $ipvideotext = $("<div></div>").text('Monitor video').css({
                        'width': '400px',
                        'float': 'left',
                        'margin-left': '200px'
                    });
                    var $ipvideoclose = $("<div></div>").text('Close').css({
                        'float': 'right',
                        'width': '60px',
                        'margin-right': '40px',
                        'cursor': 'pointer'
                    });

                    $ipvideoclose.on('click', function () {
                        $f("mediaplayer").close();
                        /*jwplayer('mediaplayer').remove();*/
                        $("#ipvideo").remove();
                        $("#aboutbusmanage").show();
                        /*$("#suspensionmenu,#suspensiondraggable").show();*/
                    });
                    var $ipvideopalyer = $("<div></div>").attr("id", "mediaplayer")/*.css({
                     "position" : "relative",
                     'z-index' : '999',
                     })*/;
                    $("#aboutbusmanage").hide();
                    $("#right").append($ipvideo.append($ipvideotitle.append($ipvideotext, $ipvideoclose), $("<div></div>").append($ipvideopalyer)));

                    $f("mediaplayer", "js/flowplayer-3.2.18.swf", {

                        clip: {
                            url: 'test.stream',
                            live: true,
                            provider: 'influxis'
                        },

                        plugins: {

                            influxis: {
                                url: "js/flowplayer.rtmp-3.2.13.swf",
                                netConnectionUrl: 'rtmp://192.168.199.151:1935/live',
                            },
                            controls: {
                                play: false,
                                opacity: 0,
                                scrubber: false,
                                volume: false,
                                mute: false
                            },
                        }
                    });
                });
                $("#picture").append($deviceDiv);
            }
            var busAttr = {};
            busAttr.busid = bus.id;
            busAttr.hostsid = bus.hosts.id;
            busAttr.subgroupid = id;
            busAttr.endStation = bus.endStation;
            busAttr.startStation = bus.startStation;
            busAttr.driver = bus.driver;
            busAttr.licensePlate = bus.licensePlate;
            busAttr.funFlag = funFlag;
            var $licensePlate = $("<div></div>").text(bus.licensePlate).css({
                marginTop: '25px',
                marginLeft: '50px',
                "cursor": "pointer"
            }).attr(busAttr);
            /*if (null != bus.hosts) {
             $bus.attr('hostsid', bus.hosts.id);
             }*/
            if (funFlag == 0) {
                $licensePlate.on("click", showBusInfo);
            }
            if (funFlag == 1) {
                $licensePlate.on("click", addSubGroup);
            }
            if (funFlag == 2) {
                $licensePlate.on("click", editH);
            }
            $bus.append($licensePlate);

            $("#picture").append($bus);
        }
    });
}
//"+bus.licensePlate+"   "+$(this).attr("licenseplate")+"
var showBusInfo = function showBusInfo(follow) {

    var d = dialog({
        id: 'busInfo',
        align: 'top',
        title: 'Vehicle Information',
        /*height: 500,
         width: 450,
         lock: false,
         left: 30*/
    });
    var content = "<div style='width: 260px'><div style='float:left'><h5 style='margin:0 0 5px 0;padding:0.2em 0;font-weight:bold'>Basic Information</h5>\
                <p style='font-size: 12px;'>License plate:" + $(this).attr("licenseplate") + "</p>\
                <p style='font-size: 12px;'>Driver: Pharaoh</p>\
                <p style='font-size: 12px;'>Team:team 1</p>\
                </div>\
                <div style='float:left;padding-left: 12px'>\
                <h5 style='margin:0 0 5px 0;padding:0.2em 0;font-weight:bold'>Operation information</h5>\
                <p style='font-size: 12px;'>Average speed:30km/h</p>\
                <p style='font-size: 12px;'>Highest speed:120km/h</p>\
                <p style='font-size: 12px;'>Comprehensive fuel consumption:9.2L/100km</p></div></div>";
    d.content(content);
    d.show(follow);
}

var addSubGroup = function addSubGroup() {
    var d = dialog({
        id: 'subGroup',
        align: 'top',
        fixed: true,
        title: 'Configuration',
        height: 500,
        width: 450,
        lock: false,
        left: 30
    });
    var content = "<div id='subgruopArtdialog'><h5 id='modifyTitle'></h5>\
		<form action='bus/changeSubGroup.do' method='post'>\
			<div class='dialogTab' style='display: block;'>\
			<input type='hidden' value='1' id='busid'/>\
			<input type='hidden' value='1' id='licensePlate'/>\
			<ul class='forminfo'>\
			    <li><label>Team:</label>\
			    <select name='subgroupselect' id='subgroupselect'>\
                </select>\
                </li>\
				<li><label>Driver:</label>\
					<input type='text' name='driver' value='' id='driver' />\
				</li>\
				<li><label>Starting station:</label>\
					<input type='text' name='startstation' value='' id='startstation' />\
				</li>\
				<li><label>Terminal station:</label>\
					<input type='text' name='endstation' value='' id='endstation' />\
				</li>\
				<li><input type='button' name='saveap' class='btn' id='saveap' value='Save' /></li>\
				<!-- <br> -->\
			</ul>\
			</div>\
		</form></div>";
    d.content(content);
    var tempbusid = $(this).attr("busid");
    $("#busid").val(tempbusid);
    $("#licensePlate").val($(this).attr("licensePlate"));
    var tempsubgroupid = $(this).attr("subgroupid");
    var temphostsid = $(this).attr("hostsid");
    var funFlag = $(this).attr("funFlag");
    /*alert(tempbusid+' '+tempsubgroupid);*/
    $.post("subgroup/getAllGroup.do", {}, function (msg) {
        var data = msg.jsonResult.data;
        for (var i = 0; i < data.length; i++) {
            var group = data[i];
            var option = $("<option></option>").text(group.name).val(group.id);
            if (group.id == tempsubgroupid) {
                option.attr("selected", "selected");
            }
            $("#subgroupselect").append(option);
        }
    });
    /*$("#chostsid").$(this).attr("busid");*/
    $("#startstation").val($(this).attr("startstation"));
    $("#endstation").val($(this).attr("endstation"));
    $("#driver").val($(this).attr("driver"));


    $("#saveap").on("click", function () {
        var upbus = {};
        upbus.id = $("#busid").val();
        upbus.licensePlate = $("#licensePlate").val();
        upbus.startStation = $("#startstation").val();
        upbus.endStation = $("#startstation").val();
        upbus.driver = $("#driver").val();
        upbus.subGroup = {id: $("#subgroupselect").val()};
        upbus.hosts = {id: temphostsid};
        $.post("bus/updateBus.do",
            {
                /*jsonPara: JSON.stringify({
                 id: $("#busid").val(),
                 licensePlate: $("#licensePlate").val(),
                 startStation: $("#startstation").val(),
                 endStation: $("#endstation").val(),
                 driver: $("#driver").val(),
                 'subGroup.id': tempsubgroupid,
                 })*/
                jsonPara: JSON.stringify(upbus)
            }, function (msg) {
                getBuses(tempsubgroupid, funFlag);
                d.close();
            });
    });
    d.showModal();
}
/* 车队管理 */
/* 网络概况*/
function initMap() {
    var map = new google.maps.Map(document.getElementById('allmap'), {
        center: {lat: 31.167695116666668, lng: 121.39841083333333},
        zoom: 15,
    });
    var markers = [];
    var carIcon = 'images/bus.png';
    getMapPoint(map, carIcon, markers);
    intervalRefresh = setInterval(function () {
        getMapPoint(map, carIcon, markers)
    }, 5000);

}
function getMapPoint(map, carIcon, markers) {
    $.post("mapPoint/getLastMapPointByHosts.do", {}, function (msg) {
        var data = msg.jsonResult.data;
        for (var i = 0; i < data.length; i++) {

            var mapPoint = data[i];

            if (mapPoint.hosts.bus == null) continue;

            (function (mapPoint) {

                // console.log(mapPoint.latitude+"---"+mapPoint.longitude);
                var point = gpsTran(mapPoint);
                // console.log(mapPoint.latitude+"---"+mapPoint.longitude);
                var licensePlate = '';
                if (typeof(mapPoint.hosts.bus) == "object") {
                    if (typeof (mapPoint.hosts.bus.licensePlate) != "undfinde") {
                        licensePlate = mapPoint.hosts.bus.licensePlate;
                    } else {
                        return;
                    }
                }
                var markerExist;
                var markerFlage = true;
                for (var j = 0; j < markers.length; j++) {
                    if (markers[j].getTitle() == licensePlate) {
                        markerExist = markers[j];
                        markerExist.setPosition({lat: point.latitude, lng: point.longitude});
                        markerFlage = false;
                        continue;
                    }
                }
                if (markerFlage) {
                    // console.log("refush");
                    var marker = new google.maps.Marker({
                        position: {lat: point.latitude, lng: point.longitude},
                        map: map,
                        title: mapPoint.hosts.bus.licensePlate,
                        icon: carIcon
                    });
                    showinfomessage(map, marker, mapPoint);

                    markers.push(marker);
                }
            })(mapPoint);

        }
    });
}
function showinfomessage(map, marker, mapPoint) {
    var licensePlate = '';
    var driver = '';
    var subGroupName = '';
    if (typeof(mapPoint.hosts.bus) == "object") {
        var bus = mapPoint.hosts.bus;
        if (typeof(bus.licensePlate) != "undefined")
            licensePlate = bus.licensePlate;
        if (typeof(bus.driver) != "undefined")
            driver = bus.driver;
        if (typeof(bus.subGroup) == "object") {
            if (typeof(bus.subGroup.name) != "undefined")
                subGroupName = bus.subGroup.name;
        }
        if (typeof (licensePlate) == "undefined") {
            licensePlate = "";
        }
        if (typeof (driver) == "undefined") {
            driver = "";
        }
        if (typeof (subGroupName) == "undefined") {
            subGroupName = "";
        }
        // alert(licensePlate+driver+subGroupName);
    }
    var sContent =
        "<div style='width: 260px'><div style='float:left'><h5 style='margin:0 0 5px 0;padding:0.2em 0;font-weight:bold'>Basic Information</h5>\
        <p style='font-size: 12px;'>License plate:" + licensePlate + "</p>\
             <p style='font-size: 12px;'>Driver:" + driver + "</p>\
             <p style='font-size: 12px;'>Team:" + subGroupName + "</p>\
             </div>\
             <div style='float:left;padding-left: 12px'>\
             <h5 style='margin:0 0 5px 0;padding:0.2em 0;font-weight:bold'>Operation information</h5>\
             <p style='font-size: 12px;'>Average speed:30km/h</p>\
             <p style='font-size: 12px;'>Highest speed:120km/h</p>\
             <p style='font-size: 12px;'>Comprehensive fuel consumption:9.2L/100km</p></div></div>";
    var infoWindow = new google.maps.InfoWindow({
        content: sContent
    });
    google.maps.event.addListener(marker, 'click', function (event) {
        infoWindow.open(map, marker);
    });
}
function networkoverview() {
    $("#tabs").tabs();
    $("#toolbar").liToRow({
        'marginLeft': '50px',
        'lineHeight': '40px'
    });
    initMap();
    /*var map = new BMap.Map("allmap");
     map.centerAndZoom(new BMap.Point(121.39841083333333, 31.167695116666668), 17);
     map.enableScrollWheelZoom(true);

     var carIcon = new BMap.Icon("images/bus.png", new BMap.Size(58, 47))
     */


    $("#linecharts").highcharts(
        {
            chart: {
                zoomType: 'x',
                type: 'line',
                plotBorderColor: '#7E7D7B',
                plotBorderWidth: 2,
                marginTop: 30
            },
            title: {
                text: 'Over the past week',
                style: {
                    "color": "#7E7D7B",
                    "fontSize": "14px",
                    "margin-top": "-13px",
                    "fontWeight": 'bold',
                    "font-family": "STKaiti"
                }
            },
            /*subtitle : {
             text : document.ontouchstart === undefined ? 'Click and drag in the plot area to zoom in' : 'Pinch the chart to zoom in'
             },*/
            xAxis: {
                type: 'datetime',
                gridLineWidth: 1,
                labels: {
                    format: '{value:%y-%m}',
                    /*align: 'right',
                     rotation: -30*/
                }
            },
            yAxis: {
                title: {
                    text: null
                    /*text : ''*/
                }
            },
            legend: {
                enabled: false
            },
            plotOptions: {
                area: {
                    fillColor: {
                        linearGradient: {
                            x1: 0,
                            y1: 0,
                            x2: 0,
                            y2: 1
                        },
                        stops: [[0, Highcharts.getOptions().colors[0]], [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]]
                    },
                    marker: {
                        radius: 2
                    },
                    lineWidth: 1,
                    states: {
                        hover: {
                            lineWidth: 1
                        }
                    },
                    threshold: null
                }
            },
            tooltip: {
                xDateFormat: '%y-%m-%d'
                /*dateTimeLabelFormats:"{value:%Y-%M-%d}"*/
            },
            credits: {
                enabled: false
            },

            series: [{
                type: 'area',
                name: 'US',
                data: [[Date.UTC(2013, 5, 2), 0.7695], [Date.UTC(2013, 5, 3), 0.7648], [Date.UTC(2013, 5, 4), 0.7645], [Date.UTC(2013, 5, 5), 0.7638],
                    [Date.UTC(2013, 5, 6), 0.7549], [Date.UTC(2013, 5, 7), 0.7562], [Date.UTC(2013, 5, 9), 0.7574], [Date.UTC(2013, 5, 10), 0.7543],
                    [Date.UTC(2013, 5, 11), 0.7510], [Date.UTC(2013, 5, 12), 0.7498], [Date.UTC(2013, 5, 13), 0.7477], [Date.UTC(2013, 5, 14), 0.7492],
                    [Date.UTC(2013, 5, 16), 0.7487], [Date.UTC(2013, 5, 17), 0.7480], [Date.UTC(2013, 5, 18), 0.7466], [Date.UTC(2013, 5, 19), 0.7521],
                    [Date.UTC(2013, 5, 20), 0.7564], [Date.UTC(2013, 5, 21), 0.7621], [Date.UTC(2013, 5, 23), 0.7630], [Date.UTC(2013, 5, 24), 0.7623],
                    [Date.UTC(2013, 5, 25), 0.7644], [Date.UTC(2013, 5, 26), 0.7685], [Date.UTC(2013, 5, 27), 0.7671], [Date.UTC(2013, 5, 28), 0.7687],
                    [Date.UTC(2013, 5, 30), 0.7687], [Date.UTC(2013, 6, 1), 0.7654], [Date.UTC(2013, 6, 2), 0.7705], [Date.UTC(2013, 6, 3), 0.7687],
                    [Date.UTC(2013, 6, 4), 0.7744], [Date.UTC(2013, 6, 5), 0.7793], [Date.UTC(2013, 6, 7), 0.7804], [Date.UTC(2013, 6, 8), 0.7770],
                    [Date.UTC(2013, 6, 9), 0.7824], [Date.UTC(2013, 6, 10), 0.7705], [Date.UTC(2013, 6, 11), 0.7635], [Date.UTC(2013, 6, 12), 0.7652],
                    [Date.UTC(2013, 6, 14), 0.7656], [Date.UTC(2013, 6, 15), 0.7655], [Date.UTC(2013, 6, 16), 0.7598], [Date.UTC(2013, 6, 17), 0.7619],
                    [Date.UTC(2013, 6, 18), 0.7628], [Date.UTC(2013, 6, 19), 0.7609], [Date.UTC(2013, 6, 21), 0.7599], [Date.UTC(2013, 6, 22), 0.7584],
                    [Date.UTC(2013, 6, 23), 0.7562], [Date.UTC(2013, 6, 24), 0.7575], [Date.UTC(2013, 6, 25), 0.7531], [Date.UTC(2013, 6, 26), 0.7530],
                    [Date.UTC(2013, 6, 28), 0.7526], [Date.UTC(2013, 6, 29), 0.7540], [Date.UTC(2013, 6, 30), 0.7540], [Date.UTC(2013, 6, 31), 0.7518],
                    [Date.UTC(2013, 7, 1), 0.7571], [Date.UTC(2013, 7, 2), 0.7529], [Date.UTC(2013, 7, 4), 0.7532], [Date.UTC(2013, 7, 5), 0.7542],
                    [Date.UTC(2013, 7, 6), 0.7515], [Date.UTC(2013, 7, 7), 0.7498], [Date.UTC(2013, 7, 8), 0.7473], [Date.UTC(2013, 7, 9), 0.7494],
                    [Date.UTC(2013, 7, 11), 0.7497], [Date.UTC(2013, 7, 12), 0.7519], [Date.UTC(2013, 7, 13), 0.7540], [Date.UTC(2013, 7, 14), 0.7543],
                    [Date.UTC(2013, 7, 15), 0.7492], [Date.UTC(2013, 7, 16), 0.7502], [Date.UTC(2013, 7, 18), 0.7503], [Date.UTC(2013, 7, 19), 0.7499],
                    [Date.UTC(2013, 7, 20), 0.7453], [Date.UTC(2013, 7, 21), 0.7487], [Date.UTC(2013, 7, 22), 0.7487], [Date.UTC(2013, 7, 23), 0.7472],
                    [Date.UTC(2013, 7, 25), 0.7471], [Date.UTC(2013, 7, 26), 0.7480], [Date.UTC(2013, 7, 27), 0.7467], [Date.UTC(2013, 7, 28), 0.7497],
                    [Date.UTC(2013, 7, 29), 0.7552], [Date.UTC(2013, 7, 30), 0.7562], [Date.UTC(2013, 8, 1), 0.7572], [Date.UTC(2013, 8, 2), 0.7581],
                    [Date.UTC(2013, 8, 3), 0.7593], [Date.UTC(2013, 8, 4), 0.7571], [Date.UTC(2013, 8, 5), 0.7622], [Date.UTC(2013, 8, 6), 0.7588],
                    [Date.UTC(2013, 8, 8), 0.7591], [Date.UTC(2013, 8, 9), 0.7544], [Date.UTC(2013, 8, 10), 0.7537], [Date.UTC(2013, 8, 11), 0.7512],
                    [Date.UTC(2013, 8, 12), 0.7519], [Date.UTC(2013, 8, 13), 0.7522], [Date.UTC(2013, 8, 15), 0.7486], [Date.UTC(2013, 8, 16), 0.7500],
                    [Date.UTC(2013, 8, 17), 0.7486], [Date.UTC(2013, 8, 18), 0.7396], [Date.UTC(2013, 8, 19), 0.7391], [Date.UTC(2013, 8, 20), 0.7394],
                    [Date.UTC(2013, 8, 22), 0.7389], [Date.UTC(2013, 8, 23), 0.7411], [Date.UTC(2013, 8, 24), 0.7422], [Date.UTC(2013, 8, 25), 0.7393],
                    [Date.UTC(2013, 8, 26), 0.7413], [Date.UTC(2013, 8, 27), 0.7396], [Date.UTC(2013, 8, 29), 0.7410], [Date.UTC(2013, 8, 30), 0.7393],
                    [Date.UTC(2013, 9, 1), 0.7393], [Date.UTC(2013, 9, 2), 0.7365], [Date.UTC(2013, 9, 3), 0.7343], [Date.UTC(2013, 9, 4), 0.7376],
                    [Date.UTC(2013, 9, 6), 0.7370], [Date.UTC(2013, 9, 7), 0.7362], [Date.UTC(2013, 9, 8), 0.7368], [Date.UTC(2013, 9, 9), 0.7393],
                    [Date.UTC(2013, 9, 10), 0.7397], [Date.UTC(2013, 9, 11), 0.7385], [Date.UTC(2013, 9, 13), 0.7377], [Date.UTC(2013, 9, 14), 0.7374],
                    [Date.UTC(2013, 9, 15), 0.7395], [Date.UTC(2013, 9, 16), 0.7389], [Date.UTC(2013, 9, 17), 0.7312], [Date.UTC(2013, 9, 18), 0.7307],
                    [Date.UTC(2013, 9, 20), 0.7309], [Date.UTC(2013, 9, 21), 0.7308], [Date.UTC(2013, 9, 22), 0.7256], [Date.UTC(2013, 9, 23), 0.7258],
                    [Date.UTC(2013, 9, 24), 0.7247], [Date.UTC(2013, 9, 25), 0.7244], [Date.UTC(2013, 9, 27), 0.7244], [Date.UTC(2013, 9, 28), 0.7255],
                    [Date.UTC(2013, 9, 29), 0.7275], [Date.UTC(2013, 9, 30), 0.7280], [Date.UTC(2013, 9, 31), 0.7361], [Date.UTC(2013, 10, 1), 0.7415],
                    [Date.UTC(2013, 10, 3), 0.7411], [Date.UTC(2013, 10, 4), 0.7399], [Date.UTC(2013, 10, 5), 0.7421], [Date.UTC(2013, 10, 6), 0.7400],
                    [Date.UTC(2013, 10, 7), 0.7452], [Date.UTC(2013, 10, 8), 0.7479], [Date.UTC(2013, 10, 10), 0.7492], [Date.UTC(2013, 10, 11), 0.7460],
                    [Date.UTC(2013, 10, 12), 0.7442], [Date.UTC(2013, 10, 13), 0.7415], [Date.UTC(2013, 10, 14), 0.7429], [Date.UTC(2013, 10, 15), 0.7410],
                    [Date.UTC(2013, 10, 17), 0.7417], [Date.UTC(2013, 10, 18), 0.7405], [Date.UTC(2013, 10, 19), 0.7386], [Date.UTC(2013, 10, 20), 0.7441],
                    [Date.UTC(2013, 10, 21), 0.7418], [Date.UTC(2013, 10, 22), 0.7376], [Date.UTC(2013, 10, 24), 0.7379], [Date.UTC(2013, 10, 25), 0.7399],
                    [Date.UTC(2013, 10, 26), 0.7369], [Date.UTC(2013, 10, 27), 0.7365], [Date.UTC(2013, 10, 28), 0.7350], [Date.UTC(2013, 10, 29), 0.7358],
                    [Date.UTC(2013, 11, 1), 0.7362], [Date.UTC(2013, 11, 2), 0.7385], [Date.UTC(2013, 11, 3), 0.7359], [Date.UTC(2013, 11, 4), 0.7357],
                    [Date.UTC(2013, 11, 5), 0.7317], [Date.UTC(2013, 11, 6), 0.7297], [Date.UTC(2013, 11, 8), 0.7296], [Date.UTC(2013, 11, 9), 0.7279],
                    [Date.UTC(2013, 11, 10), 0.7267], [Date.UTC(2013, 11, 11), 0.7254], [Date.UTC(2013, 11, 12), 0.7270], [Date.UTC(2013, 11, 13), 0.7276],
                    [Date.UTC(2013, 11, 15), 0.7278], [Date.UTC(2013, 11, 16), 0.7267], [Date.UTC(2013, 11, 17), 0.7263], [Date.UTC(2013, 11, 18), 0.7307],
                    [Date.UTC(2013, 11, 19), 0.7319], [Date.UTC(2013, 11, 20), 0.7315], [Date.UTC(2013, 11, 22), 0.7311], [Date.UTC(2013, 11, 23), 0.7301],
                    [Date.UTC(2013, 11, 24), 0.7308], [Date.UTC(2013, 11, 25), 0.7310], [Date.UTC(2013, 11, 26), 0.7304], [Date.UTC(2013, 11, 27), 0.7277],
                    [Date.UTC(2013, 11, 29), 0.7272], [Date.UTC(2013, 11, 30), 0.7244], [Date.UTC(2013, 11, 31), 0.7275], [Date.UTC(2014, 0, 1), 0.7271],
                    [Date.UTC(2014, 0, 2), 0.7314], [Date.UTC(2014, 0, 3), 0.7359], [Date.UTC(2014, 0, 5), 0.7355], [Date.UTC(2014, 0, 6), 0.7338],
                    [Date.UTC(2014, 0, 7), 0.7345], [Date.UTC(2014, 0, 8), 0.7366], [Date.UTC(2014, 0, 9), 0.7349], [Date.UTC(2014, 0, 10), 0.7316],
                    [Date.UTC(2014, 0, 12), 0.7315], [Date.UTC(2014, 0, 13), 0.7315], [Date.UTC(2014, 0, 14), 0.7310], [Date.UTC(2014, 0, 15), 0.7350],
                    [Date.UTC(2014, 0, 16), 0.7341], [Date.UTC(2014, 0, 17), 0.7385], [Date.UTC(2014, 0, 19), 0.7392], [Date.UTC(2014, 0, 20), 0.7379],
                    [Date.UTC(2014, 0, 21), 0.7373], [Date.UTC(2014, 0, 22), 0.7381], [Date.UTC(2014, 0, 23), 0.7301], [Date.UTC(2014, 0, 24), 0.7311],
                    [Date.UTC(2014, 0, 26), 0.7306], [Date.UTC(2014, 0, 27), 0.7314], [Date.UTC(2014, 0, 28), 0.7316], [Date.UTC(2014, 0, 29), 0.7319],
                    [Date.UTC(2014, 0, 30), 0.7377], [Date.UTC(2014, 0, 31), 0.7415], [Date.UTC(2014, 1, 2), 0.7414], [Date.UTC(2014, 1, 3), 0.7393],
                    [Date.UTC(2014, 1, 4), 0.7397], [Date.UTC(2014, 1, 5), 0.7389], [Date.UTC(2014, 1, 6), 0.7358], [Date.UTC(2014, 1, 7), 0.7334],
                    [Date.UTC(2014, 1, 9), 0.7343], [Date.UTC(2014, 1, 10), 0.7328], [Date.UTC(2014, 1, 11), 0.7332], [Date.UTC(2014, 1, 12), 0.7356],
                    [Date.UTC(2014, 1, 13), 0.7309], [Date.UTC(2014, 1, 14), 0.7304], [Date.UTC(2014, 1, 16), 0.7300], [Date.UTC(2014, 1, 17), 0.7295],
                    [Date.UTC(2014, 1, 18), 0.7268], [Date.UTC(2014, 1, 19), 0.7281], [Date.UTC(2014, 1, 20), 0.7289], [Date.UTC(2014, 1, 21), 0.7278],
                    [Date.UTC(2014, 1, 23), 0.7280], [Date.UTC(2014, 1, 24), 0.7280], [Date.UTC(2014, 1, 25), 0.7275], [Date.UTC(2014, 1, 26), 0.7306],
                    [Date.UTC(2014, 1, 27), 0.7295], [Date.UTC(2014, 1, 28), 0.7245], [Date.UTC(2014, 2, 2), 0.7259], [Date.UTC(2014, 2, 3), 0.7280],
                    [Date.UTC(2014, 2, 4), 0.7276], [Date.UTC(2014, 2, 5), 0.7282], [Date.UTC(2014, 2, 6), 0.7215], [Date.UTC(2014, 2, 7), 0.7206],
                    [Date.UTC(2014, 2, 9), 0.7206], [Date.UTC(2014, 2, 10), 0.7207], [Date.UTC(2014, 2, 11), 0.7216], [Date.UTC(2014, 2, 12), 0.7192],
                    [Date.UTC(2014, 2, 13), 0.7210], [Date.UTC(2014, 2, 14), 0.7187], [Date.UTC(2014, 2, 16), 0.7188], [Date.UTC(2014, 2, 17), 0.7183],
                    [Date.UTC(2014, 2, 18), 0.7177], [Date.UTC(2014, 2, 19), 0.7229], [Date.UTC(2014, 2, 20), 0.7258], [Date.UTC(2014, 2, 21), 0.7249],
                    [Date.UTC(2014, 2, 23), 0.7247], [Date.UTC(2014, 2, 24), 0.7226], [Date.UTC(2014, 2, 25), 0.7232], [Date.UTC(2014, 2, 26), 0.7255],
                    [Date.UTC(2014, 2, 27), 0.7278], [Date.UTC(2014, 2, 28), 0.7271], [Date.UTC(2014, 2, 30), 0.7272], [Date.UTC(2014, 2, 31), 0.7261],
                    [Date.UTC(2014, 3, 1), 0.7250], [Date.UTC(2014, 3, 2), 0.7264], [Date.UTC(2014, 3, 3), 0.7289], [Date.UTC(2014, 3, 4), 0.7298],
                    [Date.UTC(2014, 3, 6), 0.7298], [Date.UTC(2014, 3, 7), 0.7278], [Date.UTC(2014, 3, 8), 0.7248], [Date.UTC(2014, 3, 9), 0.7218],
                    [Date.UTC(2014, 3, 10), 0.7200], [Date.UTC(2014, 3, 11), 0.7202], [Date.UTC(2014, 3, 13), 0.7222], [Date.UTC(2014, 3, 14), 0.7236],
                    [Date.UTC(2014, 3, 15), 0.7239], [Date.UTC(2014, 3, 16), 0.7238], [Date.UTC(2014, 3, 17), 0.7238], [Date.UTC(2014, 3, 18), 0.7238],
                    [Date.UTC(2014, 3, 20), 0.7239], [Date.UTC(2014, 3, 21), 0.7250], [Date.UTC(2014, 3, 22), 0.7244], [Date.UTC(2014, 3, 23), 0.7238],
                    [Date.UTC(2014, 3, 24), 0.7229], [Date.UTC(2014, 3, 25), 0.7229], [Date.UTC(2014, 3, 27), 0.7226], [Date.UTC(2014, 3, 28), 0.7220],
                    [Date.UTC(2014, 3, 29), 0.7240], [Date.UTC(2014, 3, 30), 0.7211], [Date.UTC(2014, 4, 1), 0.7210], [Date.UTC(2014, 4, 2), 0.7209],
                    [Date.UTC(2014, 4, 4), 0.7209], [Date.UTC(2014, 4, 5), 0.7207], [Date.UTC(2014, 4, 6), 0.7180], [Date.UTC(2014, 4, 7), 0.7188],
                    [Date.UTC(2014, 4, 8), 0.7225], [Date.UTC(2014, 4, 9), 0.7268], [Date.UTC(2014, 4, 11), 0.7267], [Date.UTC(2014, 4, 12), 0.7269],
                    [Date.UTC(2014, 4, 13), 0.7297], [Date.UTC(2014, 4, 14), 0.7291], [Date.UTC(2014, 4, 15), 0.7294], [Date.UTC(2014, 4, 16), 0.7302],
                    [Date.UTC(2014, 4, 18), 0.7298], [Date.UTC(2014, 4, 19), 0.7295], [Date.UTC(2014, 4, 20), 0.7298], [Date.UTC(2014, 4, 21), 0.7307],
                    [Date.UTC(2014, 4, 22), 0.7323], [Date.UTC(2014, 4, 23), 0.7335], [Date.UTC(2014, 4, 25), 0.7338], [Date.UTC(2014, 4, 26), 0.7329],
                    [Date.UTC(2014, 4, 27), 0.7335], [Date.UTC(2014, 4, 28), 0.7358], [Date.UTC(2014, 4, 29), 0.7351], [Date.UTC(2014, 4, 30), 0.7337],
                    [Date.UTC(2014, 5, 1), 0.7338], [Date.UTC(2014, 5, 2), 0.7355], [Date.UTC(2014, 5, 3), 0.7338], [Date.UTC(2014, 5, 4), 0.7353],
                    [Date.UTC(2014, 5, 5), 0.7321], [Date.UTC(2014, 5, 6), 0.7330], [Date.UTC(2014, 5, 8), 0.7327], [Date.UTC(2014, 5, 9), 0.7356],
                    [Date.UTC(2014, 5, 10), 0.7381], [Date.UTC(2014, 5, 11), 0.7389], [Date.UTC(2014, 5, 12), 0.7379], [Date.UTC(2014, 5, 13), 0.7384],
                    [Date.UTC(2014, 5, 15), 0.7388], [Date.UTC(2014, 5, 16), 0.7367], [Date.UTC(2014, 5, 17), 0.7382], [Date.UTC(2014, 5, 18), 0.7356],
                    [Date.UTC(2014, 5, 19), 0.7349], [Date.UTC(2014, 5, 20), 0.7353], [Date.UTC(2014, 5, 22), 0.7357], [Date.UTC(2014, 5, 23), 0.7350],
                    [Date.UTC(2014, 5, 24), 0.7350], [Date.UTC(2014, 5, 25), 0.7337], [Date.UTC(2014, 5, 26), 0.7347], [Date.UTC(2014, 5, 27), 0.7327],
                    [Date.UTC(2014, 5, 29), 0.7330], [Date.UTC(2014, 5, 30), 0.7304], [Date.UTC(2014, 6, 1), 0.7310], [Date.UTC(2014, 6, 2), 0.7320],
                    [Date.UTC(2014, 6, 3), 0.7347], [Date.UTC(2014, 6, 4), 0.7356], [Date.UTC(2014, 6, 6), 0.7360], [Date.UTC(2014, 6, 7), 0.7350],
                    [Date.UTC(2014, 6, 8), 0.7346], [Date.UTC(2014, 6, 9), 0.7329], [Date.UTC(2014, 6, 10), 0.7348], [Date.UTC(2014, 6, 11), 0.7349],
                    [Date.UTC(2014, 6, 13), 0.7352], [Date.UTC(2014, 6, 14), 0.7342], [Date.UTC(2014, 6, 15), 0.7369], [Date.UTC(2014, 6, 16), 0.7393],
                    [Date.UTC(2014, 6, 17), 0.7392], [Date.UTC(2014, 6, 18), 0.7394], [Date.UTC(2014, 6, 20), 0.7390], [Date.UTC(2014, 6, 21), 0.7395],
                    [Date.UTC(2014, 6, 22), 0.7427], [Date.UTC(2014, 6, 23), 0.7427], [Date.UTC(2014, 6, 24), 0.7428], [Date.UTC(2014, 6, 25), 0.7446],
                    [Date.UTC(2014, 6, 27), 0.7447], [Date.UTC(2014, 6, 28), 0.7440], [Date.UTC(2014, 6, 29), 0.7458], [Date.UTC(2014, 6, 30), 0.7464],
                    [Date.UTC(2014, 6, 31), 0.7469], [Date.UTC(2014, 7, 1), 0.7446], [Date.UTC(2014, 7, 3), 0.7447], [Date.UTC(2014, 7, 4), 0.7450],
                    [Date.UTC(2014, 7, 5), 0.7477], [Date.UTC(2014, 7, 6), 0.7472], [Date.UTC(2014, 7, 7), 0.7483], [Date.UTC(2014, 7, 8), 0.7457],
                    [Date.UTC(2014, 7, 10), 0.7460], [Date.UTC(2014, 7, 11), 0.7470], [Date.UTC(2014, 7, 12), 0.7480], [Date.UTC(2014, 7, 13), 0.7482],
                    [Date.UTC(2014, 7, 14), 0.7482], [Date.UTC(2014, 7, 15), 0.7463], [Date.UTC(2014, 7, 17), 0.7469], [Date.UTC(2014, 7, 18), 0.7483],
                    [Date.UTC(2014, 7, 19), 0.7508], [Date.UTC(2014, 7, 20), 0.7541], [Date.UTC(2014, 7, 21), 0.7529], [Date.UTC(2014, 7, 22), 0.7551],
                    [Date.UTC(2014, 7, 24), 0.7577], [Date.UTC(2014, 7, 25), 0.7580], [Date.UTC(2014, 7, 26), 0.7593], [Date.UTC(2014, 7, 27), 0.7580],
                    [Date.UTC(2014, 7, 28), 0.7585], [Date.UTC(2014, 7, 29), 0.7614], [Date.UTC(2014, 7, 31), 0.7618], [Date.UTC(2014, 8, 1), 0.7618],
                    [Date.UTC(2014, 8, 2), 0.7614], [Date.UTC(2014, 8, 3), 0.7604], [Date.UTC(2014, 8, 4), 0.7725], [Date.UTC(2014, 8, 5), 0.7722],
                    [Date.UTC(2014, 8, 7), 0.7721], [Date.UTC(2014, 8, 8), 0.7753], [Date.UTC(2014, 8, 9), 0.7730], [Date.UTC(2014, 8, 10), 0.7742],
                    [Date.UTC(2014, 8, 11), 0.7736], [Date.UTC(2014, 8, 12), 0.7713], [Date.UTC(2014, 8, 14), 0.7717], [Date.UTC(2014, 8, 15), 0.7727],
                    [Date.UTC(2014, 8, 16), 0.7716], [Date.UTC(2014, 8, 17), 0.7772], [Date.UTC(2014, 8, 18), 0.7739], [Date.UTC(2014, 8, 19), 0.7794],
                    [Date.UTC(2014, 8, 21), 0.7788], [Date.UTC(2014, 8, 22), 0.7782], [Date.UTC(2014, 8, 23), 0.7784], [Date.UTC(2014, 8, 24), 0.7824],
                    [Date.UTC(2014, 8, 25), 0.7843], [Date.UTC(2014, 8, 26), 0.7884], [Date.UTC(2014, 8, 28), 0.7891], [Date.UTC(2014, 8, 29), 0.7883],
                    [Date.UTC(2014, 8, 30), 0.7916], [Date.UTC(2014, 9, 1), 0.7922], [Date.UTC(2014, 9, 2), 0.7893], [Date.UTC(2014, 9, 3), 0.7989],
                    [Date.UTC(2014, 9, 5), 0.7992], [Date.UTC(2014, 9, 6), 0.7903], [Date.UTC(2014, 9, 7), 0.7893], [Date.UTC(2014, 9, 8), 0.7853],
                    [Date.UTC(2014, 9, 9), 0.7880], [Date.UTC(2014, 9, 10), 0.7919], [Date.UTC(2014, 9, 12), 0.7912], [Date.UTC(2014, 9, 13), 0.7842],
                    [Date.UTC(2014, 9, 14), 0.7900], [Date.UTC(2014, 9, 15), 0.7790], [Date.UTC(2014, 9, 16), 0.7806], [Date.UTC(2014, 9, 17), 0.7835],
                    [Date.UTC(2014, 9, 19), 0.7844], [Date.UTC(2014, 9, 20), 0.7813], [Date.UTC(2014, 9, 21), 0.7864], [Date.UTC(2014, 9, 22), 0.7905],
                    [Date.UTC(2014, 9, 23), 0.7907], [Date.UTC(2014, 9, 24), 0.7893], [Date.UTC(2014, 9, 26), 0.7889], [Date.UTC(2014, 9, 27), 0.7875],
                    [Date.UTC(2014, 9, 28), 0.7853], [Date.UTC(2014, 9, 29), 0.7916], [Date.UTC(2014, 9, 30), 0.7929], [Date.UTC(2014, 9, 31), 0.7984],
                    [Date.UTC(2014, 10, 2), 0.7999], [Date.UTC(2014, 10, 3), 0.8012], [Date.UTC(2014, 10, 4), 0.7971], [Date.UTC(2014, 10, 5), 0.8009],
                    [Date.UTC(2014, 10, 6), 0.8081], [Date.UTC(2014, 10, 7), 0.8030], [Date.UTC(2014, 10, 9), 0.8025], [Date.UTC(2014, 10, 10), 0.8051],
                    [Date.UTC(2014, 10, 11), 0.8016], [Date.UTC(2014, 10, 12), 0.8040], [Date.UTC(2014, 10, 13), 0.8015], [Date.UTC(2014, 10, 14), 0.7985],
                    [Date.UTC(2014, 10, 16), 0.7988], [Date.UTC(2014, 10, 17), 0.8032], [Date.UTC(2014, 10, 18), 0.7976], [Date.UTC(2014, 10, 19), 0.7965],
                    [Date.UTC(2014, 10, 20), 0.7975], [Date.UTC(2014, 10, 21), 0.8071], [Date.UTC(2014, 10, 23), 0.8082], [Date.UTC(2014, 10, 24), 0.8037],
                    [Date.UTC(2014, 10, 25), 0.8016], [Date.UTC(2014, 10, 26), 0.7996], [Date.UTC(2014, 10, 27), 0.8022], [Date.UTC(2014, 10, 28), 0.8031],
                    [Date.UTC(2014, 10, 30), 0.8040], [Date.UTC(2014, 11, 1), 0.8020], [Date.UTC(2014, 11, 2), 0.8075], [Date.UTC(2014, 11, 3), 0.8123],
                    [Date.UTC(2014, 11, 4), 0.8078], [Date.UTC(2014, 11, 5), 0.8139], [Date.UTC(2014, 11, 7), 0.8135], [Date.UTC(2014, 11, 8), 0.8119],
                    [Date.UTC(2014, 11, 9), 0.8081], [Date.UTC(2014, 11, 10), 0.8034], [Date.UTC(2014, 11, 11), 0.8057], [Date.UTC(2014, 11, 12), 0.8024],
                    [Date.UTC(2014, 11, 14), 0.8024], [Date.UTC(2014, 11, 15), 0.8040], [Date.UTC(2014, 11, 16), 0.7993], [Date.UTC(2014, 11, 17), 0.8102],
                    [Date.UTC(2014, 11, 18), 0.8139], [Date.UTC(2014, 11, 19), 0.8177], [Date.UTC(2014, 11, 21), 0.8180], [Date.UTC(2014, 11, 22), 0.8176],
                    [Date.UTC(2014, 11, 23), 0.8215], [Date.UTC(2014, 11, 24), 0.8200], [Date.UTC(2014, 11, 25), 0.8182], [Date.UTC(2014, 11, 26), 0.8213],
                    [Date.UTC(2014, 11, 28), 0.8218], [Date.UTC(2014, 11, 29), 0.8229], [Date.UTC(2014, 11, 30), 0.8225], [Date.UTC(2014, 11, 31), 0.8266],
                    [Date.UTC(2015, 0, 1), 0.8262], [Date.UTC(2015, 0, 2), 0.8331], [Date.UTC(2015, 0, 4), 0.8371], [Date.UTC(2015, 0, 5), 0.8380],
                    [Date.UTC(2015, 0, 6), 0.8411], [Date.UTC(2015, 0, 7), 0.8447], [Date.UTC(2015, 0, 8), 0.8480], [Date.UTC(2015, 0, 9), 0.8445],
                    [Date.UTC(2015, 0, 11), 0.8425], [Date.UTC(2015, 0, 12), 0.8451], [Date.UTC(2015, 0, 13), 0.8495], [Date.UTC(2015, 0, 14), 0.8482],
                    [Date.UTC(2015, 0, 15), 0.8598], [Date.UTC(2015, 0, 16), 0.8643], [Date.UTC(2015, 0, 18), 0.8648], [Date.UTC(2015, 0, 19), 0.8617],
                    [Date.UTC(2015, 0, 20), 0.8658], [Date.UTC(2015, 0, 21), 0.8613], [Date.UTC(2015, 0, 22), 0.8798], [Date.UTC(2015, 0, 23), 0.8922],
                    [Date.UTC(2015, 0, 25), 0.8990], [Date.UTC(2015, 0, 26), 0.8898], [Date.UTC(2015, 0, 27), 0.8787], [Date.UTC(2015, 0, 28), 0.8859],
                    [Date.UTC(2015, 0, 29), 0.8834], [Date.UTC(2015, 0, 30), 0.8859], [Date.UTC(2015, 1, 1), 0.8843], [Date.UTC(2015, 1, 2), 0.8817],
                    [Date.UTC(2015, 1, 3), 0.8710], [Date.UTC(2015, 1, 4), 0.8813], [Date.UTC(2015, 1, 5), 0.8713], [Date.UTC(2015, 1, 6), 0.8837],
                    [Date.UTC(2015, 1, 8), 0.8839], [Date.UTC(2015, 1, 9), 0.8831], [Date.UTC(2015, 1, 10), 0.8833], [Date.UTC(2015, 1, 11), 0.8823],
                    [Date.UTC(2015, 1, 12), 0.8770], [Date.UTC(2015, 1, 13), 0.8783], [Date.UTC(2015, 1, 15), 0.8774], [Date.UTC(2015, 1, 16), 0.8807],
                    [Date.UTC(2015, 1, 17), 0.8762], [Date.UTC(2015, 1, 18), 0.8774], [Date.UTC(2015, 1, 19), 0.8798], [Date.UTC(2015, 1, 20), 0.8787],
                    [Date.UTC(2015, 1, 22), 0.8787], [Date.UTC(2015, 1, 23), 0.8824], [Date.UTC(2015, 1, 24), 0.8818], [Date.UTC(2015, 1, 25), 0.8801],
                    [Date.UTC(2015, 1, 26), 0.8931], [Date.UTC(2015, 1, 27), 0.8932], [Date.UTC(2015, 2, 1), 0.8960], [Date.UTC(2015, 2, 2), 0.8941],
                    [Date.UTC(2015, 2, 3), 0.8948], [Date.UTC(2015, 2, 4), 0.9026], [Date.UTC(2015, 2, 5), 0.9066], [Date.UTC(2015, 2, 6), 0.9222],
                    [Date.UTC(2015, 2, 8), 0.9221], [Date.UTC(2015, 2, 9), 0.9214], [Date.UTC(2015, 2, 10), 0.9347], [Date.UTC(2015, 2, 11), 0.9482],
                    [Date.UTC(2015, 2, 12), 0.9403], [Date.UTC(2015, 2, 13), 0.9528], [Date.UTC(2015, 2, 15), 0.9541], [Date.UTC(2015, 2, 16), 0.9462],
                    [Date.UTC(2015, 2, 17), 0.9435], [Date.UTC(2015, 2, 18), 0.9203], [Date.UTC(2015, 2, 19), 0.9381], [Date.UTC(2015, 2, 20), 0.9241],
                    [Date.UTC(2015, 2, 22), 0.9237], [Date.UTC(2015, 2, 23), 0.9135], [Date.UTC(2015, 2, 24), 0.9152], [Date.UTC(2015, 2, 25), 0.9114],
                    [Date.UTC(2015, 2, 26), 0.9188], [Date.UTC(2015, 2, 27), 0.9184], [Date.UTC(2015, 2, 29), 0.9188], [Date.UTC(2015, 2, 30), 0.9231],
                    [Date.UTC(2015, 2, 31), 0.9319], [Date.UTC(2015, 3, 1), 0.9291], [Date.UTC(2015, 3, 2), 0.9188], [Date.UTC(2015, 3, 3), 0.9109],
                    [Date.UTC(2015, 3, 5), 0.9091], [Date.UTC(2015, 3, 6), 0.9154], [Date.UTC(2015, 3, 7), 0.9246], [Date.UTC(2015, 3, 8), 0.9276],
                    [Date.UTC(2015, 3, 9), 0.9382], [Date.UTC(2015, 3, 10), 0.9431], [Date.UTC(2015, 3, 12), 0.9426], [Date.UTC(2015, 3, 13), 0.9463],
                    [Date.UTC(2015, 3, 14), 0.9386], [Date.UTC(2015, 3, 15), 0.9357], [Date.UTC(2015, 3, 16), 0.9293], [Date.UTC(2015, 3, 17), 0.9254],
                    [Date.UTC(2015, 3, 19), 0.9251], [Date.UTC(2015, 3, 20), 0.9312], [Date.UTC(2015, 3, 21), 0.9315], [Date.UTC(2015, 3, 22), 0.9323],
                    [Date.UTC(2015, 3, 23), 0.9236], [Date.UTC(2015, 3, 24), 0.9196], [Date.UTC(2015, 3, 26), 0.9201], [Date.UTC(2015, 3, 27), 0.9184],
                    [Date.UTC(2015, 3, 28), 0.9106], [Date.UTC(2015, 3, 29), 0.8983], [Date.UTC(2015, 3, 30), 0.8909], [Date.UTC(2015, 4, 1), 0.8928],
                    [Date.UTC(2015, 4, 3), 0.8941], [Date.UTC(2015, 4, 4), 0.8972], [Date.UTC(2015, 4, 5), 0.8940], [Date.UTC(2015, 4, 6), 0.8808],
                    [Date.UTC(2015, 4, 7), 0.8876], [Date.UTC(2015, 4, 8), 0.8925], [Date.UTC(2015, 4, 10), 0.8934], [Date.UTC(2015, 4, 11), 0.8964],
                    [Date.UTC(2015, 4, 12), 0.8917], [Date.UTC(2015, 4, 13), 0.8805], [Date.UTC(2015, 4, 14), 0.8764], [Date.UTC(2015, 4, 15), 0.8732],
                    [Date.UTC(2015, 4, 17), 0.8737], [Date.UTC(2015, 4, 18), 0.8838], [Date.UTC(2015, 4, 19), 0.8969], [Date.UTC(2015, 4, 20), 0.9014],
                    [Date.UTC(2015, 4, 21), 0.8999], [Date.UTC(2015, 4, 22), 0.9076], [Date.UTC(2015, 4, 24), 0.9098], [Date.UTC(2015, 4, 25), 0.9110],
                    [Date.UTC(2015, 4, 26), 0.9196], [Date.UTC(2015, 4, 27), 0.9170], [Date.UTC(2015, 4, 28), 0.9133], [Date.UTC(2015, 4, 29), 0.9101],
                    [Date.UTC(2015, 4, 31), 0.9126], [Date.UTC(2015, 5, 1), 0.9151], [Date.UTC(2015, 5, 2), 0.8965], [Date.UTC(2015, 5, 3), 0.8871],
                    [Date.UTC(2015, 5, 4), 0.8898], [Date.UTC(2015, 5, 5), 0.8999], [Date.UTC(2015, 5, 7), 0.9004], [Date.UTC(2015, 5, 8), 0.8857],
                    [Date.UTC(2015, 5, 9), 0.8862], [Date.UTC(2015, 5, 10), 0.8829], [Date.UTC(2015, 5, 11), 0.8882], [Date.UTC(2015, 5, 12), 0.8873],
                    [Date.UTC(2015, 5, 14), 0.8913], [Date.UTC(2015, 5, 15), 0.8862], [Date.UTC(2015, 5, 16), 0.8891], [Date.UTC(2015, 5, 17), 0.8821],
                    [Date.UTC(2015, 5, 18), 0.8802], [Date.UTC(2015, 5, 19), 0.8808], [Date.UTC(2015, 5, 21), 0.8794], [Date.UTC(2015, 5, 22), 0.8818],
                    [Date.UTC(2015, 5, 23), 0.8952], [Date.UTC(2015, 5, 24), 0.8924], [Date.UTC(2015, 5, 25), 0.8925], [Date.UTC(2015, 5, 26), 0.8955],
                    [Date.UTC(2015, 5, 28), 0.9113], [Date.UTC(2015, 5, 29), 0.8900], [Date.UTC(2015, 5, 30), 0.8950]]
            }]
        });
}

/* -------------轨迹显示开始-------------------- */
function bustrack() {
    var cal = Calendar.setup({
        onSelect: function (cal) {
            cal.hide()
        },
        showTime: true
    });
    cal.manageFields("f_btn1", "startTime", "%Y-%m-%d %H:%M:%S");
    cal.manageFields("f_btn2", "endTime", "%Y-%m-%d %H:%M:%S");
    initTrackMap();
}

function initTrackMap() {
    var map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 31.167695116666668, lng: 121.39841083333333},
        zoom: 15,
    });
    var markers = [];
    var carIcon = 'images/bus.png';
    var polylines = [];

    $("#submitBtn").click(
        function () {
            for (var i = 0; i < polylines.length; i++) {
                polylines[i].setMap(null);
            }
            polylines = [];
            for (var i = 0; i < markers.length; i++) {
                markers[i].setMap(null);
            }
            markers = [];
            var licencePlate = $("#licencePlate").val();
            var startTime = $("#startTime").val();
            var endTime = $("#endTime").val();
            var startdate = stringToDate(startTime);
            var enddate = stringToDate(endTime);
            if (startdate >= enddate) {
                alert("The start time is greater than the end time!");
            }
            var paraJson = {licencePlate: licencePlate, startTime: startdate.getTime(), endTime: enddate.getTime()};
            $.post("mapPoint/getTrackByBus.do", {jsonPara: JSON.stringify(paraJson)}, function (msg) {
                var data = msg.jsonResult.data;
                var flightPlanCoordinates = [];
                for (var i = 0; i < data.length; i++) {
                    var mappoint = gpsTran(data[i]);
                    if (i == 0) {
                        map.setCenter({lat: mappoint.latitude, lng: mappoint.longitude});
                    }
                    flightPlanCoordinates.push({lat: mappoint.latitude, lng: mappoint.longitude});
                    if (data.length - 1 == i) {
                        var marker = new google.maps.Marker({
                            position: {lat: mappoint.latitude, lng: mappoint.longitude},
                            map: map,
                            icon: carIcon
                        });
                        markers.push(marker);
                        // console.log('Add icon');
                    }
                }
                var flightPath = new google.maps.Polyline({
                    path: flightPlanCoordinates,
                    geodesic: true,
                    strokeColor: '#FF0000',
                    StrokeOpacity: 1.0,
                    strokeWeight: 4
                });
                flightPath.setMap(map);
                polylines.push(flightPath);
            });
        }
    );
    // alert(licencePlate+"++++"+startTime+"+++"+endTime);
    /* $.post("hosts/getHostsByIdExclude.do", {jsonPara: JSON.stringify({id: $(this).attr("hostsid")})}, function (msg) {

     });*/

}

/* -------------轨迹显示结束-------------------- */
/*------ap监控开始-- */
function defaultApMonitor() {
    cpe_valOnLine();
    intervalRefresh = setInterval("cpe_valOnLine()", 5000);
}
function cpe_valOnLine() {
    $.post("hosts/getAllBinding.do", {}, function (msg) {
        msg = eval('(' + msg + ')'); // 转为json对象
        var data = msg.data;
        //alert(JSON.stringify(data));
        $("#cpeinfo").empty();
        for (var i = 0, j = data.length; i < j; i++) {
            var u = data[i];
            if (u.bus == null) continue;
            var tr = $("<tr></tr>");
            var td0 = $("<td></td>").text(u.id);
            var td1 = $("<td></td>");
            var td2 = $("<td></td>").text(u.serialno).css("cursor", "pointer").addClass("apCon").attr("apId", u.id).attr("hostsid", u.id).on("click", editH);
            var td3 = $("<td></td>");
            var td4 = $("<td></td>").text(u.currentSoftware);
            var td5 = $("<td></td>").text(u.hardware);
            var td6 = $("<td></td>");
            var td7 = $("<td></td>");
            var td8 = $("<td></td>");
            var td8 = $("<td></td>").text(u.remark);

            if (typeof(u.bus == "object")) {
                td1.text(u.bus.licensePlate);
                td3.text(u.bus.subGroup.name);
                if (typeof(u.bus.subGroup) == "object") {
                    td7.text(u.bus.subGroup.id);
                }
            }

            td7.css("display", "none");
            td7.addClass("subGroupid");
            td0.addClass("vehicleid");
            td2.addClass("serialno");
            td8.addClass("remark");

            if (u.devicestatus == true)
                td6.text("Online");
            else {
                td6.text("Offline");
                td6.css("color", "red");
            }

            tr.append(td0, td1, td2, td3, td4, td5, td6, td7, td8);
            td2.attr("title", "Double-click Edit");
            td8.on("dblclick", updatedesByartdialog);
            $("#cpeinfo").append(tr);
        }
    });
}

function updatedesByartdialog() {
    var curr = $(this);
    var hwid = $(this).parent().children(".hwid").text();
    var cpeid = $(this).parent().children(".cpeid").text();
    var serialno = $(this).parent().children(".serialno").text();
    var d = dialog({
        fixed: true,
        title: 'Modify comments',
        content: '<input autofocus id="auto" value="' + curr.text() + '"/>',
        ok: function () {
            var a = $("#auto").val();
            $.post("cpe_addRmarkByJson.do", {
                "hbean.hwid": hwid,
                "hbean.serialno": serialno,
                "hbean.remark": a
            }, function (msg) {
                msg = eval('(' + msg + ')');
                if (msg.errormsg == 'error')
                    alert("failure");
                else {
                    $("[hid='" + cpeid + "']").text(a);
                }
            });
        },
        follow: this
    });
    d.show();
}
var editH = function editHosts() {
    var d = dialog({
        id: 'host1',
        align: 'top',
        fixed: true,
        title: 'Configuration',
        height: 500,
        width: 450,
        lock: false,
        left: 30
    });
    var content = "<div id='apArtdialog'><h5 id='modifyTitle'></h5>\
		<form action='hosts/preConfig.do' method='post'>\
			<div class='dialogTab' style='display: block;'>\
			<input type='hidden' value='1' id='chostsid'/>\
			<ul class='forminfo'>\
				<li><label>SSID:</label>\
					<input type='text' name='ssid' value='' id='ssid' />\
				</li>\
				<li><label>Security mode:</label>\
					<div class='vocation'>\
						<select name='encryptionmode' id='encryptionmode'>\
							<option value='psk'>WPA-PSK</option>\
							<option value='psk2'>WPA2-PSK</option>\
							<option value='psk-mixed'>WPA-PSK/WPA2-PSK Mixed Mode</option>\
						</select>\
					</div>\
				</li>\
				<li><label>Password:</label>\
					<input type='text' name='password' value='' id='password' />\
				</li>\
				<li><input type='button' name='saveap' class='btn' id='saveap' value='Save' /></li>\
				<!-- <br> -->\
				<li>\
					<input type='button' name='reboot' class='btn' id='reboot' value='Reboot' />\
					<input type='button' name='restore' class='btn' id='restore' value='Reset' />\
				</li>\
			</ul>\
			</div>\
		</form></div>";
    d.content(content);
    $.post("hosts/getHostsByIdExclude.do", {jsonPara: JSON.stringify({id: $(this).attr("hostsid")})}, function (msg) {
        //alert(msg);
        // msg = eval('(' + msg + ')');
        var data = msg.jsonResult.data;
        $("#modifyTitle").text(data[0].serialno + "Wireless parameter settings");
        $("#chostsid").val(data[0].id);
        $("#ssid").val(data[0].ssid);
        $("#encryptionmode").val(data[0].basicEncryptionmodes);
        $("#password").val(data[0].wpapass);
        $("#encryptionmode").uedSelect({
            width: 220
        });
        $("#saveap").on("click", function () {
            $.post("hosts/preConfig.do",
                {
                    jsonPara: JSON.stringify({
                        id: $("#chostsid").val(),
                        ssid: $("#ssid").val(),
                        WPAEncryptionmodes: $("#encryptionmode").val(),
                        wpapass: $("#password").val()
                    })
                }, function (msg) {
                    //alert(JSON.stringify(msg));
                });
        });
        $("#reboot").on("click", function () {
            $.post("hosts/requestReboot.do",
                {
                    jsonPara: JSON.stringify({
                        id: $("#chostsid").val(),
                    })
                }, function (msg) {
                    //alert(JSON.stringify(msg));
                });
        });
        $("#restore").on("click", function () {
            $.post("hosts/requestFactoryReset.do",
                {
                    jsonPara: JSON.stringify({
                        id: $("#chostsid").val(),
                    })
                }, function (msg) {
                    //alert(JSON.stringify(msg));
                });
        });
    });
    d.showModal();
};
/*------ap监控 结束----*/

function movie() {
    $.post("film/getAllFilms.do", {}, function (msg) {
        var data = msg.jsonResult.data;
        //alert(JSON.stringify(data));
        $("#addMovie").on("click", addM);
        $("#movieInfo").empty();
        for (var i = 0, j = data.length; i < j; i++) {
            var u = data[i];
            var tr = $("<tr></tr>");
            var td0 = $("<td></td>").text(u.id).css("width", "10%");
            var td1 = $("<td></td>").text(u.ename).css("width", "45%");
            var td2 = $("<td></td>").text(u.publish ? "Published" : "Unpublished").css("width", "15%");
            if (u.publish) {
                var td3 = $("<td></td>").html("<a href='#'>Unpublish</a>").attr("publishStatus", u.publish).css("width", "10%");
            } else {
                var td3 = $("<td></td>").html("<a href='#'>Publish</a>").attr("publishStatus", u.publish).css("width", "10%");
            }
            var td4 = $("<td></td>").html("<a href='#'>Edit</a>").addClass("edit").attr("movieId", u.id).css("width", "10%").on("click", editM);
            td3.addClass('publishDo');
            tr.append(td0, td1, td2, td3, td4);
            $("#movieInfo").append(tr);
        }
        $(".publishDo").on("click", function () {
            var trid = $(this).parent("tr").find("td").eq(0).text();
            var publishStatus = $(this).attr("publishStatus");
            if (publishStatus == true) {
                $.post("film/unPublishFilm.do",
                    {
                        jsonPara: JSON.stringify({
                            id: trid,
                        })
                    }, function (msg) {
                    });
            } else {
                $.post("film/publishFilm.do",
                    {
                        jsonPara: JSON.stringify({
                            id: trid,
                        })
                    }, function (msg) {
                    });
            }
        });
    });
}
/*添加新的movie*/
var addM = function addMovie() {
    var d = dialog({
        id: 'movie',
        align: 'top',
        fixed: true,
        title: 'Add configuration',
        height: 500,
        width: 450,
        lock: false,
        left: 30
    });
    $.post("filmType/getAll.do", {}, function (msg) {
        var filmType = msg.jsonResult.data;
        var content = "<div id='movieDialog'>\
			<form action='film/uploadFilmInfo.do' method='post'>\
				<div class='dialogTab' style='display: block;'>\
				<ul class='forminfo'>\
					<li><label>Movie Name:</label>\
						<input type='text' name='ename' value='' id='ename' />\
					</li>\
					<li><label>Film Introduction:</label>\
						<input type='text' name='descInfo' value='' id='descInfo' />\
					</li>\
					<li><label>Production area:</label>\
						<input type='text' name='country' value='' id='country' />\
					</li>\
					<li><label>Movie type:</label>\
						<div class='vocation' id='movieType'>\
						</div>\
					</li>\
					<li><label>Release time:</label>\
						<input type='text' name='date' value='' id='date' />\
					</li>\
					<li><label>Score:</label>\
						<input type='text' name='score' value='' id='score' />\
					</li>\
					<li><label>Click:</label>\
						<input type='text' name='clickRatio' value='' id='clickRatio' />\
					</li>\
					<li><label>File:</label>\
						<div id='filmInfo'>\
						    <input type='hidden' id='filmId'/>\
						    <input type='hidden' id='create' value='true'/>\
						</div>\
						<div id='uploader1' class='wu-example'>\
							<div id='thelist1' class='uploader-list'></div>\
                                <div class='btns'>\
                                <div id='multi'>Select file</div>\
                            </div>\
						</div>\
					</li>\
					<li><input type='button' name='saveap' class='btn' id='multiUpload' value='Save' /></li>\
				</ul>\
				</div>\
			</form></div>";
        d.content(content);
        for (var i = 0, j = filmType.length; i < j; i++) {
            $("#movieType").append("<input type='checkbox' filmTypeId='" + (i + 1) + "' name='chk_list' value='" + filmType[i].typeName + "' class='typeName'/>&nbsp;&nbsp;&nbsp;&nbsp;<span class='boxSpan'>" + filmType[i].typeName + "</span><br/>");
        }
        d.showModal();
        create_upload();
        d.hide();
    });
}

function music() {

}
function novel() {

}
function game() {

}
/*修改movie*/
var editM = function editMovie() {
    var d = dialog({
        id: 'movie',
        align: 'top',
        fixed: true,
        title: 'Configuration',
        height: 500,
        width: 450,
        lock: false,
        left: 30
    });
    var movieid = $(this).attr("movieId");
    $.post("filmType/getAll.do", {}, function (msg) {
        var filmType = msg.jsonResult.data;
        var content = "<div id='movieDialog'>\
			<form action='film/uploadFilmInfo.do' method='post'>\
				<div class='dialogTab' style='display: block;'>\
				<ul class='forminfo'>\
					<li><label>Movie Name:</label>\
						<input type='text' name='ename' value='' id='ename' />\
					</li>\
					<li><label>Film Introduction:</label>\
						<input type='text' name='descInfo' value='' id='descInfo' />\
					</li>\
					<li><label>Production area:</label>\
						<input type='text' name='country' value='' id='country' />\
					</li>\
					<li><label>Movie type:</label>\
						<div class='vocation' id='movieType'>\
						</div>\
					</li>\
					<li><label>Release time:</label>\
						<input type='text' name='date' value='' id='date' />\
					</li>\
					<li><label>Score:</label>\
						<input type='text' name='score' value='' id='score' />\
					</li>\
					<li><label>Click:</label>\
						<input type='text' name='clickRatio' value='' id='clickRatio' />\
					</li>\
					<li><input type='button' name='saveap' class='btn' id='saveap' value='Save' /></li>\
				</ul>\
				</div>\
			</form></div>";
        d.content(content);
        for (var i = 0, j = filmType.length; i < j; i++) {
            $("#movieType").append("<input type='checkbox' filmTypeId='" + (i + 1) + "' name='chk_list" + movieid + "' value='" + filmType[i].typeName + "' class='typeName'/>&nbsp;&nbsp;&nbsp;&nbsp;<span class='boxSpan'>" + filmType[i].typeName + "</span><br/>");
        }
        $.post("film/getFilm.do", {jsonPara: JSON.stringify({id: movieid})}, function (msg) {
            var getFilmData = msg.jsonResult.data[0];
            if (typeof(getFilmData.ename) != "undefined")
                $("#ename").val(getFilmData.ename);
            if (typeof(getFilmData.descInfo) != "undefined")
                $("#descInfo").val(getFilmData.descInfo);
            if (typeof(getFilmData.country) != "undefined")
                $("#country").val(getFilmData.country);
            if (typeof(getFilmData.filmTypes) != "undefined")
                for (var m = 0, n = getFilmData.filmTypes.length; m < n; m++) {
                    var arrChk = $("input[name='chk_list" + movieid + "'][type='checkbox']");
                    $(arrChk).each(function () {
                        if (this.value == getFilmData.filmTypes[m].typeName) {
                            $(this).attr("checked", true);
                            return false;
                        }
                    });
                }
            if (typeof(getFilmData.date) != "undefined")
                $("#date").val(getFilmData.date);
            if (typeof(getFilmData.score) != "undefined")
                $("#score").val(getFilmData.score);
            if (typeof(getFilmData.click) != "undefined")
                $("#clickRatio").val(getFilmData.click);
            $("#saveap").on("click", function () {
                var setFilmData = getFilmData;
                setFilmData.ename = $("#ename").val();
                setFilmData.descInfo = $("#descInfo").val();
                var arrChk = $("input[name='chk_list" + movieid + "'][type='checkbox']");
                var chkNum = 0;
                setFilmData.filmTypes = [];
                $(arrChk).each(function () {
                    if ($(this).is(":checked") == true) {
                        setFilmData.filmTypes[chkNum] = {"id": $(this).attr("filmTypeId"), "typeName": $(this).val()};
                        chkNum++;
                    }
                });
                setFilmData.country = $("#country").val();
                setFilmData.date = $("#date").val();
                var score = $.trim($("#score").val());
                if (score != "") {
                    setFilmData.score = $("#score").val();
                } else {
                    delete setFilmData["score"];
                }
                var click = $.trim($("#clickRatio").val());
                if (click != "") {
                    setFilmData.click = click;
                } else {
                    delete setFilmData["click"];
                }
                $.post("film/uploadFilmInfo.do",
                    {jsonPara: JSON.stringify(setFilmData)}, function (msg) {
                        if (msg.jsonResult.statusCode == 200) {
                            $(location).attr('href', 'default.html?model=movie');
                            ;
                        } else {
                            alert("Save failed！");
                        }
                    });
            });
        });
        d.showModal();

    });
};
/*----------设备告警管理开始------------*/
function iotAlarmManager() {
    $.post("floor_alarmIOT.do", {}, function (msg) {
        msg = eval('(' + msg + ')');
        var total = msg.total;
        if (total > 0) {
            var iots = msg.data;
            for (var i = 0; i < iots.length; i++) {
                var iot = iots[i];
                var tr = $("<tr></tr>");
                tr.append($("<td></td>").text(iot.deviceMAC));
                if (typeof (iot.deviceType) != "undefined" && iot.deviceType != null) {
                    tr.append($("<td></td>").text(iot.deviceType.deviceName));
                } else {
                    tr.append($("<td></td>"));
                }
                tr.append($("<td></td>").text(""));
                tr.append($("<td></td>").text(""));
                if (typeof (iot.building) != "undefined" && iot.building != null) {
                    tr.append($("<td></td>").text(iot.building.buildingName));
                } else {
                    tr.append($("<td></td>"));
                }
                if (typeof (iot.floor) != "undefined" && iot.floor != null) {
                    tr.append($("<td></td>").text(iot.floor.floorName));
                } else {
                    tr.append($("<td></td>"));
                }
                $("tbody").append(tr);
            }
        }
    });
}
/*----------设备告警管理结束------------*/
function carData() {

}
function otherData() {

}
function carMessage() {

}
function gatewayMessage() {

}
/*-------------------求助管理开始---------------------*/
function seekHelpManager() {
    $.post("seekHelp_getAll.do", {}, function (msg) {
        msg = eval('(' + msg + ')');
        var data = msg.data;
        for (var i = 0; i < data.length; i++) {
            var seekHelp = data[i];
            var tr = $("<tr></tr>");
            tr.append($("<td></td>").text(seekHelp.handCard.iotMac));
            tr.append($("<td></td>"));
            tr.append($("<td></td>"));
            var gotoTdTag = $("<td></td>");
            var gotoTag = $("<input>").attr("type", "submit").val("Go to the map");
            gotoTag.on("click", function () {
                var firstMenu = "menuSystemMonitor";
                var secondMenu = "defaultAreaMap";
                changeMenu(firstMenu, secondMenu);
                var model = $("#" + secondMenu).children("a").attr("model");
                var target = $("#" + secondMenu).children("a").attr("target");
                $("#right").load(target, function () {
                    // doCallback(eval(model), [ model ]);
                    doCallbackOfArgs(model, [seekHelp.hosts.tenementId]);
                });
                var sh = {};
                sh.mac = seekHelp.handCard.iotMac;
                $.post("seekHelp_deleteCache.do", {
                    jsonPara: JSON.stringify(sh)
                }, function (msg) {

                });
            });
            tr.append(gotoTdTag.append(gotoTag));
            $("#seekhelptable tbody").append(tr);
        }
    });
}
/*-------------------求助管理结束---------------------*/
