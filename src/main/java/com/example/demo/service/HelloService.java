package com.example.demo.service;

/**
 * @description:
 * @author: lingjian
 * @createDate: 2020/12/22 21:47
 */
public interface HelloService {


    /**
     * 开启定时任务
     *
     * @param id  主键
     * @param day 天数
     * @return String
     */
    String start(Long id, Integer day);

    /**
     * 关闭定时任务
     *
     * @param id 主键
     * @return String
     */
    String shutdown(Long id);
}
