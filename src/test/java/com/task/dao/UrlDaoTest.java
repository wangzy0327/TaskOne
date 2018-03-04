package com.task.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * 配置Spring和Junit整合,Junit启动时加载SpringIOC容器
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring的配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class UrlDaoTest {

    @Resource
    private UrlDao urlDao;

    @Test
    public void insertSuccessUrl() throws Exception {
        String url = "www.baidu.com";
        System.out.println(urlDao.insertSuccessUrl(url));
    }

}