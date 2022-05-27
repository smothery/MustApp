package com.must.project.monitor.service;

import com.must.project.monitor.domain.StatisOnline;

import java.util.List;

/**
 * 在线用户数量统计Service接口
 * 
 * @author zhancai
 * @date 2022-05-13
 */
public interface IStatisOnlineService 
{
    /**
     * 新增在线用户数量统计
     * 
     * @param statisOnline 在线用户数量统计
     * @return 结果
     */
    public int insertStatisOnline(StatisOnline statisOnline);

    /**
     * 查询日期在线用户统计
     * @param date yyyyMMdd
     * @return 统计列表
     */
    List<StatisOnline> selectStatisOnlineList(String date);
}
