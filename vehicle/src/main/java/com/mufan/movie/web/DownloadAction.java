package com.mufan.movie.web;

import java.io.*;
import java.net.URLEncoder;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by aswe on 2016/11/21.
 */
@Results({@Result(name = "download", type = "stream", params = {
        "contentType", "application/octet-stream",
        "inputName", "inputStream", "contentDisposition",
        "attachment;filename=\"${downloadFileName}\"", "bufferSize",
        "4096" })})
public class DownloadAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private String attachid;//附件id
    private String fileName;// 初始的通过param指定的文件名属性


    public String execute() throws Exception {
        if(StringUtils.isNotBlank(attachid)){
            setFileName("readme.xml");
        }
        return "download";
    }

    public InputStream getInputStream() throws Exception {
        // root项目上传图片路径，UPLOAD_ROOT_PATH定义为常量，从配置文件里取值
        // url就是附件在服务器上对应的路径
//        String root = FilePathSptUtil.UPLOAD_ROOT_PATH;
//        String url=root+contentAttachs.getAttachUrl();

//        return new FileInputStream(new File(url));
        String result = "aaaaa";
        return new ByteArrayInputStream(result.getBytes("UTF-8"));
    }

    public String getDownloadFileName() {
        return fileName;
    }

    public void setFileName(String fileName) throws UnsupportedEncodingException {
        String agent = ServletActionContext. getRequest().getHeader("User-agent");
        //如果浏览器是IE浏览器，就得进行编码转换
        if(agent.contains("MSIE")){
            this.fileName = URLEncoder.encode(fileName, "UTF-8");
        }else{
            this.fileName = new String(fileName.getBytes(),"ISO-8859-1");
        }
    }

}
