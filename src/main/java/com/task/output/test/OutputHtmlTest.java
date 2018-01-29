package com.task.output.test;

import com.task.output.OutputHtml;
import com.task.util.ContentUtil;

public class OutputHtmlTest {
    public static void main(String[] args) {
        String url = "http://v6.rabbitpre.com/m/neUzquH";
        OutputHtml outHtml = new OutputHtml();
        outHtml.way_1(ContentUtil.getContent(url));
    }
}
