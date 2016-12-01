function doCallback(fn, args) {
	clearIntervalRefresh();
	/*if ( typeof (dia) != "undefined") {
	 dia.close().remove();
	 }
	 $(".ui-dialog").remove();*/
	$(".suspension").remove();
	$("link").each(function() {
		$this = $(this);
		// alert($this.attr("href"));
		if ($this.attr("href") === 'css/dialog-temp.css')
			$this.remove();
	});
	var url = changeURLPar(window.location.href, 'model', args);
	window.history.pushState(null, null, url);
	fn.apply(this, args);

}
function doCallbackOfArgs(fn, args) {
	clearIntervalRefresh();
	/*if ( typeof (dia) != "undefined") {
	 dia.close().remove();
	 }
	 $(".ui-dialog").remove();*/
	$(".suspension").remove();
	$("link").each(function() {
		$this = $(this);
		// alert($this.attr("href"));
		if ($this.attr("href") === 'css/dialog-temp.css')
			$this.remove();
	});
	var url = changeURLPar(window.location.href, 'model', fn);
	window.history.pushState(null, null, url);
	eval(fn).apply(this, args);

}
function getUrl(action){
	return "http://tab-aswe.imwork.net:802/a/"+action;
}
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
function isNull(val) {
	if (val == null || typeof (val) == undefined) {
		return "";
	}
}
function JsonIsNull(value) {
	var type;
	if (value == null) { // 等同于 value === undefined || value === null
		return true;
	}
	type = Object.prototype.toString.call(value).slice(8, -1);
	switch (type) {
		case 'String':
			return !!$.trim(value);
		case 'Array':
			return !value.length;
		case 'Object':
			return $.isEmptyObject(value); // 普通对象使用 for...in 判断，有 key 即为 false
		default:
			return false; // 其他对象均视作非空
	}
};
/*修改地址字符串内容*/
function changeURLPar(destiny, par, par_value) {
	var pattern = par + '=([^&]*)';
	var replaceText = par + '=' + par_value;
	if (destiny.match(pattern)) {
		var tmp = '/\\' + par + '=[^&]*/';
		tmp = destiny.replace(eval(tmp), replaceText);
		return (tmp);
	} else {
		if (destiny.match('[\?]')) {
			return destiny + '&' + replaceText;
		} else {
			return destiny + '?' + replaceText;
		}
	}
	return destiny + '\n' + par + '\n' + par_value;
}
/*删除地址中参数*/
function deleteURLPar(destiny, par) {
	var pattern = par + '=([^&]*)';
	/* var replaceText = par + '=' + par_value; */
	if (destiny.match(pattern)) {
		var tmp = '/\\' + par + '=[^&]*/';
		tmp = destiny.replace(eval(tmp), "");
		return (tmp);
	} else {
		return destiny;
	}
}
/*从地址栏获取参数*/
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}

/*定时刷新任务*/
var intervalRefresh = "";
function clearIntervalRefresh() {
	clearInterval(intervalRefresh);
}
function date(format, timestamp) {
	var a, jsdate = ((timestamp) ? new Date(timestamp * 1000) : new Date());
	var pad = function(n, c) {
		if ((n = n + "").length < c) {
			return new Array(++c - n.length).join("0") + n;
		} else {
			return n;
		}
	};
	var txt_weekdays = [ "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" ];
	var txt_ordin = {
		1 : "st",
		2 : "nd",
		3 : "rd",
		21 : "st",
		22 : "nd",
		23 : "rd",
		31 : "st"
	};
	var txt_months = [ "", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" ];
	var f = {
		// Day
		d : function() {
			return pad(f.j(), 2)
		},
		D : function() {
			return f.l().substr(0, 3)
		},
		j : function() {
			return jsdate.getDate()
		},
		l : function() {
			return txt_weekdays[f.w()]
		},
		N : function() {
			return f.w() + 1
		},
		S : function() {
			return txt_ordin[f.j()] ? txt_ordin[f.j()] : 'th'
		},
		w : function() {
			return jsdate.getDay()
		},
		z : function() {
			return (jsdate - new Date(jsdate.getFullYear() + "/1/1")) / 864e5 >> 0
		},

		// Week
		W : function() {
			var a = f.z(), b = 364 + f.L() - a;
			var nd2, nd = (new Date(jsdate.getFullYear() + "/1/1").getDay() || 7) - 1;
			if (b <= 2 && ((jsdate.getDay() || 7) - 1) <= 2 - b) {
				return 1;
			} else {
				if (a <= 2 && nd >= 4 && a >= (6 - nd)) {
					nd2 = new Date(jsdate.getFullYear() - 1 + "/12/31");
					return date("W", Math.round(nd2.getTime() / 1000));
				} else {
					return (1 + (nd <= 3 ? ((a + nd) / 7) : (a - (7 - nd)) / 7) >> 0);
				}
			}
		},

		// Month
		F : function() {
			return txt_months[f.n()]
		},
		m : function() {
			return pad(f.n(), 2)
		},
		M : function() {
			return f.F().substr(0, 3)
		},
		n : function() {
			return jsdate.getMonth() + 1
		},
		t : function() {
			var n;
			if ((n = jsdate.getMonth() + 1) == 2) {
				return 28 + f.L();
			} else {
				if (n & 1 && n < 8 || !(n & 1) && n > 7) {
					return 31;
				} else {
					return 30;
				}
			}
		},

		// Year
		L : function() {
			var y = f.Y();
			return (!(y & 3) && (y % 1e2 || !(y % 4e2))) ? 1 : 0
		},
		// o not supported yet
		Y : function() {
			return jsdate.getFullYear()
		},
		y : function() {
			return (jsdate.getFullYear() + "").slice(2)
		},

		// Time
		a : function() {
			return jsdate.getHours() > 11 ? "pm" : "am"
		},
		A : function() {
			return f.a().toUpperCase()
		},
		B : function() {
			// peter paul koch:
			var off = (jsdate.getTimezoneOffset() + 60) * 60;
			var theSeconds = (jsdate.getHours() * 3600) + (jsdate.getMinutes() * 60) + jsdate.getSeconds() + off;
			var beat = Math.floor(theSeconds / 86.4);
			if (beat > 1000)
				beat -= 1000;
			if (beat < 0)
				beat += 1000;
			if ((String(beat)).length == 1)
				beat = "00" + beat;
			if ((String(beat)).length == 2)
				beat = "0" + beat;
			return beat;
		},
		g : function() {
			return jsdate.getHours() % 12 || 12
		},
		G : function() {
			return jsdate.getHours()
		},
		h : function() {
			return pad(f.g(), 2)
		},
		H : function() {
			return pad(jsdate.getHours(), 2)
		},
		i : function() {
			return pad(jsdate.getMinutes(), 2)
		},
		s : function() {
			return pad(jsdate.getSeconds(), 2)
		},
		// u not supported yet

		// Timezone
		// e not supported yet
		// I not supported yet
		O : function() {
			var t = pad(Math.abs(jsdate.getTimezoneOffset() / 60 * 100), 4);
			if (jsdate.getTimezoneOffset() > 0)
				t = "-" + t;
			else
				t = "+" + t;
			return t;
		},
		P : function() {
			var O = f.O();
			return (O.substr(0, 3) + ":" + O.substr(3, 2))
		},
		// T not supported yet
		// Z not supported yet

		// Full Date/Time
		c : function() {
			return f.Y() + "-" + f.m() + "-" + f.d() + "T" + f.h() + ":" + f.i() + ":" + f.s() + f.P()
		},
		// r not supported yet
		U : function() {
			return Math.round(jsdate.getTime() / 1000)
		}
	};

	return format.replace(/[\\]?([a-zA-Z])/g, function(t, s) {
		if (t != s) {
			// escaped
			ret = s;
		} else if (f[s]) {
			// a date function exists
			ret = f[s]();
		} else {
			// nothing special
			ret = s;
		}
		return ret;
	});
}
function gpsTran(mapPoint) {

	var GPS = {
		PI : 3.14159265358979324,
		x_pi : 3.14159265358979324 * 3000.0 / 180.0,
		delta : function (lat, lon) {
			// Krasovsky 1940
			//
			// a = 6378245.0, 1/f = 298.3
			// b = a * (1 - f)
			// ee = (a^2 - b^2) / a^2;
			var a = 6378245.0; //  a: 卫星椭球坐标投影到平面地图坐标系的投影因子。
			var ee = 0.00669342162296594323; //  ee: 椭球的偏心率。
			var dLat = this.transformLat(lon - 105.0, lat - 35.0);
			var dLon = this.transformLon(lon - 105.0, lat - 35.0);
			var radLat = lat / 180.0 * this.PI;
			var magic = Math.sin(radLat);
			magic = 1 - ee * magic * magic;
			var sqrtMagic = Math.sqrt(magic);
			dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * this.PI);
			dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * this.PI);
			return {'lat': dLat, 'lon': dLon};
		},

		//WGS-84 to GCJ-02
		gcj_encrypt : function (wgsLat, wgsLon) {
			if (this.outOfChina(wgsLat, wgsLon))
				return {'lat': wgsLat, 'lon': wgsLon};

			var d = this.delta(wgsLat, wgsLon);
			return {'lat' : wgsLat + d.lat,'lon' : wgsLon + d.lon};
		},
		//GCJ-02 to WGS-84
		gcj_decrypt : function (gcjLat, gcjLon) {
			if (this.outOfChina(gcjLat, gcjLon))
				return {'lat': gcjLat, 'lon': gcjLon};

			var d = this.delta(gcjLat, gcjLon);
			return {'lat': gcjLat - d.lat, 'lon': gcjLon - d.lon};
		},
		//GCJ-02 to WGS-84 exactly
		gcj_decrypt_exact : function (gcjLat, gcjLon) {
			var initDelta = 0.01;
			var threshold = 0.000000001;
			var dLat = initDelta, dLon = initDelta;
			var mLat = gcjLat - dLat, mLon = gcjLon - dLon;
			var pLat = gcjLat + dLat, pLon = gcjLon + dLon;
			var wgsLat, wgsLon, i = 0;
			while (1) {
				wgsLat = (mLat + pLat) / 2;
				wgsLon = (mLon + pLon) / 2;
				var tmp = this.gcj_encrypt(wgsLat, wgsLon)
				dLat = tmp.lat - gcjLat;
				dLon = tmp.lon - gcjLon;
				if ((Math.abs(dLat) < threshold) && (Math.abs(dLon) < threshold))
					break;

				if (dLat > 0) pLat = wgsLat; else mLat = wgsLat;
				if (dLon > 0) pLon = wgsLon; else mLon = wgsLon;

				if (++i > 10000) break;
			}
			//console.log(i);
			return {'lat': wgsLat, 'lon': wgsLon};
		},
		//GCJ-02 to BD-09
		bd_encrypt : function (gcjLat, gcjLon) {
			var x = gcjLon, y = gcjLat;
			var z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * this.x_pi);
			var theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * this.x_pi);
			bdLon = z * Math.cos(theta) + 0.0065;
			bdLat = z * Math.sin(theta) + 0.006;
			return {'lat' : bdLat,'lon' : bdLon};
		},
		//BD-09 to GCJ-02
		bd_decrypt : function (bdLat, bdLon) {
			var x = bdLon - 0.0065, y = bdLat - 0.006;
			var z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * this.x_pi);
			var theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * this.x_pi);
			var gcjLon = z * Math.cos(theta);
			var gcjLat = z * Math.sin(theta);
			return {'lat' : gcjLat, 'lon' : gcjLon};
		},
		//WGS-84 to Web mercator
		//mercatorLat -> y mercatorLon -> x
		mercator_encrypt : function(wgsLat, wgsLon) {
			var x = wgsLon * 20037508.34 / 180.;
			var y = Math.log(Math.tan((90. + wgsLat) * this.PI / 360.)) / (this.PI / 180.);
			y = y * 20037508.34 / 180.;
			return {'lat' : y, 'lon' : x};
			/*
			 if ((Math.abs(wgsLon) > 180 || Math.abs(wgsLat) > 90))
			 return null;
			 var x = 6378137.0 * wgsLon * 0.017453292519943295;
			 var a = wgsLat * 0.017453292519943295;
			 var y = 3189068.5 * Math.log((1.0 + Math.sin(a)) / (1.0 - Math.sin(a)));
			 return {'lat' : y, 'lon' : x};
			 //*/
		},
		// Web mercator to WGS-84
		// mercatorLat -> y mercatorLon -> x
		mercator_decrypt : function(mercatorLat, mercatorLon) {
			var x = mercatorLon / 20037508.34 * 180.;
			var y = mercatorLat / 20037508.34 * 180.;
			y = 180 / this.PI * (2 * Math.atan(Math.exp(y * this.PI / 180.)) - this.PI / 2);
			return {'lat' : y, 'lon' : x};
			/*
			 if (Math.abs(mercatorLon) < 180 && Math.abs(mercatorLat) < 90)
			 return null;
			 if ((Math.abs(mercatorLon) > 20037508.3427892) || (Math.abs(mercatorLat) > 20037508.3427892))
			 return null;
			 var a = mercatorLon / 6378137.0 * 57.295779513082323;
			 var x = a - (Math.floor(((a + 180.0) / 360.0)) * 360.0);
			 var y = (1.5707963267948966 - (2.0 * Math.atan(Math.exp((-1.0 * mercatorLat) / 6378137.0)))) * 57.295779513082323;
			 return {'lat' : y, 'lon' : x};
			 //*/
		},
		// two point's distance
		distance : function (latA, lonA, latB, lonB) {
			var earthR = 6371000.;
			var x = Math.cos(latA * this.PI / 180.) * Math.cos(latB * this.PI / 180.) * Math.cos((lonA - lonB) * this.PI / 180);
			var y = Math.sin(latA * this.PI / 180.) * Math.sin(latB * this.PI / 180.);
			var s = x + y;
			if (s > 1) s = 1;
			if (s < -1) s = -1;
			var alpha = Math.acos(s);
			var distance = alpha * earthR;
			return distance;
		},
		outOfChina : function (lat, lon) {
			if (lon < 72.004 || lon > 137.8347)
				return true;
			if (lat < 0.8293 || lat > 55.8271)
				return true;
			return false;
		},
		transformLat : function (x, y) {
			var ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(Math.abs(x));
			ret += (20.0 * Math.sin(6.0 * x * this.PI) + 20.0 * Math.sin(2.0 * x * this.PI)) * 2.0 / 3.0;
			ret += (20.0 * Math.sin(y * this.PI) + 40.0 * Math.sin(y / 3.0 * this.PI)) * 2.0 / 3.0;
			ret += (160.0 * Math.sin(y / 12.0 * this.PI) + 320 * Math.sin(y * this.PI / 30.0)) * 2.0 / 3.0;
			return ret;
		},
		transformLon : function (x, y) {
			var ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * Math.sqrt(Math.abs(x));
			ret += (20.0 * Math.sin(6.0 * x * this.PI) + 20.0 * Math.sin(2.0 * x * this.PI)) * 2.0 / 3.0;
			ret += (20.0 * Math.sin(x * this.PI) + 40.0 * Math.sin(x / 3.0 * this.PI)) * 2.0 / 3.0;
			ret += (150.0 * Math.sin(x / 12.0 * this.PI) + 300.0 * Math.sin(x / 30.0 * this.PI)) * 2.0 / 3.0;
			return ret;
		}
	};

	var point = GPS.gcj_encrypt(parseFloat(mapPoint.latitude),parseFloat(mapPoint.longitude));
	// console.log(point.lat,point.lon);
	mapPoint.latitude = point.lat;
	mapPoint.longitude = point.lon;
	return mapPoint;
}
function stringToDate(str){
	var tempStrs = str.split(" ");
	var dateStrs = tempStrs[0].split("-");
	var year = parseInt(dateStrs[0], 10);
	var month = parseInt(dateStrs[1], 10) - 1;
	var day = parseInt(dateStrs[2], 10);
	var timeStrs = tempStrs[1].split(":");
	var hour = parseInt(timeStrs [0], 10);
	var minute = parseInt(timeStrs[1], 10);
	var date = new Date(year, month, day, hour, minute, 00);
	return date;
}