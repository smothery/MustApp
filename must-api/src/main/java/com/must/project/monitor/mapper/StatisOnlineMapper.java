package com.must.project.monitor.mapper;

import com.must.project.monitor.domain.StatisOnline;

import java.util.List;

/**
 * 在线用户数量统计Mapper接口
 * 
 * @author zhancai
 * @date 2022-05-13
 */
public interface StatisOnlineMapper 
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
     * @param statisDate yyyyMMdd
     * @return 统计列表
     */
    List<StatisOnline> selectStatisOnlineList(String statisDate);
}
