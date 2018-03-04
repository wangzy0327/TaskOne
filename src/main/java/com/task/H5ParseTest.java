package com.task;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class H5ParseTest {
    public void service(){
        String h5_url_str = "v6.rabbitpre.com/m/neUzquH";
        StringBuffer h5_url = new StringBuffer("http://").append(h5_url_str);

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

//                String path = req.getSession().getServletContext().getRealPath("/"); // 项目地址
            String path = ""; // 项目地址
            StringBuffer pagePath = new StringBuffer("");
            pagePath.append("src/main/webapp/dist/h5_page/");
//                pagePath.append(req.getParameter("t"));
            pagePath.append(h5_url_str.substring(h5_url_str.lastIndexOf("/"), h5_url_str.length()));
            pagePath.append(".html");

            System.out.println(path+pagePath);
            File dist = new File(path + pagePath);
            FileUtils.writeStringToFile(dist, sb.toString(), "UTF-8");

            System.out.println("文件保存成功！");

//                ResponseService.ResponseJson(resp, 200, "文件保存成功", "/" + pagePath.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
            H5ParseTest h5Test = new H5ParseTest();
            h5Test.service();
        }
//        String url1 = "http://v6.rabbitpre.com/m/neUzquH";
//        String url2 = "http://www.rabbitpre.com/m/6Jn6fa8";
//        OutputHtml outHtml = new OutputHtml();
//        outHtml.way_1(ContentUtil.getContent(url1));
//        outHtml.way_1(ContentUtil.getContent(url2));

}
