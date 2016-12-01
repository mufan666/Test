package com.mufan.movie.web;

import com.google.gson.Gson;
import com.mufan.movie.domain.Film;
import com.mufan.movie.domain.FilmType;
import com.mufan.movie.domain.Films;
import com.mufan.movie.service.FilmService;
import com.mufan.utils.JsonResult;
import com.mufan.utils.jsonStrategy.json.JsonConfigStrategy;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aswe on 2016/7/26.
 */
@Component
@ParentPackage("json-default")
@Result(type = "json", params = {"includeProperties", "jsonResult.*","excludeProperties","jsonResult.data\\[\\d+\\].filmTypes\\[\\d+\\].films.*","noCache","true","encoding", "UTF-8", "excludeNullProperties", "true"})
@Namespace("/film")

public class FilmAction {
    private String fileType;
    private Long filmId;
    private File file;
    private String fileFileName;
    private int chunk;
    private int chunks;
    private String md5val;
    private String result;
    private String jsonPara;
    private JsonResult jsonResult = new JsonResult();
    private Map<String,Object> mapResult;
    private Long size;

    @Resource
    private FilmService filmService;
    @Resource
    private MessageSource messageSource;

    /*@Action(value = "uploadFilmInfo", results = {@Result(type = "json", params = {"root", "jsonResult","excludeProperties","data.filmTypes.films","noCache","true"})})*/
    @Action(value = "uploadFilmInfo")
    public String uploadFilmInfo() {
        Gson gson = new Gson();
        Film f = gson.fromJson(jsonPara,Film.class);

       /* Film f = (Film) JSONObject.toBean(JSONObject.fromObject(jsonPara), Film.class);*/
        List<FilmType> types = f.getFilmTypes();
        for(FilmType type:types){
            System.out.println(type.getId());
        }
        Film film = filmService.saveFilm(f);
        jsonResult.getData().add(film);
        jsonResult.toSuccess();
        return "success";
    }

    @Action(value = "getFilm")
    public String getFilm() {
        Gson gson = new Gson();
        Film f = gson.fromJson(jsonPara,Film.class);
        Film film = filmService.getFilmById(f.getId());
        jsonResult.getData().add(film);
        jsonResult.toSuccess();
        return "success";
    }

    @Action(value = "getProperties")
    public String getProperties() {
        String value = filmService.getPropertiesValue("film.ftp.path");
        jsonResult.getData().add(value);
        jsonResult.toSuccess();
        return "success";
    }
    /*@Action(value = "getAllFilms", results = {@Result(type = "json", params = {"includeProperties", "jsonResult.*","excludeProperties","data\\[\\d+\\].filmTypes\\[\\d+\\].films","noCache","true"})})*/
    @Action(value = "getAllFilms")
    /*@Action(value = "getAllFilms", results = {@Result(type = "json", params = {"root", "result","noCache","true"})})*/
    public String getAllFilms(){
        List<Film> filmList = filmService.findAllFilms();
        jsonResult.setData(filmList);
        jsonResult.toSuccess();
//        result = jsonResult.toJsonAsStrategies(JsonConfigStrategy.getFilmStrategy());
        return "success";
    }

    @Action(value = "publishFilm")
    public String publishFilm() {
        Gson gson = new Gson();
        Film film = gson.fromJson(jsonPara,Film.class);
        filmService.publishFilm(film.getId());
        jsonResult.toSuccess();
        return "success";
    }

    @Action(value = "unPublishFilm")
    public String unPublishFilm(){
        Gson gson = new Gson();
        Film film = gson.fromJson(jsonPara,Film.class);
        filmService.unPublishFilm(film.getId());
        jsonResult.toSuccess();
        return "success";
    }

    @Action(value = "movieUpload",results = {@Result(type = "json", params = {"root", "result"})})
    public String ajaxAttachUpload() {
        System.out.println(chunks + "\t" + chunk + "\t" + md5val + "\t" + fileFileName);
        JSONObject jsonObject = new JSONObject();
        String path = messageSource.getMessage("film.ftp.path",null,"d:/test",null) + File.separator+ filmId + File.separator + fileFileName;
        try {
            File targetFile = new File(path);
            if(!targetFile.getParentFile().exists()) targetFile.getParentFile().mkdirs();
            filmService.writeFile(targetFile, file);
            //如果文件小与5M的话，分片参数chunk的值是null
            if (chunk == 0 && chunks == 0) {
//                outJson("0", "success", "");
                Film film = createFilm();
                System.out.println(film.getImagepath());
                boolean isTrue = filmService.validateMD5(new File(path), film,fileType);
                if (isTrue) {
                    jsonObject.put("resultCode", 0);
                    result = jsonObject.toString();
                } else {
                    throw new Exception();
                }
            } else {
                //chunk 分片索引，下标从0开始
                //chunks 总分片数
                if (chunk == chunks - 1) {
                    Film film = createFilm();
                    boolean isTrue = filmService.validateMD5(new File(path), film,fileType);
                    if (isTrue) {
                        jsonObject.put("resultCode", 0);
                        result = jsonObject.toString();
                    } else {
                        throw new Exception();
                    }
                } else {
//                    outJson("2", "上传中" + fileFileName + " chunk:" + chunk, "");
                    jsonObject.put("resultCode", 2);
                    result = jsonObject.toString();
                }
            }
        } catch (Exception e) {
//            outJson("3", "上传失败", "");
            jsonObject.put("resultCode", 3);
            result = jsonObject.toString();
        }
        return "success";
    }
    @Action(value = "downloadPublishConfig",
            results = {@Result(name = "download",type = "stream",
                    params = {"contentType","application/octet-stream",
                            "inputName","inputStream","contentDisposition","attachment;filename=\"config.xml\"",
                            "bufferSize","4096"})})
    public String downloadPublishConfig(){
        /*if(StringUtils.isNotBlank(attachid)){
            contentAttachs = contenAttachService.getContentAttach(attachid);
            setFileName(contentAttachs.getAttachName());
        }*/
        return "download";
    }
    public InputStream getInputStream() throws Exception {
        PrintWriter out = null;
        Films films = filmService.getPublish();
        StringWriter stringWriter = new StringWriter();
        try {
            JAXBContext context = JAXBContext.newInstance(Films.class);
            Marshaller us = context.createMarshaller();
            us.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            us.marshal(films,stringWriter);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        result = stringWriter.toString();
        return new ByteArrayInputStream(result.getBytes("UTF-8"));
    }

    @Action(value = "getPublishConfig", results = { @Result(name ="success",type = "plainText",location = "/text.jsp",params = {"charSet","UTF-8","noCache","true","enableGZIP","true"}) })
    public String getPublishConfig(){
//        result = JSONObject.fromObject(p).toString();
        ServletActionContext.getResponse().setContentType ("text/html;charset=utf-8");
        PrintWriter out = null;
        Films films = filmService.getPublish();
        StringWriter stringWriter = new StringWriter();
        try {
            JAXBContext context = JAXBContext.newInstance(Films.class);
            Marshaller us = context.createMarshaller();
            us.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            us.marshal(films,stringWriter);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        result = stringWriter.toString();
        try {
            out = ServletActionContext.getResponse().getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.print(result);
        out.flush();
        out.close();
        return "success";
    }
    private Film createFilm() {
        String sourcePath = filmId + File.separator + fileFileName;
        String suffix = fileFileName.substring(fileFileName.lastIndexOf(".") + 1);
        System.out.println(suffix+"\t"+filmId+"\t"+sourcePath);
        Film film = new Film();
        film.setId(filmId);
        if ("0".equals(fileType)) {
            film.setFilmcode(md5val);
            film.setFilmpath(sourcePath);
            film.setFilmSuffix(suffix);
            film.setFilmsize(size);
            film.setFilmFileName(fileFileName);
        }else if("1".equals(fileType)){
            film.setImagecode(md5val);
            film.setImagepath(filmId + File.separator + fileFileName);
            film.setImageSuffix(suffix);
            film.setImagesize(size);
            film.setImageFileName(fileFileName);
        }
        return film;
    }

    private boolean validateMD5(File file) throws InvocationTargetException, NoSuchMethodException, NoSuchAlgorithmException, IllegalAccessException, IOException {
        String md5 = filmService.calculateMd5(file);
        System.out.println("上传后的md5:" + md5);
        if (!md5.equals(md5val)) {
            file.delete();
            return false;
        } else {
            return true;
        }
    }


    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public int getChunk() {
        return chunk;
    }

    public void setChunk(int chunk) {
        this.chunk = chunk;
    }

    public int getChunks() {
        return chunks;
    }

    public void setChunks(int chunks) {
        this.chunks = chunks;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMd5val() {
        return md5val;
    }

    public void setMd5val(String md5val) {
        this.md5val = md5val;
    }

    public String getJsonPara() {
        return jsonPara;
    }

    public void setJsonPara(String jsonPara) {
        this.jsonPara = jsonPara;
    }

    public JsonResult getJsonResult() {
        return jsonResult;
    }

    public void setJsonResult(JsonResult jsonResult) {
        this.jsonResult = jsonResult;
    }

    public Long getFilmId() {
        return filmId;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Map<String, Object> getMapResult() {
        return mapResult;
    }

    public void setMapResult(Map<String, Object> mapResult) {
        this.mapResult = mapResult;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
