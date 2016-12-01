/**
 * Created by aswe on 2016/7/26.
 */

/*********************************WebUpload 多文件上传 begin*****************************************/
function create_upload() {
    var $list = $("#thelist1");
    var state = 'pending';
    var $btn = $('#multiUpload');
    var md5val = "";
    var uploader;// 实例化
    var film = {};


    WebUploader.Uploader.register({
        "before-send-file": "beforeSendFile"
    }, {
        beforeSendFile: function (file) {
            //秒传验证
            var task = new $.Deferred();
            var start = new Date().getTime();
            var movieReg = /\.(wmv|asf|asx|rm|rmvb|mpg|mpeg|mpe|3gp|mp4|m4v|avi|dat|mkv|flv|vob)$/gi;
            var imageReg = /\.(bmp|jpg|jpeg|png|gif)$/gi;

            if (movieReg.test(file.name))
                film.fileType = '0';
            else if (imageReg.test(file.name))
                film.fileType = '1';
            (new WebUploader.Uploader()).md5File(file).progress(function (percentage) {
                /* console.log(percentage);*/

                //md5
                var $li = $('#' + file.id),
                    $percent = $li.find('.progress .progress-bar');

                // 避免重复创建
                if (!$percent.length) {
                    $percent = $('<div class="progress progress-striped active">' +
                        '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                        '</div>' +
                        '</div>').appendTo($li).find('.progress-bar');
                }

                $li.find('p.state').text('MD5计算中');
                $percent.css('width', percentage * 100 + '%');
            }).then(function (val) {
                // console.log("总耗时: " + ((new Date().getTime()) - start) / 1000);


                uploader.options.formData = {'md5val': val, 'filmId': film.filmId, 'fileType': film.fileType};
                // console.log(val);
                task.resolve();
            });
            return $.when(task);
        }
    })
    ;


    uploader = WebUploader.create({
        auto: false, //是否自动上传
        pick: {id: '#multi', multiple: 'true'},
        swf: 'js/Uploader.swf',
        server: "film/movieUpload.action",
        duplicate: false, //同一文件是否可重复选择
        resize: false,
        compress: null,//图片不压缩
        chunked: true,  //分片
        chunkSize: 5 * 1024 * 1024,   //每片5M
        chunkRetry: false,//如果失败，则不重试
        threads: 1,//上传并发数。允许同时最大上传进程数。
        //fileNumLimit: 2,//验证文件总数量, 超出则不允许加入队列
        // runtimeOrder: 'flash',
        // 禁掉全局的拖拽功能。这样不会出现图片拖进页面的时候，把图片打开。
        disableGlobalDnd: true,
    });

// 当有文件添加进来的时候
    uploader.on("fileQueued", function (file) {
        // console.log("fileQueued:");
        $list.append("<div id='" + file.id + "' class='item'>" +
            "<h4 class='info'>" + file.name + "</h4>" +
            "<p class='state'>等待上传...</p>" +
            "</div>");
    });

// 当开始上传流程时触发
    uploader.on("startUpload", function () {
        // console.log("startUpload");
        //添加额外的表单参数
        $.extend(true, uploader.options.formData, {});
    });


//当某个文件上传到服务端响应后，会派送此事件来询问服务端响应是否有效。
    uploader.on("uploadAccept", function (object, ret) {
        //服务器响应了
        //ret._raw  类似于 data
        console.log("uploadAccept");
        var data = JSON.parse(ret);
        /*var data = eval('('+ret+')');*/
        if (data.resultCode != "1" && data.resultCode != "3") {
        } else {
            uploader.reset();
            return false;
            /* $("#" + file.id).find("p.state").text("上传失败").css("color","red");*/
        }
    });

    uploader.on("uploadSuccess", function (file) {
        $("#" + file.id).find("p.state").text("上传成功");
    });

    uploader.on('uploadComplete', function (file) {
        $('#' + file.id).find('.progress').fadeOut();
    });

    uploader.on("uploadError", function (file, reason) {
        $("#" + file.id).find("p.state").text("上传出错");
        /*console.log("uploadError");
        console.log(file);
        console.log(reason);*/
        //多个文件
        var fileArray = uploader.getFiles();
        for (var i = 0; i < fileArray.length; i++) {
            uploader.cancelFile(fileArray[i]);
            uploader.removeFile(fileArray[i], true);
        }
        uploader.reset();
    });

//当validate不通过时，会以派送错误事件的形式通知调用者
    uploader.on("error", function (handler) {
        /*console.log(handler);*/
        uploader.reset();
        if ('F_DUPLICATE' == handler) {
            alert("文件重复");
        } else if ('Q_EXCEED_NUM_LIMIT' == handler) {
            alert("最多选择两个文件");
        }

        /*alert(handler);*/
    });

// 文件上传过程中创建进度条实时显示。
    uploader.on('uploadProgress', function (file, percentage) {
        var $li = $('#' + file.id),
            $percent = $li.find('.progress .progress-bar');

        // 避免重复创建
        if (!$percent.length) {
            $percent = $('<div class="progress progress-striped active">' +
                '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                '</div>' +
                '</div>').appendTo($li).find('.progress-bar');
        }

        $li.find('p.state').text('上传中');
        $percent.css('width', percentage * 100 + '%');
    });

    $btn.on("click", function () {
		var setFilmData = {};
		setFilmData.ename = $("#ename").val();
		setFilmData.descInfo = $("#descInfo").val();
		var arrChk = $("input[name='chk_list'][type='checkbox']");
		var chkNum = 0;
		setFilmData.filmTypes = [];
		$(arrChk).each(function(){
			if($(this).is(":checked") == true){
				setFilmData.filmTypes[chkNum] = {"id":$(this).attr("filmTypeId"),"typeName":$(this).val()};
				chkNum ++;
			}
		});
		setFilmData.country = $("#country").val();
		setFilmData.date = $("#date").val();
		var score = $.trim($("#score").val());
		if(score != "") {
			setFilmData.score = $("#score").val();
		}
		var click = $.trim($("#clickRatio").val());
		if(click != "") {
			setFilmData.click = click;
		}  	
        //判断是否为新建的电影
        var filmId = $("#filmId").val();
        var exist = typeof(filmId) != 'undefined' && filmId != '';
        /*alert(exist);*/
        if (exist) {
            film.filmId = filmId;
        }
        film.cname = $("#cname").val();
        film.ename = $("#ename").val();
        film.country = $("#country").val();
        if (!exist) {
        	alert("11");
			$.post("film/uploadFilmInfo.do",
				{jsonPara:JSON.stringify(setFilmData)}, function (msg) {
					if(msg.jsonResult.statusCode == 200){
		                $("#filmId").val(msg.jsonResult.data[0].id);
		                film.filmId = msg.jsonResult.data[0].id;
		                uploader.upload();						
						//$(location).attr('href', 'default.html?model=movie');							
					}else{
						alert("保存失败！");
					}
			});          	
        }else {
            uploader.upload();
        }
       /* if (state === 'uploading') {
            uploader.stop();
        } else {
            uploader.upload();
        }*/
    });
}
