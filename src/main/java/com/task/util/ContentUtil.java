package com.task.util;

import com.task.content.Almanac;
import com.task.content.Content;
import com.task.content.Paragraph;
import com.task.content.Segment;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContentUtil {

    private ContentUtil() {
    }

    public static Map<String, String> headers = null;

    static {
        headers = new HashMap<String, String>();
        headers.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.110 Safari/537.36");
        headers.put("Accept-Language", "zh-CN,zh;q=0.8");
        headers.put("Accept-Encoding", "gzip, deflate, sdch, br");
        headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        headers.put("Connection", "Keep-Alive");
        headers.put("Content-Type", "application/json;charset=UTF-8");
    }


    public static Content getContent() {
        String url = "http://v6.rabbitpre.com/m/neUzquH";
        int waitTime = 10000;
        String jsonStr = JsoupHelper.get(url, null, "utf-8", headers);
//        System.out.println("返回的json字符串：" + jsonStr);
        Document document = HttpUnitHelper.getHtmlPage(url,waitTime);
        String iframe = document.getElementById("app-iframe").attr("src");
        System.out.println(iframe);
        Document innerDocument = HttpUnitHelper.getHtmlPage(iframe,waitTime);
//        writeHtmlContent("product/display2.html",innerDocument.toString());
        System.out.println(innerDocument.toString());
        Content content = analyzeHTMLByString(jsonStr);
//        System.out.println(document.toString());
//        String html = pickData(url);
//        System.out.println(html);
//        Content content = analyzeHTMLByString(jsonStr);
        return content;
    }
//    public static boolean writeHtmlContent(String filepath,String content){
//        Boolean bool = false;
//        byte[] sourceByte = content.getBytes();
//        if(null != sourceByte) {
//            try {
//                File file = new File(filepath);     //文件路径（路径+文件名）
//                if (!file.exists()) {   //文件不存在则创建文件，先创建目录
//                    File dir = new File(file.getParent());
//                    dir.mkdirs();
//                    file.createNewFile();
//                }
//                FileOutputStream outStream = new FileOutputStream(file);    //文件输出流用于将数据写入文件
//                outStream.write(sourceByte);
//                outStream.close();  //关闭文件输出流
//                bool = true;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return bool;
//    }

    /*
     * 爬取网页信息
     */
    private static String pickData(String url) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet(url);
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                // 打印响应状态
                if (entity != null) {
                    return EntityUtils.toString(entity);
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /*
     * 使用jsoup解析网页信息
     */
    private static Content analyzeHTMLByString(String html) {
        List<Segment> segments = new ArrayList<Segment>();
        Document document = Jsoup.parse(html);
        System.out.println(document.toString());

        Element phone = document.getElementsByClass("main-container").get(0);
        String title = phone.getElementsByClass("title ellipse").get(0).html();
        System.out.println(title);
        Element ul = phone.getElementsByClass("mobile-content").get(0);
        Elements lis = ul.getElementsByTag("li");

        for(Element li:lis){
            Elements cmpImages = li.getElementsByTag("img");
            List<String> cmp = new ArrayList<String>();
            for(Element cmpImage:cmpImages){
                cmp.add(cmpImage.attr("src"));
            }
            Elements writings = li.select("div");
            List<Paragraph> paras = new ArrayList<Paragraph>();
            for(Element writing:writings){
                List<String> scps = new ArrayList<String>();
                Elements scripts = writing.select("div");
                scripts.remove(0);
                for(Element script:scripts){
                    scps.add(script.html().toString());
                }
//                for(int i = 0;i < scripts.size();i++){
//                    scps.add(scripts.html().toString());
//                }
                Paragraph para;
                if(scps.size()>0){
                    para = new Paragraph(scps);
                    paras.add(para);
                }
            }
            Segment segment = new Segment(paras,cmp);
            segments.add(segment);
        }
        Content content = new Content(title,segments);
        return content;
    }

}
