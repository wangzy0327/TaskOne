package com.task.util.test;

import com.task.content.Content;
import com.task.content.Paragraph;
import com.task.content.Segment;
import com.task.util.ContentUtil;

import java.util.ArrayList;
import java.util.List;

public class ContentUtilTest {
    public static void main(String[] args) {
        Content content = ContentUtil.getContent();
        System.out.println("获取html标题："+content.getTitle());
        List<Segment> pages = content.getPage();
        for(int i = 0;i < pages.size();i++){
            System.out.println("第 "+(i+1)+" 部分内容:");
            List<String> cmp = pages.get(i).getImages();
            for(int j = 0;j < cmp.size();j++){
                System.out.println("图片Url:"+cmp.get(j));
            }
            List<Paragraph> paras = pages.get(i).getWritings();
            for(int j = 0;j < paras.size();j++){
                System.out.println("段落"+(j+1)+":");
                List<String> scripts = paras.get(j).getScripts();
                for(int k = 0;k < scripts.size();k++){
                    System.out.println("\t"+scripts.get(k));
                }
            }
        }
    }
}
