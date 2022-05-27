package com.must.framework.scheduler;

import com.must.common.constant.Constants;
import com.must.common.utils.DateUtils;
import com.must.framework.redis.RedisCache;
import com.must.project.monitor.domain.StatisOnline;
import com.must.project.monitor.service.IStatisOnlineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;

/**
 * 在线用户数量统计任务
 * todo 分布式任务调度组件
 *
 * @author zhancai
 * @date 2022-05-13
 */
@Component
public class StatisOnlineScheduler {
    private static final Logger log = LoggerFactory.getLogger(StatisOnlineScheduler.class);

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private IStatisOnlineService statisOnlineService;

    @Scheduled(cron = "0 0/5 * * * *")
    public void statisOnline() {
        log.info("statisOnline...");
        Collection<String> keys = redisCache.keys(Constants.LOGIN_TOKEN_KEY + "*");

        StatisOnline statisOnline = new StatisOnline();
        statisOnline.setOnlineNum(String.valueOf(keys.size()));
        statisOnline.setStatisDate(DateTimeFormatter.ofPattern(DateUtils.YYYYMMDD).format(LocalDateTime.now()));
        statisOnline.setCreateTime(new Date());
        statisOnlineService.insertStatisOnline(statisOnline);
    }
}
