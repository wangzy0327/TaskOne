package com.task.controller;

import com.task.entity.NewsMessage;
import com.task.entity.Url;
import com.task.enums.UrlEnum;
import com.task.service.UrlService;
import com.task.vo.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/urlSubmit")//url:模块/资源/{}/细分
public class UrlController {
    @Autowired
    private UrlService urlService;

    @RequestMapping(
            value = "/result",
//            path={"/result"},
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public DataResult execute(HttpServletRequest req, HttpServletResponse resp, @RequestBody NewsMessage newsMessage)
    {
//        System.out.println(newsMessage.getUrl());
//        Url url = newsMessage.getUrl();
        String urlStr = newsMessage.getUrl();
        System.out.println(urlStr);
//        String[] tagStrs = newsMessage.getTags();
        String[] tags = newsMessage.getTags();
        for(int i = 0 ;i<tags.length;i++){
            System.out.println("tag"+(i+1)+":"+tags[i]);
        }
        //下面是单步调试
//        // 这个判断是如果为空采取执行，
        if (urlStr != null && urlService.queryUrl(urlStr) == null){
            urlService.insertSuccessUrl(urlStr);
            if(urlService.H5ParseTest(req,resp,urlStr) == UrlEnum.SUCCESS.getState())
                return new DataResult(true,new Url(urlStr));
        }
        if(urlStr != null && urlService.queryUrl(urlStr) != null){
            return new DataResult(true,UrlEnum.REPEAT_INSERT.getInfo());
        }
        return new DataResult(true,UrlEnum.FAIL.getState());
    }
}
