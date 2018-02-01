package com.task.util;
import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HttpUnitHelper {
    /** HtmlUnit请求web页面 */
    public static Document getHtmlPage(String url, int waitTime) {
        //设置浏览器版本
        WebClient wc = new WebClient(BrowserVersion.CHROME);
        //是否使用不安全的SSL
        wc.getOptions().setUseInsecureSSL(true);
        //启用JS解释器，默认为true
        wc.getOptions().setJavaScriptEnabled(true);
        //禁用CSS
        wc.getOptions().setCssEnabled(false);
        //js运行错误时，是否抛出异常
        wc.getOptions().setThrowExceptionOnScriptError(false);
        //状态码错误时，是否抛出异常
        wc.getOptions().setThrowExceptionOnFailingStatusCode(false);
        //是否允许使用ActiveX
        wc.getOptions().setActiveXNative(false);
        //等待js时间
        wc.waitForBackgroundJavaScript(600*1000);
        //设置Ajax异步处理控制器即启用Ajax支持
        wc.setAjaxController(new NicelyResynchronizingAjaxController());
        //设置超时时间
        wc.getOptions().setTimeout(waitTime);
        //不跟踪抓取
        wc.getOptions().setDoNotTrackEnabled(false);
        try {
            //模拟浏览器打开一个目标网址
            HtmlPage htmlPage = wc.getPage(url);
            //为了获取js执行的数据 线程开始沉睡等待
            Thread.sleep(1500);//这个线程的等待 因为js加载需要时间的
            //以xml形式获取响应文本
            String xml = htmlPage.asXml();
            //并转为Document对象return
            return Jsoup.parse(xml);
//            return Jsoup.connect(url).get();
//            return Jsoup.connect(url)
//                    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31")
//                    .get();
//            return Jsoup.connect(url).data("query", "Java")
//                    .userAgent("Mozilla")
//                    .cookie("auth", "token")
//                    .timeout(3000)
//                    .post();
            //System.out.println(xml.contains("结果.xls"));//false
        } catch (FailingHttpStatusCodeException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
