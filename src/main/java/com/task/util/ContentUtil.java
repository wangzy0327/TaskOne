package com.task.util;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.HashMap;
import java.util.Map;

public class ContentUtil {

    private ContentUtil() {
    }

    public static Map<String, String> headers = null;

    static {
        headers = new HashMap<String, String>();
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.110 Safari/537.36");
        headers.put("Accept-Language", "zh-CN,zh;q=0.8");
        headers.put("Accept-Encoding", "gzip, deflate, sdch, br");
        headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        headers.put("Connection", "Keep-Alive");
        headers.put("Content-Type", "application/json;charset=UTF-8");
    }


    public static Map<String, String> getContent(String url) {
//        String url = "http://v6.rabbitpre.com/m/neUzquH";
        int waitTime = 10000;
        String jsonStr = JsoupHelper.get(url, null, "utf-8", headers);
//        System.out.println("返回的json字符串：" + jsonStr);
        Document document = HttpUnitHelper.getHtmlPage(url, waitTime);
        Elements elementTitle = document.getElementsByClass("title ellipse");
        String title = elementTitle.get(0).html();
        System.out.println(title);
        String iframe = document.getElementById("app-iframe").attr("src");
        System.out.println(iframe);
        Map<String, String> map = new HashMap<String, String>();
        map.put(title, iframe);
//        Document innerDocument = HttpUnitHelper.getHtmlPage(iframe, waitTime);
//        System.out.println(innerDocument.toString());
        return map;
    }
}
