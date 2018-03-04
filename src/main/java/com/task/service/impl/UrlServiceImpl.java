package com.task.service.impl;

import com.task.dao.UrlDao;
import com.task.entity.Url;
import com.task.enums.UrlEnum;
import com.task.service.UrlService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class UrlServiceImpl implements UrlService{

    @Resource
    private UrlDao urlDao;

    public int insertSuccessUrl(String url) {
        if(url.indexOf("http://")>=0)
            url = url.substring("http://".length(),url.length());
        if(url.indexOf("https://")>=0)
            url = url.substring("https://".length(),url.length());
        return urlDao.insertSuccessUrl(url);
    }

    public int H5ParseTest(HttpServletRequest req, HttpServletResponse resp,String urlStr) {
        String h5_url_str = urlStr;
        StringBuffer h5_url = new StringBuffer();
        if (h5_url_str.indexOf("http://") < 0 && h5_url_str.indexOf("https://") < 0)
            h5_url = new StringBuffer("http://").append(h5_url_str);
        else{
            h5_url.append(h5_url_str);
        }
        h5_url.append("?mobile=1");
        try {
            URL url = new URL(h5_url.toString());
            InputStream input;
            input = url.openStream();   // 打开输入流
            String text = IOUtils.toString(input, "UTF-8");
            input.close();

            StringBuffer sb = new StringBuffer(text);

            int index = text.indexOf("</body>");
            // 指定位置插入js
            sb.insert(index, "<script src=\"../../js/h5-page-listen.js\"></script>");

//            String path = ""; // 项目地址
            String path = req.getSession().getServletContext().getRealPath("/");
            StringBuffer pagePath = new StringBuffer("");
            pagePath.append("dist/h5_page");
//                pagePath.append(req.getParameter("t"));
            pagePath.append(h5_url_str.substring(h5_url_str.lastIndexOf("/"), h5_url_str.length()));
            pagePath.append(".html");
            System.out.println(pagePath);

            System.out.println(path+pagePath);
            File dist = new File(path+pagePath);
            FileUtils.writeStringToFile(dist, sb.toString(), "UTF-8");

            System.out.println("文件保存成功！");

            return UrlEnum.SUCCESS.getState();
//                ResponseService.ResponseJson(resp, 200, "文件保存成功", "/" + pagePath.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return UrlEnum.FAIL.getState();
    }

    public Url queryUrl(String url) {
        if(url.indexOf("http://")>=0)
            url = url.substring("http://".length(),url.length());
        if(url.indexOf("https://")>=0)
            url = url.substring("https://".length(),url.length());
        return urlDao.queryUrl(url);
    }
}
