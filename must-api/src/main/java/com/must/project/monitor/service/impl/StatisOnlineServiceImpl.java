package com.must.project.monitor.service.impl;

import com.must.common.utils.DateUtils;
import com.must.project.monitor.domain.StatisOnline;
import com.must.project.monitor.mapper.StatisOnlineMapper;
import com.must.project.monitor.service.IStatisOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 在线用户数量统计Service业务层处理
 * 
 * @author zhancai
 * @date 2022-05-13
 */
@Service
public class StatisOnlineServiceImpl implements IStatisOnlineService 
{
    @Autowired
    private StatisOnlineMapper statisOnlineMapper;

    /**
     * 新增在线用户数量统计
     * 
     * @param statisOnline 在线用户数量统计
     * @return 结果
     */
    @Override
    public int insertStatisOnline(StatisOnline statisOnline)
    {
        statisOnline.setCreateTime(DateUtils.getNowDate());
        return statisOnlineMapper.insertStatisOnline(statisOnline);
    }

    /**
     * 查询日期在线用户统计
     * @param date yyyyMMdd
     * @return 统计列表
     */
    @Override
    public List<StatisOnline> selectStatisOnlineList(String date) {
        return statisOnlineMapper.selectStatisOnlineList(date);
    }
}
