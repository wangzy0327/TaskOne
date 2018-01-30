package com.task.output.test;

import com.task.output.OutputHtml;
import com.task.util.ContentUtil;

public class OutputHtmlTest {
    public static void main(String[] args) {
        String url1 = "http://v6.rabbitpre.com/m/neUzquH";
        String url2 = "http://www.rabbitpre.com/m/6Jn6fa8";
        OutputHtml outHtml = new OutputHtml();
        outHtml.way_1(ContentUtil.getContent(url1));
        outHtml.way_1(ContentUtil.getContent(url2));
    }
}
