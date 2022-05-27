package com.must.project.monitor.domain;

import com.must.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 在线用户数量统计对象 statis_online
 * 
 * @author zhancai
 * @date 2022-05-13
 */
public class StatisOnline extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 在线人数 */
    private String onlineNum;

    /** 统计日期yyyyMMdd */
    private String statisDate;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOnlineNum(String onlineNum) 
    {
        this.onlineNum = onlineNum;
    }

    public String getOnlineNum() 
    {
        return onlineNum;
    }
    public void setStatisDate(String statisDate) 
    {
        this.statisDate = statisDate;
    }

    public String getStatisDate() 
    {
        return statisDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("onlineNum", getOnlineNum())
            .append("statisDate", getStatisDate())
            .append("createTime", getCreateTime())
            .toString();
    }
}
