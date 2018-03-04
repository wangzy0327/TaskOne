package com.task.service;

import com.task.entity.Url;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UrlService {
    int insertSuccessUrl(String url);
    int H5ParseTest(HttpServletRequest req, HttpServletResponse resp,String urlStr);
    Url queryUrl(String url);
}
