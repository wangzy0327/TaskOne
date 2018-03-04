package com.task.dao;


import com.task.entity.Url;

public interface UrlDao {
    int insertSuccessUrl(String url);
    Url queryUrl(String url);
}
