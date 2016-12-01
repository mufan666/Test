package com.mufan.movie.service;

import com.mufan.movie.dao.FilmDao;
import com.mufan.movie.dao.PublishFilmVersionDao;
import com.mufan.movie.domain.*;
import com.mufan.utils.JsonHandlerException;
import com.mufan.utils.SpringPropertiesUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.PropertiesPersister;
import sun.nio.ch.FileChannelImpl;

import javax.annotation.Resource;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Created by aswe on 2016/7/27.
 */
@Service("filmService")
@Transactional(rollbackFor = {Exception.class})
public class FilmServiceImpl implements FilmService {
    /*public String calculateMD5(String path) throws IOException {
        System.out.println(path);
        FileInputStream fis = new FileInputStream(path);
        String md5 = DigestUtils.md5Hex(IOUtils.toByteArray(fis));
        IOUtils.closeQuietly(fis);
        System.out.println("计算完成");
        return md5;
    }*/

    public String calculateMd5(File file) throws IOException, NoSuchAlgorithmException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        FileInputStream in = new FileInputStream(file);
        MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(byteBuffer);
        /*BigInteger bi = new BigInteger(1, md.digest());
        System.out.println(bi.toString());
        String value = bi.toString(16);*/
        byte encrypt[] = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte t : encrypt) {
            String s = Integer.toHexString(t & 0xFF);
            if (s.length() == 1) {
                s = "0" + s;
            }
            sb.append(s);
        }
        String value = sb.toString();
        in.close();
        Method m = FileChannelImpl.class.getDeclaredMethod("unmap", MappedByteBuffer.class);
        m.setAccessible(true);
        m.invoke(FileChannelImpl.class, byteBuffer);
        return value;
    }

    public void writeFile(File outPath, File tempFile) throws IOException {
        RandomAccessFile raFile = null;
        BufferedInputStream inputStream = null;
        try {
            //以读写的方式打开目标文件
            raFile = new RandomAccessFile(outPath, "rw");
            raFile.seek(raFile.length());
            inputStream = new BufferedInputStream(new FileInputStream(tempFile));
            byte[] buf = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buf)) != -1) {
                raFile.write(buf, 0, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException(e.getMessage());
        } finally {
            outPath.delete();
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (raFile != null) {
                    raFile.close();
                }
            } catch (Exception e) {
                throw new IOException(e.getMessage());
            }
        }
    }

    public boolean validateMD5(File file, Film film, String type) throws InvocationTargetException, NoSuchMethodException, NoSuchAlgorithmException, IllegalAccessException, IOException {
        String md5 = calculateMd5(file);
        if ("0".equals(type)) {
            if (!md5.equals(film.getFilmcode())) {
                file.delete();
                return false;
            } else {
                filmDao.updateFilmInfo(film.getFilmcode(),film.getFilmFileName(),film.getFilmSuffix(),film.getFilmpath(),film.getFilmsize(),film.getId());
                return true;
            }
        } else {
            if (!md5.equals(film.getImagecode())) {
                file.delete();
                return false;
            } else {
                filmDao.updateImageInfo(film.getImagecode(),film.getImageFileName(),film.getImageSuffix(),film.getImagepath(),film.getImagesize(),film.getId());
                return true;
            }
        }
    }

    public Film saveFilm(Film f) {
        Film film = filmDao.save(f);
        return film;
    }

    public Film getFilmById(Long id) {
        Film film = filmDao.findOne(id);
        return film;
    }

//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    @Transactional(rollbackFor = {Exception.class})
    public int publishFilm(Long id) {
        long time = System.currentTimeMillis();
        int i = filmDao.updateFilmPublish(id);
        if(publishFilmVersionDao.exists(1L)){
            publishFilmVersionDao.updateVersion(time);
        }else
            publishFilmVersionDao.insertVersion(time);
        return i;
    }

    public int unPublishFilm(Long id) {
        int i = filmDao.updateFilmUnPublish(id);
        if(publishFilmVersionDao.exists(1L)){
            publishFilmVersionDao.updateVersion(System.currentTimeMillis());
        }else
            publishFilmVersionDao.insertVersion(System.currentTimeMillis());
        return i;
    }

    public Films getPublish() {

        /*List<FilmInfo> filmInfos = new ArrayList<FilmInfo>();
        for(int i=1;i<10;i++){
            FilmInfo filmInfo = new FilmInfo();
            filmInfo.setId((long)i);
//            filmInfo.setCname("cname"+i);
            filmInfo.setEname("ename"+i);
            filmInfo.setDescInfo("descinfo"+i);
            filmInfo.setCountry("cn"+i);
            List<String> list = new ArrayList<String>();
            list.add("horror"+i);
            list.add("action"+i);
            filmInfo.setType(list);
            filmInfo.setDate("201"+i);
//            filmInfo.setScore(8.0);
            filmInfo.setClick(i);
            filmInfo.setImagepath("/"+i+"/image"+i+"/.jpg");
            filmInfo.setImagesize((long) 1111);
            filmInfo.setImagecode("imagemd5"+i);
            filmInfo.setFilmpath("/"+i+"/film"+i+"/.avi");
            filmInfo.setFilmcode("filmMd5"+i);
            filmInfo.setFilmsize(1231);
            filmInfos.add(filmInfo);
        }*/
        List<Film> filmList = filmDao.findByPublishIsTrue();
        List<FilmInfo> filmInfos = new ArrayList<FilmInfo>();
        for(Film f:filmList){
            filmInfos.add(new FilmInfo().genFilmInfo(f));
        }

        HostInfo hostInfo = new HostInfo();
        hostInfo.setIp(messageSource.getMessage("film.ftp.host",null,"192.168.199.151", null));
        hostInfo.setPort(messageSource.getMessage("film.ftp.port",null,"21", null));
        Films films = new Films();
        long version = publishFilmVersionDao.findOne(1L).getVersion();
        films.setDateversion(version);
        films.setFilmInfos(filmInfos);
        films.setHostInfo(hostInfo);
        return films;
    }

    public List<Film> findAllFilms() {
        List<Film> filmList = filmDao.findAll();
        return filmList;
    }

    public String getPropertiesValue(String value){
        /*return springPropertiesUtil.parseStr(value);*/
        String ss = messageSource.getMessage(value,null, null);
        System.out.println("filmserviceImpl:"+ss);
        return ss;
    }

    @Resource
    private FilmDao filmDao;
    @Resource
    private PublishFilmVersionDao publishFilmVersionDao;
    @Resource
    private SpringPropertiesUtil springPropertiesUtil;
    @Resource
    private MessageSource messageSource;
}
