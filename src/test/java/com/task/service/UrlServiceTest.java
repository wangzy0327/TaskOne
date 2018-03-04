package com.task.service;

import com.task.entity.Url;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
                        "classpath:spring/spring-service.xml"})
public class UrlServiceTest {

    @Resource
    private UrlService urlService;

    @Test
    public void insertSuccessUrl() throws Exception {
        String urlStr = "www.google.com";
        System.out.println(urlService.insertSuccessUrl(urlStr));
    }

    @Test
    public void queryUrl() throws Exception {
        String urlStr = "www.baidu.com";
        Url url = urlService.queryUrl(urlStr);
        System.out.println(url);
    }

}